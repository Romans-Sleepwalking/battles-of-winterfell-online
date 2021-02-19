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
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref"),

    "North Warrior" -> Map(
      "Attribute" -> "STR",
      "Class" -> "Knight",
      "Crewmates" -> "North Warrior",
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref"),

    "North Commander" -> Map(
      "Attribute" -> "STR",
      "Class" -> "Crusader",
      "Crewmates" -> "North Warrior",
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref"),

    "NightKing" -> Map(
      "Name" -> "Night King",
      "Class" -> "Necromancer",
      "Attribute" -> "INT",
      "STR" -> "30",
      "AGI" -> "30",
      "INT" -> "40",
      "LVL" -> "30",
      "Crewmates" -> "Zombie",
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref"),

    "White Walker" -> Map(
      "Class" -> "Necromancer",
      "Attribute" -> "STR",
      "Crewmates" -> "Zombie",
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref"),

    "Zombie" -> Map(
      "Class" -> "Zombie",
      "Attribute" -> "STR",
      "Crewmates" -> "Zombie",
      "BasicModel" -> "/ref",
      "1skillModel" -> "/ref",
      "2skillModel" -> "/ref",
      "3skillModel" -> "/ref",
      "4skillModel" -> "/ref",
      "FrozenModel" -> "/ref",
      "DeadModel" -> "/ref")
  )
}
