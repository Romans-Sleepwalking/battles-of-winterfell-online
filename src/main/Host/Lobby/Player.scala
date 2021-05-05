package main.Host.Lobby

import main.Host.Characters.Character
import com.corundumstudio.socketio.SocketIOClient


class Player(val name: String, val socket: SocketIOClient = null) {
  /*
  The Player class represents the player - the entity which choose actions based on received information.
  Every Player must be initialized when will be connected to the server.

  A Player has:
    ·) name
    ·) socket — client web address
    ·) four characters to play with based on random basic set: Northern Alliance [50%] or Undead Army [50%]
    ·) game reference
  */
  var char1: Character = _
  var char2: Character = _
  var char3: Character = _
  var char4: Character = _
  var numCharactersAlive: Int = 4
  var game: Game = _
  var side: String = _

  if (scala.util.Random.nextFloat >= 0.5){
    this.char1 = new Character(
      this,
      "John Snow",
      "Snow",
      "Crusader",
      30,
      20,
      15
    )
    this.char2 = new Character(
      this,
      "Gray Sparrow",
      "Commander",
      "Crusader",
      24,
      15,
      12
    )
    this.char3 = new Character(
      this,
      "Albus",
      "Warrior",
      "Knight",
      15,
      10,
      8
    )
    this.char4 = new Character(
      this,
      "Bancroft",
      "Warrior",
      "Knight",
      15,
      10,
      8
    )
  }
  else {
    this.char1 = new Character(
      this,
      "The Night King",
      "King",
      "Necromancer",
      20,
      15,
      30
    )
    this.char2 = new Character(
      this,
      "White Walker",
      "Walker",
      "Necromancer",
      15,
      12,
      20
    )
    this.char3 = new Character(
      this,
      "Rotten blacksmith",
      "Zombie",
      "Knight",
      12,
      12,
      12
    )
    this.char4 = new Character(
      this,
      "Fallen mercenary",
      "Zombie",
      "Knight",
      12,
      12,
      12
    )
  }
  val party: List[Character] = List(this.char1, this.char2, this.char3, this.char4)
}


