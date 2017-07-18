## Labyrinth ##

Le programme :

Votre programme doit déterminer toutes les sorties possibles d'un labyrinthe.
Le labyrinthe peut avoir plusieurs sorties ou aucune, et il peut contenir des
boucles ou des parties non atteignables depuis le point de départ.

### Entrée ###

 * Ligne 1: deux entiers `W` et `H` représentant la largeur et la hauteur du
   labyrinthe.
 * Ligne 2: deux entiers `X` et `Y` représentant votre position de départ dans
   le labyrinthe.
 * Les `H` lignes suivantes : une ligne du labyrinthe. Un `.` représente une
   case vide et un `#` représente un mur qui ne peut pas être traversé.

### Sortie ###

 * Ligne 1 : un entier `N` représentant le nombre de sorties.
 * Les `N` lignes suivantes : deux entiers `EX` et `EY` représentant les
   coordonnées de chaque sortie, triées par `EX` puis par `EY` (i.e. `2 10`
   vient avant `5 1`).

### Contraintes ###

```
7<=W<=21
7<=H<=21
```

### Exemples ###

#### Unique sortie ####

```
7 7
1 1
#######
#.....#
#####.#
#.#...#
#.#.###
#......
#######
```

```
1
6 5
```

#### Plusieurs sorties ####

```
11 11
5 5
###########
......#...#
#.###.###.#
#...#.....#
#.#.#######
#.#...#...#
#####.###.#
#...#.....#
#.#######.#
#..........
###########
```

```
2
0 1
10 9
```

#### Pas de sortie ###

```
11 11
5 5
###########
#.....#...#
#.###.###.#
#...#.....#
#.#.#######
#.#...#...#
#####.###.#
....#.....#
#.#########
#..........
###########
```

```
0
```

#### Boucles ####

```
11 11
5 5
###########
......#...#
#.###.###.#
#...#.....#
#.#.#.##.##
#.#...#...#
#.###.###.#
#...#.....#
#.###.###.#
#.........#
###########
```

```
1
0 1
```

#### Tout ensemble, 21x21 ####

```
21 21
9 10
##########.##########
..#...........#.....#
#.#.#########.#.###.#
#...#.........#.#...#
###############.#.###
#.....#.......#.#...#
#.#######.###.#.#.#.#
#...#...#...#...#.#..
###.###.###.###.#.#.#
#.#.#.#...#.#...#.#.#
#.#.#.#.#.#.#.###.#.#
#...#.#.#.#.#...#.#.#
#####.###.#.#####.###
#.#.......#.#...#...#
#.#.#######.#.#.###.#
#.#.#...#...#.#.#...#
#.#.###.#.#####.#####
#.#.................#
#.#######.#########.#
#.........#..........
####.######.#########
```

```
4
4 20
11 20
20 7
20 19
```
