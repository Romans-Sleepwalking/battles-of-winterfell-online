package Server

import com.corundumstudio.socketio.SocketIOClient
import scala.collection.mutable.ListBuffer

object Launch {
  var players_online: ListBuffer[SocketIOClient] = ListBuffer()

  def main(args: Array[String]): Unit = {
    val server: Server = new Server
  }
}
