package name.lemerdy.sebastian.katas.matchsticks

class LengthCharacter(santaWish: String) {
  val numberOfCharactersOfStringCode = santaWish.length
  val numberOfCharactersInMemory = santaWish
    .replaceAll("^\"(.*)\"$", "$1")
    .replaceAll("\\\\(\")", "$1")
    .replaceAll("\\\\(\\\\)", "$1")
    .replaceAll("\\\\x[0-9a-f]{2}", "x")
    .length
  val encode = s""""${santaWish.replace("\\", "\\\\").replace("\"", "\\\"")}""""
}
