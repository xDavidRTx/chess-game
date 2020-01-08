package com.david.domain.pieces

import com.david.domain.{Direction, Move}
import com.david.engine.GameEngine.ChessBoard

case object Knight extends PieceType {
  override val displayName: Char = 'N'
  override val allowedDirections: Set[Direction] = Set.empty //This Piece does not follow the standard moves

  // this piece moves in L shape ie 2 in one direction 1 in the other
  override def validMove(move: Move, board: ChessBoard): Boolean = {
    Vector((2, 1), (1, 2)).contains(
      (Math.abs(move.startPosition.column - move.destinationPosition.column), Math.abs(move.startPosition.row - move.destinationPosition.row)))
  }
}

