import munit.FunSuite

import java.time.LocalDate

class AccountTest extends FunSuite:

  test("Solde par défaut est de 0"):
    val account = Account()
    assertEquals(0, account.balance, "Solde par défaut incorrect")

  test("Transactions par défaut sont vides") :
    val account = Account()
    assertEquals(List[Transaction](), account.transactions)

  test("Dépôt d'un montant positif augmente le solde et ajoute une transaction") :
    val account = Account()
    val newAccount = account.deposit(1000)
    assertEquals(1000, newAccount.balance, "Solde après dépôt incorrect")
    assertEquals(List(Transaction(LocalDate.now(), 1000, 1000)), account.transactions) 

  test("Dépôt d'un montant négatif ou nul ne modifie pas le solde et n'ajoute pas de transaction") :
    val account = Account()
    val newAccount1 = account.deposit(-100)
    val newAccount2 = account.deposit(0)
    assertEquals(0, newAccount1.balance, "Solde après dépôt d'un montant négatif incorrect")
    assertEquals(0, newAccount2.balance, "Solde après dépôt d'un montant nul incorrect")
    assertEquals(account.transactions, newAccount1.transactions, "Transactions après dépôt d'un montant négatif incorrectes")
    assertEquals(account.transactions, newAccount2.transactions, "Transactions après dépôt d'un montant nul incorrectes")

  test("Retrait d'un montant positif inférieur ou égal au solde diminue le solde et ajoute une transaction") :
    val account = Account(balance = 1000)
    val newAccount1 = account.withdraw(500)
    val newAccount2 = account.withdraw(1000)
    assertEquals(500, newAccount1.balance, "Solde après retrait incorrect")
    assertEquals(List(Transaction(LocalDate.now(), -500, 500), account.transactions), newAccount1.transactions, "Transactions après retrait incorrectes")
    assertEquals(0, newAccount2.balance, "Solde après retrait incorrect")
    assertEquals(List(Transaction(LocalDate.now(), -1000, 0), account.transactions), newAccount2.transactions, "Transactions après retrait incorrectes")

  test("Retrait d'un montant négatif ou nul ne modifie pas le solde et n'ajoute pas de transaction") :
    val account = Account(balance = 1000)
    val newAccount1 = account.withdraw(-100)
    val newAccount2 = account.withdraw(0)
    assertEquals(1000, newAccount1.balance, "Solde après retrait d'un montant négatif incorrect")
    assertEquals(1000, newAccount2.balance, "Solde après retrait d'un montant nul incorrect")
    assertEquals(account.transactions, newAccount1.transactions, "Transactions après retrait d'un montant négatif incorrectes")
    assertEquals(account.transactions, newAccount2.transactions, "Transactions après retrait d'un montant nul incorrectes")

  test("Retrait d'un montant supérieur au solde ne modifie pas le solde et n'ajoute pas de transaction") :
    val account = Account(balance = 1000)
    val newAccount = account.withdraw(2000)
    assertEquals(1000, newAccount.balance, "Solde après retrait d'un montant supérieur au solde incorrect")
    assertEquals(account.transactions, newAccount.transactions, "Transactions après retrait d'un montant supérieur au solde incorrectes")
