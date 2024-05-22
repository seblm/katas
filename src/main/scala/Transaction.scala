import java.time.LocalDate

case class Transaction(date: LocalDate, amount: Int, balance: Int):
  override def toString: String = s"$date ${if (amount >= 0) "+" else ""}$amount $balance"

