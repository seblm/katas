package name.lemerdy.sebastian.changemachine

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class ChangeMachineSpec extends AnyFlatSpec:

  "ChangeMachine" should "change 285€ into notes and coins" in:
    ChangeMachine.change("1 2 5 10 20 50 100 200 500")(285) shouldEqual "1x200 1x50 1x20 1x10 1x5"

  it should "change 1888€ into notes and coins" in:
    ChangeMachine.change("1 2 5 10 20 50 100 200 500")(1888) shouldEqual "3x500 1x200 1x100 1x50 1x20 1x10 1x5 1x2 1x1"
