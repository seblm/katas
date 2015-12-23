package name.lemerdy.sebastian.katas.lights

import scala.io.Source
import scala.math.max
import scala.util.matching.Regex

class Lights {

  private val lights: Array[(Boolean, Int)] = new Array[(Boolean, Int)](1000000)

  forEachLight(0, 0, 999, 999, lightState => (false, 0))

  def input(specification: String): Lights = {
    val parsers = Map[Regex, (Int, Int, Int, Int) => Lights](
      ("turn on (\\d+),(\\d+) through (\\d+),(\\d+)".r, turnOn),
      ("toggle (\\d+),(\\d+) through (\\d+),(\\d+)".r, toggle),
      ("turn off (\\d+),(\\d+) through (\\d+),(\\d+)".r, turnOff)
    )
    parsers.flatMap({
      case (regex, f) =>
        regex.findFirstMatchIn(specification)
          .map(m => f(m.group(1).toInt, m.group(2).toInt, m.group(3).toInt, m.group(4).toInt))
    }).headOption.getOrElse(this)
  }

  def turnOn(x1: Int, y1: Int, x2: Int, y2: Int): Lights =
    forEachLight(x1, y1, x2, y2, lightState => {
      val (_, brightness) = lightState
      (true, brightness + 1)
    })

  def toggle(x1: Int, y1: Int, x2: Int, y2: Int): Lights =
    forEachLight(x1, y1, x2, y2, lightState => {
      val (state, brigthness) = lightState
      (!state, brigthness + 2)
    })

  def turnOff(x1: Int, y1: Int, x2: Int, y2: Int): Lights =
    forEachLight(x1, y1, x2, y2, lightState => {
      val (_, brightness) = lightState
      (false, max(brightness - 1, 0))
    })

  def count(): Int = {
    var lightsOn = 0
    forEachLight(0, 0, 999, 999, lightState => {
      val (state, _) = lightState
      if (state) lightsOn += 1
      lightState
    })
    lightsOn
  }

  def totalBrightness(): Int = {
    var totalBrightness = 0
    forEachLight(0, 0, 999, 999, lightState => {
      val (_, brightness) = lightState
      totalBrightness += brightness
      lightState
    })
    totalBrightness
  }

  private def forEachLight(x1: Int, y1: Int, x2: Int, y2: Int, newLightState: ((Boolean, Int)) => (Boolean, Int)): Lights = {
    for (x <- Stream.range(x1, x2 + 1))
      for (y <- Stream.range(y1, y2 + 1)) {
        val index: Int = y * 1000 + x
        val oldLightState = lights(index)
        lights(index) = newLightState(oldLightState)
      }
    this
  }
}

object Lights {
  def main(args: Array[String]) {
    val lights = new Lights
    Source.fromInputStream(getClass.getResourceAsStream("input"))
      .getLines()
      .foreach(lights.input)
    println(s"number of lights: ${lights.count()}")
    println(s"total brightness: ${lights.totalBrightness()}")
  }
}