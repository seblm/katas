package insurance.car

import scala.collection.mutable
import insurance.Cover

import java.util.UUID

class PolicyRepository:

  val events: mutable.Buffer[String] = mutable.Buffer[String]()
  private val policies: mutable.Map[UUID, Policy] = mutable.Map[UUID, Policy]()

  def createPolicy(newPolicy: Policy): UUID =
    val newId: UUID = UUID.randomUUID()
    events.addOne(s"""{
                     |  "operation": "createPolicy",
                     |  "input": [
                     |${newPolicy.toJson()}
                     |  ],
                     |  "output": [
                     |    "$newId"
                     |  ]
                     |}""".stripMargin)
    policies.addOne(newId -> newPolicy)
    newId

  def find(id: UUID): Option[Policy] = policies.get(id)
