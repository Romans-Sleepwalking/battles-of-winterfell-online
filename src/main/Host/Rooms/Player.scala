package Host.Rooms

import Host.Characters
import Host.Characters.createCharacter
import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.JsValue

class Player(json: JsValue, val socket: SocketIOClient) {
  val data: Map[String, String] = ConvJSON.JSON_2_Player(json)

  val name: String = data("username")
  var wins: String = data("wins")
  var loses: String = data("loses")

  val char1: Characters.Character = createCharacter.Initialize(dataSet("slot1"))
  var char2: Characters.Character = _
  var char3: Characters.Character = _
  var char4: Characters.Character = _

  if (dataSet("slot2")("key") != "none"){
    slot2 = createCharacter.Initialize(dataSet("slot2"))
    slotCounter += 1
  }
  if (dataSet("slot3")("key") != "none"){
    slot3 = createCharacter.Initialize(dataSet("slot3"))
    slotCounter += 1
  }
  if (dataSet("slot4")("key") != "none"){
    slot4 = createCharacter.Initialize(dataSet("slot4"))
    slotCounter += 1
  }
}



