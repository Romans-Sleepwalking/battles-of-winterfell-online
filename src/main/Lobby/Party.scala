package Lobby

import Characters.Character
import Characters.Generator

import scala.collection.mutable

class Party(val name: String, val leader: Character) {
  var group: mutable.Map[Int, Any] = mutable.Map(
    1 -> leader,
    2 -> null,
    3 -> null
  )
  var size: Int = 1

  def generateCrewmates(howMuch: Int): Unit = {
    for (i <- 0 until howMuch) {
      if (this.size == 1) {
        this.group(2) = Generator.Random(this.leader.Crewmates)
        this.size += 1
      }
      else if (this.size == 2) {
        this.group(3) = Generator.Random(this.leader.Crewmates)
        this.size += 1
      }
    }
  }
}
