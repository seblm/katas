package name.lemerdy.sebastian

import com.datastax.oss.driver.api.core.{CqlSession, CqlSessionBuilder}
import munit.FunSuite
import name.lemerdy.sebastian.BusinessCase

import java.net.InetSocketAddress
import scala.io.Source
import scala.jdk.CollectionConverters.*
import scala.util.Using

class BusinessCaseSpec extends FunSuite:

  override def beforeAll(): Unit = Using.Manager { use =>
    val session = use(cqlSession(None))
    val schema = use(Source.fromResource("schema.cql"))
    schema.getLines().toVector.filterNot(_.isBlank).map(session.execute)
  }

  test("BusinessCase should works") {
    Using(cqlSession()) { session =>
      val resultSet = session.execute("select id, name from cats")
      val cats = resultSet.all().asScala.map(cat => Cat(cat.getUuid("id"), cat.getString("name")))

      new BusinessCase().run()

      val resultSet1 = session.execute("select id, name from cats")
      val cats1 = resultSet1.all().asScala.map(cat => Cat(cat.getUuid("id"), cat.getString("name")))
      cats1.zip(cats).foreach { case (c1: Cat, c2: Cat) => assertEquals(c1.name.reverse, c2.name) }
    }
  }

  test("BusinessCase should not work") {
    Using(cqlSession()) { session =>
      val resultSet = session.execute("select id, name from cats")
      val cats = resultSet.all().asScala.map(cat => Cat(cat.getUuid("id"), cat.getString("name")))

      val shouldBeLeft = new BusinessCase().run("stac")
      assert(shouldBeLeft.isLeft)

      val resultSet1 = session.execute("select id, name from cats")
      val cats1 = resultSet1.all().asScala.map(cat => Cat(cat.getUuid("id"), cat.getString("name")))
      cats1.zip(cats).foreach { case (c1: Cat, c2: Cat) => assertEquals(c1.name, c2.name) }
    }
  }

  private def cqlSession(keyspace: Option[String] = Some("cats_effect")): CqlSession =
    val builder = new CqlSessionBuilder()
      .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
      .withLocalDatacenter("datacenter1")
    keyspace.foreach(builder.withKeyspace)
    builder.build()
