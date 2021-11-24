package insurance

enum Cover(val fixedCost: Long):

  case Legal extends Cover(10)
  case Provident extends Cover(20)
  case Serenity extends Cover(30)
