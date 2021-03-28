package Host.Characters

class Character(
    val Name: String,
    var Class: String,
    var STR: Int,
    var AGI: Int,
    var INT: Int,
    var State: String = "ok",
    var IMG: String = "passive"
               ){
  var maxHP: Int = this.STR * 10
  var maxMP: Int = this.INT * 10
  var HP: Int = this.maxHP // initial current HP
  var MP: Int = this.maxMP // initial current MP

  var abi1_status: String = _
  var abi1_name: String = _
  var abi1_description: String = _
  var abi1_target: String = _
  var abi1_cost: String = _
  var abi1_icon: String = _

  var abi2_status: String = _
  var abi2_name: String = _
  var abi2_description: String = _
  var abi2_target: String = _
  var abi2_cost: String = _
  var abi2_icon: String = _

  var abi3_status: String = "lock"
  var abi3_name: String = ""
  var abi3_description: String = ""
  var abi3_target: String = "nobody"
  var abi3_cost: String = ""
  var abi3_icon: String = "lock.jpg"

  var abi4_status: String = "lock"
  var abi4_name: String = ""
  var abi4_description: String = ""
  var abi4_target: String = "nobody"
  var abi4_cost: String = ""
  var abi4_icon: String = "lock.jpg"


  // BASIC METHODS


  def Restore(recharge: Boolean = true, heal: Boolean = true): Unit = {
    if (recharge) {
      this.MP = this.maxMP  // restores MP
    }
    if (heal) {
      this.HP = this.maxHP  // restores HP
    }
  }

  def CheckHealth(): Unit = {
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
    else { // CRUSADER
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

