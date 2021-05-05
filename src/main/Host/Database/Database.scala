/*
package Host.Database


import com.corundumstudio.socketio.SocketIOClient
import play.api.libs.json.{JsValue, Json}

import java.sql.{Connection, DriverManager}
import java.sql.ResultSet


class Database {
  val url: String = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username: String = "root"
  val password: String = "root"
  var connection: Connection = DriverManager.getConnection(url, username, password)

  def Login(data: JsValue, socket: SocketIOClient): Unit = {
    val username: String = (data \ "username").as[String]
    val password: String = (data \ "password").as[String]

    val getUserStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username=? AND password=?")
    getUserStatement.setString(1, username)
    getUserStatement.setString(2, password)
    val userData: ResultSet = getUserStatement.executeQuery()  // executes: get account data by username and password

    if (userData.next()) {  // if query has returned something: returns full data Map with positive response
      socket.sendEvent("user", Json.obj(
          "username" -> userData.getString("username"),
          "wins" -> userData.getString("wins"),
          "loses" -> userData.getString("loses")
        )
      )
    }
    else {  // if query has returned something: returns full data Map with positive response
      socket.sendEvent("user", Json.obj(
          "username" -> "wrong username or password",
          "wins" -> "?",
          "loses" -> "?"
        )
      )
    }
  }

  def SignUp(data: JsValue, socket: SocketIOClient): Unit = {
    val username: String = (data \ "username").as[String]
    val password: String = (data \ "password").as[String]

    val checkUsernameStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username=?")
    checkUsernameStatement.setString(1, username)
    val existence: ResultSet = checkUsernameStatement.executeQuery() // executes: get account data by username

    if (!existence.next()) { // if the returned dataset by username is empty: creates a new username
      val registerAccountStatement = connection.prepareStatement(
        "INSERT INTO accounts (`username`, `password`) VALUES (username=?, password=?)")
      registerAccountStatement.setString(1, username)
      registerAccountStatement.setString(2, password)
      registerAccountStatement.executeQuery() // executes: record new account and return new ID

      socket.sendEvent("user", Json.obj(
          "username" -> username,
          "wins" -> "0",
          "loses" -> "0"
        )
      )
    }
  }

  def incrementWins(username: String): Unit = {
    val updateWinsStatement = connection.prepareStatement("UPDATE accounts SET wins=wins+1 WHERE username=?")
    updateWinsStatement.setString(1, username)
    updateWinsStatement.executeQuery()  // executes: increment accounts wins by 1
  }

  def incrementLoses(username: String): Unit = {
    val updateWinsStatement = connection.prepareStatement("UPDATE accounts SET loses=loses+1 WHERE username=?")
    updateWinsStatement.setString(1, username)
    updateWinsStatement.executeQuery()  // executes: increment accounts loses by 1
  }
}
*/