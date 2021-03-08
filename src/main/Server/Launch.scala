package Server

import akka.actor._
import Rooms.Room

object Launch extends App {
  val server: Server = new Server("localhost", 8080)
  val room: Room = new Room(1)
  val actor = ActorSystem("ServerActors").actorOf(Props(classOf[Actors], room))
}
