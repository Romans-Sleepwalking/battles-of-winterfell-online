package Rooms

class Room(val number: Int) {
  var p1: Player = _
  var p2: Player = _
  var p1_status: String = "not ready"
  var p2_status: String = "not ready"

  // STATE PATTERN

  var state: State = new Empty(this)

  def takeSlots(presser: Player): Unit = {
    this.state.takeSlots(presser)
  }
  def Leave(presser: Player): Unit = {
    this.state.Leave(presser)
  }
  def Play(presser: Player): Unit = {
    this.state.Play(presser)
  }
}

abstract class State(room: Room) {
  def takeSlots(player: Player): String
  def Leave(player: Player): String
  def Play(player: Player): String
}

class Empty(room: Room) extends State(room) {
  override def takeSlots(presser: Player): String = {
    room.p1 = presser
    room.state = new Waiting(room)
    "welcome to the room " + room.number + " " + room.p1.name + " as player 1"
  }

  override def Leave(presser: Player): String = {
    presser.name + " bruh, stop messin' with me, get the f outta here"
  }

  override def Play(presser: Player): String = {
    presser.name + " bruh, stop messin' with me, get the f outta here"
  }
}

class Waiting(room: Room) extends State(room) {
  override def takeSlots(presser: Player): String = {
    room.p2 = presser
    room.state = new Full(room)
    "welcome to the room " + room.number + " " + room.p2.name + " as player 2"
  }

  override def Leave(presser: Player): String = {
    if (presser == room.p1){
      room.p1 = _
      room.p1_status = "not ready"
      room.state = new Empty(room)
      presser + "has left the room " + room.number
    }
    else {
      presser.name + " bruh, stop messin' with me, get the f outta here"
    }
  }

  override def Play(presser: Player): String = {
    if (presser == room.p1 && room.p1_status == "not ready") {
      room.p1_status = "ready"
      room.p1.name + " (player 1) is ready now"
    }
    else if (presser == room.p1 && room.p1_status == "ready"){
      room.p1_status = "not ready"
      room.p1.name + " (player 1) is not ready now"
    }
    else {
      presser.name + " bruh, stop messin' with me, get the f outta here"
    }
  }
}

class Full(room: Room) extends State(room) {
  override def takeSlots(presser: Player): String = {
    "the room " + room.number + " is full!"
  }

  override def Leave(presser: Player): String = {
    if (presser == room.p1){
      room.p1_status = "not ready"
      room.p2_status = "not ready"
      room.p1 = room.p2
      room.p2 = _
      room.state = new Waiting(room)
      presser + "has left the room " + room.number + " and " + room.p1 + " is player1 now"
    }
    else if (presser == room.p2){
      room.p1_status = "not ready"
      room.p2_status = "not ready"
      room.p2 = _
      room.state = new Waiting(room)
      presser + "has left the room " + room.number
    }
    else {
      presser.name + " bruh, stop messin' with me, get the f outta here"
    }
  }

  override def Play(presser: Player): String = {
    if ((presser == room.p1 || presser == room.p2) && (room.p1_status == "ready" && room.p2_status == "ready")) {
      val game = new Game(room.p1, room.p2)
      val winner: Player = game.Play()  // PLAY

      if (winner == room.p1){
        room.p1.wins += 1
        room.p2.loses += 1
      }
      else if (winner == room.p2){
        room.p1.loses += 1
        room.p2.wins += 1
      }
      room.p1_status = "not ready"
      room.p1_status = "not ready"
      room.p1 = _
      room.p2 = _
      room.state = new Empty(room)

      "Game Over! " + winner.name + " won! Room " + room.number + "is ready for the next game!"
    }
    else if (presser == room.p1 && room.p1_status == "not ready") {
      room.p1_status = "ready"
      room.p1.name + " (player 1) is ready now"
    }
    else if (presser == room.p1 && room.p1_status == "ready"){
      room.p1_status = "not ready"
      room.p1.name + " (player 1) is not ready now"
    }
    else if (presser == room.p2 && room.p2_status == "not ready") {
      room.p2_status = "ready"
      room.p2.name + " (player 2) is ready now"
    }
    else if (presser == room.p2 && room.p2_status == "ready"){
      room.p2_status = "not ready"
      room.p2.name + " (player 2) is not ready now"
    }
    else {
      presser.name + " bruh, stop messin' with me, get the f outta here"
    }
  }
}

