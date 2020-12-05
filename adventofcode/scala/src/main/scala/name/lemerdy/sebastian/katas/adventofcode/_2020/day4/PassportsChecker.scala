package name.lemerdy.sebastian.katas.adventofcode._2020.day4

import scala.annotation.tailrec

object PassportsChecker {

  private val fieldRegex = """([a-z]{3}):(\S+)""".r

  private val optionalFields = List("cid")
  private val centimeters = """(\d+)cm""".r
  private val inches = """(\d+)in""".r

  case class FieldRule(name: String, validator: String => Boolean) {
    def isValid(value: String, validate: Boolean): Boolean = !validate || validator(value)
  }

  private val requiredFields: List[FieldRule] = List(
    FieldRule("byr", checkRange(1920, 2002)(_)),
    FieldRule("iyr", checkRange(2010, 2020)(_)),
    FieldRule("eyr", checkRange(2020, 2030)(_)),
    FieldRule(
      "hgt",
      PartialFunction.cond(_) {
        case centimeters(height) => checkRange(150, 193)(height)
        case inches(height)      => checkRange(59, 76)(height)
      }
    ),
    FieldRule("hcl", (value: String) => value.matches("#[0-9a-f]{6}")),
    FieldRule("ecl", (value: String) => value.matches("(?:amb|blu|brn|gry|grn|hzl|oth)")),
    FieldRule("pid", (value: String) => value.matches("[0-9]{9}"))
  )

  private def checkRange(min: Long, max: Long)(value: String): Boolean =
    value.toLongOption.exists(number => min <= number && number <= max)

  case class Field(name: String, value: String)

  def validateCount(passportsBatch: String, validate: Boolean = false): Long =
    passportsBatch.split("""\n\n""").count { passport =>
      val fields = passport
        .split(Array('\n', ' '))
        .flatMap {
          case fieldRegex(name, value) => Some(Field(name, value))
          case _                       => None
        }
        .toList

      val unknownFields = for {
        withoutRequiredFields <- checkRequiredFields(requiredFields, fields, validate)
        unknownFields <- checkOptionalFields(optionalFields, withoutRequiredFields.map(_.name))
      } yield unknownFields

      unknownFields.fold(_ => false, _.isEmpty)
    }

  @tailrec
  private def checkRequiredFields(
      required: List[FieldRule],
      fields: List[Field],
      validate: Boolean
  ): Either[Unit, List[Field]] =
    required match {
      case Nil => Right(fields)
      case head :: tail if fields.find(_.name == head.name).exists(value => head.isValid(value.value, validate)) =>
        checkRequiredFields(tail, fields.filterNot(_.name == head.name), validate)
      case _ => Left(())
    }

  @tailrec
  private def checkOptionalFields(optional: List[String], values: List[String]): Either[Unit, List[String]] =
    optional match {
      case Nil          => Right(values)
      case head :: tail => checkOptionalFields(tail, values.filterNot(_ == head))
    }

}
