package Characters

object Generator {

  def Random(Name: String): Character = {
    val lore = Lore.characters(Name)

    new Character( // returns created Character class object reference
      Name,
      lore("Class"),
      lore("Attribute"),
      lore("Crewmates"),
      lore("BasicModel"),
      lore("1skillModel"),
      lore("2skillModel"),
      lore("3skillModel"),
      lore("4skillModel"),
      lore("FrozenModel"),
      lore("DeadModel"),
      6,
      6,
      6,
      6
    )
  }

  // special plot character from lore database
  def Special(name: String): Character = {
    val lore = Lore.characters(name)

    new Character( // returns created Character class object reference
      lore("Name"),
      lore("Class"),
      lore("Attribute"),
      lore("Crewmates"),
      lore("BasicModel"),
      lore("1skillModel"),
      lore("2skillModel"),
      lore("3skillModel"),
      lore("4skillModel"),
      lore("FrozenModel"),
      lore("DeadModel"),
      lore("STR").toInt,
      lore("AGI").toInt,
      lore("INT").toInt,
      lore("LVL").toInt
    )
  }
}
