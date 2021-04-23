package Host.ServerNextGen
/*
import Host.Database.DatabaseActor
import Host.Lobby.{Player, Lobby}
import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props}
import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json.JsValue
import Host.{Action, Fight, Login, SignUp}


class Server() {

  class SignUpListener(server: Host.Server.Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, regData: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + " trying to register!")
      val actor: ActorRef = server.Socket_2_Actor(socket)
      actor ! SignUp(regData, socket)
    }
  }


  class LoginListener(server: Host.Server.Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, logData: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + " trying to login!")
      val actor: ActorRef = server.Socket_2_Actor(socket)
      actor ! Login(logData, socket)
    }
  }


  class FightListener(server: Host.Server.Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, partyData: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + " trying to fight!")
      val actor: ActorRef = server.Socket_2_Actor(socket)
      actor ! Fight(partyData, server)
    }
  }


  class ActionListener(server: Host.Server.Server) extends DataListener[JsValue] {
    override def onData(socket: SocketIOClient, actionData: JsValue, ackRequest: AckRequest): Unit = {
      println(socket + "'s character is trying to do something!")
      val actor: ActorRef = server.Socket_2_Actor(socket)
      actor ! Action(actionData, server)
    }
  }


  class ConnectionListener(server: Host.Server.Server) extends ConnectListener {
    override def onConnect(socket: SocketIOClient): Unit = {
      println(socket + " landed on main page!")

      if (!server.Socket_2_Actor.contains(socket)) {
        val system = ActorSystem()
        val Main: ActorRef = system.actorOf(Props(classOf[DatabaseActor]))
        val DB: ActorRef = system.actorOf(Props(classOf[MainActor], DB))

        server.Socket_2_Actor += (socket -> Main)
        server.Actor_2_Socket += (Main -> socket)
      }
    }
  }


  class DisconnectionListener(server: Host.Server.Server) extends DisconnectListener {
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


  class StopServer(server: Host.Server.Server) extends DataListener[Nothing] {
    override def onData(socket: SocketIOClient, data: Nothing, ackRequest: AckRequest): Unit = {
      server.server.stop()
      println("safe to stop program")
    }
  }


  val room: Lobby = new Lobby(1)
  var Actor_2_Socket: Map[ActorRef, SocketIOClient] = Map()
  var Socket_2_Actor: Map[SocketIOClient, ActorRef] = Map()
  var Socket_2_Player: Map[SocketIOClient, Player] = Map()

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)

  server.addEventListener("signup", classOf[JsValue], new SignUpListener(this))
  server.addEventListener("login", classOf[JsValue], new LoginListener(this))
  server.addEventListener("fight", classOf[JsValue], new FightListener(this))
  server.addEventListener("action", classOf[JsValue], new ActionListener(this))

  server.addConnectListener(new ConnectionListener(this))
  server.addDisconnectListener(new DisconnectionListener(this))
  server.addEventListener("stop", classOf[Nothing], new StopServer(this))
  server.start()

}


object Launch {
  def main(args: Array[String]): Unit = {
    val server: Host.Server.Server = new Host.Server.Server
  }
}
*/