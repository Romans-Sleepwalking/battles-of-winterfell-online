package test

import Characters.{Character, Generator}
import Rooms.{Party, Player}
import org.scalatest._

class PartyTest extends FunSuite {

  def printInfo(party: Party): Unit = {
    println("\nGroup: " + party.name)
    println("Leader: " + party.leader.Name)
    println("Member quantity: " + party.size + "\n")
    /*
    for (key <- party.group.keys) {
      if (party.group(key) != null) {
        println(character.Name + " [" + character.Class + "], HP: " + character.HP + ", MP: " + character.MP)
      }
    }
    */
  }

  test("Test Party") {

    val player: Player = new Player(4000)
    player.newHero("Thormund", "North Commander")

    val northArmy: Party = new Party("North Army", player.Hero)
    northArmy.generateCrewmates(1)
    assert(northArmy.size == 2)

    val undeadArmy: Party = new Party("The Undeads", Generator.Special("Night King"))
    undeadArmy.generateCrewmates(2)
    assert(undeadArmy.size == 3)

    printInfo(northArmy)
    printInfo(undeadArmy)
  }
}