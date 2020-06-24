 - [hexagonalThis initial kata](https://github.com/tpierrain/hexagonalThis) for reference

### Steps

1. create a new module _tests_ with scalatest

   update sbt

   create a directory `tests/src/test/scala/hexagonalthis`
   
   create a class `hexagonalthis.AcceptanceTests` that extends `org.scalatest.FunSuite`
   
   create a new test `test("Should give verses when asked for poetry") { }`
   
   create a new instance of `hexagonalthis.PoetryReader[Id]` : it doesn't compile
   
   create a new module _domain_
   
   add `cats-core` dependency to _domain_ module
   
   add `cats-core` dependency with test scope to _tests_ module
   
   add _domain_ dependency to _tests_ module with `Test` scope
   
   update sbt
   
   import `cats.Id`
   
   create a directory `domain/src/main/scala/hexagonalthis`
   
   go to `hexagonalthis.AcceptanceTests` and let IntelliJ create class `PoetryReader[F[_]: Applicative]`
   
   move this class to domain main sources
   
   go to `hexagonalthis.AcceptanceTests` and assign `new PoetryReader[Id]` to _val_  `poetryReader` of new type
   `IRequestVerses[Id]`
   
   create `IRequestVerses[F]` trait
   
   move this trait to _domain_ module
   
   it doesn't compile : make `PoetryReader[F[_]: Applicative]` extends `IRequestVerses[F]`
   
   call `giveMeSomePoetry` from `poetryReader`
   
   declare it with IntelliJ : a function that returns a `F[String]`
   
   put result of this call to a val `verses`
   
   import `org.scalatest.Matchers._` in order to assert that `verses` should equal
   
   > If you could read a leaf or tree
   > you’d have no need of books.
   > -- © Alistair Cockburn (1987)
   
   compile and fix it by implementing the hard-coded result with `pure` imported from `cats.syntax.applicative._`
   
2. duplicates test to introduce a right-side port `IObtainPoem[F]` and its unique `getAPoem` function

   name it _Should give verses from a PoetryLibrary_
   
   introduce a parameter to the constructor of `PoetryReader` named `poetryLibrary` of type `IObtainPoems[F]`
   
   ask IntelliJ to create a value of type `IObtainPoems[Id]` assigned to `mock[IObtainPoems[Id]]`
   
   ask IntelliJ to create trait `IObtainPoems[F[_]]`
   
   move it to _domain_ module
   
   go to `PoetryReader` and create a companion object to build a `PoetryReader[Id]` with `UniquePoem` as
   `IObtainPoems[Id]`
   
   create object `UniquePoem` that extends `IObtainPoems[Id]`
   
   go back to `AcceptanceTests` to replace `new PoetryReader[Id]()` by `PoetryReader()`
   
   add `mockito` dependency with test scope to _tests_ module

   describe behavior of `poetryLibrary`: `when(poetryLibrary.getAPoem())`