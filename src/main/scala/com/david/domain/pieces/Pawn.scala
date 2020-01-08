package com.david.domain.pieces

import com.david.domain.Directions.{N, NE, NW, S, SE, SW}
import com.david.domain.{Black, Direction, Directions, Move, White}
import com.david.engine.GameEngine.ChessBoard

case object Pawn extends PieceType {
  override val displayName: Char = 'P'
  override val allowedDirections: Set[Direction] = Set.empty //The directions depend on the board state

  override def validMove(move: Move, board: ChessBoard): Boolean = {

    board.isDefinedAt(move.startPosition) && {
      val maxDistance = if (isFirstMove(move, board)) 2 else 1
      val directions: Set[Direction] = board(move.startPosition).color match {
        case White => if (isPawnCapture(move, board)) Set(N, NW, NE) else Set(N)
        case Black => if (isPawnCapture(move, board)) Set(S, SW, SE) else Set(S)
      }

      move.distance <= maxDistance && Directions.getDirection(move).exists(dir => directions.contains(dir))
    }
  }

  private def isFirstMove(move: Move, board: ChessBoard): Boolean = board.get(move.startPosition).exists { piece =>
    piece.color match {
      case White => move.startPosition.row == 2
      case Black => move.startPosition.row == 7
    }
  }

  private def isPawnCapture(move: Move, board: ChessBoard): Boolean = board.isDefinedAt(move.destinationPosition)
}

