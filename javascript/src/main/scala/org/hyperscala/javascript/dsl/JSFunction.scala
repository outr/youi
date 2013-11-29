package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JSFunction[R] extends JavaScriptContext {
  var name: String = _
  def manifest: Manifest[R]

  override protected def write(b: StringBuilder, depth: Int) = super.write(b, depth + 1)

  override protected def after(b: StringBuilder, depth: Int) = {
    writeLine("}", b, depth - 1, semicolon = false)
  }

  override protected def hasReturn = manifest.runtimeClass.getName != "void"
}

case class FunctionName[R](f: JSFunction[R]) extends Statement[String] {
  def content = f.name

  def sideEffects = false
}

abstract class JSFunction0[R](implicit val manifest: Manifest[R]) extends JSFunction[R] {
  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function() {\r\n")
  }

  def apply() = MultiStatement[R](sideEffects = true, FunctionName[R](this), "()")
}

object JSFunction0 {
  def apply[R](content: String)(implicit manifest: Manifest[R]) = new JSFunction0[R] {
    override def toJS(depth: Int) = content
  }
}

abstract class JSFunction1[P1, R](implicit val manifest: Manifest[R]) extends JSFunction[R] {
  val p1 = ExistingStatement[P1]("p1")

  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function(p1) {\r\n")
  }

  override def variable(v: Any) = if (v == p1) {
    Some("p1")
  } else {
    super.variable(v)
  }

  def apply(p1: Statement[P1]) = MultiStatement[R](sideEffects = true, FunctionName[R](this), "(", p1, ")")
}

object JSFunction1 {
  def apply[P1, R](content: String)(implicit manifest: Manifest[R]) = new JSFunction1[P1, R] {
    override def toJS(depth: Int) = content
  }
}

abstract class JSFunction2[P1, P2, R](implicit val manifest: Manifest[R]) extends JSFunction[R] {
  val p1 = ExistingStatement[P1]("p1")
  val p2 = ExistingStatement[P2]("p2")

  override protected def before(b: StringBuilder, depth: Int) = {
    b.append(s"function(p1, p1) {\r\n")
  }

  override def variable(v: Any) = if (v == p1) {
    Some("p1")
  } else if (v == p2) {
    Some("p2")
  } else {
    super.variable(v)
  }

  def apply(p1: Statement[P1], p2: Statement[P2]) = {
    MultiStatement[R](sideEffects = true, FunctionName[R](this), "(", p1, ", ", p2, ")")
  }
}

object JSFunction2 {
  def apply[P1, P2, R](content: String)(implicit manifest: Manifest[R]) = new JSFunction2[P1, P2, R] {
    override def toJS(depth: Int) = content
  }
}