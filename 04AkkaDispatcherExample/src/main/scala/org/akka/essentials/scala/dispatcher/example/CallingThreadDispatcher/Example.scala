package org.akka.essentials.scala.dispatcher.example.CallingThreadDispatcher
import org.akka.essentials.scala.dispatcher.MsgEchoActor
import com.typesafe.config.ConfigFactory
import akka.actor.actorRef2Scala
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.{RoundRobinPool}

object Example {
	def main(args: Array[String]): Unit = {}
	val _system = ActorSystem.create("callingThread-dispatcher",ConfigFactory.load().getConfig("MyDispatcherExample"))

	val actor = _system.actorOf(RoundRobinPool(5).withDispatcher("CallingThreadDispatcher").props(Props[MsgEchoActor]))

	0 to 9 foreach {
		i => actor ! i
	}
  Thread.sleep(3000)
	_system.terminate()
}