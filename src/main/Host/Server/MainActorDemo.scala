package Host.Server

import Host.{ActionDemo, FightDemo}
import akka.actor.{Actor, ActorRef}
import com.corundumstudio.socketio.{SocketIOClient, SocketIOServer}
import play.api.libs.json.JsValue


class MainActorDemo(server: SocketIOServer) extends Actor {

  def receive: Receive = {
    case FightDemo(socket: SocketIOClient) =>
      // TODO
      socket.sendEvent("start", "side")

    case ActionDemo(actionData: JsValue, socket: SocketIOClient) =>
      socket.sendEvent("start", "side")
      // TODO
  }
}
