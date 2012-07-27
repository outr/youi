package org.hyperscala.js2

import org.objectweb.asm._
import tree.{LocalVariableNode, MethodNode}

import org.objectweb.asm.Opcodes._
import collection.mutable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BytecodeConverter(methodNode: MethodNode, includeLineNumbers: Boolean, debug: Boolean = false) extends MethodVisitor(Opcodes.ASM4) {
  var depth = 0
  private var label: Label = null
  private var lineNumber = -1
  private val instructions = mutable.Queue[String]()

  private val builder = new StringBuilder

  def writeVariable() = writeLine("var %2$s = %1$s;".format(instructions.dequeue(), instructions.dequeue()))

  def writeReturn(includeVariable: Boolean = false) = {
    if (includeVariable) {
      writeLine("return %s;".format(instructions.dequeue()))
    } else {
      writeLine("return;")
    }
//    writeLine("return%s;".format(if (includeVariable && localVariable != null) " %s".format(localVariable.name) else ""))
  }

  def writeLine(s: String) = {
    (0 until depth).foreach {
      case index => builder.append('\t')
    }
    builder.append(s)
    if (includeLineNumbers) {
      builder.append(" // Line #%s".format(lineNumber))
    }
    builder.append("\r\n")
  }

  override def toString = builder.toString()

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

  override def visitInsn(opcode: Int) = {
    if (debug) println("visitInsn: %s".format(opcode))
    opcode match {
      case NOP => println("Unsupported opcode (visitInsn):NOP")
      case ACONST_NULL => println("Unsupported opcode (visitInsn):ACONST_NULL")
      case ICONST_M1 => instructions.enqueue("-1")
      case ICONST_0 | LCONST_0 => instructions.enqueue("0")
      case ICONST_1 | LCONST_1 => instructions.enqueue("1")
      case ICONST_2 => instructions.enqueue("2")
      case ICONST_3 => instructions.enqueue("3")
      case ICONST_4 => instructions.enqueue("4")
      case ICONST_5 => instructions.enqueue("5")
      case FCONST_0 | DCONST_0 => instructions.enqueue("0.0")
      case FCONST_1 | DCONST_1 => instructions.enqueue("1.0")
      case FCONST_2 => instructions.enqueue("2.0")
      case IALOAD => println("Unsupported opcode (visitInsn):IALOAD")
      case LALOAD => println("Unsupported opcode (visitInsn):LALOAD")
      case FALOAD => println("Unsupported opcode (visitInsn):FALOAD")
      case DALOAD => println("Unsupported opcode (visitInsn):DALOAD")
      case AALOAD => println("Unsupported opcode (visitInsn):AALOAD")
      case BALOAD => println("Unsupported opcode (visitInsn):BALOAD")
      case CALOAD => println("Unsupported opcode (visitInsn):CALOAD")
      case SALOAD => println("Unsupported opcode (visitInsn):SALOAD")
      case IASTORE => println("Unsupported opcode (visitInsn):IASTORE")
      case LASTORE => println("Unsupported opcode (visitInsn):LASTORE")
      case FASTORE => println("Unsupported opcode (visitInsn):FASTORE")
      case DASTORE => println("Unsupported opcode (visitInsn):DASTORE")
      case AASTORE => println("Unsupported opcode (visitInsn):AASTORE")
      case BASTORE => println("Unsupported opcode (visitInsn):BASTORE")
      case CASTORE => println("Unsupported opcode (visitInsn):CASTORE")
      case SASTORE => println("Unsupported opcode (visitInsn):SASTORE")
      case POP => println("Unsupported opcode (visitInsn):POP")
      case POP2 => println("Unsupported opcode (visitInsn):POP2")
      case DUP => println("Unsupported opcode (visitInsn):DUP")
      case DUP_X1 => println("Unsupported opcode (visitInsn):DUP_X1")
      case DUP_X2 => println("Unsupported opcode (visitInsn):DUP_X2")
      case DUP2 => println("Unsupported opcode (visitInsn):DUP2")
      case DUP2_X1 => println("Unsupported opcode (visitInsn):DUP2_X1")
      case DUP2_X2 => println("Unsupported opcode (visitInsn):DUP2_X2")
      case SWAP => println("Unsupported opcode (visitInsn):SWAP")
      case IADD => println("Unsupported opcode (visitInsn):IADD")
      case LADD => println("Unsupported opcode (visitInsn):LADD")
      case FADD => println("Unsupported opcode (visitInsn):FADD")
      case DADD => println("Unsupported opcode (visitInsn):DADD")
      case ISUB => println("Unsupported opcode (visitInsn):ISUB")
      case LSUB => println("Unsupported opcode (visitInsn):LSUB")
      case FSUB => println("Unsupported opcode (visitInsn):FSUB")
      case DSUB => println("Unsupported opcode (visitInsn):DSUB")
      case IMUL => println("Unsupported opcode (visitInsn):IMUL")
      case LMUL => println("Unsupported opcode (visitInsn):LMUL")
      case FMUL => println("Unsupported opcode (visitInsn):FMUL")
      case DMUL => println("Unsupported opcode (visitInsn):DMUL")
      case IDIV => println("Unsupported opcode (visitInsn):IDIV")
      case LDIV => println("Unsupported opcode (visitInsn):LDIV")
      case FDIV => println("Unsupported opcode (visitInsn):FDIV")
      case DDIV => println("Unsupported opcode (visitInsn):DDIV")
      case IREM => println("Unsupported opcode (visitInsn):IREM")
      case LREM => println("Unsupported opcode (visitInsn):LREM")
      case FREM => println("Unsupported opcode (visitInsn):FREM")
      case DREM => println("Unsupported opcode (visitInsn):DREM")
      case INEG => println("Unsupported opcode (visitInsn):INEG")
      case LNEG => println("Unsupported opcode (visitInsn):LNEG")
      case FNEG => println("Unsupported opcode (visitInsn):FNEG")
      case DNEG => println("Unsupported opcode (visitInsn):DNEG")
      case ISHL => println("Unsupported opcode (visitInsn):ISHL")
      case LSHL => println("Unsupported opcode (visitInsn):LSHL")
      case ISHR => println("Unsupported opcode (visitInsn):ISHR")
      case LSHR => println("Unsupported opcode (visitInsn):LSHR")
      case IUSHR => println("Unsupported opcode (visitInsn):IUSHR")
      case LUSHR => println("Unsupported opcode (visitInsn):LUSHR")
      case IAND => println("Unsupported opcode (visitInsn):IAND")
      case LAND => println("Unsupported opcode (visitInsn):LAND")
      case IOR => println("Unsupported opcode (visitInsn):IOR")
      case LOR => println("Unsupported opcode (visitInsn):LOR")
      case IXOR => println("Unsupported opcode (visitInsn):IXOR")
      case LXOR => println("Unsupported opcode (visitInsn):LXOR")
      case I2L => println("Unsupported opcode (visitInsn):I2L")
      case I2F => println("Unsupported opcode (visitInsn):I2F")
      case I2D => println("Unsupported opcode (visitInsn):I2D")
      case L2I => println("Unsupported opcode (visitInsn):L2I")
      case L2F => println("Unsupported opcode (visitInsn):L2F")
      case L2D => println("Unsupported opcode (visitInsn):L2D")
      case F2I => println("Unsupported opcode (visitInsn):F2I")
      case F2L => println("Unsupported opcode (visitInsn):F2L")
      case F2D => println("Unsupported opcode (visitInsn):F2D")
      case D2I => println("Unsupported opcode (visitInsn):D2I")
      case D2L => println("Unsupported opcode (visitInsn):D2L")
      case D2F => println("Unsupported opcode (visitInsn):D2F")
      case I2B => println("Unsupported opcode (visitInsn):I2B")
      case I2C => println("Unsupported opcode (visitInsn):I2C")
      case I2S => println("Unsupported opcode (visitInsn):I2S")
      case LCMP => println("Unsupported opcode (visitInsn):LCMP")
      case FCMPL => println("Unsupported opcode (visitInsn):FCMPL")
      case FCMPG => println("Unsupported opcode (visitInsn):FCMPG")
      case DCMPL => println("Unsupported opcode (visitInsn):DCMPL")
      case DCMPG => println("Unsupported opcode (visitInsn):DCMPG")
      case IRETURN | LRETURN | FRETURN | DRETURN | ARETURN => {
        writeReturn(includeVariable = true)
//        b.append("return %s; // Line #%s".format(localVariable.name, lineNumber))
//        println("Unsupported opcode (visitInsn):IRETURN")
      }
      case RETURN => {
        writeReturn(includeVariable = false)
//        b.append("return; // Line #%s".format(lineNumber))
//        b.append("\r\n")
//        println("Unsupported opcode (visitInsn):RETURN")
      }
      case ARRAYLENGTH => println("Unsupported opcode (visitInsn):ARRAYLENGTH")
      case ATHROW => println("Unsupported opcode (visitInsn):ATHROW")
      case MONITORENTER => println("Unsupported opcode (visitInsn):MONITORENTER")
      case MONITOREXIT => println("Unsupported opcode (visitInsn):MONITOREXIT")
      //    super.visitInsn(opcode)
    }
  }

  override def visitIntInsn(opcode: Int, operand: Int) {
    if (debug) println("visitIntInsn: %s / %s".format(opcode, operand))
    opcode match {
      case BIPUSH | SIPUSH => instructions.enqueue(operand.toString)
      case NEWARRAY => println("visitIntInsn: NEWARRAY " + operand)
    }
  }

  override def visitVarInsn(opcode: Int, varIndex: Int) {
    if (debug) println("visitVarInsn: %s / %s".format(opcode, varIndex))
    opcode match {
      case ILOAD | LLOAD | FLOAD | DLOAD | ALOAD => {
        if (varIndex >= methodNode.localVariables.size()) {
          throw new RuntimeException("No variable found: %s / %s / %s".format(opcode, varIndex, methodNode.localVariables.size()))
        } else {
          val localVariable = methodNode.localVariables.get(varIndex).asInstanceOf[LocalVariableNode]
          instructions.enqueue(localVariable.name)
        }
      }
      case ISTORE | LSTORE | FSTORE | DSTORE | ASTORE => {
        val localVariable = methodNode.localVariables.get(varIndex).asInstanceOf[LocalVariableNode]
        instructions.enqueue(localVariable.name)
        writeVariable()
      }
      case RET => println("Unsupported opcode (visitVarInsn):RET")
    }
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
    opcode match {
      case INVOKEVIRTUAL => {
        println("visitMethodInsn: INVOKEVIRTUAL / %s / %s / %s".format(owner, name, desc))
        val clazz = Class.forName(owner.replaceAll("/", "."))
        val method = clazz.getMethods.find(m => m.getName == name).get
        val methodNode = FunctionConverter.lookupMethodNode(method).get
        println("CONVERTED: %s".format(FunctionConverter.convert(methodNode, true, true)))
        println("clazz: %s".format(clazz))
      }
      case INVOKESPECIAL => println("visitMethodInsn: INVOKESPECIAL / %s / %s / %s".format(owner, name, desc))
      case INVOKESTATIC => println("visitMethodInsn: INVOKESTATIC / %s / %s / %s".format(owner, name, desc))
      case INVOKEINTERFACE => println("visitMethodInsn: INVOKEINTERFACE / %s / %s / %s".format(owner, name, desc))
    }
  }

  override def visitInvokeDynamicInsn(name: String, desc: String, bsm: Handle, bsmArgs: AnyRef*) {
    println("visitInvokeDynamicInsn: %s / %s / %s / %s".format(name, desc, bsm, bsmArgs))
    super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs)
  }

  override def visitJumpInsn(opcode: Int, label: Label) {
    opcode match {
      case IFEQ => throw new RuntimeException("Unsupported visitJumpInsn (IFEQ) - %s".format(label))
      case IFNE => throw new RuntimeException("Unsupported visitJumpInsn (IFNE) - %s".format(label))
      case IFLT => throw new RuntimeException("Unsupported visitJumpInsn (IFLT) - %s".format(label))
      case IFGE => throw new RuntimeException("Unsupported visitJumpInsn (IFGE) - %s".format(label))
      case IFGT => throw new RuntimeException("Unsupported visitJumpInsn (IFGT) - %s".format(label))
      case IFLE => throw new RuntimeException("Unsupported visitJumpInsn (IFLE) - %s".format(label))
      case IF_ICMPEQ => throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPEQ) - %s".format(label))
      case IF_ICMPNE => {
        println("IF_ICMPNE - Stack: %s / %s".format(instructions.dequeue(), instructions.dequeue()))
//        throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPNE) - %s".format(label))
      }
      case IF_ICMPLT => throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPLT) - %s".format(label))
      case IF_ICMPGE => throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPGE) - %s".format(label))
      case IF_ICMPGT => throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPGT) - %s".format(label))
      case IF_ICMPLE => throw new RuntimeException("Unsupported visitJumpInsn (IF_ICMPLE) - %s".format(label))
      case IF_ACMPEQ => throw new RuntimeException("Unsupported visitJumpInsn (IF_ACMPEQ) - %s".format(label))
      case IF_ACMPNE => throw new RuntimeException("Unsupported visitJumpInsn (IF_ACMPNE) - %s".format(label))
      case GOTO => throw new RuntimeException("Unsupported visitJumpInsn (GOTO) - %s".format(label))
      case JSR => throw new RuntimeException("Unsupported visitJumpInsn (JSR) - %s".format(label))
      case IFNULL => throw new RuntimeException("Unsupported visitJumpInsn (IFNULL) - %s".format(label))
      case IFNONNULL => throw new RuntimeException("Unsupported visitJumpInsn (IFNONNULL) - %s".format(label))
    }
//    println("visitJumpInsn: %s / %s".format(opcode, label))
  }

  override def visitLabel(label: Label) {
    if (debug) println("visitLabel: %s".format(label))
    this.label = label
  }

  override def visitLdcInsn(cst: Any) {
    if (debug) println("visitLdcInsn: %s".format(cst))
    instructions.enqueue(cst match {
      case s: String => "'%s'".format(s)
      case l: Long => l.toString
      case f: Float => f.toString
      case d: Double => d.toString
      case _ => throw new RuntimeException("Unhandled variable type for ldcsInsn: %s (%s)".format(cst.asInstanceOf[AnyRef].getClass.getName, cst))
    })
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
    if (debug) println("visitLineNumber: %s / %s".format(line, start))
    lineNumber = line
  }

  override def visitMaxs(maxStack: Int, maxLocals: Int) {
    println("visitMaxs: %s / %s".format(maxStack, maxLocals))
    super.visitMaxs(maxStack, maxLocals)
  }

  override def visitEnd() {
    println("visitEnd")
    super.visitEnd()
  }
}