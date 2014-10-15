package org.hyperscala.examples.concurrent

import java.util.concurrent.Executors

import org.hyperscala.examples.Example
import org.powerscala.concurrent.Time
import org.powerscala.event.concurrent.{Status, Task}

/**
 * @author Matt Hicks <matt@outr.com>
 */
//class TasksExample extends Example {
//
//}
//
//class SleepingTask(time: Double) extends Task[Long] {
//  override protected[concurrent] def run(update: (Status) => Unit) = {
//    Time.sleep(time)
//    System.currentTimeMillis()
//  }
//}
//
//object Tasks {
//  implicit val executor = Executors.newFixedThreadPool(4)
//}