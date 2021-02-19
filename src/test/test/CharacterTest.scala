package test

import org.scalatest._

import Lobby.Player
import Characters.Character
import Characters.Generator

class CharacterTest extends FunSuite {

  def printInfo(unit: Character): Unit = {
    println("\nName: " + unit.Name)
    println("Class: " + unit.Class)
    println("ATR: " + unit.ATR)
    println("STR: " + unit.STR)
    println("AGI: " + unit.AGI)
    println("INT: " + unit.INT)
    println("LVL: " + unit.LVL)
    println("HP: " + unit.HP)
    println("MP: " + unit.MP)
    println("maxHP: " + unit.maxHP)
    println("maxMP: " + unit.maxMP)
  }

  test("Test Hero"){

    val player: Player = new Player(1)
    player.newHero("Ceasar", "North Commander")

    // HERO'S INITIALS
    assert(player.XP == 1)
    assert(player.Hero.LVL == 1)
    printInfo(player.Hero)

    // HERO's UPDATED
    player.addXP(1000)
    assert(player.XP == 1001)
    assert(player.Hero.LVL == 4)
    printInfo(player.Hero)
  }

  test("Test Special Character"){

    val special: Character = Generator.Special("John")
    printInfo(special)
  }

  test("Test Random Character"){

    val random: Character = Generator.Random("White Walker")
    printInfo(random)
  }
}