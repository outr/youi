package io.youi.example

import com.outr.hookup.{Hookup, HookupSupport}
import reactify.Var

trait ExampleHookup extends Hookup {
  val name: Var[Option[String]] = prop[Option[String]](None)
  val communication: ExampleCommunication with HookupSupport = auto[ExampleCommunication]
  val simple: SimpleCommunication with HookupSupport = auto[SimpleCommunication]

  name.attach { value =>
    scribe.info(s"Name changed: $value")
  }
}
