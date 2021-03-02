package Rooms

import scala.collection.mutable

class Game(val P1: Player, val P2: Player) {

  // SLOTS INIT

  var slots: mutable.Map[Int, Slot] = mutable.Map()

  this.slots += (1 -> new Slot(this.P1.slot1, "Left"))

  if (P1.slotCounter >= 2){
    this.slots += (2 -> new Slot(this.P1.slot2, "Left"))
  }
  if (P1.slotCounter >= 3){
    this.slots += (3 -> new Slot(this.P1.slot3, "Left"))
  }
  if (P1.slotCounter == 4){
    this.slots += (4 -> new Slot(this.P1.slot4, "Left"))
  }

  this.slots += (5 -> new Slot(this.P2.slot1, "Right"))

  if (P2.slotCounter >= 2){
    this.slots += (6 -> new Slot(this.P2.slot2, "Right"))
  }
  if (P2.slotCounter >= 2) {
    this.slots += (7 -> new Slot(this.P2.slot3, "Right"))
  }
  if (P2.slotCounter >= 2){
    this.slots += (8 -> new Slot(this.P2.slot4, "Right"))
  }

  // STEP 2

  def Round(): Unit ={
    for (slot <- slots){
      // TODO
    }
  }

  def Play(): Player ={
    this.P1
  }
}

