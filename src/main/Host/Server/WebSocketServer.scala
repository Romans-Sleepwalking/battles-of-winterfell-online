package Host.Server

import Host.Lobby.Player
import Host.{ActionDemo, FightDemo}
import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props}
import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json.JsValue


class Server() {
  class ActionListener(server: Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, actionData: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + "'s character is trying to do something!")
      val actor: ActorRef = server.Socket_2_Actor(socket)
      actor ! ActionDemo(actionData, socket)
    }
  }


  class ConnectionListener(server: Server) extends ConnectListener {
    override def onConnect(socket: SocketIOClient): Unit = {
      println(socket + " connected!")

      if (!server.Socket_2_Actor.contains(socket)) {
        val system = ActorSystem()
        val actor: ActorRef = system.actorOf(Props(classOf[MainActorDemo], server))
        server.Socket_2_Actor += (socket -> actor)
        server.Actor_2_Socket += (actor -> socket)

        actor ! FightDemo(socket)
      }
    }
  }


  class DisconnectionListener(server: Server) extends DisconnectListener {
    override def onDisconnect(socket: SocketIOClient): Unit = {
      if (server.Socket_2_Actor.contains(socket)) {
        server.Socket_2_Actor(socket) ! PoisonPill
        val username = server.Socket_2_Actor(socket)
        server.Socket_2_Actor -= socket
        server.Actor_2_Socket -= username
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

  var Actor_2_Socket: Map[ActorRef, SocketIOClient] = Map()
  var Socket_2_Actor: Map[SocketIOClient, ActorRef] = Map()
  var Socket_2_Player: Map[SocketIOClient, Player] = Map()

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
