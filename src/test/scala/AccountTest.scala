import munit.FunSuite

import java.time.LocalDate

class AccountTest extends FunSuite:
  test("should print statement") {
    val initialAccount = Account()
    val accountWithDeposit = Account.deposit(initialAccount, 500).getOrElse(initialAccount)
    val accountWithWithdraw = Account.withdraw(accountWithDeposit, 100).getOrElse(accountWithDeposit)

    val statements = Account.printStatement(accountWithWithdraw)

    val today = LocalDate.now()

    assertEquals(statements,
      s"""Date        Amount  Balance
         |$today     +500    500
         |$today     -100    400""".stripMargin)
  }
