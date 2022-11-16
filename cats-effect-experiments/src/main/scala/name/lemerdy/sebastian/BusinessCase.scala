package name.lemerdy.sebastian

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.datastax.oss.driver.api.core.CqlSessionBuilder
import com.datastax.oss.driver.api.core.`type`.reflect.GenericType
import com.datastax.oss.driver.api.core.cql.{ResultSet, SimpleStatement, SimpleStatementBuilder, Statement}
import name.lemerdy.sebastian.Cat.names

import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Files, Paths}
import java.util.UUID
import scala.io.Source
import scala.jdk.CollectionConverters.*
import scala.util.Try

class BusinessCase:

  def run(tableName: String = "cats"): Either[Throwable, List[String]] =
    val program = for {
      session <- IO {
        new CqlSessionBuilder()
          .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
          .withLocalDatacenter("datacenter1")
          .withKeyspace("cats_effect")
          .build()
      }
      cats <- IO {
        session
          .execute(new SimpleStatementBuilder(s"select id, name from $tableName").build())
          .all()
          .asScala
          .map(cat => Cat(cat.getUuid("id"), cat.getString("name")))
      }
      _ <- IO { println(names(cats.toSeq).mkString(", ")) }
      reversedCats = cats.map(cat => cat.copy(name = cat.name.reverse))
      _ <- IO { println(names(reversedCats.toSeq).mkString(", ")) }
      _ <- IO { Files.write(Paths.get("target", "cats.txt"), names(reversedCats.toSeq).mkString("\n").getBytes(UTF_8)) }
      _ <- reversedCats.foldLeft(IO.unit) { (acc, cat) =>
        acc.flatMap { _ =>
          IO {
            session.execute(
              new SimpleStatementBuilder("update cats set name = :name where id = :id")
                .addNamedValue("name", cat.name)
                .addNamedValue("id", cat.id)
                .build(),
              GenericType.of(classOf[Unit])
            )
          }
        }
      }
      _ <- IO { session.close() }
    } yield {
      names(reversedCats.toSeq).toList
    }

    Try(program.unsafeRunSync()).toEither
