package name.lemerdy.sebastian.katas.wrappingpaper

import scala.io.Source

class WrappingPaper() {

  def computeDimensions(specification: String): Int = {
    specification.split("\n").map(computeDimension).sum
  }

  private def computeDimension(specification: String): Int = {
    val dimensions = specificationAsInts(specification)
    computeDimension(dimensions(0), dimensions(1), dimensions(2))
  }

  def computeDimension(l: Int, w: Int, h: Int) = {
    val surfaces = List(l*w, w*h, h*l)
    surfaces.map(2*_).sum + surfaces.min
  }

  def computeRibbons(specification: String): Int = {
    specification.split("\n").map(computeRibbon).sum
  }

  def computeRibbon(specification: String): Int = {
    val dimensions = specificationAsInts(specification)
    computeRibbon(dimensions(0), dimensions(1), dimensions(2))
  }

  private def computeRibbon(l: Int, w: Int, h: Int): Int = {
    val twoLowerValues: List[Int] = List(l, w, h).sorted.take(2)
    val wrapPresentLength = twoLowerValues(0) * 2 + twoLowerValues(1) * 2
    val bowLength = l * w * h
    wrapPresentLength + bowLength
  }

  private def specificationAsInts(specification: String): Array[Int] = {
    specification.split("x").map(_.toInt)
  }
}

object WrappingPaper {
  def main(args: Array[String]) {
    val input = Source.fromInputStream(getClass.getResourceAsStream("input")).mkString
    val wrappingPaper: WrappingPaper = new WrappingPaper()
    println(s"square feet of wrapping paper: ${wrappingPaper.computeDimensions(input)}\n" +
      s"feet of ribbon: ${wrappingPaper.computeRibbons(input)}")
  }
}