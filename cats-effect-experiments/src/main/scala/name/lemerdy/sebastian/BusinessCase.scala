package name.lemerdy.sebastian

import cats.effect.unsafe.implicits.global
import cats.effect.{IO, Resource}
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder
import com.datastax.oss.driver.api.core.{CqlSession, CqlSessionBuilder}
import name.lemerdy.sebastian.Cat.names

import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Files, Paths}
import java.util.UUID
import scala.jdk.CollectionConverters.*
import scala.util.Try

class BusinessCase:

  private def cqlSession(): Resource[IO, CqlSession] = Resource.make(
    IO:
      new CqlSessionBuilder()
        .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
        .withLocalDatacenter("datacenter1")
        .withKeyspace("cats_effect")
        .build()
  )(session => IO(session.close()))

  def run(tableName: String = "cats"): Either[Throwable, List[String]] =
    val program = cqlSession().use: session =>
      for
        cats <- IO:
          session
            .execute(new SimpleStatementBuilder(s"select id, name from $tableName").build())
            .all()
            .asScala
            .map(cat => Cat(cat.getUuid("id"), cat.getString("name")))
        _ <- IO.println(names(cats.toSeq).mkString(", "))
        reversedCats = cats.map(cat => cat.copy(name = cat.name.reverse))
        _ <- IO.println(names(reversedCats.toSeq).mkString(", "))
        _ <- IO:
          Files.write(Paths.get("target", "cats.txt"), names(reversedCats.toSeq).mkString("\n").getBytes(UTF_8))
        _ <- reversedCats.foldLeft(IO.unit): (acc, cat) =>
          acc.flatMap: _ =>
            IO:
              session.execute(
                new SimpleStatementBuilder("update cats set name = :name where id = :id")
                  .addNamedValue("name", cat.name)
                  .addNamedValue("id", cat.id)
                  .build()
              )
              ()
      yield names(reversedCats.toSeq).toList

    Try(program.unsafeRunSync()).toEither
