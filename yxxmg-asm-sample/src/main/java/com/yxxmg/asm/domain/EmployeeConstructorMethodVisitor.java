package com.yxxmg.asm.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/18
 */
public class EmployeeConstructorMethodVisitor extends MethodVisitor {
    // 定义一个全局变量记录父类名称
    private String superClassName;

    public EmployeeConstructorMethodVisitor(int i, MethodVisitor methodVisitor, String superClassName) {
        super(i, methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean b) {
        // 当开始初始化构造函数时，先访问父类构造函数,类似源码中的super()
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        super.visitMethodInsn(opcode, owner, name, desc, b);
    }
}
