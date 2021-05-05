package main.Host.Server

import main.Host.Lobby.{Game, Player}
import main.Host.Characters.Character
import main.Host.Lobby.targetFinder.getTargets
import play.api.libs.json._

object Message {
  /*
  The Message object is responsible for translating and sending communications to players

  The Message has:
    playerToJSON:    translates a complete one player to JSON-string
    characterToJSON: translates a complete character to JSON-string
    One:             sends a string to a player for displaying in chat
    Both:            sends a string to both players for displaying in chat
    Update:          sends sends full game information to both players with a message
    Turn:            sends sends a turn to a player
    Translate:       translates a command from a player
  */
  def characterToJSON(char: Character, game: Game): JsValue = Json.obj(
    "id" -> game.idByCharacter(char).toString(),
    "side" -> char.owner.side,
    "name" -> char.name,
    "class" -> char.Class,
    "img" -> char.img,
    "state" -> char.State,
    "HP" -> char.HP.toString(),
    "maxHP" -> char.maxHP.toString(),
    "MP" -> char.MP.toString(),
    "maxMP" -> char.maxMP.toString(),
    "abi1" -> Json.obj(
      "num" -> 1,
      "status" -> char.abi1_status,
      "name" -> char.abi1_name,
      "description" -> char.abi1_description,
      "target" -> Json.toJson(getTargets(char.abi1_target, char, game)), // JS array
      "effect" -> char.abi1_effect,
      "cost" -> char.abi1_cost,
    ),
    "abi2" -> Json.obj(
      "num" -> 2,
      "status" -> char.abi2_status,
      "name" -> char.abi2_name,
      "description" -> char.abi2_description,
      "target" -> Json.toJson(getTargets(char.abi2_target, char, game)),
      "effect" -> char.abi2_effect,
      "cost" -> char.abi2_cost,
    ),
    "abi3" -> Json.obj(
      "num" -> 3,
      "status" -> char.abi3_status,
      "name" -> char.abi3_name,
      "description" -> char.abi3_description,
      "target" -> Json.toJson(getTargets(char.abi3_target, char, game)),
      "effect" -> char.abi3_effect,
      "cost" -> char.abi3_cost,
    ),
    "abi4" -> Json.obj(
      "num" -> 4,
      "status" -> char.abi4_status,
      "name" -> char.abi4_name,
      "description" -> char.abi4_description,
      "target" -> Json.toJson(getTargets(char.abi4_target, char, game)),
      "effect" -> char.abi4_effect,
      "cost" -> char.abi4_cost,
    ),
  )
  def playerToJSON(P: Player, game: Game): JsValue = {
    Json.obj(
      "1" -> characterToJSON(P.char1, game),
      "2" -> characterToJSON(P.char2, game),
      "3" -> characterToJSON(P.char3, game),
      "4" -> characterToJSON(P.char4, game),
    )
  }

  def One(P: Player, msg: String): Unit = { // Emits a message to Player
    P.socket.sendEvent("msg", msg)
  }

  def Both(P1: Player, P2: Player, msg: String): Unit = { // Emits one message to both Player-1 and Player-2
    P1.socket.sendEvent("msg", msg)
    P2.socket.sendEvent("msg", msg)
  }

  def Update(game: Game, msg: String): Unit = {
    println("sending turn")
    val upd: JsValue = Json.obj(
      "P1" -> playerToJSON(game.P1, game),
      "P2" -> playerToJSON(game.P2, game),
      "msg" -> msg
    )
    val JSstring: String = Json.stringify(upd)
    game.P1.socket.sendEvent("upd", JSstring)
    game.P2.socket.sendEvent("upd", JSstring)
  }

  def Turn(char: Character, game: Game): Unit = {
    println("sending turn")
    val JSstring: String = Json.stringify(characterToJSON(char, game))
    char.owner.socket.sendEvent("turn", JSstring)
  }

  def Translate(jsonString: String): Map[String, Int] = {
    println("JSON action:  " + jsonString)
    val json: JsValue = Json.parse(jsonString)
    Map(
      "charID" -> (json \ "charID").as[Int],
      "targetID" -> (json \ "target").as[Int],
      "skill" -> (json \ "skill").as[Int],
    )
  }
}
