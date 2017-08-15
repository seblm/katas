# Objectif

Dans les gares et aéroports on croise souvent ce type d'écran :

Vous êtes-vous demandé comment il serait possible de simuler cet affichage dans un bon vieux terminal ? Nous oui : avec l'art ASCII !

# Règles

L'art ASCII permet de représenter des formes en utilisant des caractères. Dans notre cas, ces formes sont précisément des mots. Par exemple, le mot "MANHATTAN" pourra être affiché ainsi en art ASCII :

```
# #  #  ### # #  #  ### ###  #  ###
### # # # # # # # #  #   #  # # # #
### ### # # ### ###  #   #  ### # #
# # # # # # # # # #  #   #  # # # #
# # # # # # # # # #  #   #  # # # #
```
 
​Votre mission : Ecrire un programme capable d'afficher une ligne de texte en art ASCII dans un style qui vous est fourni en entrée.

# Entrées du jeu

## Entrée

Ligne 1 : la largeur L d'une lettre représentée en art ASCII. Toutes les lettres font la même largeur.

Ligne 2 : la hauteur H d'une lettre représentée en art ASCII. Toutes les lettres font la même hauteur.

Ligne 3 : La ligne de texte T, composée de N caractères ASCII

Lignes suivantes : La chaîne de caractères N représentée en art ASCII.

## Sortie

Le texte T en art ASCII.

Les caractères de a à z seront affichés en art ASCII par leur équivalent en majuscule.

Les caractères qui ne sont pas dans les intervales [a-z] ou [A-Z], seront affichés par le point d'interrogation en art ASCII.

Contraintes

 * 0 < L < 30
 * 0 < H < 30
 * 0 < N < 200

# Exemple 1

## Entrée

```
4
5 
E
 #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  
```

## Sortie

```
### 
#   
##  
#   
### 
```

# Exemple 2

## Entrée

```
4
5
MANHATTAN
 #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ### 
# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   # 
### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ## 
# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #       
# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  
```

## Sortie

```
# #  #  ### # #  #  ### ###  #  ###  
### # # # # # # # #  #   #  # # # #  
### ### # # ### ###  #   #  ### # #  
# # # # # # # # # #  #   #  # # # #  
# # # # # # # # # #  #   #  # # # # 
```
