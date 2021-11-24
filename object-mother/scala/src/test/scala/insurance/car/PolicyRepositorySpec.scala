package insurance.car

import insurance.Cover.Legal
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.OptionValues._
import org.scalatest.matchers.should.Matchers._

class PolicyRepositorySpec extends AnyFlatSpec:

  "PolicyRepository" should "create a new policy" in {
    val policyRepository = new PolicyRepository()
    val policy = Policy(Car(0), Legal, Driver(0, 0))

    val newId = policyRepository.createPolicy(policy)

    policyRepository.find(newId).value should be(policy)
  }

  it should "register an event at creation" in {
    val policyRepository = new PolicyRepository()
    val policy = Policy(Car(0), Legal, Driver(0, 0))

    val newId = policyRepository.createPolicy(policy)

    policyRepository.events should have size 1
    policyRepository.events.headOption.value should be(s"""{
                                                          |  "operation": "createPolicy",
                                                          |  "input": [
                                                          |${policy.toJson()}
                                                          |  ],
                                                          |  "output": [
                                                          |    "$newId"
                                                          |  ]
                                                          |}""".stripMargin)
  }
