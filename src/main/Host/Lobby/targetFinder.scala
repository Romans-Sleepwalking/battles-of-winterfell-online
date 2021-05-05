package main.Host.Lobby

import main.Host.Characters.Character

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object targetFinder {
  def getTargets(targetType: String, char: Character, game: Game): Array[Int] ={
    var targets: mutable.ListBuffer[Int] = mutable.ListBuffer()

    if (targetType == "self"){
      targets += game.idByCharacter(char)
    }
    else if ((targetType == "corpse" || targetType == "ally") & char.owner.side == "L"){
      targets += (1, 2, 3, 4)
    }
    else if (targetType == "enemy" & char.owner.side == "L"){
      targets += (5, 6, 7, 8)
    }
    else if ((targetType == "corpse" || targetType == "ally") & char.owner.side == "R"){
      targets += (5, 6, 7, 8)
    }
    else if (targetType == "enemy" & char.owner.side == "R"){
      targets += (1, 2, 3, 4)
    }
    for (id <- targets){
      if (targetType == "corpse" && game.charByID(id).State != "Dead"){
        targets -= id
      }
      if ((targetType == "enemy" || targetType == "ally") && game.charByID(id).State == "Dead") {
        targets -= id
      }
    }
    targets.toArray
  }
}
