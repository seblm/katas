package name.lemerdy.sebastian.asciiart

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import java.io.*
import scala.Console.{withIn, withOut}

class SolutionSpec extends AnyFlatSpec:

  "Solution" should "Test une seul lettre : E" in:
    val in: InputStream = new ByteArrayInputStream(
      """4
        |5
        |E
        | #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
        |# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
        |### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
        |# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
        |# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  """.stripMargin.getBytes
    )
    val out: OutputStream = new ByteArrayOutputStream()

    withIn(in):
      withOut(out):
        Solution.printArt()

    out.toString should be("""### 
                             |#   
                             |##  
                             |#   
                             |### 
                             |""".stripMargin)

  it should "Test MANHATTAN" in:
    val in: InputStream = new ByteArrayInputStream(
      """4
        |5
        |MANHATTAN
        | #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
        |# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
        |### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
        |# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
        |# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  """.stripMargin.getBytes
    )
    val out: OutputStream = new ByteArrayOutputStream()

    withIn(in):
      withOut(out):
        Solution.printArt()

    out.toString should be(
      """# #  #  ### # #  #  ### ###  #  ### 
        |### # # # # # # # #  #   #  # # # # 
        |### ### # # ### ###  #   #  ### # # 
        |# # # # # # # # # #  #   #  # # # # 
        |# # # # # # # # # #  #   #  # # # # 
        |""".stripMargin
    )

  it should "Test ManhAtTan" in:
    val in: InputStream = new ByteArrayInputStream(
      """4
        |5
        |ManhAtTan
        | #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
        |# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
        |### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
        |# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
        |# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  """.stripMargin.getBytes
    )
    val out: OutputStream = new ByteArrayOutputStream()

    withIn(in):
      withOut(out):
        Solution.printArt()

    out.toString should be(
      """# #  #  ### # #  #  ### ###  #  ### 
        |### # # # # # # # #  #   #  # # # # 
        |### ### # # ### ###  #   #  ### # # 
        |# # # # # # # # # #  #   #  # # # # 
        |# # # # # # # # # #  #   #  # # # # 
        |""".stripMargin
    )

  it should "Test M@NH@TT@N" in:
    val in: InputStream = new ByteArrayInputStream(
      """4
        |5
        |M@NH@TT@N
        | #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
        |# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
        |### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
        |# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
        |# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  """.stripMargin.getBytes
    )
    val out: OutputStream = new ByteArrayOutputStream()

    withIn(in):
      withOut(out):
        Solution.printArt()

    out.toString should be(
      """# # ### ### # # ### ### ### ### ### 
        |###   # # # # #   #  #   #    # # # 
        |###  ## # # ###  ##  #   #   ## # # 
        |# #     # # # #      #   #      # # 
        |# #  #  # # # #  #   #   #   #  # # 
        |""".stripMargin
    )

  it should "MANHATTAN avec une autre représentation ASCII" in:
    val in: InputStream = new ByteArrayInputStream(
      """20
        ~11
        ~MANHATTAN
        ~ .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
        ~| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
        ~| |      __      | || |   ______     | || |     ______   | || |  ________    | || |  _________   | || |  _________   | || |    ______    | || |  ____  ____  | || |     _____    | || |     _____    | || |  ___  ____   | || |   _____      | || | ____    ____ | || | ____  _____  | || |     ____     | || |   ______     | || |    ___       | || |  _______     | || |    _______   | || |  _________   | || | _____  _____ | || | ____   ____  | || | _____  _____ | || |  ____  ____  | || |  ____  ____  | || |   ________   | || |    ______    | |
        ~| |     /  \     | || |  |_   _ \    | || |   .' ___  |  | || | |_   ___ `.  | || | |_   ___  |  | || | |_   ___  |  | || |  .' ___  |   | || | |_   ||   _| | || |    |_   _|   | || |    |_   _|   | || | |_  ||_  _|  | || |  |_   _|     | || ||_   \  /   _|| || ||_   \|_   _| | || |   .'    `.   | || |  |_   __ \   | || |  .'   '.     | || | |_   __ \    | || |   /  ___  |  | || | |  _   _  |  | || ||_   _||_   _|| || ||_  _| |_  _| | || ||_   _||_   _|| || | |_  _||_  _| | || | |_  _||_  _| | || |  |  __   _|  | || |   / _ __ `.  | |
        ~| |    / /\ \    | || |    | |_) |   | || |  / .'   \_|  | || |   | |   `. \ | || |   | |_  \_|  | || |   | |_  \_|  | || | / .'   \_|   | || |   | |__| |   | || |      | |     | || |      | |     | || |   | |_/ /    | || |    | |       | || |  |   \/   |  | || |  |   \ | |   | || |  /  .--.  \  | || |    | |__) |  | || | /  .-.  \    | || |   | |__) |   | || |  |  (__ \_|  | || | |_/ | | \_|  | || |  | |    | |  | || |  \ \   / /   | || |  | | /\ | |  | || |   \ \  / /   | || |   \ \  / /   | || |  |_/  / /    | || |  |_/____) |  | |
        ~| |   / ____ \   | || |    |  __'.   | || |  | |         | || |   | |    | | | || |   |  _|  _   | || |   |  _|      | || | | |    ____  | || |   |  __  |   | || |      | |     | || |   _  | |     | || |   |  __'.    | || |    | |   _   | || |  | |\  /| |  | || |  | |\ \| |   | || |  | |    | |  | || |    |  ___/   | || | | |   | |    | || |   |  __ /    | || |   '.___`-.   | || |     | |      | || |  | '    ' |  | || |   \ \ / /    | || |  | |/  \| |  | || |    > `' <    | || |    \ \/ /    | || |     .'.' _   | || |    /  ___.'  | |
        ~| | _/ /    \ \_ | || |   _| |__) |  | || |  \ `.___.'\  | || |  _| |___.' / | || |  _| |___/ |  | || |  _| |_       | || | \ `.___]  _| | || |  _| |  | |_  | || |     _| |_    | || |  | |_' |     | || |  _| |  \ \_  | || |   _| |__/ |  | || | _| |_\/_| |_ | || | _| |_\   |_  | || |  \  `--'  /  | || |   _| |_      | || | \  `-'  \_   | || |  _| |  \ \_  | || |  |`\____) |  | || |    _| |_     | || |   \ `--' /   | || |    \ ' /     | || |  |   /\   |  | || |  _/ /'`\ \_  | || |    _|  |_    | || |   _/ /__/ |  | || |    |_|       | |
        ~| ||____|  |____|| || |  |_______/   | || |   `._____.'  | || | |________.'  | || | |_________|  | || | |_____|      | || |  `._____.'   | || | |____||____| | || |    |_____|   | || |  `.___.'     | || | |____||____| | || |  |________|  | || ||_____||_____|| || ||_____|\____| | || |   `.____.'   | || |  |_____|     | || |  `.___.\__|  | || | |____| |___| | || |  |_______.'  | || |   |_____|    | || |    `.__.'    | || |     \_/      | || |  |__/  \__|  | || | |____||____| | || |   |______|   | || |  |________|  | || |    (_)       | |
        ~| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |
        ~| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
        ~ '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
        ~""".stripMargin('~').getBytes
    )
    val out: OutputStream = new ByteArrayOutputStream()

    withIn(in):
      withOut(out):
        Solution.printArt()

    out.toString should be(
      """ .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------.
        ~| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
        ~| | ____    ____ | || |      __      | || | ____  _____  | || |  ____  ____  | || |      __      | || |  _________   | || |  _________   | || |      __      | || | ____  _____  | |
        ~| ||_   \  /   _|| || |     /  \     | || ||_   \|_   _| | || | |_   ||   _| | || |     /  \     | || | |  _   _  |  | || | |  _   _  |  | || |     /  \     | || ||_   \|_   _| | |
        ~| |  |   \/   |  | || |    / /\ \    | || |  |   \ | |   | || |   | |__| |   | || |    / /\ \    | || | |_/ | | \_|  | || | |_/ | | \_|  | || |    / /\ \    | || |  |   \ | |   | |
        ~| |  | |\  /| |  | || |   / ____ \   | || |  | |\ \| |   | || |   |  __  |   | || |   / ____ \   | || |     | |      | || |     | |      | || |   / ____ \   | || |  | |\ \| |   | |
        ~| | _| |_\/_| |_ | || | _/ /    \ \_ | || | _| |_\   |_  | || |  _| |  | |_  | || | _/ /    \ \_ | || |    _| |_     | || |    _| |_     | || | _/ /    \ \_ | || | _| |_\   |_  | |
        ~| ||_____||_____|| || ||____|  |____|| || ||_____|\____| | || | |____||____| | || ||____|  |____|| || |   |_____|    | || |   |_____|    | || ||____|  |____|| || ||_____|\____| | |
        ~| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |
        ~| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
        ~ '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 
        ~""".stripMargin('~')
    )
