package Host

import Host.Server.Server
import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.JsValue

// Database messages
case class SignUp(regData: JsValue, socket: SocketIOClient)
case class Login(logData: JsValue, socket: SocketIOClient)
case class incrementWins(username: String)
case class incrementLoses(username: String)

// Game messages
case object Join
case object Leave
case class Action(data: JsValue)

