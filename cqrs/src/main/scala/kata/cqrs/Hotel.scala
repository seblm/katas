package kata.cqrs

import java.time.{LocalDate, Period}
import scala.collection.mutable

class Hotel(roomsCount: Int):

  private val readRegistry = new ReadRegistry(roomsCount)
  private val writeRegistry = new WriteRegistry(roomsCount, readRegistry)

  def freeRooms(arrival: LocalDate, departure: LocalDate): Vector[Room] =
    readRegistry.freeRooms(arrival, departure)

  def bookARoom(booking: Booking): Unit =
    writeRegistry.bookARoom(booking)

  class ReadRegistry(roomsCount: Int):
    private val rooms = Range.inclusive(1, roomsCount).map(roomIndex => Room(s"room $roomIndex")).toSet
    private val bookedRooms = mutable.Map.empty[LocalDate, Set[Room]]

    private[Hotel] def freeRooms(arrival: LocalDate, departure: LocalDate): Vector[Room] =
      val bookedRoomsBetweenPeriod = Vector
        .iterate(arrival, Period.between(arrival, departure).getDays + 1)(_.plusDays(1))
        .flatMap(bookedRooms.get)
        .flatten
        .toSet
      rooms.diff(bookedRoomsBetweenPeriod).toVector

    private[Hotel] def roomBooked(room: Room, arrival: LocalDate, departure: LocalDate): Unit =
      Vector
        .iterate(arrival, Period.between(arrival, departure).getDays + 1)(_.plusDays(1))
        .map { date =>
          bookedRooms.updateWith(date) {
            case None                => Some(Set(room))
            case Some(alreadyBooked) => Some(alreadyBooked + room)
          }
        }

  class WriteRegistry(roomsCount: Int, readRegistry: ReadRegistry):
    private val rooms =
      mutable.Map(
        Range(1, roomsCount + 1)
          .map(roomIndex => Room(s"room $roomIndex") -> Vector.empty[(LocalDate, LocalDate)]): _*
      )

    def bookARoom(booking: Booking): Unit =
      rooms.get(booking.room) match {
        case None => ()
        case Some(dates) =>
          if dates.forall { case (arrival, departure) =>
              arrival.isAfter(booking.departure) || arrival.isEqual(booking.departure) || departure.isBefore(
                booking.arrival
              ) || departure.isEqual(booking.arrival)
            }
          then
            rooms.updateWith(booking.room) {
              case None                => Some(Vector((booking.arrival, booking.departure)))
              case Some(alreadyBooked) => Some(alreadyBooked :+ (booking.arrival, booking.departure))
            }
            readRegistry.roomBooked(booking.room, booking.arrival, booking.departure)
            ()
          else ()
      }
