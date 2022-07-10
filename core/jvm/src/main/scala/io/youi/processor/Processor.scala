package io.youi.processor

import cats.effect.IO
import cats.implicits._

import java.util.concurrent.ConcurrentLinkedQueue

object Processor {
  lazy val DefaultThreads: Int = Runtime.getRuntime.availableProcessors() * 2

  def apply[Input, Output](name: String,
                           handler: Input => IO[Output]): Processor[Input, Output] = {
    new Processor[Input, Output](name, handler)
  }
}

class Processor[Input, Output](name: String,
                               handler: Input => IO[Output]) {
  def process(input: List[Input],
              threads: Int = Processor.DefaultThreads,
              monitor: ProcessorMonitor = ProcessorMonitor.Default): IO[List[Output]] = {
    IO.ref[Long](0L).flatMap { processed =>
      val started = System.currentTimeMillis()
      val total = input.length

      val queue = new ConcurrentLinkedQueue[WorkUnit]
      val work = input.map { i =>
        val wu = WorkUnit(i)
        queue.add(wu)
        wu
      }

      def processWork(): IO[Unit] = Option(queue.poll()) match {
        case None => IO.unit
        case Some(wu) => handler(wu.input).flatMap { out =>
          processed.update(_ + 1) >> IO(wu.output = Some(out)) >> processWork()
        }
      }

      val ios = (0 until threads).map(_ => processWork()).toList.sequence
      val io = ios.map(_ => work.flatMap(_.output))

      val monitorStream = fs2.Stream.awakeEvery[IO](monitor.resolution).foreach { _ =>
        val now = System.currentTimeMillis()
        val elapsed = (now - started) / 1000.0
        for {
          processedValue <- processed.get
          perSecond = processedValue / elapsed
          _ <- IO(monitor.monitor(name, processedValue, total, elapsed, perSecond, threads, finished = false))
        } yield {
          ()
        }
      }
      fs2.Stream.evals(io).concurrently(monitorStream).compile.toList
    }
  }

  case class WorkUnit(input: Input, var output: Option[Output] = None)
}

