package Rooms

import Characters.createCharacter
import Characters.Character

class Player(dataSet: Map[String, Map[String, String]]) {
  val user: Map[String, String] = dataSet("user")

  val name: String = user("username")
  var wins: Int = user("wins").toInt
  var loses: Int = user("loses").toInt

  var slotCounter: Int = 1
  val slot1: Character = createCharacter.Initialize(dataSet("slot1"))
  var slot2: Character = _
  var slot3: Character = _
  var slot4: Character = _

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

