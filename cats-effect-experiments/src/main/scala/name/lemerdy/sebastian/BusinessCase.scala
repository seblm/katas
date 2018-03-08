package name.lemerdy.sebastian

import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Files, Paths}
import java.util.UUID

import com.datastax.driver.core.Cluster
import name.lemerdy.sebastian.Cat.names

import scala.collection.JavaConverters._

class BusinessCase {

  def run(): Unit = {

    var cluster: Cluster = null

    try {
      cluster = Cluster.builder().addContactPoint("127.0.0.1").build()
      val session = cluster.connect("cats_effect")
      val resultSet = session.execute("select id, name from cats")
      val cats = resultSet.all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name")))

      println(names(cats).mkString(", "))

      val reversedCats = cats.map(cat ⇒ cat.copy(name = cat.name.reverse))

      println(names(reversedCats).mkString(", "))

      Files.write(Paths.get("target", "cats.txt"), names(reversedCats).mkString("\n").getBytes(UTF_8))

      reversedCats.foreach { cat ⇒
        session.execute(
          "update cats set name = :name where id = :id",
          Map[String, AnyRef]("name" → cat.name, "id" → cat.id).asJava)
      }

    } finally {
      Option(cluster).foreach(_.close)
    }
  }

}

object BusinessCase extends App {

  new BusinessCase().run()

}

case class Cat(id: UUID, name: String)

object Cat {

  def names(cats: Seq[Cat]): Seq[String] = cats.map(_.name)

}
