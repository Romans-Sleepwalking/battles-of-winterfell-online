package Host.Characters
import java.util.Calendar


class AbilityCalc(val char: Character) {
  def CheckMana(MP: Int, cost: Int): String ={
    if (MP >= cost) {
      "ok"
    }
    else {
      "mana"
    }
  }

  def TimeString(): String = {
    val time = Calendar.getInstance()
    time.get(Calendar.HOUR).toString + ":" + time.get(Calendar.MINUTE).toString + ":" +
    time.get(Calendar.SECOND).toString + " | "
  }

  def ActionStatement(action: String, value: String, units: String, target: Character): String = {
    TimeString() + this.char.Name + " " + action + " " + target.Name + " with " + value + " " +
    units + "! " + target.Name + " " + target.HP.toString + "/" + target.maxHP.toString + " HP left."
  }

  def Attack(dmg: Int, target: Character, multiplier: Double = 1, action: String = "attacked"): String ={
    target.HP -= dmg * multiplier
    target.CheckHealth()
    this.ActionStatement(action, (dmg * multiplier).toString, "damage", target)
  }

  def InitAttack(): Int ={
    this.char.abi1_status = "ok"
    this.char.abi1_name = "Attack"
    this.char.abi1_description = ""
    this.char.abi1_target = "enemy"
    this.char.abi1_cost = "0"
    this.char.abi1_icon = "attack.jpg"
    this.char.AGI * 6 + this.char.STR * 3 + this.char.INT * 2
  }
}


class Knight(char: Character) extends State(char) {
  val calc: AbilityCalc = new AbilityCalc(char)
  val dmg: Int = this.calc.InitAttack()

  char.abi2_status = "ok"
  char.abi2_name = "Rush"
  char.abi2_description = "Powerful slash"
  char.abi2_target = "enemy"
  char.abi2_cost = "15"
  char.abi2_icon = "rush.jpg"

  char.abi3_status = "lock"
  char.abi3_name = ""
  char.abi3_description = ""
  char.abi3_target = "nobody"
  char.abi3_cost = ""
  char.abi3_icon = "lock.jpg"
  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_cost = ""
  char.abi4_icon = "lock.jpg"


  // METHODS

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }

  override def Skill2(target: Character): String ={
    this.char.MP - this.char.abi2_cost
    calc.Attack(this.dmg, target, 1.7, "slammed")
  }
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}

  override def CheckAbilities(): Unit ={
    this.char.abi1_status = this.calc.CheckMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.CheckMana(this.char.MP, this.char.abi2_cost.toInt)
  }
}


class Necromancer(val char: Character) extends State(char) {
  val calc: AbilityCalc = new AbilityCalc(char)
  val dmg: Int = this.calc.InitAttack()

  char.abi2_status = "ok"
  char.abi2_name = "Summon"
  char.abi2_description = "Animates fallen dead walkers"
  char.abi2_target = "corpse"
  char.abi2_cost = "30"
  char.abi2_icon = "summon.jpg"

  char.abi3_status = "ok"
  char.abi3_name = "Freeze"
  char.abi3_description = "Freezes the enemy"
  char.abi3_target = "enemy"
  char.abi3_cost = "40"
  char.abi3_icon = "freeze.jpg"

  char.abi4_status = "ok"
  char.abi4_name = "Critical"
  char.abi4_description = "Trick-shot"
  char.abi4_target = "enemy"
  char.abi4_cost = "70"
  char.abi4_icon = "critical.jpg"


  // METHODS

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }

  override def Skill2(target: Character): String ={
    this.char.MP -= 30
    target.state = new Knight(target)
    target.State = "ok"
    target.Class = "zombie"
    target.HP = 8 * this.char.INT
    target.MP = 4 * this.char.INT
    this.calc.ActionStatement("animated", (4 * this.char.INT).toString, "mana points", target)
  }

  override def Skill3(target: Character): String ={
    this.char.MP -= 40
    target.state = new Frozen(target)
    target.State = "frozen"
    this.calc.ActionStatement("cooled", "frost wind for next 2", "rounds", target)
  }

  override def Skill4(target: Character): String ={
    this.char.MP - this.char.abi4_cost
    calc.Attack(this.dmg, target, 3.5, "DEVASTATED")
  }

  override def CheckAbilities(): Unit ={
    this.char.abi1_status = this.calc.CheckMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.CheckMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.CheckMana(this.char.MP, this.char.abi3_cost.toInt)
    this.char.abi4_status = this.calc.CheckMana(this.char.MP, this.char.abi4_cost.toInt)
  }
}


class Crusader(val char: Character) extends State(char) {
  val calc: AbilityCalc = new AbilityCalc(char)
  val dmg: Int = this.calc.InitAttack()

  char.abi2_status = "ok"
  char.abi2_name = "Heal"
  char.abi2_description = ""
  char.abi2_target = "ally"
  char.abi2_cost = "0"
  char.abi2_icon = "heal.jpg"

  char.abi3_status = "ok"
  char.abi3_name = "Morale"
  char.abi3_description = "Cheer up the team"
  char.abi3_target = "team"
  char.abi3_cost = "40"
  char.abi3_icon = "morale.jpg"

  char.abi4_status = "ok"
  char.abi4_name = "Jägerbomb"
  char.abi4_description = "RedBull gives you wings"
  char.abi4_target = "self"
  char.abi4_cost = "80"
  char.abi4_icon = "jagerbomb.jpg"


  // METHODS

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target)
  }

  override def Skill2(target: Character): String ={
    val heal: Int = 10 * this.char.INT
    target.HP += heal
    this.calc.ActionStatement("healed", heal.toString, "health points", target)
  }

  override def Skill3(target: Character): String ={
    this.char.MP -= 40
    this.char.AGI += 8
    this.char.HP += 15
    this.calc.TimeString() + "The Northern Alliance boosted their morale by 8 AGI, 15 HP and 15 MP!"
  }

  override def Skill4(target: Character): String ={
    this.char.state = new Angel(this.char)
    this.char.maxHP += 30
    this.char.HP += 50
    this.calc.TimeString() + "PFSS GLOG-GLOG-GLOG-GLOG-GLOG-GLOG-GLOG"
  }

  override def CheckAbilities(): Unit ={
    this.char.abi1_status = this.calc.CheckMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.CheckMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.CheckMana(this.char.MP, this.char.abi3_cost.toInt)
    this.char.abi4_status = this.calc.CheckMana(this.char.MP, this.char.abi4_cost.toInt)
  }
}


class Angel(val char: Character) extends State(char) {
  this.char.Class = "Angel"
  val calc: AbilityCalc = new AbilityCalc(char)
  val dmg: Int = this.calc.InitAttack()

  char.abi2_status = "ok"
  char.abi2_name = "Heal"
  char.abi2_description = ""
  char.abi2_target = "ally"
  char.abi2_cost = "0"
  char.abi2_icon = "heal.jpg"

  char.abi3_status = "ok"
  char.abi3_name = "Bless"
  char.abi3_description = ""
  char.abi3_target = "team"
  char.abi3_cost = "40"
  char.abi3_icon = "morale.jpg"

  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_cost = ""
  char.abi4_icon = "lock.jpg"

  // METHODS

  override def Skill1(target: Character): String ={
    calc.Attack(this.dmg, target, 1.5, "Burned")
  }

  override def Skill2(target: Character): String ={
    val heal: Int = 20 * this.char.INT
    target.HP += heal
    this.calc.ActionStatement("healed", heal.toString, "health points", target)
  }

  override def Skill3(target: Character): String ={
    this.char.MP -= 50
    this.char.AGI += 20
    this.char.HP += 30
    this.calc.TimeString() + "The Northern Alliance has been blessed by 20 AGI, 30 HP and 30 MP!"
  }

  override def Skill4(target: Character): String ={"lock"}

  override def CheckAbilities(): Unit ={
    this.char.abi1_status = this.calc.CheckMana(this.char.MP, this.char.abi1_cost.toInt)
    this.char.abi2_status = this.calc.CheckMana(this.char.MP, this.char.abi2_cost.toInt)
    this.char.abi3_status = this.calc.CheckMana(this.char.MP, this.char.abi3_cost.toInt)
  }
}

class Frozen(char: Character) extends State(char) {
  val calc: AbilityCalc = new AbilityCalc(char)
  var round_counter: Int = 2

  char.abi1_status = "ok"
  char.abi1_name = "Heart-warm"
  char.abi1_description = "Think about kittens to unfreeze"
  char.abi1_target = "self"
  char.abi1_cost = "0"
  char.abi1_icon = "kittens.jpg"

  char.abi2_status = "lock"
  char.abi2_name = ""
  char.abi2_description = ""
  char.abi2_target = "nobody"
  char.abi2_cost = ""
  char.abi2_icon = "lock.jpg"
  char.abi3_status = "lock"
  char.abi3_name = ""
  char.abi3_description = ""
  char.abi3_target = "nobody"
  char.abi3_cost = ""
  char.abi3_icon = "lock.jpg"
  char.abi4_status = "lock"
  char.abi4_name = ""
  char.abi4_description = ""
  char.abi4_target = "nobody"
  char.abi4_cost = ""
  char.abi4_icon = "lock.jpg"

  // METHOD

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
    this.calc.TimeString() + "Meow-meow-meow"
  }
  override def Skill2(target: Character): String = {"lock"}
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}
  override def CheckAbilities(): Unit ={}
}


class Dead(char: Character) extends State(char) {
  override def Skill1(target: Character): String = {"lock"}
  override def Skill2(target: Character): String = {"lock"}
  override def Skill3(target: Character): String = {"lock"}
  override def Skill4(target: Character): String = {"lock"}
  override def CheckAbilities(): Unit ={}
}
