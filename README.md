# PDLA_Appli_Benevolat

### Compilation
Pour compiler le projet, une fois Maven installé sur votre machine, il faut se placer dans le dossier **Appli_Benevolat** et exécuter la commande `mvn compile package`

### Exécution
Pour exécuter le projet, lancer `java -jar target/Appli_Benevolat-1.0-SNAPSHOT.jar` toujours dans le même dossier

*Pour une raison que nous ignorons, la connexion à la Database échoue avec cette dernière commande, cela à l'air d'être dû à un driver du jdbc. Cependant les tests JUnit passent donc la connection à la Database fonctionne. Il est également possible de lancer l'application avec une configuration IntelliJ* 
