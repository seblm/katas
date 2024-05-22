object AccountApp extends App:
  val account = Account()
  println(s"Solde initial : ${account.balance}")

  val newAccount1 = account.deposit(1000)
  println(s"Solde après le premier dépôt : ${newAccount1.balance}")

  val newAccount2 = newAccount1.withdraw(500)
  println(s"Solde après le premier retrait : ${newAccount2.balance}")

  val newAccount3 = newAccount2.deposit(200)
  println(s"Solde après le deuxième dépôt : ${newAccount3.balance}")

  newAccount3.printStatement()