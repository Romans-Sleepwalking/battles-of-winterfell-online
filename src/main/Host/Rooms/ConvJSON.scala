package Host.Rooms

import play.api.libs.json._
import Host.Characters.Character

object ConvJSON {
  def JSON_2_Player(json: JsValue): Map[String, String] ={
    val slots: JsValue = (json \ "slots").as[JsValue]

    def decodeSlot(slot: JsValue)
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

  def Player_2_JSON(p: Player): JsValue = {
    def convCharacter(char: Character): JsValue = Json.obj(
      "class" -> "Crusader",
      "name" -> "John Snow",
      "img" -> "Snow",
      "state" -> "passive",
      "STR" -> "10",
      "AGI" -> "15",
      "INT" -> "9",
      "HP" -> "50",
      "maxHP" -> "100",
      "MP" -> "50",
      "maxMP" -> "60",
      "abi1" -> "ok",
      "abi2" -> "ok",
      "abi3" -> "no mana",
      "abi4" -> "does not make sense"
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
      )
    )
  }
}
