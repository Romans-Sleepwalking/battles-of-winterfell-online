package Server

import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json._
import scala.collection.mutable.ListBuffer


class ConnectionListener() extends ConnectListener{
  override def onConnect(client: SocketIOClient): Unit = {
    println("Connected" + client)
    Launch.players_online += client
  }
}

class DisconnectionListener() extends DisconnectListener{
  override def onDisconnect(client: SocketIOClient): Unit = {
    println("Disconnected" + client)
    Launch.players_online -= client
  }
}

class RegisterUser() extends DataListener[String]{
  override def onData(socket: SocketIOClient, data: String, ackRequest: AckRequest): Unit = {
    println("received message: " + data + " from " + socket)
    socket.sendEvent("msg_received", "msg_received2")
  }
}



class StopServer(server: Server) extends DataListener[Nothing] {
  override def onData(socket: SocketIOClient, data: Nothing, ackRequest: AckRequest): Unit = {
    server.server.stop()
    println("safe to stop program")
  }
}

class Server(hostname: String, port: Int) {
  var players_online: ListBuffer[SocketIOClient] = ListBuffer()

  val config: Configuration = new Configuration {
    setHostname(hostname)
    setPort(port)
  }

  val server: SocketIOServer = new SocketIOServer(config)
  server.addConnectListener(new ConnectionListener())
  server.addDisconnectListener(new DisconnectionListener())
  server.addEventListener("regUser", classOf[Object], new RegisterUser())
  server.addEventListener("stop_server", classOf[Nothing], new StopServer(this))
  server.start()
}

  /*

def toJSON: JsValue = Json.toJson(  // map to JSON
    Map("cashInRegister" -> cashInRegister.toString, // stringify values
        "inventory" -> inventory.mkString(","))
  )


def decodeJSON(dataJSON: JsValue): Map[String, String] = {
  Map(
  "name" -> (dataJSON \ "name").as[String],
  "key" -> (dataJSON \ "key").as[String],
  "lvl" -> (dataJSON \ "lvl").as[String],
  "str" -> (dataJSON \ "str").as[String],
  "agi" -> (dataJSON \ "agi").as[String],
  "int" -> (dataJSON \ "int").as[String],
  )
}

*/
