Device Driver Kata
==================

In this Kata, your job is to implement the device driver that operates a flash memory device. The protocol for talking to the hardware is outlined below. It's a simplified version of a protocol used in a real device. The code you develop should allow the operating system to both read and write binary data to and from the device.

You will of course want to develop test cases alongside the code, but because you don't have access to the actual device, you will need to replace it with a test double. The device driver code should not be aware it's talking to a test double instead of the real device, only your tests should know this.

Flash Memory Device Protocol
----------------------------

When you want to write data to the device:

  - Begin by writing the 'Program' command, 0x40 to address 0x0
  - Then make a call to write the data to the address you want to write to.
  - Then read the value in address 0x0 and check bit 7 in the returned data, also known as the 'ready bit'. Repeat the read operation until the ready bit is set to 1. This means the write operation is complete. In a typical device it should take around ten microseconds, but it will vary from write to write.
  - There could have been an error, so in the value from address 0x0, examine the contents of the other bits. If all of them are set to 0 then the operation was successful.
  - You should then make a read from the memory address you previously set, in order to check it returns the value you set.
  - If that is successful, then you can assume the whole write operation was successful.

In the case of an error, the device sets the one of the other bits in the data at location 0x0, that is, bit 3, 4 or 5. The meaning of the error codes is as follows:

  - bit 3: Vpp error. The voltage on the device was wrong and it is now physically damaged.
  - bit 4: internal error. Something went wrong this time but next time it might work.
  - bit 5: protected block error. You cannot write to that address, but other addresses may work.

If any of these error bits are set, you must write 0xFF to address 0x0 before the device will accept any new write requests.
This will reset the error bits to zero. Note that until the 'ready bit' is set, then you will not get valid values for any of the error bits.

When you want to read data from the device:

  - simply make a call to read the contents of the address. There is no need to begin by writing the 'Program' command.

Credits
-------

[Device Driver Kata](https://github.com/emilybache/Device-Driver-Kata)
