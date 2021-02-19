package Characters

object Generator {

  def Random(Class: String): Character = {
    val lore = Lore.characters(Class)

    new Character( // returns created Character class object reference
      Class,
      lore("Class"),
      lore("Attribute"),
      lore("Crewmates"),
      6,
      6,
      6,
      6,
      lore("ModelRef")
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
      lore("STR").toInt,
      lore("AGI").toInt,
      lore("INT").toInt,
      lore("LVL").toInt,
      lore("ModelRef")
    )
  }

  def Hero(name: String, Class: String): Character = {
    val lore = Lore.characters(Class)

    new Character( // returns created Character class object reference
      name,
      lore("Class"),
      lore("Attribute"),
      lore("Crewmates"),
      1,
      1,
      1,
      1,
      lore("ModelRef")
    )
  }
}
