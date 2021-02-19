package Characters

class NecromancerEVO1(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: SUMMON
  }
  override def Skill3(): Unit ={println("I am too dumb")}
  override def Skill4(): Unit ={println("I am too dumb")}
}

class NecromancerEVO2(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: SUMMON
  }
  override def Skill3(): Unit ={
    // TASK: SHACKLES
  }
  override def Skill4(): Unit ={println("I am too dumb")}
}

class NecromancerEVO3(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: HEAL
  }
  override def Skill3(): Unit ={
    // TASK: SHACKLES
  }
  override def Skill4(): Unit ={
    // TASK: CRITICAL
  }
}

