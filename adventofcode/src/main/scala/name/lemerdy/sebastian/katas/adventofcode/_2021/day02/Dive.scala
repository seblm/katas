package name.lemerdy.sebastian.katas.adventofcode._2021.day02

object Dive:

  private val forwardCommand = """forward (\d+)""".r
  private val downCommand = """down (\d+)""".r
  private val upCommand = """up (\d+)""".r

  def computeHorizontalPositionAndDepth(commands: List[String]): (Long, Long) = commands.foldLeft((0L, 0L)) {
    case ((horizontalPosition, depth), forwardCommand(amount)) => (horizontalPosition + amount.toLong, depth)
    case ((horizontalPosition, depth), downCommand(amount))    => (horizontalPosition, depth + amount.toLong)
    case ((horizontalPosition, depth), upCommand(amount))      => (horizontalPosition, depth - amount.toLong)
    case ((horizontalPosition, depth), _)                      => (horizontalPosition, depth)
  }

  def computeHorizontalPositionAndDepthWithAim(commands: List[String]): (Long, Long, Long) =
    commands.foldLeft((0L, 0L, 0L)) {
      case ((horizontalPosition, depth, aim), forwardCommand(amount)) =>
        (horizontalPosition + amount.toLong, depth + aim * amount.toLong, aim)
      case ((horizontalPosition, depth, aim), downCommand(amount)) => (horizontalPosition, depth, aim + amount.toLong)
      case ((horizontalPosition, depth, aim), upCommand(amount))   => (horizontalPosition, depth, aim - amount.toLong)
      case ((horizontalPosition, depth, aim), _)                   => (horizontalPosition, depth, aim)
    }
