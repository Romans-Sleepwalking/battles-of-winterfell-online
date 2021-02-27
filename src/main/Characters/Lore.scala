package Characters

object Lore {
  def getLore(key: String): Map[String, String] ={
    val characters: Map[String, Map[String, String]] = Map(
      "Snow" -> Map(
        "Name" -> "John Snow",
        "Class" -> "Crusader",
        "Attribute" -> "STR"
      ),
      "Warrior" -> Map(
        "Name" -> "North Warrior",
        "Class" -> "Knight",
        "Attribute" -> "STR"
      ),
      "Commander" -> Map(
        "Name" -> "North Commander",
        "Class" -> "Crusader",
        "Attribute" -> "STR"
      ),
      "King" -> Map(
        "Name" -> "Night King",
        "Class" -> "Necromancer",
        "Attribute" -> "INT"
      ),
      "Walker" -> Map(
        "Name" -> "White Walker",
        "Class" -> "Necromancer",
        "Attribute" -> "STR"
      ),
      "Zombie" -> Map(
        "Name" -> "White Walker",
        "Class" -> "Knight",
        "Attribute" -> "STR",
      )
    )
    characters(key)
  }

  // generate character
  def Generate(key: String, LVL: Int, STR: Int = 15, AGI: Int = 15, INT: Int = 15): Character = {
    val lore = getLore(key)

    new Character(  // returns initialized Character object's reference
      lore("Name"),
      lore("Class"),
      LVL,
      lore("Attribute"),
      10,
      10,
      10,
      key
    )
  }
}
