package name.lemerdy.sebastian.katas.md5

import java.security.MessageDigest

class AdventCoinsMining {

  def mine(secretKey: String, numberOfZeros: Int = 5): Int = {
    val hasher: MessageDigest = MessageDigest.getInstance("MD5")
    var hash = "123456"
    var i = -1
    while (!hash.substring(0, 6).equals("000000")) {
      i += 1
      hash = hasher.digest(s"$secretKey$i".getBytes()).map("%02X".format(_)).mkString
      if (i % 1000 == 0) print(s"${i / 1000} ")
      if (i % 10000 == 0) println
    }
    i
  }

}

object AdventCoinsMining {

  def main(args: Array[String]) {
    println(new AdventCoinsMining().mine("bgvyzdsv"))
  }

}
