package com.yxxmg.gof.create.pattern.prototype;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 原型模式 通过复制来创建新的实例，无需知道相应类的信息<br/>
 *              深拷贝 将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的 <br/>
 *              浅拷贝 将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，<br/>
 *              而浅复制不彻底。clone明显是深复制，clone出来的对象是是不能去影响原型对象的
 * @since : 2023/5/5
 */
public class Prototype implements Cloneable {
    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype)super.clone();
    }
}
