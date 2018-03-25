package name.lemerdy.sebastian

import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Files, Paths}
import java.util.UUID

import cats.effect.IO
import com.datastax.driver.core.Cluster
import com.datastax.driver.core.querybuilder.QueryBuilder.select
import name.lemerdy.sebastian.Cat.names

import scala.collection.JavaConverters._
import scala.util.Try

class BusinessCase {

  def run(tableName: String = "cats"): Either[Throwable, List[String]]= {
    val program = for {
      cluster ← IO { Cluster.builder().addContactPoint("127.0.0.1").build() }
      session ← IO { cluster.connect("cats_effect") }
      cats ← IO { session.execute(select("id", "name").from(tableName)).all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name"))) }
      _ ← IO { println(names(cats).mkString(", ")) }
      reversedCats = cats.map(cat ⇒ cat.copy(name = cat.name.reverse))
      _ ← IO { println(names(reversedCats).mkString(", ")) }
      _ ← IO { Files.write(Paths.get("target", "cats.txt"), names(reversedCats).mkString("\n").getBytes(UTF_8)) }
      _ ← reversedCats.foldLeft(IO.unit){ (acc, cat) ⇒
        acc.flatMap { _ ⇒
          IO {
            session.execute(
              "update cats set name = :name where id = :id",
              Map[String, AnyRef]("name" → cat.name, "id" → cat.id).asJava)
          }
        }
      }
      _ ← IO { cluster.close() }
    } yield {
      names(reversedCats).toList
    }

    Try(program.unsafeRunSync()).toEither
  }

}
