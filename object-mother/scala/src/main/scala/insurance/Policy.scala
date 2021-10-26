package insurance

enum Policy(val fixedCost: Long):

  case Legal extends Policy(10)
  case Provident extends Policy(20)
  case Serenity extends Policy(30)
