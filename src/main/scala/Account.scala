import java.time.LocalDate

case class Transaction(date: LocalDate, amount: Int, balance: Int)

object Transaction {
  def apply(amount: Int, balance: Int): Transaction =
    Transaction(LocalDate.now(), amount, balance)
}

case class Account(balance: Int = 0, transactions: List[Transaction] = Nil)

object Account {

  def deposit(account: Account, amount: Int): Either[String, Account] = {
    if (amount > 0) {
      val newBalance = account.balance + amount
      val newTransaction = Transaction(amount, newBalance)
      Right(account.copy(balance = newBalance, transactions = newTransaction :: account.transactions))
    } else {
      Left("Deposit amount must be positive")
    }
  }

  def withdraw(account: Account, amount: Int): Either[String, Account] = {
    if (amount <= 0) {
      Left("Withdraw amount must be positive")
    } else if (amount > account.balance) {
      Left("Insufficient funds")
    } else {
      val newBalance = account.balance - amount
      val newTransaction = Transaction(-amount, newBalance)
      Right(account.copy(balance = newBalance, transactions = newTransaction :: account.transactions))
    }
  }

  def printStatement(account: Account): String = {
    val header = "Date        Amount  Balance"
    val statementLines = account.transactions.reverse.map { t =>
      f"${t.date}%-12s ${t.amount}%+6d ${t.balance}%6d"
    }
    (header +: statementLines).mkString("\r\n")
  }
}

