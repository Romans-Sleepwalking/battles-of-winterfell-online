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
    var State: String = "Passive",
    var next: Character = null
               ){
  /*
  The Character class represents the playable unit-creature itself - the living-object which has health, abilities, etc.
  Every Player has four characters

  Character has parameters:
    ·)  health and mana points: HP, MP
    ·)  maximum health and mana point limits: maxHP, maxMP
    ·)  lore Class: Knight, Crusader or Necromancer
    ·)  name and img code to directly access the correct image folder
    ·)  three attributes which will be used to calculate ability damage: STR, AGI, INT

  Character abilities has been directed inside lore Classes

  Character has methods:
    ·)  checkHealth:  to self-check if he/she is not dead
    ·)  stateMe:  to self-re-state from Dead or Frozen states
    ·)  checkAbilities:   to update the abilities
    ·)  Update:   calls stateMe and checkAbilities
    ·)  Skill1:   basic
    ·)  Skill2:   intermediate
    ·)  Skill3:   advanced
    ·)  Skill4:   ultimate
  */
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

  var abi3_status: String = _
  var abi3_name: String = _
  var abi3_description: String = _
  var abi3_target: String = _
  var abi3_effect: String = _
  var abi3_cost: String = _

  var abi4_status: String = _
  var abi4_name: String = _
  var abi4_description: String = _
  var abi4_target: String = _
  var abi4_effect: String = _
  var abi4_cost: String = _

  var maxHP: Int = this.STR * 10
  var maxMP: Int = this.INT * 10
  var HP: Int = this.maxHP // initial current HP
  var MP: Int = this.maxMP // initial current MP

  def checkHealth(): Unit = {
    if (this.HP <= 0){
      this.HP = 0
      this.MP = 0
      this.state = new Dead(this)
    }
  }

  // STATE METHODS

  def stateMe(): State ={
    if (this.Class == "Knight") {
      new Knight(this)
    }
    else if (this.Class == "Necromancer") {
      new Necromancer(this)
    }
    else {
      new Crusader(this)
    }
  }

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
  def checkAbilities(): Unit = {
    this.state.checkAbilities()
  }

  var state: State = stateMe()  // Init state
  this.checkAbilities()

  def Update(): Unit = {
    this.state = stateMe()
    this.checkAbilities()
  }
}

abstract class State(char: Character) {
  def Skill1(target: Character): String
  def Skill2(target: Character): String
  def Skill3(target: Character): String
  def Skill4(target: Character): String
  def checkAbilities()
}

