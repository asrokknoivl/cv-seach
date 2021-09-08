Ce fichier contiendra les nouvelles du cours. Un mail sera envoyé quand le fichier est mis à jour.

# 8/09/2021 : Emploi du temps et salles ce vendredi

Ce vendredi (10/9), l'emploi du temps est :

* 9h45 : TD, nous ne respectons pas les groupes de TD (il n'y a que 4 enseignants ...), votre salle est indiquée sur TOMUSS. Si vous n'avez pas d'indication sur TOMUSS, vous pouvez choisir votre salle (cf. [README.md](README.md)). Certains étudiants ne voient pas ce créneau dans ADE, si c'est le cas c'est que vous avez mal sélectionné les groupes, attention pour les prochaines fois.

* 11h30 : TP, comme d'habitude : la salle est indiquée sur la page du cours.

# 8/09/2021 : Quelques coquilles dans l'énoncé

Il restait quelques coquilles dans le barème (restes de l'an dernier). C'est corrigé sur la page du cours. Faites un `git pull moy master` (cf. ci-dessous) si vous avez l'habitude de lire les fichiers d'énoncé dans votre dépôt.

# 7/09/2021 (bis) : Problème d'installation d'Eclipse sous Ubuntu au Nautibus

L'installation d'Eclipse au Nautibus a l'air cassée. Ne l'utilisez pas. Je vais
essayer de corriger, vous serez prévenu quand ce sera le cas.

# 7/09/2021 : Corrections dans le squelette

Il y a deux corrections mineures dans le squelette de code fourni :

- Quelques lignes qui n'auraient pas du être là dans `pom.xml`
- Une section `pages:` qui n'aurait pas du être là non plus dans `.gitlab-ci.yml`

Vous pouvez récupérer ces modifications comme ceci :

```
git remote add moy https://forge.univ-lyon1.fr/matthieu.moy/mif01-2021.git
git pull moy master
```

Si vous avez encore des erreurs liées à "pages" ou "deploy" dans Gitlab-CI, allez dans votre projet sur la forge, barre latérale « Settings », « General », « Project features », et désactivez « pages ».

