package mowitnow

import mowitnow.Orientation.{East, North, South, West}
import mowitnow.seblm.MowItNow
import processing.core.{PApplet, PImage}

import java.awt.event.KeyEvent
import scala.collection.mutable
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
  private val mowerControl = MowerControl(input, Map("seblm" -> MowItNow))
  private val state: mutable.Map[String, Either[String, Seq[Position]]] =
    mutable.Map("seblm" -> Right(Seq(Position(1, 2, North), Position(3, 3, East))))
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
    translate(border.toFloat * onePositionSize, border.toFloat * onePositionSize)
    rect(0, 0, (topX.toFloat + 1) * onePositionSize, (topY.toFloat + 1) * onePositionSize)
    stroke(color(17, 94, 10))
    Range
      .inclusive(0, topX + 1)
      .foreach: x =>
        line(x.toFloat * onePositionSize, 0, x.toFloat * onePositionSize, (topY.toFloat + 1) * onePositionSize)
    Range
      .inclusive(0, topY + 1)
      .foreach: y =>
        line(0, y.toFloat * onePositionSize, (topX.toFloat + 1) * onePositionSize, y.toFloat * onePositionSize)
    state.foreach: (_, positions) =>
      positions.fold(
        _ => (),
        mowers =>
          mowers.foreach: mower =>
            val imageFromOrientation = mower.orientation match
              case North => imageUp
              case South => imageDown
              case West  => imageLeft
              case East  => imageRight
            pushMatrix()
            translate(mower.x.toFloat * onePositionSize, (topY - mower.y.toFloat) * onePositionSize)
            imageFromOrientation.foreach(image(_, 0, 0, onePositionSize.toFloat, onePositionSize.toFloat))
            popMatrix()
      )
    popMatrix()

  override def keyPressed(): Unit =
    keyCode match
      case KeyEvent.VK_RIGHT =>
        mowerControl
          .next()
          .foreach:
            case (participant, mowers) => state.update(participant, mowers)
        redraw()
      case KeyEvent.VK_LEFT =>
        mowerControl
          .previous()
          .foreach:
            case (participant, mowers) => state.update(participant, mowers)
        redraw()
      case _ => ()

@main def launch(): Unit = PApplet.main(classOf[Main])
