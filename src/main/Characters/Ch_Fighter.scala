package Characters

class Fighter(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    // TASK: ATTACK
    "Attack"
  }
  override def Skill2(): String ={
    // TASK: BASH
    "Bash"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}
