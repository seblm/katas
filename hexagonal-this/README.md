 - [hexagonalThis initial kata](https://github.com/tpierrain/hexagonalThis) for reference

### Steps

1. create a new module _tests_ with scalatest

   update sbt

   create a directory `src/test/scala/hexagonalthis`
   
   create a class `hexagonalthis.AcceptanceTests` that extends `org.scalatest.FunSuite`
   
   create a new test _Should give verses when asked for poetry_
   
   create a new instance of `hexagonalthis.PoetryReader[Id]` : it doesn't compile
   
   create a new module _domain_
   
   add `cats-core` dependency to _domain_ module
   
   add `cats-core` dependency with test scope to _tests_ module
   
   add _domain_ dependency to _tests_ module
   
   update sbt
   
   import `cats.Id`
   
   create a directory `src/main/scala/hexagonalthis`
   
   go to `hexagonalthis.AcceptanceTests` and let IntelliJ create class `PoetryReader[F[_]: Applicative]`
   
   move this class to domain main sources
   
   because we don't want a left-side adapter to depends onto an implementation, we state that `PoetryReader` is an
   implementation of a `IRequestVerses[Id]` trait that IntelliJ can create for us with type parameter `F[_]`
   
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
   
2. duplicates test to introduce a right-side port `IObtainPoem[5]` and its unique `getAPoem` function

   name it _Should give verses from a PoetryLibrary_
   
   introduce a parameter to the constructor of `PoetryReader` named `poetryLibrary`
   
   ask IntelliJ to create a value of type `IObtainPoems` assigned to `mock[IObtainPoems]`
   
   ask IntelliJ to create trait `IObtainPoems`
   
   move it to _domain_ module
   
   go to `PoetryReader` and create a companion object to build a `IRequestVerses` that doesn't requires an instance of
   `IObtainPoems`
   
   go back to `AcceptanceTests` to replace `new PoetryReader()` by `PoetryReader()`
   
   add dependency to _Mockito_