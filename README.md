# Period Manager #

## Problem ##

Given a list of periods, we need to move one period by his begin date without
disturbing chronological order.

## What've we talking about ##

1. `period` : identified by a unique symbol and defined by a begin and an end
   date.
2. `list of periods` : chronologically sorted periods without overlap (i.e.
   each begin date's period is equal to end date's previous period) and with
   last period which must not have end date.

## Examples ##

Given this list of periods :

    0. 01/01/2000 - 01/02/2000
    1. 01/02/2000 - 01/03/2000
    2. 01/03/2000 - 01/04/2000
    3. 01/04/2000 - 01/05/2000
    4. 01/05/2000

Moving `2` to 15/12/1999 must reorder periods like this :

    2. 15/12/1999 - 01/01/2000
    0. 01/01/2000 - 01/02/2000
    1. 01/02/2000 - 01/04/2000
    3. 01/04/2000 - 01/05/2000
    4. 01/05/2000

Moving `2` to 15/01/2000 must reorder periods like this :

    0. 01/01/2000 - 15/01/2000
    2. 15/01/2000 - 01/02/2000
    1. 01/02/2000 - 01/04/2000
    3. 01/04/2000 - 01/05/2000
    4. 01/05/2000

Moving `2` to 15/05/2000 must reorder periods like this :

    0. 01/01/2000 - 01/02/2000
    1. 01/02/2000 - 01/04/2000
    3. 01/04/2000 - 01/05/2000
    4. 01/05/2000 - 15/05/2000
    2. 15/05/2000

## Solution ##

Here is a proposition of solution :

### Finds correct end date of the period to move ###

1. Get the period to move and remove it temporarily from the list
2. Set begin date of the period to move to new begin date
3. Set end date of the period to move to nothing
4. For each period (which are browsed chronolgically)
5.   If the current begin date is after the new begin date
6.     the end date of the period becomes current beginDate
7. Add the period to move again into the list

### Finds correct begin dates of all periods ###

1. For each period
2.   the end date of the previous periods becomes the begin date of the current
     period
