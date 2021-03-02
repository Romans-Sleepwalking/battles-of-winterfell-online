package test

import Characters.{Character, Generator}
import Rooms.{Party, Player}
import org.scalatest._

class ClashTest extends FunSuite {
/*
  def printInfo(party: Party): Unit = {
    println("\nGroup: " + party.name)
    println("Leader: " + party.leader.Name)
    println("Member quantity: " + party.group.length + "\n")

    for (character <- party.group) {
      println(character.Name + " [" + character.Class + "], HP: " + character.HP + ", MP: " + character.MP)
    }
  }

  test("Test Party") {

    val player: Player = new Player(4000)
    player.newHero("Thormund", "North Commander")

    val northArmy: Party = new Party("North Army", player.Hero)
    northArmy.generateCrewmates(2)
    assert(northArmy.group.length == 3)

    val undeadArmy: Party = new Party("The Undeads", Generator.Special("Night King"))
    undeadArmy.generateCrewmates(10)
    assert(undeadArmy.group.length == 11)

    printInfo(northArmy)
    printInfo(undeadArmy)
  }
  */
}
