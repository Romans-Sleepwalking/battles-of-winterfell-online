package Characters

class CrusaderEVO1(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: HEAL
  }
  override def Skill3(): Unit ={println("I am too dumb")}
  override def Skill4(): Unit ={println("I am too dumb")}
}

class CrusaderEVO2(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: HEAL
  }
  override def Skill3(): Unit ={
    // TASK: MORALE
  }
  override def Skill4(): Unit ={println("I am too dumb")}
}

class CrusaderEVO3(unit: Character) extends CharacterState(unit) {
  override def Skill1(): Unit ={
    // TASK: ATTACK
  }
  override def Skill2(): Unit ={
    // TASK: HEAL
  }
  override def Skill3(): Unit ={
    // TASK: MORALE
  }
  override def Skill4(): Unit ={
    // TASK: FRENZY
  }
}

