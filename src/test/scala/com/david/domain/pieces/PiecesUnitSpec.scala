package com.david.domain.pieces


import com.david.domain.{Move, Piece, Position, White}
import com.david.engine.GameEngine.ChessBoard
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class PiecesUnitSpec extends AnyFlatSpec with Matchers {

  "King" should "only move one square" in {
    val board = new ChessBoard().addOne((Position(1, 1), Piece(King, White)))
    val moveWithDistance1 = Move(Position(1, 1), Position(2, 1))
    val moveWithDistance2 = Move(Position(1, 1), Position(3, 3))
    King.validMove(moveWithDistance1, board) must be(true)
    King.validMove(moveWithDistance2, board) must be(false)
  }

  "Bishop" should "only move NE, NW, SE, SW" in {
    val board = new ChessBoard().addOne((Position(2, 2), Piece(Bishop, White)))
    val moveNorth = Move(Position(2, 2), Position(2, 6))
    val moveSouthEast = Move(Position(2, 2), Position(3, 3))
    val moveNorthEast = Move(Position(2, 2), Position(6, 6))

    Bishop.validMove(moveNorth, board) must be(false)
    Bishop.validMove(moveSouthEast, board) must be(true)
    Bishop.validMove(moveNorthEast, board) must be(true)
  }

  "Knight" should "only move in L" in {
    val board = new ChessBoard().addOne((Position(2, 2), Piece(Knight, White)))
    val moveL = Move(Position(2, 2), Position(4, 3))
    val moveNotL1 = Move(Position(2, 2), Position(6, 6))
    val moveNotL2 = Move(Position(2, 2), Position(2, 5))

    Knight.validMove(moveL, board) must be(true)
    Knight.validMove(moveNotL1, board) must be(false)
    Knight.validMove(moveNotL2, board) must be(false)
  }

  "Pawn" should "respect the directions and initial rules" in {
    val board = new ChessBoard().addOne((Position(2, 2), Piece(Pawn, White)))
    board.addOne((Position(4, 4), Piece(Pawn, White)))
    val moveNorth2Initial = Move(Position(2, 2), Position(2, 4))
    val moveNorth2 = Move(Position(2, 4), Position(2, 6))
    val moveNorth1 = Move(Position(2, 2), Position(2, 3))
    val moveSouth1 = Move(Position(2, 2), Position(2, 1))

    Pawn.validMove(moveNorth2Initial, board) must be(true) // At initial position the pawn can move 2 squares
    Pawn.validMove(moveNorth2, board) must be(false)
    Pawn.validMove(moveNorth1, board) must be(true)
    Pawn.validMove(moveSouth1, board) must be(false)
  }

  "Rook" should "only move E, N, S, W" in {
    val board = new ChessBoard().addOne((Position(2, 2), Piece(Rook, White)))
    val moveNorth = Move(Position(2, 2), Position(2, 6))
    val moveSouthEast = Move(Position(2, 2), Position(3, 3))
    val moveNorthEast = Move(Position(2, 2), Position(6, 6))

    Rook.validMove(moveNorth, board) must be(true)
    Rook.validMove(moveSouthEast, board) must be(false)
    Rook.validMove(moveNorthEast, board) must be(false)
  }
}
