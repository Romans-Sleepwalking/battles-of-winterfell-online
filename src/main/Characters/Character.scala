package Characters

class Character(val Name: String,        // displayed name
                val Class: String,       // displayed class
                val LVL: Int,            // displayed level
                val ATR: String,         // main attribute
                var STR: Int,            // strength
                var AGI: Int,            // agility
                var INT: Int,            // intelligence
                val modelPath: String){  // model path


  // INITIALIZATION


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

  HealthMana() // calculates health and mana
  var HP: Int = this.maxHP // initial current HP
  var MP: Int = this.maxMP // initial current MP


  // DYNAMIC METHODS


  def Restore(energize: Boolean = true, heal: Boolean = true): Unit = {
    if (energize) {
      this.MP = this.maxMP  // restores MP
    }
    if (heal) {
      this.HP = this.maxHP  // restores HP
    }
  }

  def Restate(): State = {
    if (this.Class == "Knight") {  // KNIGHT
      new State_Knight(this)
    }
    else if (this.Class == "Crusader") {  // CRUSADER
      if (LVL < 5){
        new CrusaderLVL1(this)
      }
      else if (LVL > 9){
        new CrusaderLVL10(this)
      }
      else {
        new CrusaderLVL5(this)
      }
      new State_Knight(this)
    }
    else {  // NECROMANCER
      if (LVL < 5) {
        new NecromancerLVL1(this)
      }
      else if (LVL > 9) {
        new NecromancerLVL10(this)
      }
      else {
        new NecromancerLVL5(this)
      }
    }
  }


  // STATE METHODS


  var state: State = Restate()

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

