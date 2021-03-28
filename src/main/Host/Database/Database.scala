package Host.Database

import java.sql.{Connection, DriverManager, ResultSet}
import java.sql.ResultSet
/*

trait DB {
  def Login(username: String, password: String): Map[String, String]
  def Register(username: String, password: String): Map[String, String]
  def updateSlot(slot_id: String, character: Character)
  def incrementWins(id: String)
  def incrementLoses(id: String)
}


class Database extends DB {
  val url: String = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username: String = "root"
  val password: String = "root"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  override def Login(username: String, password: String): Map[String, String] = {
    val getUserStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username=? AND password=?")
    getUserStatement.setString(1, username)
    getUserStatement.setString(2, password)
    val userData: ResultSet = getUserStatement.executeQuery()  // executes: get account data by username and password

    if (userData.next()) {  // if query has returned something: returns full data Map with positive response
      Map(
        "success?" -> "yes",
        "account_id" -> userData.getString("account_id"),
        "wins" -> userData.getString("wins"),
        "loses" -> userData.getString("loses"),
        "slot1_id" -> userData.getString("slot1_id"),
        "slot2_id" -> userData.getString("slot2_id"),
        "slot3_id" -> userData.getString("slot3_id"),
        "slot4_id" -> userData.getString("slot4_id"),
      )
    }
    else {  // else: returns empty data Map with negative response
      Map("success?" -> "no")
    }
  }

  override def Register(username: String, password: String): Map[String, String] ={
    val checkUsernameStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username=?")
    checkUsernameStatement.setString(1, username)
    val existence: ResultSet = checkUsernameStatement.executeQuery()  // executes: get account data by username

    if (!existence.next()){  // if the returned dataset by username is empty: creates a new username
      val registerAccountStatement = connection.prepareStatement(
        "INSERT INTO accounts (`username`, `password`) VALUES (username=?, password=?); SELECT LAST_INSERT_ID()")
      registerAccountStatement.setString(1, username)
      registerAccountStatement.setString(2, password)
      val newAccountID: ResultSet = registerAccountStatement.executeQuery()  // executes: record new account and return new ID

      Map(
        "success?" -> "yes",
        "account_id" -> newAccountID.getString("account_id"),
        "wins" -> "0",
        "loses" -> "0",
        "slot1_id" -> "none",
        "slot2_id" -> "none",
        "slot3_id" -> "none",
        "slot4_id" -> "none",
      )
    } else { // else: declines registration
      Map("success?" -> "no")
    }
  }

  override def updateSlot(slot_id: String, character: Character): Unit ={
    // TODO
  }

  override def incrementWins(id: String): Unit ={
    val updateWinsStatement = connection.prepareStatement("UPDATE accounts SET wins=wins+1 WHERE account_id=?")
    updateWinsStatement.setString(1, id)
    updateWinsStatement.executeQuery()  // executes: increment accounts wins by 1
  }

  override def incrementLoses(id: String): Unit ={
    val updateWinsStatement = connection.prepareStatement("UPDATE accounts SET loses=loses+1 WHERE account_id=?")
    updateWinsStatement.setString(1, id)
    updateWinsStatement.executeQuery()  // executes: increment accounts loses by 1
  }
}

 */
