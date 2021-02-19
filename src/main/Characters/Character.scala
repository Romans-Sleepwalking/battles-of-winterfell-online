package Characters

class Character(val Name: String,
                val Class: String,     // creature's class to display
                val ATR: String,       // main attribute
                val Crewmates: String, // auto crew gen key
                var STR: Int,  // strength
                var AGI: Int,  // agility
                var INT: Int,  // intelligence
                var LVL: Int,  // level
                val modelReference: String){  // model path reference


  // STATIC PARAMETERS AND METHOD

  var maxHP: Int = 1
  var maxMP: Int = 1

  def HealthMana() {
    if (ATR == "STR") {  // STR master has more health
      maxHP = this.STR * 8
      maxMP = this.INT * 4
    }
    else if (ATR == "INT") {  // INT master has more mana
      maxHP = this.STR * 4
      maxMP = this.INT * 8
    }
  }

  HealthMana() // initial health and mana
  var HP: Int = this.maxHP // initial current HP
  var MP: Int = this.maxMP // initial current MP


  // DYNAMIC METHODS

  def Restore(): Unit = { // fully restores fighter's HP and MP
    this.HP = this.maxHP
    this.MP = this.maxMP
  }

  // STATE METHODS

  var state: State = new Dummy(this)

  def Skill1(): String = {
    this.state.Skill1()
  }
  def Skill2(): String = {
    this.state.Skill2()
  }
  def Skill3(): String = {
    this.state.Skill3()
  }
  def Skill4(): String = {
    this.state.Skill4()
  }
}

abstract class State(unit: Character) {
  def Skill1(): String
  def Skill2(): String
  def Skill3(): String
  def Skill4(): String
}

class Dummy(unit: Character) extends State(unit) {
  override def Skill1(): String ={"locked"}
  override def Skill2(): String ={"locked"}
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}

