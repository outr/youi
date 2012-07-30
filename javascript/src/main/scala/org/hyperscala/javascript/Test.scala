package org.hyperscala.javascript

import scala.collection.JavaConversions._
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.MethodNode

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object Test {
  def main(args: Array[String]): Unit = {
    val f = () => {
      val x = true
      x
    }
    //    f.getClass.getMethods.foreach(println)

    val clazz = f.getClass // classOf[Test]
    val classLoader = clazz.getClassLoader
    val declaringType = Type.getType(clazz)
    val url = declaringType.getInternalName + ".class"
    println(url)
    val classNode = new ClassNode()
    val input = classLoader.getResourceAsStream(url)
    try {
      val classReader = new ClassReader(input)
      classReader.accept(classNode, 0)
    } finally {
      input.close()
    }

    clazz.getMethods.foreach {
      case method => {
        //if (method.getName.startsWith("apply$")) => {
        val desc = Type.getMethodDescriptor(method)
        println("************** " + desc + " - " + method.getName + " - " + method)
        classNode.methods.asInstanceOf[java.util.List[MethodNode]].find(m => m.name == method.getName && m.desc == desc) match {
          case Some(methodNode) => {
            val converter = new BytecodeConverter(methodNode, true)
            methodNode.instructions.accept(converter)
            println("--------------")
            println(converter)
            println("--------------")
            //            methodNode.accept(converter)
            //            methodNode.instructions.accept(converter)
            /*methodNode.accept(new MethodVisitor(Opcodes.ASM4) {
              override def visitLocalVariable(name: String, desc: String, signature: String, start: Label, end: Label, index: Int) {
                println("visitLocalVariable: %s / %s / %s / %s / %s / %s".format(name, desc, signature, start, end, index))
              }

              override def visitAnnotationDefault() = {
                println("visitAnnotationDefault")
                super.visitAnnotationDefault()
              }

              override def visitAnnotation(desc: String, visible: Boolean) = {
                println("visitAnnotation: %s / %s".format(desc, visible))
                super.visitAnnotation(desc, visible)
              }

              override def visitParameterAnnotation(parameter: Int, desc: String, visible: Boolean) = {
                println("visitParameterAnnotation: %s / %s / %s".format(parameter, desc, visible))
                super.visitParameterAnnotation(parameter, desc, visible)
              }

              override def visitAttribute(attr: Attribute) {
                println("visitAttribute: %s".format(attr))
                super.visitAttribute(attr)
              }

              override def visitCode() {
                println("visitCode")
                super.visitCode()
              }

              override def visitFrame(`type`: Int, nLocal: Int, local: Array[AnyRef], nStack: Int, stack: Array[AnyRef]) {
                println("visitFrame: %s / %s / %s / %s / %s".format(`type`, nLocal, local, nStack, stack))
                super.visitFrame(`type`, nLocal, local, nStack, stack)
              }

              override def visitInsn(opcode: Int) {
                println("visitInsn: %s".format(opcode))
                super.visitInsn(opcode)
              }

              override def visitIntInsn(opcode: Int, operand: Int) {
                println("visitIntInsn: %s / %s".format(opcode, operand))
                super.visitIntInsn(opcode, operand)
              }

              override def visitVarInsn(opcode: Int, `var`: Int) {
                println("visitVarInsn: %s / %s".format(opcode, `var`))
                super.visitVarInsn(opcode, `var`)
              }

              override def visitTypeInsn(opcode: Int, `type`: String) {
                println("visitTypeInsn: %s / %s".format(opcode, `type`))
                super.visitTypeInsn(opcode, `type`)
              }

              override def visitFieldInsn(opcode: Int, owner: String, name: String, desc: String) {
                println("visitFieldInsn: %s / %s / %s / %s".format(opcode, owner, name, desc))
                super.visitFieldInsn(opcode, owner, name, desc)
              }

              override def visitMethodInsn(opcode: Int, owner: String, name: String, desc: String) {
                println("visitMethodInsn: %s / %s / %s / %s".format(opcode, owner, name, desc))
                super.visitMethodInsn(opcode, owner, name, desc)
              }

              override def visitInvokeDynamicInsn(name: String, desc: String, bsm: Handle, bsmArgs: AnyRef*) {
                println("visitInvokeDynamicInsn: %s / %s / %s / %s".format(name, desc, bsm, bsmArgs))
                super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs)
              }

              override def visitJumpInsn(opcode: Int, label: Label) {
                println("visitJumpInsn: %s / %s".format(opcode, label))
                super.visitJumpInsn(opcode, label)
              }

              override def visitLabel(label: Label) {
                println("visitLabel: %s".format(label))
                super.visitLabel(label)
              }

              override def visitLdcInsn(cst: Any) {
                println("visitLdcInsn: %s".format(cst))
                super.visitLdcInsn(cst)
              }

              override def visitIincInsn(`var`: Int, increment: Int) {
                println("visitIincInsns: %s / %s".format(`var`, increment))
                super.visitIincInsn(`var`, increment)
              }

              override def visitTableSwitchInsn(min: Int, max: Int, dflt: Label, labels: Label*) {
                println("visitTableSwitchInsn: %s / %s / %s / %s".format(min, max, dflt, labels))
                super.visitTableSwitchInsn(min, max, dflt, labels: _*)
              }

              override def visitLookupSwitchInsn(dflt: Label, keys: Array[Int], labels: Array[Label]) {
                println("visitLookupSwitchInsn: %s / %s / %s".format(dflt, keys, labels))
                super.visitLookupSwitchInsn(dflt, keys, labels)
              }

              override def visitMultiANewArrayInsn(desc: String, dims: Int) {
                println("visitMultiANewArrayInsn: %s / %s".format(desc, dims))
                super.visitMultiANewArrayInsn(desc, dims)
              }

              override def visitTryCatchBlock(start: Label, end: Label, handler: Label, `type`: String) {
                println("visitTryCatchBlock: %s / %s / %s / %s".format(start, end, handler, `type`))
                super.visitTryCatchBlock(start, end, handler, `type`)
              }

              override def visitLineNumber(line: Int, start: Label) {
                println("visitLineNumber: %s / %s".format(line, start))
                super.visitLineNumber(line, start)
              }

              override def visitMaxs(maxStack: Int, maxLocals: Int) {
                println("visitMaxs: %s / %s".format(maxStack, maxLocals))
                super.visitMaxs(maxStack, maxLocals)
              }

              override def visitEnd() {
                println("visitEnd")
                super.visitEnd()
              }
            })*/
          }
          case None =>
        }
        //        methodNode.localVariables.foreach {
        //          case n: LocalVariableNode => println(n.name + " / " + n.desc + " / " + n.signature + " / " + n.index)
        //        }
      }
      //      case _ =>
    }
    //    for (instruction <- methodNode.instructions.iterator()) instruction match {
    //      case n: LabelNode => {
    //        println("LabelNode: %s".format(n.getLabel.info))
    //      }
    //      case n: LineNumberNode => {
    //        println("LineNumber: %s / %s".format(n.line, n.start.getLabel))
    //      }
    //      case n: InsnNode => {
    //        println("InsnNode: %s".format(n.getType))
    //      }
    //      case n: VarInsnNode => {
    //        println("VarIsnsNode: %s".format(n.`var`))
    //      }
    //      case n: LdcInsnNode => {
    //        println(n)
    //      }
    //    }
  }

  val s = 5 -> 6
}

class Test {
  def test() = {
    val x = 5
    val y = "6"
  }
}