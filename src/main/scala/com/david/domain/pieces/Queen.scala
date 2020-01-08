package com.david.domain.pieces

import com.david.domain.Direction
import com.david.domain.Directions.{E, N, NE, NW, S, SE, SW, W}

case object Queen extends PieceType {
  override val displayName: Char = 'Q'
  override val allowedDirections: Set[Direction] = Set(N, NE, NW, E, S, SE, SW, W)
}

