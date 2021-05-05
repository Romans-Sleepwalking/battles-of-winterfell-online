package main.Host.Characters

import java.util.Calendar

class abilityCalculator(val char: Character) {
  /*
  The Ability Calculator class save space by gathering common ability methods of characters' state classes:
    checkMana:           checks if character has enough mana to fulfill an ability's mana cost
    getTimeString:       returns a current time as a string
    getActionStatement:  returns a statement of performed actions as a string
    Attack:              performs the most basic ability of almost all state classes
    haveAttack:          initialize attack as 1st skill + calculates the base damage
  */
  def checkMana(MP: Int, cost: Int): String ={
    if (MP >= cost) {
      "ok"
    }
    else {
      "mana"
    }
  }

  def getTimeString(): String = {
    val time = Calendar.getInstance()
    time.get(Calendar.HOUR).toString + ":" + time.get(Calendar.MINUTE).toString + ":" + time.get(Calendar.SECOND).toString + " | "
  }

  def getActionStatement(action: String, value: String, units: String, target: Character): String = {
    getTimeString() + this.char.name + " " + action + " " + target.name + " with " + value + " " +
      units + "! " + target.name + " " + target.HP.toString + "/" + target.maxHP.toString + " HP left."
  }

  def Attack(dmg: Int, target: Character, multiplier: Double = 1, action: String = "attacked"): String ={
    target.HP -= (dmg * multiplier).toInt
    target.checkHealth()
    this.getActionStatement(action, (dmg * multiplier).toString, "damage", target)
  }

  def haveAttack: Int ={
    this.char.abi1_status = "ok"
    this.char.abi1_name = "Attack"
    this.char.abi1_description = ""
    this.char.abi1_target = "enemy"
    this.char.abi1_effect = "Attacked"
    this.char.abi1_cost = "0"
    this.char.AGI * 6 + this.char.STR * 3 + this.char.INT * 2
  }
}
