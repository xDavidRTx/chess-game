import com.david.engine.GameEngine
import com.david.reader.{UserInput, UserInputFile}

import scala.util.Try

object ChessGame extends App {

  val inputFile = "src/main/resources/checkmate.txt"
  val userInput: Option[UserInput] = Try(new UserInputFile(inputFile)).toOption

  userInput match {
    case None => println(s"Unable to find $inputFile, exiting game!")
    case Some(moves) => GameEngine.startGame(moves)
  }
}
