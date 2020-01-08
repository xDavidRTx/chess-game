package com.david.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class MoveUnitSpec extends AnyFlatSpec with Matchers {

  "The val distance" should "be correctly calculated" in {
    val moveWithDistance1 = Move(Position(1, 1), Position(0, 1))
    val moveWithDistance2 = Move(Position(1, 1), Position(3, 3))
    val moveWithDistance5 = Move(Position(1, 1), Position(6, 1))

    moveWithDistance1.distance must be(1)
    moveWithDistance2.distance must be(2)
    moveWithDistance5.distance must be(5)
  }
}
