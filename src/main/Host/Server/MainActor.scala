package Host.Server

import Host.Rooms.Room
import play.api.libs.json.JsValue
import akka.actor.{Actor, ActorRef}
import Host.{Action, Fight, Login, SignUp}
import com.corundumstudio.socketio.SocketIOClient


class MainActor(DB: ActorRef) extends Actor {

  def receive: Receive = {

    case SignUp(regData: JsValue, server: Server) =>
      DB ! SignUp(regData, server)

    case Login(logData: JsValue, client: SocketIOClient, server: Server) =>
      DB ! Login(logData, client, server)

    case Fight(partyData: JsValue, server: Server) =>
      val Player = new Player(JsValue)
      // TODO

    case Action(actionData: JsValue, server: Server) =>
      // TODO
  }
}
