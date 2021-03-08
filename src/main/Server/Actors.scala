package Server

import akka.actor._
import Rooms.Room
import

class Actors(room: Room) extends Actor {
  def receive: Receive = {
    case Dot_ =>
      model.Dot()
    case Digit_(digit: String) =>
      model.Digit(digit)
  }
}
