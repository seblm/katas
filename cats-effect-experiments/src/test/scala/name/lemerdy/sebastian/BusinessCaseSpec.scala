package name.lemerdy.sebastian

import com.datastax.driver.core.Cluster
import org.scalatest.{Matchers, WordSpec}

import scala.collection.JavaConverters._

class BusinessCaseSpec extends WordSpec with Matchers{

  "BusinessCase" should {
    "works" in {
      var cluster: Cluster = null
      try {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build()
        val session = cluster.connect("cats_effect")
        val resultSet = session.execute("select id, name from cats")
        val cats = resultSet.all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name")))

        new BusinessCase().run()

        val resultSet1 = session.execute("select id, name from cats")
        val cats1 = resultSet1.all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name")))
        cats1.zip(cats).foreach { case (c1: Cat, c2: Cat) => c1.name.reverse should be(c2.name) }
      } finally {
        Option(cluster).foreach(_.close)
      }
    }
    "not work" in {
      var cluster: Cluster = null
      try {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build()
        val session = cluster.connect("cats_effect")
        val resultSet = session.execute("select id, name from cats")
        val cats = resultSet.all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name")))

        val shouldBeLeft = new BusinessCase().run("stac")
        shouldBeLeft should be a 'Left

        val resultSet1 = session.execute("select id, name from cats")
        val cats1 = resultSet1.all().asScala.map(cat ⇒ Cat(cat.getUUID("id"), cat.getString("name")))
        cats1.zip(cats).foreach { case (c1: Cat, c2: Cat) => c1.name should be(c2.name) }
      } finally {
        Option(cluster).foreach(_.close)
      }
    }
  }

}
