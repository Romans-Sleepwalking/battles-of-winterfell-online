package Host.Database

import com.datawizards.class2csv._
import com.datawizards.csv2class._

class Temporal_CSV_Database {  // temporal STABLE CSV DB

  case class Account(username: String, password: String, wins: Int, loses: Int)
  val filename: String = "accounts.csv"

  def Init(): Unit = {
    val data = Seq(
      Account("admin", "admin", 6, 9),
      Account("test", "case", 4, 2),
      Account("dummy", "123frg", 0, 154),
    )
    writeCSV(data, filename)
  }

  Init()

  def Read(): (Iterable[Account], Iterable[Throwable]) = {
    println(parseCSV[Account](filename))
    parseCSV[Account](filename)
  }

  def SignUp(username: String, password: String): Unit = {
    var data: (Iterable[Account], Iterable[Throwable]) = Read()
  }
}
