package Characters

object createCharacter {
  def getLore(key: String): Map[String, String] ={
    val characters: Map[String, Map[String, String]] = Map(
      "Snow" -> Map(
        "name" -> "John Snow",
        "class" -> "Crusader",
        "attribute" -> "STR"
      ),
      "Warrior" -> Map(
        "name" -> "North Warrior",
        "class" -> "Knight",
        "attribute" -> "STR"
      ),
      "Commander" -> Map(
        "name" -> "North Commander",
        "class" -> "Crusader",
        "attribute" -> "STR"
      ),
      "King" -> Map(
        "name" -> "Night King",
        "class" -> "Necromancer",
        "attribute" -> "INT"
      ),
      "Walker" -> Map(
        "name" -> "White Walker",
        "class" -> "Necromancer",
        "attribute" -> "STR"
      ),
      "Zombie" -> Map(
        "name" -> "White Walker",
        "class" -> "Knight",
        "attribute" -> "STR",
      )
    )
    characters(key)
  }

  // generate character
  def Initialize(dataSet: Map[String, String]): Character = {
    val lore = getLore(dataSet("key"))  // additional game data

    // returns initialized Character object's reference
    new Character(
      dataSet("name"),       // displayed name
      lore("class"),         // displayed class
      dataSet("lvl").toInt,  // displayed level
      lore("attribute"),     // main attribute
      dataSet("str").toInt,  // strength value
      dataSet("agi").toInt,  // agility value
      dataSet("int").toInt,  // intelligence value
      dataSet("key")       // character's model reference
    )
  }
}