package com.yxxmg.asm.domain;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/18
 */
public class EmployeeClassVisitor extends ClassVisitor {
    private String clazzName;
    private String superName;

    public EmployeeClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.clazzName = name;
        this.superName = superName;
        super.visit(version, access, name + "$EnhanceByASM", signature, name, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("EmployeeClassVisitor.visitField");
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
        String[] exceptions) {
        System.out.println("EmployeeClassVisitor.visitMethod");
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        // 判断当前读取的方法
        MethodVisitor wrapper = methodVisitor;
        if ("working".equals(name)) {
            // 如果是working方法，则包装一个方法的Visitor
            wrapper = new EmployeeMethodVisitor(Opcodes.ASM5, methodVisitor);
        } else if ("<init>".equals(name)) {
            wrapper = new EmployeeMethodVisitor(Opcodes.ASM5, methodVisitor);
        }
        return wrapper;
    }
}
