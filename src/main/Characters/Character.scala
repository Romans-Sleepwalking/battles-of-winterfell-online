package Characters

class Character(val Name: String,
                val Class: String,     // creature's class to display
                val ATR: String,       // main attribute
                val Crewmates: String, // auto crew gen key
                val BasicModel: String,
                val skill1Model: String,
                val skill2Model: String,
                val skill3Model: String,
                val skill4Model: String,
                val FrozenModel: String,
                val DeadModel: String,
                var STR: Int,  // strength
                var AGI: Int,  // agility
                var INT: Int,  // intelligence
                var LVL: Int) { // level


  var maxHP: Int = this.STR * 5
  var maxMP: Int = this.INT * 5

  if (ATR == "STR") {
    maxMP += this.STR * 3 // STR master bonus
  }
  else if (ATR == "INT") {
    maxMP += this.INT * 3 // INT master bonus
  }

  var HP: Int = this.maxHP // current HP
  var MP: Int = this.maxMP // current MP


  // STATIC METHODS

  def Restore(): Unit = { // fully restores fighter's HP and MP
    this.HP = this.maxHP
    this.MP = this.maxMP
  }

  def Die(): Unit = {
    // TASK
  }

  // STATE METHODS
  def Update(): Unit = {
    // TASK
  }
}

abstract class CharacterState(unit: Character) {
  def Skill1()
  def Skill2()
  def Skill3()
  def Skill4()
}

class Dead(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={println("I am dead")}
  override def Skill2(): Unit ={println("I am dead")}
  override def Skill3(): Unit ={println("I am dead")}
  override def Skill4(): Unit ={println("I am dead")}
}

class Fighter(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: BASH
  }
  override def Skill3(): Unit ={println("I am too dumb")}
  override def Skill4(): Unit ={println("I am too dumb")}
}

class Frozen(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={println("I am frozen")}
  override def Skill2(): Unit ={println("I am frozen")}
  override def Skill3(): Unit ={println("I am frozen")}
  override def Skill4(): Unit ={println("I am frozen")}
}
