package Host.Server

import Host.Lobby.Player
import Host.Characters.Character
import play.api.libs.json._

object Message {
  def One(P: Player, msg: String): Unit ={  // Emits a message to Player
    P.socket.sendEvent("msg", msg)
  }
  def Both(P1: Player, P2: Player, msg: String): Unit ={  // Emits one message to both Player-1 and Player-2
    P1.socket.sendEvent("msg", msg)
    P2.socket.sendEvent("msg", msg)
  }


  def Receive(): Unit ={

  }


  def JSON_2_Player(json: JsValue): Map[String, String] = {
    val slots: JsValue = (json \ "slots").as[JsValue]

    val slot1: JsValue = (json \ "slots").as[JsValue]


    Map(
      "name" -> (json \ "name").as[String],
      "key" -> (json \ "key").as[String],
      "lvl" -> (json \ "lvl").as[String],
      "str" -> (json \ "str").as[String],
      "agi" -> (json \ "agi").as[String],
      "int" -> (json \ "int").as[String],
    )
  }

  def Greeting(p: Player): JsValue = Json.obj(
    "msg" -> ("Greetings from " + p.name + " (wins=0, loses=0)! Good luck, have fun!")
  )

  def Effect(p: Player, char: Character, msg: String): JsValue = Json.obj(
    "username" -> p.name,
    "class" -> char.Class,
    "msg" -> msg,
    "name" -> char.name,
    "class" -> char.Class,
    "img" -> char.img,
    "state" -> char.State
  )

  def Turn(p: Player, char: Character, msg: String, num: String): JsValue = Json.obj(
    "username" -> p.name,
    "msg" -> msg,
    "char" -> num,
    "name" -> char.name,
    "Class" -> char.Class,
    "img" -> char.img,
    "state" -> char.State,
    "HP" -> char.HP,
    "maxHP" -> char.maxHP,
    "MP" -> char.MP,
    "maxMP" -> char.maxMP,
    "abi1" -> Json.obj(
        "status" -> char.abi1_status,
        "name" -> char.abi1_name,
        "description" -> char.abi1_name,
        "target" -> char.abi1_name,
        "effect" -> char.abi1_effect,
        "cost" -> char.abi1_name,
    ),
    "abi2" -> Json.obj(
        "status" -> char.abi2_status,
        "name" -> char.abi2_name,
        "description" -> char.abi2_name,
        "target" -> char.abi2_name,
        "effect" -> char.abi2_effect,
        "cost" -> char.abi2_name,
    ),
    "abi3" -> Json.obj(
        "status" -> char.abi3_status,
        "name" -> char.abi3_name,
        "description" -> char.abi3_name,
        "target" -> char.abi3_name,
        "effect" -> char.abi3_effect,
        "cost" -> char.abi3_name,
    ),
    "abi4" -> Json.obj(
        "status" -> char.abi4_status,
        "name" -> char.abi4_name,
        "description" -> char.abi4_name,
        "target" -> char.abi4_name,
        "effect" -> char.abi4_effect,
        "cost" -> char.abi4_name,
    ),
  )

  def Chill(): Unit ={

  }

  def Prepare(p: Player, message: String): JsValue = {
    def convCharacter(char: Character): JsValue = Json.obj(
      "name" -> char.name,
      "class" -> char.Class,
      "img" -> "Snow",
      "state" -> "passive",
      "STR" -> char.STR,
      "AGI" -> char.AGI,
      "INT" -> char.INT,
      "HP" -> char.HP,
      "maxHP" -> char.maxHP,
      "MP" -> char.MP,
      "maxMP" -> char.maxMP,
      "abi1" -> Json.obj(
        "status" -> char.abi1_status,
        "name" -> char.abi1_name,
        "description" -> char.abi1_name,
        "target" -> char.abi1_name,
        "cost" -> char.abi1_name,
        "icon" -> char.abi1_name,
      ),
      "abi2" -> Json.obj(
        "status" -> char.abi2_status,
        "name" -> char.abi2_name,
        "description" -> char.abi2_name,
        "target" -> char.abi2_name,
        "cost" -> char.abi2_name,
        "icon" -> char.abi2_name,
      ),
      "abi3" -> Json.obj(
        "status" -> char.abi3_status,
        "name" -> char.abi3_name,
        "description" -> char.abi3_name,
        "target" -> char.abi3_name,
        "cost" -> char.abi3_name,
        "icon" -> char.abi3_name,
      ),
      "abi4" -> Json.obj(
        "status" -> char.abi4_status,
        "name" -> char.abi4_name,
        "description" -> char.abi4_name,
        "target" -> char.abi4_name,
        "cost" -> char.abi4_name,
        "icon" -> char.abi4_name,
      ),
    )

    Json.obj(
      "username" -> p.name,
      "wins" -> p.wins,
      "loses" -> p.loses,
      "characters" -> Json.obj(
        "1" -> convCharacter(p.char1),
        "2" -> convCharacter(p.char2),
        "3" -> convCharacter(p.char3),
        "4" -> convCharacter(p.char4),
      "msg" -> message
      )
    )
  }
}
