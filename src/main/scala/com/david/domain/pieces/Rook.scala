package com.david.domain.pieces

import com.david.domain.Direction
import com.david.domain.Directions.{E, N, S, W}

case object Rook extends PieceType {
  override val displayName: Char = 'R'
  override val allowedDirections: Set[Direction] = Set(N, E, S, W)
}
