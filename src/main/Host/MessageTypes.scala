package Host

import Host.Server.Server
import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.JsValue

// Account messages
case class SignUp(regData: JsValue, server: Server)
case class Login(logData: JsValue, socket: SocketIOClient, server: Server)

// Game messages
case class Fight(partyData: JsValue, server: Server)
case class Action(actionData: JsValue, server: Server)
