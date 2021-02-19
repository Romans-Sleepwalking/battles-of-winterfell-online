package Characters

class CrusaderEVO1(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Heal"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}

class CrusaderEVO2(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Heal"
  }
  override def Skill3(): String ={
    "Morale"
  }
  override def Skill4(): String ={"locked"}
}

class CrusaderEVO3(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Heal"
  }
  override def Skill3(): String ={
    "Morale"
  }
  override def Skill4(): String ={
    "Frenzy"
  }
}

