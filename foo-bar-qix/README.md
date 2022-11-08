# Premier exercice de sélection, [FooBarQix](http://www.code-story.net/2011/11/16/foobarqix.html) #

### Proposition de solution par [ericlemerdy](https://github.com/ericlemerdy) & [seblm](https://github.com/seblm) - 21 Nov 2011 ###

## Comment utiliser et tester le projet ? ##

	mvn clean package
	java -jar target/foo-bar-qix-1.0.0.jar

## Petit descriptif ##

Le projet a été réalisé durant un trajet de train en un peu plus de 2 heures, en TDD avec Pair-Hero (au moins durant les trois premières sessions... ).  
Nous n'avons utilisé que JavaSE (_keep it simple_ comme on dit).

**La** difficulté de l'exercice c'est clairement le _à la place_ dans l'énoncé. Ça porte à confusion : faut-il substituer seulement le chiffre dans le nombre ? Heureusement que les exemples sont là et qu'aucun d'eux ne contient à la fois un **FooBarQix** et un chiffre.

Voici l'évolution du nombre de _nombres_ correctement transformés en _FooBarQix_ au fil des pomodoros de 25 minutes :

* 6/100
* 55/100
* 94/100
* 100/100

## Rappel de l'énoncé ##

Ecrivez un programme qui affiche les nombres de 1 à 100. Un nombre par ligne. Respectez les règles suivantes :

* Si le nombre est divisible par 3 ou contient 3, écrire &#8220;Foo&#8221; à la place de 3.</li>
* Si le nombre est divisible par 5 ou contient 5, écrire &#8220;Bar&#8221; à la place de 5.</li>
* Si le nombre est divisible par 7 ou contient 7, écrire &#8220;Qix&#8221; à la place de 7.</li>

### Voici un exemple de rendu ###

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
	...

### Mise à jour : clarifications sur les règles ###

* On regarde les diviseurs avant le contenu (ex: 51 -&gt; FooBar)
* On regarde le contenu dans l&#8217;ordre où il apparait (ex: 53 -&gt; BarFoo)
* On regarde les multiples dans l&#8217;ordre Foo, Bar puis Qix (ex: 21 -&gt; FooQix)
* 13 contient 3 donc s&#8217;écrit &#8220;Foo&#8221;
* 15 est divisible par 3 et 5 et contient un 5 donc s&#8217;écrit &#8220;FooBarBar&#8221;
* 33 contient deux fois 3 et est divisible par 3 donc s&#8217;écrit &#8220;FooFooFoo&#8221;

