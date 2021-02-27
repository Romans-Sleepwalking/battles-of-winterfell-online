package Characters

class NecromancerLVL1(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}

class NecromancerLVL5(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={
    "Freeze"
  }
  override def Skill4(): String ={"locked"}
}

class NecromancerLVL10(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Summon"
  }
  override def Skill3(): String ={
    "Freeze"
  }
  override def Skill4(): String ={
    "Critical"
  }
}

