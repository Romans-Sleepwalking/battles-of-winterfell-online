package Host.Lobby

import Host.Characters.Character

class Game(val P1: Player, val P2: Player) {
  /*
  The Game class represents the game process itself - the brain which calls, listens, process, calls...
  A game begins when the Game class is called - when 2 PLAYERS are connected to the same room.
  A game is a recursion of characters' turns.

  By every character's turn, the Game must:
    1) inform both players about the beginning of the turn.
    2) send to active player his action options.
    3) process the received action choice.
    4) inform both players about the action consequences.
    5) call a next turn.

  The Game class contains:
    ·)  A map of characters to their assigned IDs
    ·)  A cycle of characters by assigning them graph-node relations
    ·)  startTurn method which performs actions 1), 2)
    ·)  endTurn method which performs actions 3), 4), 5)
  */
  val charByID: Map[Int, Character] = Map(
    1 -> P1.char1,
    2 -> P1.char2,
    3 -> P1.char3,
    4 -> P1.char4,
    5 -> P2.char1,
    6 -> P2.char2,
    7 -> P2.char3,
    8 -> P2.char4
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
    if (char.owner == P1){
      P1.socket.sendEvent("Turn", "turn data")  // TODO: add turn data
      P2.socket.sendEvent("Wait")  // TODO: send wait command
    }
    else {
      P1.socket.sendEvent("Wait")
      P2.socket.sendEvent("Turn", "turn data")  // TODO: add turn data
    }
  }

  def endTurn(char: Character, skill: Int, target: Character): Unit = {
    if (skill == 1){
      char.Skill1(target)
    }
    else if (skill == 2){
      char.Skill2(target)
    }
    else if (skill == 3){
      char.Skill3(target)
    }
    else if (skill == 4){
      char.Skill4(target)
    }
    // TODO: inform players about consequences
    startTurn(char.next)
  }
}

