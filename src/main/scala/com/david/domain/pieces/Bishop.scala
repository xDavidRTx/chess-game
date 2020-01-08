package com.david.domain.pieces

import com.david.domain.Direction
import com.david.domain.Directions.{NE, NW, SE, SW}

case object Bishop extends PieceType {
  override val displayName: Char = 'B'
  override val allowedDirections: Set[Direction] = Set(NE, NW, SE, SW)
}

