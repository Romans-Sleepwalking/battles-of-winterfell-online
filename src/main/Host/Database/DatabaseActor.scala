package Host.Database

import akka.actor.Actor
import Host.{Login, SignUp, incrementWins, incrementLoses}
import play.api.libs.json.JsValue
import com.corundumstudio.socketio.SocketIOClient


class DatabaseActor() extends Actor {
  val DB: Database = new Database()

  def receive: Receive = {
    case SignUp(regData: JsValue, socket: SocketIOClient) =>
      DB.SignUp(regData, socket)

    case Login(logData: JsValue, socket: SocketIOClient) =>
      DB.Login(logData, socket)

    case incrementWins(username: String) =>
      DB.incrementWins(username)

    case incrementLoses(username: String) =>
      DB.incrementLoses(username)
  }
}

