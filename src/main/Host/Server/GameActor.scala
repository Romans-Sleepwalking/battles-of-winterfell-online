package Host.Server

import Host.{Action, Leave}
import akka.actor.{Actor, ActorRef}
import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.JsValue
import Host.Lobby._

class GameActor(socket: SocketIOClient, server: Server) extends Actor {
  val player: Player = new Player("User" + scala.util.Random.nextInt(200).toString, socket)
  var lobbyFound: Lobby = _
  for (lobby <- this.server.lobbies){
      if (lobby.State != "full"){
        lobbyFound = lobby
        lobbyFound.Join(player)
      }
  }

  override def receive: Receive = {
    case Leave =>
      lobbyFound.Leave(this.player)

    case Action(data: JsValue) =>
      val choice: Map[String, Int] = Message.Translate(data)
      this.player.game.endTurn(choice("charID"), choice("targetID"), choice("skill"))
  }
}
