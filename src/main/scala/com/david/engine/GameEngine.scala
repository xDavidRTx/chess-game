package com.david.engine

import com.david.domain.{Black, Board, Color, Move, Piece, Position, White}
import com.david.domain.pieces.King
import com.david.reader.UserInput

import scala.annotation.tailrec
import scala.collection.mutable

object GameEngine {

  type ChessBoard = mutable.HashMap[Position, Piece]
  private val columns = 1 to 8
  private val rows = 1 to 8

  def startGame(userInput: UserInput): Unit = {
    interactiveLoop(userInput, White, Board.initializeBoard())
  }

  @tailrec
  def interactiveLoop(userInput: UserInput, currentPlayer: Color, board: Board): Unit = {
    printBoard(board.chessBoard)
    if (inCheck(board, currentPlayer)) println("In Check!!")
    waitForNextMove()
    userInput.nextMove() match {
      case array: Array[Int] if array.size == 4 =>
        clearConsole()
        val move = Move(array)
        if (validateMove(move, currentPlayer, board.chessBoard)) {
          // If the move is valid we do the change
          val updatedBoard = movePieces(move, board, board.chessBoard(move.startPosition))
          if (inCheck(updatedBoard, currentPlayer)) {
            println("The player can not end his play in check! Please repeat you play!")
            interactiveLoop(userInput, currentPlayer, board)
          } else interactiveLoop(userInput, getOpponentColor(currentPlayer), updatedBoard)
        } else {
          // If not the current player does not change and we try the next
          println(s"Invalid move! The $currentPlayer needs to try again! ")
          interactiveLoop(userInput, currentPlayer, board)
        }
      case null => println("No more plays in the input file")
      case _ => println("Invalid input")
    }
  }

  def movePieces(move: Move, board: Board, piece: Piece): Board = {
    board.chessBoard.update(move.destinationPosition, piece) //This also removes the opponent piece if any
    board.chessBoard.remove(move.startPosition)
    //Updates the king position on the board
    piece.pieceType match {
      case King =>
        if (piece.color equals White) board.copy(whiteKing = move.destinationPosition)
        else board.copy(blackKing = move.destinationPosition)
      case _ => board
    }
  }

  // Print to the console the current state of the board
  private def printBoard(chessBoard: ChessBoard): Unit = {
    val gridLine = "|-----|-----|-----|-----|-----|-----|-----|-----|"
    println(gridLine)
    for (row <- rows.reverse) {
      print('|')
      for (col <- columns) {
        print(s"  ${chessBoard.get(Position(col, row)).map(_.getName).getOrElse(' ')}  |")
      }
      print(System.lineSeparator)
      println(gridLine)
    }
  }

  def validateMove(move: Move, currentPlayer: Color, chessBoard: ChessBoard): Boolean = {
    // Collection of rules that we need to check to validate the move
    Vector(
      columns.contains(move.destinationPosition.column) && rows.contains(move.destinationPosition.row), //Destination inside the board
      chessBoard.isDefinedAt(move.startPosition), //There is a piece at the starting point
      chessBoard.get(move.startPosition).map(_.color).contains(currentPlayer), //the piece owner is the current player
      !chessBoard.isDefinedAt(move.destinationPosition) ||
        chessBoard.get(move.destinationPosition).map(_.color).contains(getOpponentColor(currentPlayer)), // Opponent or empty at
      chessBoard.get(move.startPosition).exists(_.pieceType.validMove(move, chessBoard) //Verify specific piece logic
      )
    ).forall(identity)
  }

  // Check if its possible for the king to be captured on next move
  def inCheck(board: Board, color: Color): Boolean = {
    board.chessBoard.iterator
      .filter { case (_, piece) => getOpponentColor(piece.color) equals color }
      .map { case (pos, piece: Piece) =>
        val kingPosition = if (color equals White) board.whiteKing else board.blackKing
        piece.pieceType.validMove(Move(pos, kingPosition), board.chessBoard)
      }
      .exists(identity)
  }

  //Small hack to give some space between the boards
  private def clearConsole(): Unit = (1 to 15).foreach(_ => print("\n"))

  private def waitForNextMove(): Unit = {
    println("Waiting 5 seconds for the next move read: ")
    (5 to 1 by -1).foreach { i =>
      Thread.sleep(1000)
      print(s"$i seconds left!  ")
    }
    print("\n")
  }

  private def getOpponentColor(color: Color): Color = {
    color match {
      case White => Black
      case Black => White
    }
  }
}
