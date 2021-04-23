package Host.Server

import Host.Lobby.Lobby
import Host.{Join, Leave, Action}
import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props}
import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json.JsValue


class Server() {
  var lobbies: List[Lobby] = List(new Lobby(1), new Lobby(2))


  class ActionListener(server: Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, choice: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + "'s character is trying to do something!")
      val actor: ActorRef = server.socketToActor(socket)
      actor ! Action(choice)
    }
  }


  class ConnectionListener(server: Server) extends ConnectListener {
    override def onConnect(socket: SocketIOClient): Unit = {
      println(socket + " connected!")
      if (!server.socketToActor.contains(socket)) {
        val system = ActorSystem()
        val actor: ActorRef = system.actorOf(Props(classOf[GameActor], socket, server))
        server.socketToActor += (socket -> actor)
        server.actorToSocket += (actor -> socket)
        actor ! Join
      }
    }
  }


  class DisconnectionListener(server: Server) extends DisconnectListener {
    override def onDisconnect(socket: SocketIOClient): Unit = {
      if (server.socketToActor.contains(socket)){
        val actor: ActorRef = server.socketToActor(socket)
        actor ! Leave
        server.socketToActor(socket) ! PoisonPill
        val username = server.socketToActor(socket)
        server.socketToActor -= socket
        server.actorToSocket -= username
        println(username + " Disconnected")
      }
    }
  }


  class StopServer(server: Server) extends DataListener[Nothing] {
    override def onData(socket: SocketIOClient, data: Nothing, ackRequest: AckRequest): Unit = {
      server.server.stop()
      println("safe to stop program")
    }
  }

  var actorToSocket: Map[ActorRef, SocketIOClient] = Map()
  var socketToActor: Map[SocketIOClient, ActorRef] = Map()

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)

  server.addEventListener("action", classOf[JsValue], new ActionListener(this))
  server.addConnectListener(new ConnectionListener(this))
  server.addDisconnectListener(new DisconnectionListener(this))
  server.addEventListener("stop", classOf[Nothing], new StopServer(this))
  server.start()
}


object Launch {
  def main(args: Array[String]): Unit = {
    val server: Server = new Server
  }
}
