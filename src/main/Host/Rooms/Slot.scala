package Host.Rooms

import Host.Characters.Character

class Slot(val unit: Character,
           val side: String) {

  var Ability1: String = "locked"
  var Ability2: String = "locked"
  var Ability3: String = "locked"
  var Ability4: String = "locked"

  val BasicModel: String = "unloaded"
  val HealedModel: String = "unloaded"
  val AttackedModel: String = "unloaded"
  val Skill1Model: String = "unloaded"
  val Skill2Model: String = "unloaded"
  val Skill3Model: String = "unloaded"
  val Skill4Model: String = "unloaded"
  val FrozenModel: String = "unloaded"
  val DeadModel: String = "unloaded"

  var currentModel: String = this.BasicModel

  var state: StateSlot = new Active(this)

  def Dummy(): Unit = {
    this.state.dummy()
  }
}

abstract class StateSlot(slot: Slot) {
  def dummy()
}

class Active(slot: Slot) extends StateSlot(slot) {
  slot.currentModel = slot.BasicModel
  slot.Ability1 = slot.unit.Skill1()
  slot.Ability2 = slot.unit.Skill2()
  slot.Ability3 = slot.unit.Skill3()
  slot.Ability4 = slot.unit.Skill4()

  def dummy(): Unit ={
    // TODO
  }
}

class Dead(slot: Slot) extends StateSlot(slot) {
  slot.currentModel = slot.DeadModel
  slot.Ability1 = "locked"
  slot.Ability2 = "locked"
  slot.Ability3 = "locked"
  slot.Ability4 = "locked"
  def dummy(): Unit ={
    // TODO
  }
}

class Frozen(slot: Slot) extends StateSlot(slot) {
  slot.currentModel = slot.FrozenModel
  slot.Ability1 = "locked"
  slot.Ability2 = "locked"
  slot.Ability3 = "locked"
  slot.Ability4 = "locked"
  def dummy(): Unit ={
    // TODO
  }
}