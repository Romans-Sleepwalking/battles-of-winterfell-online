package Characters

class CrusaderLVL1(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "Attack"
  }
  override def Skill2(): String ={
    "Heal"
  }
  override def Skill3(): String ={"locked"}
  override def Skill4(): String ={"locked"}
}

class CrusaderLVL5(unit: Character) extends State(unit) {
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

class CrusaderLVL10(unit: Character) extends State(unit) {
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
    "Awaken"
  }
}

class Angel(unit: Character) extends State(unit) {
  override def Skill1(): String ={
    "UberAttack"
  }
  override def Skill2(): String ={
    "Heal"
  }
  override def Skill3(): String ={
    "Morale"
  }
  override def Skill4(): String ={"locked"}
}

