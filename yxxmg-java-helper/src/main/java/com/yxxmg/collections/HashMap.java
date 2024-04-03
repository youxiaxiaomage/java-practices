package com.yxxmg.collections;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 手写HashMap
 * @since : 2024/3/15
 */
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    private static final long serialVersionUID = -6557590146345925999L;
    /**
     * 初始容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 最大容量 2^31s
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 链表转红黑树的阈值
     */
    static final int TREEIFY_THRESHOLD = 8;
    /**
     * 红黑树转链表
     * <p>
     * 从时间复杂度来分析，红黑树的平均查找长度是log(n)， 如果长度为8，平均查找长度为log(8)=3，链表的平均查找长度为n/2， 当长度为8时，平均查找长度为8/2=4，这才有转换成树的必要；
     * 链表长度如果是小于等于6，6/2=3，而log(6)=2.6， 虽然速度也很快的，但是转化为树结构和生成树的时间并不会太短
     * </p>
     */
    static final int UNTREEFIY_THRESHOLD = 6;
    /**
     * 转红黑树的另一个条件 数组长度要达到64
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 数组
     */
    transient Node<K, V>[] table;
    /**
     * 存放key、value缓冲区
     */
    transient Set<Map.Entry<K, V>> entrySet;
    /**
     * HashMap中存放了多少个键值对
     */
    transient int size;
    /**
     * 对map的修改次数
     */
    transient int modCount;
    /**
     * 阈值
     */
    int threshold;
    /**
     * 加载因子
     */
    final float loadFactor;

    /**
     * 单向链表 O(n)
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash; // 当前hash值
        final K key; // 当前节点key
        V value; // 当前节点value
        Node<K, V> next; // 下一个节点

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final int hashCode() {
            // key value进行异或
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final boolean equals(Object o) { // 判断两个Node是否相等 key、value相等，则返回true
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Entry<?, ?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    // 方便理解 不简化
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 红黑树 O(logn)
     *
     * @param <K>
     * @param <V>
     */
    static final class TreeNode<K, V> extends Node<K, V> {
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;
        boolean red;

        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }

        /**
         * 返回当前节点根节点
         *
         * @return 根节点
         */
        final TreeNode<K, V> root() {
            for (TreeNode<K, V> r = this, p;;) {
                if ((p = r.parent) == null) {
                    // 若父节点为null即为root节点
                    return r;
                }
                r = p;
            }
        }

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                TreeNode<K, V> first = (TreeNode<K, V>)tab[index];
                if (root != first) {
                    Node<K, V> rn;
                    tab[index] = root;
                    TreeNode<K, V> rp = root.prev;
                    if ((rn = root.next) != null) {
                        ((TreeNode<K, V>)rn).prev = rp;
                    }
                    if (rp != null) {
                        rp.next = rn;
                    }
                    if (first != null) {
                        first.prev = root;
                    }
                    root.next = first;
                    root.prev = null;
                }
                // 检查
            }
        }

        final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            TreeNode<K, V> p = this;
            do {
                int ph, dir;
                K pk;
                TreeNode<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h) {
                    p = pl;
                } else if (ph < h) {
                    p = pr;
                } else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                } else if (pl == null) {
                    p = pr;
                } else if (pr == null) {
                    p = pl;
                } else if ((kc != null || (kc = comparableClassFor(k)) != null)
                    && (dir = compareComparables(kc, k, pk)) != 0) {
                    p = (dir < 0) ? pl : pr;
                } else if ((q = pr.find(h, k, kc)) != null) {
                    return q;
                }
            } while (p != null);
            return null;
        }

        private int compareComparables(Class<?> kc, Object k, K x) {
            return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
        }

        private Class<?> comparableClassFor(Object x) {
            if (x instanceof Comparable) {
                Class<?> c;
                Type[] ts, as;
                Type t;
                ParameterizedType p;
                if ((c = x.getClass()) == String.class) {
                    return c;
                }
                if ((ts = c.getGenericInterfaces()) != null) {
                    for (int i = 0; i < ts.length; ++i) {
                        if (((t = ts[i]) instanceof ParameterizedType)
                            && ((p = (ParameterizedType)t).getRawType() == Comparable.class)
                            && (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c) {
                            return c;
                        }
                    }
                }
            }
            return null;
        }

    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : n + 1);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es;
        return null;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * @param hash hash for key
     * @param key the key
     * @param value the value of put
     * @param onlyIfAbsent true不改变存在的value
     * @param evict false：链表是创建模式
     * @return 前值或者null
     */
    private V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }

        return null;
    }

    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        Node<K, V>[] newTab = (Node<K, V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {

        }
        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
