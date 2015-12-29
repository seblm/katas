## Advent of Code ##

Brilliant challenges from [this website](http://adventofcode.com).

## Day 1: Not Quite Lisp ##

### Part one ###

Santa is trying to deliver presents in a large apartment building, but he can't
find the right floor - the directions he got are a little confusing. He starts
on the ground floor (floor `0`) and then follows the instructions one character
at a time.

An opening parenthesis, `(`, means he should go up one floor, and a closing
parenthesis, `)`, means he should go down one floor.

The apartment building is very tall, and the basement is very deep; he will
never find the top or bottom floors.

For example:

 * `(())` and `()()` both result in floor `0`.
 * `(((` and `(()(()(` both result in floor `3`.
 * `))(((((` also results in floor `3`.
 * `())` and `))(` both result in floor `-1` (the first basement level).
 * `)))` and `)())())` both result in floor `-3`.

To *what floor* do the instructions take Santa?

### Part two ###

Now, given the same instructions, find the *position* of the first character
that causes him to enter the basement (floor `-1`). The first character in the
instructions has position `1`, the second character has position `2`, and so
on.

For example:

 * `)` causes him to enter the basement at character position `1`.
 * `()())` causes him to enter the basement at character position `5`.

What is the *position* of the character that causes Santa to first enter the
basement?

## Day 2: I Was Told There Would Be No Math ##

### Part One ###

The elves are running low on wrapping paper, and so they need to submit an
order for more. They have a list of the dimensions (length `l`, width `w`, and
height `h`) of each present, and only want to order exactly as much as they need.

Fortunately, every present is a box (a perfect
[right rectangular prism](https://en.wikipedia.org/wiki/Cuboid#Rectangular_cuboid)),
which makes calculating the required wrapping paper for each gift a little
easier: find the surface area of the box, which is `2*l*w + 2*w*h + 2*h*l`. The
elves also need a little extra paper for each present: the area of the smallest side.

For example:

 * A present with dimensions `2x3x4` requires `2*6 + 2*12 + 2*8 = 52` square
   feet of wrapping paper plus `6` square feet of slack, for a total of `58`
   square feet.
 * A present with dimensions `1x1x10` requires `2*1 + 2*10 + 2*10 = 42` square
   feet of wrapping paper plus `1` square foot of slack, for a total of `43`
   square feet.

All numbers in the elves' list are in feet. How many total *square feet of
wrapping paper* should they order?

### Part Two ###

The elves are also running low on ribbon. Ribbon is all the same width, so they
only have to worry about the length they need to order, which they would again
like to be exact.

The ribbon required to wrap a present is the shortest distance around its
sides, or the smallest perimeter of any one face. Each present also requires a
bow made out of ribbon as well; the feet of ribbon required for the perfect bow
is equal to the cubic feet of volume of the present. Don't ask how they tie the
bow, though; they'll never tell.

For example:

 * A present with dimensions `2x3x4` requires `2+2+3+3 = 10` feet of ribbon to
   wrap the present plus `2*3*4 = 24` feet of ribbon for the bow, for a total
   of `34` feet.
 * A present with dimensions `1x1x10` requires `1+1+1+1 = 4` feet of ribbon to
   wrap the present plus `1*1*10 = 10` feet of ribbon for the bow, for a total
   of `14` feet.

How many total *feet of ribbon* should they order?

## Day 3: Perfectly Spherical Houses in a Vacuum ##

### Part One ###

Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and
then an elf at the North Pole calls him via radio and tells him where to move
next. Moves are always exactly one house to the north (`^`), south (`v`), east
(`>`), or west (`<`). After each move, he delivers another present to the house
at his new location.

However, the elf back at the north pole has had a little too much eggnog, and
so his directions are a little off, and Santa ends up visiting some houses more
than once. How many houses receive *at least one present*?

For example:

 * `>` delivers presents to `2` houses: one at the starting location, and one
   to the east.
 * `^>v<` delivers presents to `4` houses in a square, including twice to the
   house at his starting/ending location.
 * `^v^v^v^v^v` delivers a bunch of presents to some very lucky children at
   only `2` houses.

### Part Two ###

The next year, to speed up the process, Santa creates a robot version of
himself, *Robo-Santa*, to deliver presents with him.

Santa and Robo-Santa start at the same location (delivering two presents to the
same starting house), then take turns moving based on instructions from the
elf, who is eggnoggedly reading from the same script as the previous year.

This year, how many houses receive *at least one present*?

For example:

 * `^v` delivers presents to `3` houses, because Santa goes north, and then
   Robo-Santa goes south.
 * `^>v<` now delivers presents to `3` houses, and Santa and Robo-Santa end up
   back where they started.
 * `^v^v^v^v^v` now delivers presents to `11` houses, with Santa going one
   direction and Robo-Santa going the other.

## Day 4: The Ideal Stocking Stuffer ##

Santa needs help [mining](https://en.wikipedia.org/wiki/Bitcoin#Mining) some
AdventCoins (very similar to [bitcoins](https://en.wikipedia.org/wiki/Bitcoin))
to use as gifts for all the economically forward-thinking little girls and
boys.

To do this, he needs to find [MD5](https://en.wikipedia.org/wiki/MD5) hashes
which, in [hexadecimal](https://en.wikipedia.org/wiki/Hexadecimal), start with
at least *five zeroes*. The input to the MD5 hash is some secret key (your
puzzle input, given below) followed by a number in decimal. To mine
AdventCoins, you must find Santa the lowest positive number (no leading zeroes:
`1`, `2`, `3`, ...) that produces such a hash.

For example:

 * If your secret key is `abcdef`, the answer is `609043`, because the MD5 hash
   of `abcdef609043` starts with five zeroes (`000001dbbfa...`), and it is the
   lowest such number to do so.
 * If your secret key is `pqrstuv`, the lowest number it combines with to make
   an MD5 hash starting with five zeroes is `1048970`; that is, the MD5 hash of
   `pqrstuv1048970` looks like `000006136ef...`.

### Part Two ###

Now find one that starts with *six zeroes*.

## Day 5: Doesn't He Have Intern-Elves For This? ##

### Part One ###

Santa needs help figuring out which strings in his text file are naughty or
nice.

A *nice string* is one with all of the following properties:

 * It contains at least three vowels (`aeiou` only), like `aei`, `xazegov`, or
   `aeiouaeiouaeiou`.
 * It contains at least one letter that appears twice in a row, like `xx`,
   `abcdde` (`dd`), or `aabbccdd` (`aa`, `bb`, `cc`, or `dd`).
 * It does not contain the strings `ab`, `cd`, `pq`, or `xy`, even if they are
   part of one of the other requirements.

For example:

 * `ugknbfddgicrmopn` is nice because it has at least three vowels
   (`u...i...o...`), a double letter (`...dd...`), and none of the disallowed
   substrings.
 * `aaa` is nice because it has at least three vowels and a double letter, even
   though the letters used by different rules overlap.
 * `jchzalrnumimnmhp` is naughty because it has no double letter.
 * `haegwjzuvuyypxyu` is naughty because it contains the string xy.
 * `dvszwmarrgswjxmb` is naughty because it contains only one vowel.

How many strings are nice?

### Part Two ###

Realizing the error of his ways, Santa has switched to a better model of
determining whether a string is naughty or nice. None of the old rules apply,
as they are all clearly ridiculous.

Now, a nice string is one with all of the following properties:

 * It contains a pair of any two letters that appears at least twice in the
   string without overlapping, like `xyxy` (`xy`) or `aabcdefgaa` (`aa`), but
   not like `aaa` (`aa`, but it overlaps).
 * It contains at least one letter which repeats with exactly one letter
   between them, like `xyx`, `abcdefeghi` (`efe`), or even `aaa`.

For example:

 * `qjhvhtzxzqqjkmpb` is nice because is has a pair that appears twice (`qj`)
   and a letter that repeats with exactly one letter between them (`zxz`).
 * `xxyxx` is nice because it has a pair that appears twice and a letter that
   repeats with one between, even though the letters used by each rule overlap.
 * `uurcxstgmygtbstg` is naughty because it has a pair (`tg`) but no repeat
   with a single letter between them.
 * `ieodomkazucvgmuy` is naughty because it has a repeating letter with one
   between (`odo`), but no pair that appears twice.

How many strings are nice under these new rules?

## Day 6: Probably a Fire Hazard ##

### Part One ###

Because your neighbors keep defeating you in the holiday house decorating
contest year after year, you've decided to deploy one million lights in a
1000x1000 grid.

Furthermore, because you've been especially nice this year, Santa has mailed
you instructions on how to display the ideal lighting configuration.

Lights in your grid are numbered from 0 to 999 in each direction; the lights at
each corner are at `0,0`, `0,999`, `999,999`, and `999,0`. The instructions
include whether to `turn on`, `turn off`, or `toggle` various inclusive ranges
given as coordinate pairs. Each coordinate pair represents opposite corners of
a rectangle, inclusive; a coordinate pair like `0,0` through `2,2` therefore
refers to 9 lights in a 3x3 square. The lights all start turned off.

To defeat your neighbors this year, all you have to do is set up your lights by
doing the instructions Santa sent you in order.

For example:

 * `turn on 0,0 through 999,999` would turn on (or leave on) every light.
 * `toggle 0,0 through 999,0` would toggle the first line of 1000 lights,
   turning off the ones that were on, and turning on the ones that were off.
 * `turn off 499,499 through 500,500` would turn off (or leave off) the middle
   four lights.

After following the instructions, *how many lights are lit*?

### Part Two ###

You just finish implementing your winning light pattern when you realize you
mistranslated Santa's message from Ancient Nordic Elvish.

The light grid you bought actually has individual brightness controls; each
light can have a brightness of zero or more. The lights all start at zero.

The phrase `turn on` actually means that you should increase the brightness of
those lights by `1`.

The phrase `turn off` actually means that you should decrease the brightness of
those lights by `1`, to a minimum of zero.

The phrase `toggle` actually means that you should increase the brightness of
those lights by `2`.

What is the *total brightness* of all lights combined after following Santa's
instructions?

For example:

 * `turn on 0,0 through 0,0` would increase the total brightness by `1`.
 * `toggle 0,0 through 999,999` would increase the total brightness by
   `2000000`.

## Day 7: Some Assembly Required ##

### Part One ###

This year, Santa brought little Bobby Tables a set of wires and
[bitwise logic gates](https://en.wikipedia.org/wiki/Bitwise_operation)!
Unfortunately, little Bobby is a little under the recommended age range, and he
needs help assembling the circuit.

Each wire has an identifier (some lowercase letters) and can carry a
[16-bit](https://en.wikipedia.org/wiki/16-bit) signal (a number from `0` to
`65535`). A signal is provided to each wire by a gate, another wire, or some
specific value. Each wire can only get a signal from one source, but can
provide its signal to multiple destinations. A gate provides no signal until
all of its inputs have a signal.

The included instructions booklet describes how to connect the parts together:
`x AND y -> z` means to connect wires `x` and `y` to an AND gate, and then
connect its output to wire `z`.

For example:

 * `123 -> x` means that the signal `123` is provided to wire `x`.
 * `x AND y -> z` means that the
   [bitwise AND](https://en.wikipedia.org/wiki/Bitwise_operation#AND) of wire
   `x` and wire `y` is provided to wire `z`.
 * `p LSHIFT 2 -> q` means that the value from wire `p` is
   [left-shifted](https://en.wikipedia.org/wiki/Logical_shift) by `2` and then
   provided to wire `q`.
 * `NOT e -> f` means that the
   [bitwise complement](https://en.wikipedia.org/wiki/Bitwise_operation#NOT) of
   the value from wire `e` is provided to wire `f`.

Other possible gates include `OR`
([bitwise OR](https://en.wikipedia.org/wiki/Bitwise_operation#OR)) and `RSHIFT`
([right-shift](https://en.wikipedia.org/wiki/Logical_shift)). If, for some
reason, you'd like to *emulate* the circuit instead, almost all programming
languages (for example,
[C](https://en.wikipedia.org/wiki/Bitwise_operations_in_C),
[JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Bitwise_Operators),
or [Python](https://wiki.python.org/moin/BitwiseOperators)) provide operators
for these gates.

For example, here is a simple circuit:

```
123 -> x
456 -> y
x AND y -> d
x OR y -> e
x LSHIFT 2 -> f
y RSHIFT 2 -> g
NOT x -> h
NOT y -> i
```

After it is run, these are the signals on the wires:

```
d: 72
e: 507
f: 492
g: 114
h: 65412
i: 65079
x: 123
y: 456
```

In little Bobby's kit's instructions booklet (provided as your puzzle input),
what signal is ultimately provided to *wire `a`*?

### Part Two ###

Now, take the signal you got on wire `a`, override wire `b` to that signal, and
reset the other wires (including wire `a`). What new signal is ultimately
provided to wire `a`?
