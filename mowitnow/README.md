La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.

La tondeuse peut être programmée pour parcourir l’intégralité de la surface.
La position de la tondeuse est représentée par une combinaison de coordonnées (x, y) et d’une lettre indiquant l’orientation selon la notation cardinale anglaise (N, E, W, S). La pelouse est divisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut-être « 0, 0, N », ce qui signifie qu’elle se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A » signifie que l’on avance la tondeuse d’une case dans la direction à laquelle elle fait face, et sans modifier son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante.

On assume que la case directement au Nord de la position (x, y) a pour coordonnées (x, y + 1).

Pour programmer la tondeuse, on lui fournit un fichier d’entrée construit comme suit :

- La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles du coin inférieur gauche sont supposées être (0,0)
- La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque tondeuse a deux lignes la concernant :
- la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l’orientation sont fournies sous la forme de 2 chiffres et une lettre, séparés par un espace
- la seconde ligne est une série d’instructions ordonnant à la tondeuse d’explorer la pelouse. Les instructions sont une suite de caractères sans espaces.

Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa série d’instructions.

Lorsqu’une tondeuse achève une série d’instructions, elle communique sa position et son orientation.

## Objectif

Concevoir et écrire un programme s’exécutant sur une JVM ≥ 1.7, un navigateur web ou un serveur node.js, et implémentant la spécification ci-dessus et passant le test ci-après

## Test

Le fichier suivant est fourni en entrée :

```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
```

On attend le résultat suivant (position finale des tondeuses) :

```
1 3 N
5 1 E
```

NB : Les données en entrée peuvent être injectées sous une autre forme qu’un fichier (par exemple un test automatisé).