package Host.Database

import akka.actor.Actor
import play.api.libs.json.JsValue
import Host.{SignUp, Login}


class DatabaseActor() extends Actor {
  def TODO(): Int = {
    4
  }
  /*
  val DB: MySQLDatabase = new MySQLDatabase

  /*
  val database: Database = dbType match {
    case "mySQL" => new MySQLDatabase()
    case "test" => new TestDatabase()
  }*/

  override def receive: Receive = {
    case received: LoadGame =>
      DB.Load(received.game)

    case received: SaveGame =>
      DB.Save(received.game)
  }
  */
  def receive: Receive = {

    case SignUp(regData: JsValue) =>
      // TODO

    case Login(regData: JsValue) =>
    // TODO
  }
}

