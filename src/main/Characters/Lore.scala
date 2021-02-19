package Characters

object Lore {
  val characters: Map[String, Map[String, String]] = Map(
    "John" -> Map(
      "Name" -> "John Snow",
      "Class" -> "Crusader",
      "Attribute" -> "STR",
      "STR" -> "25",
      "AGI" -> "30",
      "INT" -> "5",
      "LVL" -> "18",
      "Crewmates" -> "North Warrior",
      "ModelRef" -> "/ref"),

    "North Warrior" -> Map(
      "Attribute" -> "STR",
      "Class" -> "Knight",
      "Crewmates" -> "North Warrior",
      "ModelRef" -> "/ref"),

    "North Commander" -> Map(
      "Attribute" -> "STR",
      "Class" -> "Crusader",
      "Crewmates" -> "North Warrior",
      "ModelRef" -> "/ref"),

    "Night King" -> Map(
      "Name" -> "Night King",
      "Class" -> "Necromancer",
      "Attribute" -> "INT",
      "STR" -> "30",
      "AGI" -> "30",
      "INT" -> "40",
      "LVL" -> "30",
      "Crewmates" -> "Zombie",
      "ModelRef" -> "/ref"),

    "White Walker" -> Map(
      "Class" -> "Necromancer",
      "Attribute" -> "STR",
      "Crewmates" -> "Zombie",
      "ModelRef" -> "/ref"),

    "Zombie" -> Map(
      "Class" -> "Knight",
      "Attribute" -> "STR",
      "Crewmates" -> "Zombie",
      "ModelRef" -> "/ref"),
  )
}
