package test

import main.Host.Characters.Character
import main.Host.Lobby.targetFinder.getTargets
import main.Host.Lobby.{Game, Player, targetFinder}
import main.Host.Server.Message.playerToJSON
import org.scalatest._
import play.api.libs.json.{JsValue, Json}

class testJSON extends FunSuite {
    test("Testing JSON update content") {

      val P1: Player = new Player("Mike")
      val P2: Player = new Player("Jake")
      val game: Game = new Game(P1, P2)


      def makeTest(num: Int, name: String, target: String, char: Character, game: Game): String ={
          println(num.toString + ") Ability: " + name + "; Target: " + target + "; char class: " + char.Class + "; side: " + char.owner.side)
          getTargets(target, char, game).mkString("Array(", ", ", ")")
      }

      P1.char4.State = "Dead"
      P2.char4.State = "Dead"

      val targets1: String = makeTest(1, P1.char1.abi1_name, P1.char1.abi1_target, P1.char1, game)
      println(targets1 + "\n")

      val targets2: String = makeTest(2, P1.char1.abi2_name, P1.char1.abi2_target, P1.char1, game)
      println(targets2 + "\n")

      val targets3: String = makeTest(3, P2.char1.abi2_name, P2.char1.abi2_target, P2.char1, game)
      println(targets3 + "\n")

      val targets4: String = makeTest(4, P2.char1.abi3_name, P2.char1.abi3_target, P2.char1, game)
      println(targets4 + "\n")

      val targets5: String = makeTest(5, P2.char1.abi4_name, P2.char1.abi4_target, P2.char1, game)
      println(targets5 + "\n")

      val jsonUpdate: JsValue = Json.obj(
        "P1" -> playerToJSON(game.P1, game),
        "P2" -> playerToJSON(game.P2, game)
      )
      println(jsonUpdate)
    }
}
