package devicedriver

trait FlashMemoryDevice {

  def read(address: Long): Char

  def write(address: Long, data: Char): Unit

}
