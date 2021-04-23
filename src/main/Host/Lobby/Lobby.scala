 package Host.Lobby

 import Host.Server.Message


class Lobby(val number: Int) {
  /*
  The Lobby class represents the players matching system of the MMO server.
  The first Lobby will be created then the server started.
  The others Lobby will be created then the other server Lobbies are full or abandoned.

  The Lobby is represented by State Pattern:
    1) EMPTY State:
      ·) state = 'empty'
      ·) method Join assigns a new Player as P1  ->  moves to the WAITING

    2) WAITING State:
      ·) state = 'waiting'
      ·) method Join assigns a new Player as P2  ->  moves to the FULL
      ·) method Leave removes a P1               ->  moves to the EMPTY

    3) FULL State:
      ·) state = 'full'
      ·) method Leave assigns a lose to the player left  ->  moves to the ABANDONED
      ·) creates a new Game

    4) ABANDONED State:
      ·) state = 'abandoned'
  */

  var P1: Player = _
  var P2: Player = _

  // STATE PATTERN

  var State: String = _
  var state: State = new Empty(this)

  def Join(P: Player): Unit = {
    this.state.Join(P)
  }
  def Leave(P: Player): Unit = {
    this.state.Leave(P)
  }
}


abstract class State(lobby: Lobby) {
  def Join(P: Player): Unit
  def Leave(P: Player): Unit
}


class Empty(lobby: Lobby) extends State(lobby) {
  lobby.State = "empty"

  override def Join(P: Player): Unit = {
    lobby.P1 = P
    lobby.P1.side = "L"
    Message.One(lobby.P1, "Welcome to the room " + lobby.number + ", " + lobby.P1.name + "!")
    lobby.state = new Waiting(lobby)
  }
  override def Leave(caller: Player): Unit = {
    println("Command 'Leave' from " + caller.name + " does not make sense! The room is empty!")
  }
}


class Waiting(lobby: Lobby) extends State(lobby) {
  lobby.State = "waiting"
  Message.One(lobby.P1, lobby.P1.name + ", please, wait kindly for Player-2.")

  override def Join(P: Player): Unit = {
    lobby.P2 = P
    lobby.P2.side = "R"
    Message.One(lobby.P1, "Player 2 has joined! Say \"hello\" to " + lobby.P2.name + ".")
    Message.One(lobby.P2, "Welcome to the room " + lobby.number + ", " + lobby.P2.name + "! Say \"hello\" to " + lobby.P1.name)
    lobby.state = new Full(lobby)
  }
  override def Leave(P1: Player): Unit = {
    println("Room " + lobby.number + " is empty again! " + P1.name + " has left!")
    lobby.state = new Empty(lobby)
  }
}


class Full(lobby: Lobby) extends State(lobby) {
  lobby.State = "full"
  Message.Both(lobby.P1, lobby.P2, "The game begins!")

  val game = new Game(this.lobby.P1, this.lobby.P2)  // Game Launch!

  override def Join(caller: Player): Unit = {
    println("Command 'Leave' from " + caller.name + " does not make sense! The room is full!")
  }
  override def Leave(leaver: Player): Unit = {
    if (leaver == lobby.P1){
      Message.One(lobby.P2, "Congratulations " + lobby.P2.name + ", you won because the " + lobby.P1.name + " has fled away in tears!")
    }
    else if (leaver == lobby.P2){
      Message.One(lobby.P1, "Congratulations " + lobby.P1.name + ", you won because the " + lobby.P2.name + " has fled away in tears!")
    }
    lobby.state = new Abandoned(lobby)
  }
}


 class Abandoned(lobby: Lobby) extends State(lobby) {
   lobby.State = "abandoned"

   override def Join(caller: Player): Unit = {
     println("Command 'Join' from " + caller.name + " does not make sense! The room has been abandoned!")
   }
   override def Leave(caller: Player): Unit = {
     println("Command 'Leave' from " + caller.name + " does not make sense! The room has been abandoned!")
   }
 }