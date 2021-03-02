package Characters

class State_Knight(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    // TASK: ATTACK
    "Attack"
  }
  override def Skill2(): String ={
    // TASK: RUSH
    "RUSH"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}
