package Characters

class NecromancerEVO1(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}

class NecromancerEVO2(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={
    "Shackles"
  }
  override def Skill4(): String ={"locked"}
}

class NecromancerEVO3(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={
    "Shackles"
  }
  override def Skill4(): String ={
    "Critical"
  }
}

