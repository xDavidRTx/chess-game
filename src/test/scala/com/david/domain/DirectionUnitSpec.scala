package com.david.domain

import com.david.domain.Directions.{N, NE, NW, S, SE, SW}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class DirectionUnitSpec extends AnyFlatSpec with Matchers {

  "getDirectionsOffset" should "return the offset for a specific direction" in {
    Directions.getDirectionOffset(N) must be (0,1)
    Directions.getDirectionOffset(S) must be (0,-1)
    Directions.getDirectionOffset(NW) must be (-1, 1)
    Directions.getDirectionOffset(SW) must be (-1,-1)
  }

  "getDirection" should "calculate te direction of the piece movement" in {
    val moveNorth = Move(Position(2, 3), Position(2, 6))
    val moveSouthEast = Move(Position(2, 3), Position(3, 2))
    val moveNorthEast = Move(Position(4, 4), Position(6, 6))

    Directions.getDirection(moveNorth).get must be (N)
    Directions.getDirection(moveSouthEast).get must be (SE)
    Directions.getDirection(moveNorthEast).get must be (NE)
  }

}
