package mowitnow

import processing.core.{PApplet, PImage}

import scala.util.Try

class Main extends PApplet:

  private val onePositionSize = 50
  private val topX = 5
  private val topY = 5
  private val border = 2
  private val input: String = """5 5
                                |1 2 N
                                |GAGAGAGAA
                                |3 3 E
                                |AADAADADDA""".stripMargin
  private val mowerControl = MowerControl(input, Seq(new mowitnow.seblm.Mower()))
  private var imageUp: Option[PImage] = None
  private var imageDown: Option[PImage] = None
  private var imageLeft: Option[PImage] = None
  private var imageRight: Option[PImage] = None

  override def settings(): Unit =
    size((topX + 1 + border * 2) * onePositionSize + 1, (topY + 1 + border * 2) * onePositionSize + 1)

  override def setup(): Unit =
    this.imageUp = Try(loadImage("up.png")).toOption
    this.imageDown = Try(loadImage("down.png")).toOption
    this.imageLeft = Try(loadImage("left.png")).toOption
    this.imageRight = Try(loadImage("right.png")).toOption
    noLoop()

  override def draw(): Unit =
    background(color(95, 60, 26))
    fill(color(115, 155, 77))
    pushMatrix()
    translate(border * onePositionSize, border * onePositionSize)
    rect(0, 0, (topX + 1) * onePositionSize, (topY + 1) * onePositionSize)
    stroke(color(17, 94, 10))
    Range
      .inclusive(0, topX + 1)
      .foreach: x =>
        line(x * onePositionSize, 0, x * onePositionSize, (topY + 1) * onePositionSize)
    Range
      .inclusive(0, topY + 1)
      .foreach: y =>
        line(0, y * onePositionSize, (topX + 1) * onePositionSize, y * onePositionSize)
    imageUp.foreach(image(_, 0, 0, onePositionSize, onePositionSize))
    imageDown.foreach(image(_, 0, onePositionSize, onePositionSize, onePositionSize))
    imageLeft.foreach(image(_, onePositionSize, 0, onePositionSize, onePositionSize))
    imageRight.foreach(image(_, onePositionSize, onePositionSize, onePositionSize, onePositionSize))
    popMatrix()

@main def launch(): Unit = PApplet.main(classOf[Main])
