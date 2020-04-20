package io.youi

trait Modifiable {
  lazy val modified: Var[Long] = Var(0L)
}
