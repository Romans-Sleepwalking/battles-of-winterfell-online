package Rooms

import Characters.Character

object Dummies {
  val dummyDataset: Map[String, Map[String, String]] = Map(
    "user" -> Map(
      "username" -> "Dummy",
      "wins" -> "0",
      "loses" -> "0"
    ),
    "slot1" -> Map(
      "key" -> "Snow",
      "name" -> "Dummy",
      "lvl" -> "1",
      "str" -> "1",
      "agi" -> "1",
      "int" -> "1"
    )
  )
  def InitDummy(): Player ={
    new Player(dummyDataset)
  }
}
