package kata.cqrs

import munit.FunSuite

import java.time.LocalDate
import java.util.UUID

class BookingSuite extends FunSuite:

  test("should get all free rooms") {
    val hotel = new Hotel(3)

    val freeRooms = hotel.freeRooms(arrival = LocalDate.parse("2022-08-28"), departure = LocalDate.parse("2022-08-28"))

    assertEquals(freeRooms, Vector(Room("room 1"), Room("room 2"), Room("room 3")))
  }

  test("should book a room") {
    val hotel = new Hotel(3)
    val booking =
      Booking(UUID.randomUUID(), Room("room 1"), LocalDate.parse("2022-08-28"), LocalDate.parse("2022-08-28"))

    hotel.bookARoom(booking)

    val freeRooms = hotel.freeRooms(arrival = LocalDate.parse("2022-08-28"), departure = LocalDate.parse("2022-08-28"))
    assert(!freeRooms.contains(Room("room 1")))
  }

  test("should omit already booked room") {
    val hotel = new Hotel(3)
    val booking =
      Booking(UUID.randomUUID(), Room("room 1"), LocalDate.parse("2022-08-28"), LocalDate.parse("2022-08-28"))
    hotel.bookARoom(booking)

    val freeRooms = hotel.freeRooms(arrival = LocalDate.parse("2022-08-27"), departure = LocalDate.parse("2022-08-29"))

    assert(!freeRooms.contains(Room("room 1")))
  }
