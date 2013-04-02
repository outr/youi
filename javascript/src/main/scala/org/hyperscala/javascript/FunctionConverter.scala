package org.hyperscala.javascript

import org.objectweb.asm.{ClassReader, Type}
import org.objectweb.asm.tree.{LocalVariableNode, MethodNode, ClassNode}
import java.lang.reflect.Method

import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object FunctionConverter {
  def f0[R](name: String, f: () => R, includeLineNumbers: Boolean = false, debug: Boolean = false)
           (implicit manifest: Manifest[R]) = {
    //    println("Erasure: %s".format(manifest.runtimeClass.getName))
    //    f.getClass.getMethods.foreach(println)
    val methodName = lookupMethodName(manifest.runtimeClass)
    val method = f.getClass.getMethods.find(m => m.getReturnType == manifest.runtimeClass && m.getName == methodName && m.getParameterTypes.length == 0).get
    val node = lookupMethodNode(method).get
    val script = convert(node, includeLineNumbers, debug)
    "function %s() {\r\n%s}".format(name, script)
  }

  def f1[P1, R](name: String, f: (P1) => R, includeLineNumbers: Boolean = false, debug: Boolean = false)
               (implicit manifest: Manifest[R]) = {
    //    f.getClass.getMethods.foreach(println)
    val methodName = "apply"
    val method = f.getClass.getMethods.find(m => m.getReturnType == manifest.runtimeClass && m.getName == methodName && m.getParameterTypes.length == 1).get
    val node = lookupMethodNode(method).get
    val script = convert(node, includeLineNumbers, debug)
    val arg1Name = node.localVariables.get(1).asInstanceOf[LocalVariableNode].name
    "function %s(%s) {\r\n%s}".format(name, arg1Name, script)
  }

  def f2[P1, P2, R](name: String, f: (P1, P2) => R, includeLineNumbers: Boolean = false, debug: Boolean = false)
                   (implicit manifest: Manifest[R]) = {
    //    f.getClass.getMethods.foreach(println)
    val methodName = "apply"
    val method = f.getClass.getMethods.find(m => m.getReturnType == manifest.runtimeClass && m.getName == methodName && m.getParameterTypes.length == 2).get
    val node = lookupMethodNode(method).get
    val script = convert(node, includeLineNumbers, debug)
    val arg1Name = node.localVariables.get(1).asInstanceOf[LocalVariableNode].name
    val arg2Name = node.localVariables.get(2).asInstanceOf[LocalVariableNode].name
    "function %s(%s, %s) {\r\n%s}".format(name, arg1Name, arg2Name, script)
  }

  def f3[P1, P2, P3, R](name: String, f: (P1, P2, P3) => R, includeLineNumbers: Boolean = false, debug: Boolean = false)
                       (implicit manifest: Manifest[R]) = {
    //    f.getClass.getMethods.foreach(println)
    val methodName = "apply"
    val method = f.getClass.getMethods.find(m => m.getReturnType == manifest.runtimeClass && m.getName == methodName && m.getParameterTypes.length == 3).get
    val node = lookupMethodNode(method).get
    val script = convert(node, includeLineNumbers, debug)
    val arg1Name = node.localVariables.get(1).asInstanceOf[LocalVariableNode].name
    val arg2Name = node.localVariables.get(2).asInstanceOf[LocalVariableNode].name
    val arg3Name = node.localVariables.get(3).asInstanceOf[LocalVariableNode].name
    "function %s(%s, %s, %s) {\r\n%s}".format(name, arg1Name, arg2Name, arg3Name, script)
  }

  def f4[P1, P2, P3, P4, R](name: String, f: (P1, P2, P3, P4) => R, includeLineNumbers: Boolean = false, debug: Boolean = false)
                           (implicit manifest: Manifest[R]) = {
    //    f.getClass.getMethods.foreach(println)
    val methodName = "apply"
    val method = f.getClass.getMethods.find(m => m.getReturnType == manifest.runtimeClass && m.getName == methodName && m.getParameterTypes.length == 4).get
    val node = lookupMethodNode(method).get
    val script = convert(node, includeLineNumbers, debug)
    val arg1Name = node.localVariables.get(1).asInstanceOf[LocalVariableNode].name
    val arg2Name = node.localVariables.get(2).asInstanceOf[LocalVariableNode].name
    val arg3Name = node.localVariables.get(3).asInstanceOf[LocalVariableNode].name
    val arg4Name = node.localVariables.get(4).asInstanceOf[LocalVariableNode].name
    "function %s(%s, %s, %s, %s) {\r\n%s}".format(name, arg1Name, arg2Name, arg3Name, arg4Name, script)
  }

  def lookupMethodName(returnType: Class[_]) = returnType.getName match {
    case "void" => "apply$mcV$sp"
    case "boolean" => "apply$mcZ$sp"
    case "int" => "apply$mcI$sp"
    case "long" => "apply$mcJ$sp"
    case "float" => "apply$mcF$sp"
    case "double" => "apply$mcD$sp"
    case _ => "apply"
  }

  def lookupMethodNode(method: Method) = {
    val clazz = method.getDeclaringClass
    val classLoader = clazz.getClassLoader
    val declaringType = Type.getType(clazz)
    val url = "%s.class".format(declaringType.getInternalName)
    val classNode = new ClassNode()
    val input = classLoader.getResourceAsStream(url)
    try {
      val classReader = new ClassReader(input)
      classReader.accept(classNode, 0) // Visit ClassNode to populate the data
    } finally {
      input.close()
    }
    val desc = Type.getMethodDescriptor(method)
    classNode.methods.asInstanceOf[java.util.List[MethodNode]].find(m => m.name == method.getName && m.desc == desc)
  }

  def convert(methodNode: MethodNode, includeLineNumbers: Boolean, debug: Boolean) = {
    val converter = new BytecodeConverter(methodNode, includeLineNumbers, debug)
    converter.depth = 1
    methodNode.instructions.accept(converter)
    converter.toString
  }

  def main(args: Array[String]): Unit = {
    val f = (b: Boolean) => {
      b
    }
    val result = f1("test", f, includeLineNumbers = true, debug = true)
    println("-------------------")
    println(result)
    println("-------------------")
  }
}
