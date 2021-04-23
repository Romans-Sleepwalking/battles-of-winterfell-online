package Host.Characters


class Knight(char: Character) extends State(char) {
  /*
  The Knight state class is a most basic unit lore class
    1st Ability - Attack:   1.0 attack damage
    2nd Ability - Rush:     1.7 attack damage for 15 mana points
  */
  this.char.State = "ok"
  val calc: abilityCalculator = new abilityCalculator(char)
  val dmg: Int = this.calc.haveAttack

  char.abi2_status = "ok"
  char.abi2_name = "Rush"
  char.abi2_description = "Powerful slash"
  char.abi2_target = "enemy"
  char.abi2_effect = "Attacked"
  char.abi2_cost = "15"

  char.abi3_status = "lock"
  char.abi3_name = ""
  char.abi3_description = ""
  char.abi3_target = "nobody"
  char.abi3_effect = ""
  char.abi3_cost = ""

  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_effect = ""
  char.abi4_cost = ""

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }
  override def Skill2(target: Character): String ={
    this.char.MP - this.char.abi2_cost.toInt
    calc.Attack(this.dmg, target, 1.7, "slammed")
  }
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}
  override def checkAbilities(): Unit ={
    this.char.abi1_status = this.calc.checkMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.checkMana(this.char.MP, this.char.abi2_cost.toInt)
  }
}


class Necromancer(val char: Character) extends State(char) {
  /*
  The Necromancer state class is an advanced wizard unit lore class
    1st Ability - Attack:   1.0 attack damage
    2nd Ability - Summon:   resurrects dead unit for 30 mana points
    3rd Ability - Freeze:   resurrects dead unit for 30 mana points
    4th Ability - Critical: 3.5 attack damage for 30 mana points
  */
  this.char.State = "ok"
  val calc: abilityCalculator = new abilityCalculator(char)
  val dmg: Int = this.calc.haveAttack

  char.abi2_status = "ok"
  char.abi2_name = "Summon"
  char.abi2_description = "Animates fallen dead walkers"
  char.abi2_target = "corpse"
  char.abi2_effect = "Passive"
  char.abi2_cost = "30"

  char.abi3_status = "ok"
  char.abi3_name = "Freeze"
  char.abi3_description = "Freezes the enemy"
  char.abi3_target = "enemy"
  char.abi3_effect = "Frozen"
  char.abi3_cost = "40"

  char.abi4_status = "ok"
  char.abi4_name = "Critical"
  char.abi4_description = "Trick-shot"
  char.abi4_target = "enemy"
  char.abi4_effect = "Attacked"
  char.abi4_cost = "70"

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }
  override def Skill2(target: Character): String ={
    this.char.MP - this.char.abi2_cost.toInt
    target.HP = 8 * this.char.INT
    target.MP = 4 * this.char.INT
    target.state = new Knight(target)
    target.Update()
    this.calc.getActionStatement("animated", (4 * this.char.INT).toString, "mana points", target)
  }
  override def Skill3(target: Character): String ={
    this.char.MP - this.char.abi3_cost.toInt
    target.state = new Frozen(target)
    this.calc.getActionStatement("cooled", "frost wind for next 2", "rounds", target)
  }
  override def Skill4(target: Character): String ={
    this.char.MP - this.char.abi4_cost.toInt
    calc.Attack(this.dmg, target, 3.5, "DEVASTATED")
  }
  override def checkAbilities(): Unit ={
    this.char.abi1_status = this.calc.checkMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.checkMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.checkMana(this.char.MP, this.char.abi3_cost.toInt)
    this.char.abi4_status = this.calc.checkMana(this.char.MP, this.char.abi4_cost.toInt)
  }
}


class Crusader(val char: Character) extends State(char) {
  /*
  The Crusader state class is an advanced warrior unit lore class
    1st Ability - Attack:    1.0 attack damage
    2nd Ability - Heal:      heals himself or an ally
    3rd Ability - Morale:    heals and makes more agile the allies for 40 mana points
    4th Ability - Jagerbomb: re-states to angel for 70 mana points
  */
  this.char.State = "ok"
  val calc: abilityCalculator = new abilityCalculator(char)
  val dmg: Int = this.calc.haveAttack

  char.abi2_status = "ok"
  char.abi2_name = "Heal"
  char.abi2_description = ""
  char.abi2_target = "ally"
  char.abi2_effect = "Healed"
  char.abi2_cost = "0"

  char.abi3_status = "ok"
  char.abi3_name = "Morale"
  char.abi3_description = "Cheer up the team"
  char.abi3_target = "team"
  char.abi3_effect = "Blessed"
  char.abi3_cost = "40"

  char.abi4_status = "ok"
  char.abi4_name = "Jägerbomb"
  char.abi4_description = "RedBull gives you wings"
  char.abi4_target = "self"
  char.abi4_effect = "Passive"
  char.abi4_cost = "80"

  override def Skill1(target: Character): String ={
    this.calc.Attack(this.dmg, target)
  }
  override def Skill2(target: Character): String ={
    val heal: Int = 10 * this.char.INT
    target.HP += heal
    this.calc.getActionStatement("healed", heal.toString, "health points", target)
  }
  override def Skill3(target: Character): String ={
    this.char.MP - this.char.abi3_cost.toInt
    this.char.AGI += 8
    this.char.HP += 15
    this.calc.getTimeString() + "The Northern Alliance boosted their morale by 8 AGI, 15 HP and 15 MP!"
  }
  override def Skill4(target: Character): String ={
    this.char.MP - this.char.abi4_cost.toInt
    this.char.state = new Angel(this.char)
    this.calc.getTimeString() + "PFSS GLOG-GLOG-GLOG-GLOG-GLOG-GLOG-GLOG"
  }
  override def checkAbilities(): Unit ={
    this.char.abi1_status = this.calc.checkMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.checkMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.checkMana(this.char.MP, this.char.abi3_cost.toInt)
    this.char.abi4_status = this.calc.checkMana(this.char.MP, this.char.abi4_cost.toInt)
  }
}


class Angel(val char: Character) extends State(char) {
  /*
  The Angel state class is a legendary warrior unit lore class
    1st Ability - Attack:    1.0 attack damage
    2nd Ability - Heal:      heals himself or an ally
    3rd Ability - Morale:    heals and makes more agile the allies for 40 mana points
  */
  this.char.Class = "Angel"
  this.char.State = "ok"
  this.char.maxHP = 999
  this.char.maxMP = 999
  this.char.HP += 50
  this.char.AGI += 20
  this.char.INT += 20
  val calc: abilityCalculator = new abilityCalculator(char)
  val dmg: Int = this.calc.haveAttack

  char.abi2_status = "ok"
  char.abi2_name = "Heal"
  char.abi2_description = ""
  char.abi2_target = "ally"
  char.abi2_effect = "Healed"
  char.abi2_cost = "0"

  char.abi3_status = "ok"
  char.abi3_name = "Bless"
  char.abi3_description = ""
  char.abi3_target = "team"
  char.abi3_effect = "Blessed"
  char.abi3_cost = "40"

  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_effect = ""
  char.abi4_cost = ""

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }
  override def Skill2(target: Character): String ={
    this.char.MP - this.char.abi2_cost.toInt
    val heal: Int = 10 * this.char.INT
    target.HP += heal
    this.calc.getActionStatement("healed", heal.toString, "health points", target)
  }
  override def Skill3(target: Character): String ={
    this.char.MP - this.char.abi3_cost.toInt
    this.char.AGI += 8
    this.char.HP += 15
    this.calc.getTimeString() + "The Northern Alliance boosted their morale by 8 AGI, 15 HP and 15 MP!"
  }
  override def Skill4(target: Character): String = {"lock"}
  override def checkAbilities(): Unit ={
    this.char.abi1_status = this.calc.checkMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.checkMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.checkMana(this.char.MP, this.char.abi3_cost.toInt)
  }
}

class Frozen(char: Character) extends State(char) {
  /*
  The Frozen state class is a debuffed state - requires three 1st skills to unfreeze
    1st Ability - Heart-warm:  1/3 steps to unfreeze
  */
  this.char.State = "Frozen"
  val calc: abilityCalculator = new abilityCalculator(char)
  var round_counter: Int = 2

  char.abi1_status = "ok"
  char.abi1_name = "Heart-warm"
  char.abi1_description = "Think about kittens to unfreeze"
  char.abi1_target = "self"
  char.abi1_effect = "Warmed"
  char.abi1_cost = "0"

  char.abi2_status = "lock"
  char.abi2_name = ""
  char.abi2_description = ""
  char.abi2_target = "nobody"
  char.abi2_effect = ""
  char.abi2_cost = ""

  char.abi3_status = "lock"
  char.abi3_name = ""
  char.abi3_description = ""
  char.abi3_target = "nobody"
  char.abi3_effect = ""
  char.abi3_cost = ""

  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_effect = ""
  char.abi4_cost = ""

  override def Skill1(target: Character): String = {
    this.round_counter -= 1
    if (this.round_counter == 0){
      if (this.char.Class == "Northern Warrior"){
        this.char.state = new Knight(this.char)
      }
      else if (this.char.Class == "Angel"){
        this.char.state = new Angel(this.char)
      }
      else {
        this.char.state = new Crusader(this.char)
      }
    }
    this.calc.getTimeString() + "Meow-meow-meow"
  }
  override def Skill2(target: Character): String = {"lock"}
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}
  override def checkAbilities(): Unit ={}
}


class Dead(char: Character) extends State(char) {
  /*
  The Dead state speaks for itself. The dead man waits to be awaken by Necromancer
  */
  this.char.State = "Dead"
  override def Skill1(target: Character): String = {"lock"}
  override def Skill2(target: Character): String = {"lock"}
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}
  override def checkAbilities(): Unit ={}
}
