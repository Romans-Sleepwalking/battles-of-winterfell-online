package Host.Characters

import Host.Lobby.Player

class Character(
    val owner: Player,
    val name: String,
    val img: String,
    var Class: String,
    var STR: Int,
    var AGI: Int,
    var INT: Int,
    var State: String = "passive",
    var next: Character = null
               ){
  /*
  The Character class represents the playable unit-creature itself - the living-object which has health, abilities, etc.
  Every Player has four characters

  Every character has:
    ·)  health and mana points: HP, MP
    ·)  maximum health and mana point limits: maxHP, maxMP
    ·)  lore Class: Knight, Crusader or Necromancer
    ·)  name and img code to directly access the correct image folder
    ·)  three attributes which will be used to calculate ability damage: STR, AGI, INT

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
  var maxHP: Int = this.STR * 10
  var maxMP: Int = this.INT * 10
  var HP: Int = this.maxHP // initial current HP
  var MP: Int = this.maxMP // initial current MP

  var abi1_status: String = _
  var abi1_name: String = _
  var abi1_description: String = _
  var abi1_target: String = _
  var abi1_effect: String = _
  var abi1_cost: String = _

  var abi2_status: String = _
  var abi2_name: String = _
  var abi2_description: String = _
  var abi2_target: String = _
  var abi2_effect: String = _
  var abi2_cost: String = _

  var abi3_status: String = "lock"
  var abi3_name: String = ""
  var abi3_description: String = ""
  var abi3_target: String = "nobody"
  var abi3_effect: String = _
  var abi3_cost: String = ""

  var abi4_status: String = "lock"
  var abi4_name: String = ""
  var abi4_description: String = ""
  var abi4_target: String = "nobody"
  var abi4_effect: String = _
  var abi4_cost: String = ""


  // BASIC METHODS

  def Restore(recharge: Boolean = true, heal: Boolean = true): Unit = {
    if (recharge) {
      this.MP = this.maxMP  // restores MP
    }
    if (heal) {
      this.HP = this.maxHP  // restores HP
    }
  }

  def checkHealth(): Unit = {
    if (this.HP <= 0){
      this.HP = 0
      this.MP = 0
      this.State = "dead"
      this.state = new Dead(this)
    }
  }


  // STATE METHODS


  def StateMe(): State ={
    if (this.Class == "Knight") {  // KNIGHT
      new Knight(this)
    }
    else if (this.Class == "Necromancer") {  // NECROMANCER
      new Necromancer(this)
    }
    else {  // CRUSADER
      new Crusader(this)
    }
  }


  // STATE METHODS


  def Skill1(target: Character): String = {
    this.state.Skill1(target)
  }
  def Skill2(target: Character): String = {
    this.state.Skill2(target)
  }
  def Skill3(target: Character): String = {
    this.state.Skill3(target)
  }
  def Skill4(target: Character): String = {
    this.state.Skill4(target)
  }
  def CheckAbilities(): Unit = {
    this.state.CheckAbilities()
  }


  // INIT CONTINUES


  var state: State = StateMe()
  CheckAbilities()
}

abstract class State(char: Character) {
  def Skill1(target: Character): String
  def Skill2(target: Character): String
  def Skill3(target: Character): String
  def Skill4(target: Character): String
  def CheckAbilities()
}

