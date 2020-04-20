package io.youi.processor

import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.{ConcurrentLinkedQueue, Executors}

import scala.concurrent.{ExecutionContext, Future}

object Processor {
  lazy val DefaultThreads: Int = Runtime.getRuntime.availableProcessors() * 2

  private lazy val executionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def apply[Input, Output](name: String,
                           handler: Input => Future[Output],
                           executionContext: ExecutionContext = executionContext): Processor[Input, Output] = {
    new Processor[Input, Output](name, handler, executionContext)
  }
}

class Processor[Input, Output](name: String,
                               handler: Input => Future[Output],
                               executionContext: ExecutionContext) {
  private implicit val implicitExecutionContext: ExecutionContext = executionContext

  def process(input: List[Input],
              threads: Int = Processor.DefaultThreads,
              monitor: ProcessorMonitor = ProcessorMonitor.Default): Future[List[Output]] = {
    val started = System.currentTimeMillis()
    val processed = new AtomicLong(0L)
    val total = input.length

    val queue = new ConcurrentLinkedQueue[WorkUnit]
    val work = input.map { i =>
      val wu = WorkUnit(i)
      queue.add(wu)
      wu
    }

    def processWork(): Future[Unit] = Option(queue.poll()) match {
      case None => Future.successful(())
      case Some(wu) => handler(wu.input).flatMap { out =>
        processed.incrementAndGet()
        wu.output = Some(out)
        processWork()
      }
    }

    val futures = (0 until threads).map(_ => processWork()).toList
    val future = Future.sequence(futures).map(_ => work.flatMap(_.output))

    // Monitor
    Future {
      val logEvery = monitor.resolution.toMillis
      var lastRun: Long = System.currentTimeMillis()
      while (!future.isCompleted) {
        if (System.currentTimeMillis() > lastRun + logEvery) {
          val now = System.currentTimeMillis()
          val elapsed = (now - started) / 1000.0
          val perSecond = processed.get() / elapsed
          monitor.monitor(name, processed.get(), total, elapsed, perSecond, threads, finished = false)
          lastRun = now
        }
        Thread.sleep(10L)
      }
      val elapsed = (System.currentTimeMillis() - started) / 1000.0
      val perSecond = processed.get() / elapsed
      monitor.monitor(name, processed.get(), total, elapsed, perSecond, threads, finished = true)
    }

    future
  }

  case class WorkUnit(input: Input, var output: Option[Output] = None)
}

