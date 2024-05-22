import java.time.LocalDate

case class Account(balance: Int = 0, transactions: List[Transaction] = Nil):

  def deposit(amount: Int): Account =
    if (amount > 0) then
      val newBalance = balance + amount
      val newTransaction = Transaction(LocalDate.now(), amount, newBalance)
      Account(newBalance, transactions :+ newTransaction)
    else
      this

  def withdraw(amount: Int): Account =
    if (amount > 0 && amount <= balance) then
      val newBalance = balance - amount
      val newTransaction = Transaction(LocalDate.now(), -amount, newBalance)
      Account(newBalance, transactions :+ newTransaction)
    else
      this

  def printStatement(): String =
    "Date        Amount  Balance" + "\n" +
      transactions.map(_.toString).mkString("\n")
