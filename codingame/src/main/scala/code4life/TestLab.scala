package code4life

class TestLab extends Lab {

  override def projectcount: Int = 0

  override def unknownabcde: Array[Int] = Array(0, 0, 0, 0, 0, 0)

  override def me: PlayerState = PlayerState(Me, "target", 0, 0, 0, 0, 0, 0, 0)

  override def dr: PlayerState = PlayerState(Dr, "target", 0, 0, 0, 0, 0, 0, 0)

  override def availables: Array[Int] = Array(0, 0, 0, 0, 0)

  override def samples: Seq[Sample] = ???
}
