 - [hexagonalThis initial kata](https://github.com/tpierrain/hexagonalThis) for reference
 - [slides](doc/reveal.js-3.8.0/index.html)

### Steps

1. create a new module _tests_ with scalatest

   update sbt

   create a directory `src/test/scala/hexagonalthis`
   
   create a class `hexagonalthis.AcceptanceTests` that extends `org.scalatest.FunSuite`
   
   create a new test _Should give verses when asked for poetry_
   
   create a new instance of `hexagonalthis.PoetryReader` : it doesn't compile
   
   create a new module _domain_ and let _tests_ depends on it
   
   update sbt
   
   create a directory `src/main/scala/hexagonalthis`
   
   go to `hexagonalthis.AcceptanceTests` and let IntelliJ create class `PoetryReader`
   
   move this class to domain main sources
   
   because we don't want a left-side adapter to depends onto an implementation, we state that `PoetryReader` is an
   implementation of a `IRequestVerses` trait that IntelliJ can create for us
   
   move this trait to _domain_ module
   
   it doesn't compile : make `PoetryReader` extends `IRequestVerses`
   
   call `giveMeSomePoetry` from `poetryReader`
   
   declare it with IntelliJ : a function that returns a `String`
   
   put result of this call to a val `verses`
   
   import `org.scalatest.Matchers._` in order to assert that `verses` should equal
   
   > If you could read a leaf or tree
   > you’d have no need of books.
   > -- © Alistair Cockburn (1987)
   
   compile and fix it by implementing the hard-coded result
   
2. duplicates test to introduce a right-side port `IObtainPoems` and its unique `getAPoem` function

   name it _Should give verses from a PoetryLibrary_
   
   introduce a parameter to the constructor of `PoetryReader` named `poetryLibrary`
   
   ask IntelliJ to create a value of type `IObtainPoems` assigned to `mock[IObtainPoems]`
   
   ask IntelliJ to create trait `IObtainPoems`
   
   move it to _domain_ module
   
   go to `PoetryReader` and create a companion object to build a `IRequestVerses` that doesn't requires an instance of
   `IObtainPoems`
   
   go back to `AcceptanceTests` to replace `new PoetryReader()` by `PoetryReader()`
   
   add dependency to _Mockito_