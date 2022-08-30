package kata.cqrs

import java.time.LocalDate
import java.util.UUID

case class Booking(clientId: UUID, room: Room, arrival: LocalDate, departure: LocalDate)
