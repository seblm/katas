package name.lemerdy.sebastian.katas.adventofcode._2015.day4

import java.security.MessageDigest

class AdventCoinsMining:

  def mine(secretKey: String, numberOfZeros: Int = 5): Int =
    val zeros = (0 until numberOfZeros).map(_ => "0").mkString
    val hasher: MessageDigest = MessageDigest.getInstance("MD5")
    var hash = "123456"
    var i = -1
    while (!hash.substring(0, numberOfZeros).equals(zeros)) {
      i += 1
      hash = hasher.digest(s"$secretKey$i".getBytes()).map("%02X".format(_)).mkString
    }
    i

object AdventCoinsMining:

  def main(args: Array[String]): Unit =
    println(new AdventCoinsMining().mine("bgvyzdsv"))
