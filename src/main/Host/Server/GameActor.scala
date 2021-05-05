package main.Host.Server

import main.Host.{Action, Leave}
import akka.actor.Actor
import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.JsValue
import main.Host.Lobby._
import main.Host.Server.Message.Translate

class GameActor(socket: SocketIOClient, server: Server) extends Actor {
  val player: Player = new Player("User" + scala.util.Random.nextInt(200).toString, socket)
  var lobbyFound: Lobby = _
  for (lobby <- this.server.lobbies){
    if (lobby.State != "full" && lobby.State != "abandoned"){
        lobbyFound = lobby
        lobbyFound.Join(player)
    }
  }


  override def receive: Receive = {
    case Leave =>
      lobbyFound.Leave(this.player)

    case Action(data: String) =>
      val choice: Map[String, Int] = Translate(data)
      this.player.game.endTurn(choice("charID"), choice("targetID"), choice("skill"))
  }
}
