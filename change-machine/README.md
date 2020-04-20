Given a currency spec and an amount, you have to return how many each bank notes and coins it takes.

Currency spec is a string that describes coins and bank notes in ascending order. Example for Euros:

```
1 2 5 10 20 50 100 200 500
```

Return is a string with number of bank notes or coins plus `x` plus bank note or coin in descending order. Example for
285€:

```
1x200 1x50 1x20 1x10 1x5
```
