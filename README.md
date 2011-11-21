<link rel="stylesheet" href="http://www.code-story.net/css/screen.css" type="text/css" media="screen, projection"></link>

<div id="title">
	<h1>Premier exercice de sélection, <a href="http://www.code-story.net/2011/11/16/foobarqix.html">FooBarQix</a></h1>
	<h3>Proposition de solution par <a href="https://github.com/ericlemerdy">ericlemerdy</a> &amp; <a href="https://github.com/seblm">seblm</a> - 21 Nov 2011</h3>
</div>

<div id="post">

<p>Ecrivez un programme qui affiche les nombres de 1 à 100. Un nombre par ligne. Respectez les règles suivantes :</p>

<ul>
<li>Si le nombre est divisible par 3 ou contient 3, écrire &#8220;Foo&#8221; à la place de 3.</li>
<li>Si le nombre est divisible par 5 ou contient 5, écrire &#8220;Bar&#8221; à la place de 5.</li>
<li>Si le nombre est divisible par 7 ou contient 7, écrire &#8220;Qix&#8221; à la place de 7.</li>
</ul>

<h1 id='voici_un_exemple_de_rendu'>Voici un exemple de rendu</h1>

<pre><code>1
2
FooFoo
4
BarBar
Foo
QixQix
8
Foo
Bar
...</code></pre>

<h1 id='mise__jour__clarifications_sur_les_rgles'>Mise à jour : clarifications sur les règles</h1>

<ul>
<li>On regarde les diviseurs avant le contenu (ex: 51 -&gt; FooBar)</li>
<li>On regarde le contenu dans l&#8217;ordre où il apparait (ex: 53 -&gt; BarFoo)</li>
<li>On regarde les multiples dans l&#8217;ordre Foo, Bar puis Qix (ex: 21 -&gt; FooQix)</li>
<li>13 contient 3 donc s&#8217;écrit &#8220;Foo&#8221;</li>
<li>15 est divisible par 3 et 5 et contient un 5 donc s&#8217;écrit &#8220;FooBarBar&#8221;</li>
<li>33 contient deux fois 3 et est divisible par 3 donc s&#8217;écrit &#8220;FooFooFoo&#8221;</li>
</ul>

<h1>Début</h1>
<p>Avancement :</p>
<ol>
 <li>16h39 - Début : <span style="background-color: red; padding-right: 50%;">100%</span></li>
</ol>

</div>
