package Host.ServerNextGen

import Host.Lobby.Lobby
import play.api.libs.json.JsValue
import akka.actor.{Actor, ActorRef}
import Host.{Action, Fight, Login, SignUp}
import com.corundumstudio.socketio.SocketIOClient


class MainActor(DB: ActorRef) extends Actor {

  def receive: Receive = {

    case SignUp(regData: JsValue, socket: SocketIOClient) =>
      DB ! SignUp(regData, socket)

    case Login(logData: JsValue, socket: SocketIOClient) =>
      DB ! Login(logData, socket)

    case Fight(partyData: JsValue, server: Host.Server.Server) =>
      // TODO

    case Action(actionData: JsValue, server: Host.Server.Server) =>
      // TODO
  }
}
