package com.david.domain.pieces

import com.david.domain.{Direction, Move}
import com.david.domain.Directions.{E, N, NE, NW, S, SE, SW, W}
import com.david.engine.GameEngine.ChessBoard

case object King extends PieceType {
  override val displayName: Char = 'K'
  override val allowedDirections: Set[Direction] = Set(N, NE, NW, E, S, SE, SW, W)

  override def validMove(move: Move, board: ChessBoard): Boolean = super.validMove(move, board) && move.distance == 1
}

