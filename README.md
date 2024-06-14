# PolyNames
# Diagramme BDD
![Diagramme_PolyNames drawio](https://github.com/mateohubert2/PolyNames/assets/104895927/69e33bf6-bb88-4aee-8c69-fe0f9fd55ddd)
# Utilisation
## Création de la BDD et de l'utilisateur
Pour jouer à PolyNames, il faut cloner le dépôt git. Ensuite, utiliser le SGBD de votre choix pour créer la BDD avec le script sql. Vous devrez ensuite créer un nouvel utilisateur ayant les droits sur cette BDD.
## Modification des informations de connexion
Rendez-vous dans le dossier Backend, dans le fichier PolyNamesDatabase.java et modifier le constructeur suivant avec vos informations:
![remplacementbdd](https://github.com/mateohubert2/PolyNames/assets/104895927/2177aa2a-adf8-430e-be05-943346503739)
## Lancer une partie
our lancer une partie, rendez-vous dans le dossier Backend et lancez le fichier App.java.
## Comment jouer?
Une fois le serveur lancé, rendez-vous sur http://localhost:8080/home.html vous devriez tomber sur cette page:
![home html](https://github.com/mateohubert2/PolyNames/assets/104895927/d54f9ed9-45ad-41ae-8717-cdc6d23906e0)
Rentrez vos informations de connexion. Un utilisateur sera créé si il n'existait pas déja. Ensuite, cliquez sur créer une partie. Vous devriez être redirigé sur cette page:
![creation](https://github.com/mateohubert2/PolyNames/assets/104895927/53034824-cba3-46ed-a5dc-de62d9c09418)
Rentrez le nom de la partie que voulez créer. Une fois validé, vous allez obtenir le code à donner au deuxième joueur:
![demonstration](https://github.com/mateohubert2/PolyNames/assets/104895927/1f606e27-6bad-46e4-9d4e-77257f2a2494)
Avec un autre onglet ou un autre navigateur, rendez-vous de nouveau sur http://localhost:8080/home.html connectez-vous et cliquez sur rejoindre une partie (IL NE FAUT PAS UTILISER LE MEME JOUEUR POUR CREER ET REJOINDRE LA PARTIE). Saisissez le code fourni par le premier joueur et cliquez sur rejoindre. La page n'affiche rien et c'est normal, le premier joueur doit choisir les rôles:
![choix](https://github.com/mateohubert2/PolyNames/assets/104895927/3737b220-7ffe-4416-9826-57cd6ad3a881)
Si vous choisissez Maitre des mots alors vous enverrez les indices à l'autre joueur. Si vous choisissez Maitre des intuitions alors vous devrez découvrir les mots. Une fois les rôles choisis, le Maitre des intuitions voit une page avec 25 mots blancs. Le Maitre des mots voit ceci:
![indice](https://github.com/mateohubert2/PolyNames/assets/104895927/24373b5c-ec56-4444-8f85-860a720195c7)
Les mots en bleus sont les mots à faire deviner à l'autre joueur, les mots gris ne rapportent pas de points et les mots noirs font perdre la partie. Le Maitre des mots peut envoyé un indice et un nombre de mots à faire deviner. Une fois l'indice envoyer, c'est au tour du maitre des intuitions d'essayer de deviner les mots en cliquant dessus. Le Maitre des intuitions peut essayer de deviner jusqu'à N+1 mots en cliquant dessus. L'indice est le nombre de mots à deviner sont affichés en haut à gauche comme ceci:
![LED](https://github.com/mateohubert2/PolyNames/assets/104895927/6a696a29-d768-431f-8503-be50f80e6508)
Une fois que le joueur clique sur un mot, la couleur du mot s'affiche.
