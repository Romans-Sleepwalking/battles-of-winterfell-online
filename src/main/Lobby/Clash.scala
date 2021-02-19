package Lobby

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Clash(Invaders: Party, Defenders: Party) {
  val fight3: Map[String, mutable.Map[Int, Any]] = Map(
    "Invaders" -> Invaders.group,
    "Defenders" -> Defenders.group
  )
  var fight: mutable.Map[String, ListBuffer[Any]] = mutable.Map(
    "R1" -> mutable.ListBuffer(null, "active"),
    "R2" -> mutable.ListBuffer(null, "active"),
    "R3" -> mutable.ListBuffer(null, "active"),
    "L1" -> mutable.ListBuffer(null, "active"),
    "L2" -> mutable.ListBuffer(null, "active"),
    "L3" -> mutable.ListBuffer(null, "active")
  )

  for (unit_key <- Invaders.group.keys){
    if (Invaders.group(unit_key) != null){
      //
    }
  }

  def Round(): Unit = {
    def Turn(unit: Character): Unit ={
      def Action(): Unit ={
        // Perform action
        // Kill?
      }
      // Choose action
      // Choose target
    }
    for (key <- this.fight.keys){
      //if (fight(key)){

      //}
    }
  }
  def Kill(): Unit ={

  }
}
