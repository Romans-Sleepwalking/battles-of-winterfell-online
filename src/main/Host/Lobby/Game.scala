package Host.Lobby

import Host.Characters.Character
import Host.Server.Message

import scala.annotation.tailrec

class Game(val P1: Player, val P2: Player) {
  /*
  The Game class represents the game process itself - the brain which calls, listens, process, calls...
  A game begins when the Game class is called - when 2 PLAYERS are connected to the same room.
  A game is a recursion of characters' turns.

  By every character's turn, the Game must:
    1) inform both players about the beginning of the turn.
    2) send to active player his action options.
    3) process the received action choice.
    4) emit an update to both players.
    5) call a next turn.

  The Game class contains:
    ·)  A map of characters to their assigned IDs
    ·)  A map of IDs to their assigned characters
    ·)  A cycle of characters by assigning them graph-node relations
    ·)  startTurn method which performs actions 1), 2)
    ·)  endTurn method which performs actions 3), 4), 5)
  */
  val charByID: Map[Int, Character] = Map(
    1 -> this.P1.char1,
    2 -> this.P1.char2,
    3 -> this.P1.char3,
    4 -> this.P1.char4,
    5 -> this.P2.char1,
    6 -> this.P2.char2,
    7 -> this.P2.char3,
    8 -> this.P2.char4
  )
  val idByCharacter: Map[Character, Int] = Map(
    this.P1.char1 -> 1,
    this.P1.char2 -> 2,
    this.P1.char3 -> 3,
    this.P1.char4 -> 4,
    this.P2.char1 -> 5,
    this.P2.char2 -> 6,
    this.P2.char3 -> 7,
    this.P2.char4 -> 8
  )
  // creates a cycle of characters
  charByID(4).next = charByID(5)
  charByID(5).next = charByID(3)
  charByID(3).next = charByID(6)
  charByID(6).next = charByID(2)
  charByID(2).next = charByID(7)
  charByID(7).next = charByID(1)
  charByID(1).next = charByID(8)
  charByID(8).next = charByID(4)

  def startTurn(char: Character): Unit ={
    if (char.owner == this.P1){
      Message.Turn(char, this)
      Message.One(this.P2, "It is " + this.P1.name + "'s turn. Please wait.")
    }
    else {
      Message.Turn(char, this)
      Message.One(this.P1, "It is " + this.P2.name + "'s turn. Please wait.")
    }
  }

  def endTurn(charID: Int, targetID: Int, skill: Int): Unit = {
    val char: Character = this.charByID(charID)
    val target: Character = this.charByID(targetID)

    if (skill == 1){
      Message.Update(this, char.Skill1(target))
    }
    else if (skill == 2){
      Message.Update(this, char.Skill2(target))
    }
    else if (skill == 3){
      Message.Update(this, char.Skill3(target))
    }
    else if (skill == 4){
      Message.Update(this, char.Skill4(target))
    }
    // Recursively finds the next living character
    @tailrec
    def avoidDeadCharacters(char: Character): Character ={
      if (char.State != "dead") {
        char
      }
      else {
        avoidDeadCharacters(char.next)
      }
    }
    // If both players have living characters, game continues. If not - game over!
    if (this.P1.numCharactersAlive > 0 && this.P2.numCharactersAlive > 0){
      startTurn(avoidDeadCharacters(char.next))
    }
    else if (P1.numCharactersAlive == 0){
      Message.Both(this.P1, this.P2, "Game Over! " + this.P2.name + "'s party is dead! " + this.P1.name + " won!!!")
    }
    else if (P2.numCharactersAlive == 0){
      Message.Both(this.P1, this.P2, "Game Over! " + this.P1.name + "'s party is dead! " + this.P2.name + " won!!!")
    }
  }
}

