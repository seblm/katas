package devicedriver

import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

class DeviceDriverSpec extends FeatureSpec with Matchers with MockitoSugar with GivenWhenThen {

  feature("Device Driver") {
    scenario("write data when everything works fine") {
      val (deviceDriver, mockMemoryDevice) = given_a_device_driver()
      given_flash_memory_device_read(mockMemoryDevice, 0x00,
        Integer.parseInt("0010010", 2).toChar,
        Integer.parseInt("0110101", 2).toChar,
        Integer.parseInt("1000000", 2).toChar
      )
      given_flash_memory_device_read(mockMemoryDevice, 0x01, 'd')

      when_device_driver_write(deviceDriver, 0x01, 'd')

      Then("flash memory device has received a write of 0x40 to 0x00 ('Program' command)")
      verify(mockMemoryDevice).write(0x00, 0x40)
      Then("flash memory device has received a write of 'd' to 0x01")
      verify(mockMemoryDevice).write(0x01, 'd')
      Then("flash memory device has received two reads from 0x00 until the third returns 1 on 'ready bit'")
      verify(mockMemoryDevice, Mockito.times(3)).read(0x00)
      Then("flash memory device has received a read from 0x01")
      verify(mockMemoryDevice).read(0x01)
    }

    scenario("write data then Vpp error") { // TODO
      info("Vpp error: the voltage on the device was wrong and it is now physically damaged")
      val (deviceDriver, mockMemoryDevice) = given_a_device_driver()
      given_flash_memory_device_read(mockMemoryDevice, 0x00,
        Integer.parseInt("0010010", 2).toChar,
        Integer.parseInt("0110101", 2).toChar,
        Integer.parseInt("1000000", 2).toChar
      )
      given_flash_memory_device_read(mockMemoryDevice, 0x01, 'd')

      when_device_driver_write(deviceDriver, 0x01, 'd')

      Then("flash memory device has received a write of 0x40 to 0x00 ('Program' command)")
      verify(mockMemoryDevice).write(0x00, 0x40)
      Then("flash memory device has received a write of 'd' to 0x01")
      verify(mockMemoryDevice).write(0x01, 'd')
      Then("flash memory device has received two reads from 0x00 until the third returns 1 on 'ready bit'")
      verify(mockMemoryDevice, Mockito.times(3)).read(0x00)
      Then("flash memory device has received a read from 0x01")
      verify(mockMemoryDevice).read(0x01)
    }

  }

  private def given_a_device_driver(): (DeviceDriver, FlashMemoryDevice) = {
    Given("a device driver")
    val mockMemoryDevice: FlashMemoryDevice = mock[FlashMemoryDevice]
    (new DeviceDriver(mockMemoryDevice), mockMemoryDevice)
  }

  private def given_flash_memory_device_read(mockMemoryDevice: FlashMemoryDevice, address: Long, value: Char, values: Char*): Unit = {
    Given(s"flash memory device returns ${(Seq(value) ++ values).map(_.toBinaryString).mkString(", ")} when reading 0x${address.toHexString}")
    given(mockMemoryDevice.read(address)).willReturn(value, values: _*)
    ()
  }

  private def when_device_driver_write(deviceDriver: DeviceDriver, address: Long, data: Int): Unit = {
    When(s"device driver write ${data.toBinaryString} to 0x${address.toHexString}")
    deviceDriver.write(address, data)
    ()
  }

}
