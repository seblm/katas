Écrivez un programme qui affiche les nombres des 1 à 100.
Un nombre par ligne.

Respectez les règles suivantes :
	1.	Si le nombre est divisible par 3 ou contient 3 écrire "Foo" à la place de 3
	2.	Si le nombre est divisible par 5 ou contient 5 écrire "Bar" à la place de 5
	3.	Si le nombre est divisible par 7 ou contient 7 écrire "Qix" à la place de 7
Voici un exemple de rendu :
1
2
FooFoo
4
BarBar
Foo
QixQix
8
Foo
Bar
…

	1.	On regarde les diviseurs avant le contenu (ex : 51 -> FooBar)
	2.	On regarde le contenu dans l'ordre où il apparaît (ex : 53 -> BarFoo)
	3.	On regarde les multiples dans l'ordre Foo, Bar puis Qix (ex : 21 -> FooQix)
	4.	13 contient 3 donc s'écrit Foo
	5.	15 est divisible par 3 et 5 et contient un 5 donc s'écrit "FooBarBar"
	6.	33 contient deux fois 3 et est divisible par 3 donc s'écrit "FooFooFoo"
