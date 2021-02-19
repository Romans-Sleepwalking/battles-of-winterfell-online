package Lobby

import Characters.{Generator, Character}

class Player(var XP: Int = 1, var Hero: Character = null) {

  def newHero(name: String, Class: String): Unit = {
    this.Hero = Generator.Hero(name, Class)
  }

  def loadHero(): Unit = {
    // load character
  }

  def addXP(gainedXP: Int): Int = {
    this.XP += gainedXP  // add xp
    val updatedLVL: Int = (0.3 * math.sqrt(this.XP / 4)).toInt  // updates level

    if (updatedLVL > Hero.LVL) {  // updates stats if hero levels up
      Hero.LVL = updatedLVL
      Hero.STR = Hero.LVL
      Hero.AGI = Hero.LVL
      Hero.INT = Hero.LVL
      Hero.HealthMana()
      Hero.Restore()
    }
    Hero.LVL  // returns LVL
  }
}
