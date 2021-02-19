package Lobby

import Characters.Character

class Player(var XP: Int, var Hero: Character = null) {

  def newCharacter(): Unit = {
    // new character
  }

  def loadCharacter(): Unit = {
    // load character
  }

  def addXP(gainedXP: Int): Int = {
    this.XP += gainedXP  // add xp
    Hero.LVL = (0.3 * math.sqrt(this.XP / 4)).toInt  // updates level
    Hero.LVL  // returns updated LVL
  }
}
