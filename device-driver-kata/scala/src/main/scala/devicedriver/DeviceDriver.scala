package devicedriver

class DeviceDriver(flashMemoryDevice: FlashMemoryDevice) {

  def read(address: Long): Int = flashMemoryDevice.read(address)

  def write(address: Long, data: Int): Unit = {
    flashMemoryDevice.write(0x00, 0X40)
    flashMemoryDevice.write(address, data.toChar)
    var readValue: Char = 'a'
    do {
      readValue = flashMemoryDevice.read(0x00)
    } while (((readValue >> 6) & 1) != 1)
    if (flashMemoryDevice.read(address) != data) {
      println("error")
    }
    ()
  }

}
