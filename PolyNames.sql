CREATE DATABASE PolyNamesBDD;
USE PolyNamesBDD;
CREATE TABLE `Partie` (`id` INT NOT NULL AUTO_INCREMENT, `code_numerique` INT NOT NULL, `code_perso` VARCHAR(50) NOT NULL, `etat` INT NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `Role` (`id` INT NOT NULL AUTO_INCREMENT, `role` VARCHAR(30) NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `Joueur` (`id` INT NOT NULL AUTO_INCREMENT, `nom` VARCHAR(50) NOT NULL, `hash` VARCHAR(50) NOT NULL, `role` INT NOT NULL, `partie` INT NOT NULL, PRIMARY KEY(`id`), CONSTRAINT `fonction` FOREIGN KEY (`role`) REFERENCES `Role` (`id`), CONSTRAINT `jouer` FOREIGN KEY (`partie`) REFERENCES `Partie` (`id`));
CREATE TABLE `Couleur` (`id` INT NOT NULL AUTO_INCREMENT, `color` VARCHAR(15) NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `Mot` (`id` INT NOT NULL AUTO_INCREMENT, `mot` VARCHAR(30) NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `Carte` (`id` INT NOT NULL AUTO_INCREMENT, `mot` VARCHAR(30) NOT NULL, `couleur` INT NOT NULL, `partie` INT NOT NULL, PRIMARY KEY(`id`), CONSTRAINT `colorisation` FOREIGN KEY (`couleur`) REFERENCES `Couleur` (`id`), CONSTRAINT `appartenir` FOREIGN KEY (`partie`) REFERENCES `Partie` (`id`));
INSERT INTO Couleur (color) VALUES ('gris');
INSERT INTO Couleur (color) VALUES ('noir');
INSERT INTO Couleur (color) VALUES ('bleu');
INSERT INTO Partie (code_numerique, code_perso, etat) VALUES ('1111', 'partie_temporaire_pour_les_nouveaux_joueur', '0');
INSERT INTO Role (role) VALUES ('MDI');
INSERT INTO Role (role) VALUES ('MDM');
INSERT INTO Mot (mot) VALUES ('chaise');
INSERT INTO Mot (mot) VALUES ('table');
INSERT INTO Mot (mot) VALUES ('lit');
INSERT INTO Mot (mot) VALUES ('canapé');
INSERT INTO Mot (mot) VALUES ('lampe');
INSERT INTO Mot (mot) VALUES ('livre');
INSERT INTO Mot (mot) VALUES ('stylo');
INSERT INTO Mot (mot) VALUES ('papier');
INSERT INTO Mot (mot) VALUES ('téléphone');
INSERT INTO Mot (mot) VALUES ('ordinateur');
INSERT INTO Mot (mot) VALUES ('télévision');
INSERT INTO Mot (mot) VALUES ('radio');
INSERT INTO Mot (mot) VALUES ('montre');
INSERT INTO Mot (mot) VALUES ('lunettes');
INSERT INTO Mot (mot) VALUES ('clavier');
INSERT INTO Mot (mot) VALUES ('souris');
INSERT INTO Mot (mot) VALUES ('bouteille');
INSERT INTO Mot (mot) VALUES ('verre');
INSERT INTO Mot (mot) VALUES ('tasse');
INSERT INTO Mot (mot) VALUES ('assiette');
INSERT INTO Mot (mot) VALUES ('fourchette');
INSERT INTO Mot (mot) VALUES ('couteau');
INSERT INTO Mot (mot) VALUES ('cuillère');
INSERT INTO Mot (mot) VALUES ('chaussure');
INSERT INTO Mot (mot) VALUES ('chapeau');
INSERT INTO Mot (mot) VALUES ('veste');
INSERT INTO Mot (mot) VALUES ('pantalon');
INSERT INTO Mot (mot) VALUES ('chemise');
INSERT INTO Mot (mot) VALUES ('robe');
INSERT INTO Mot (mot) VALUES ('pull');
INSERT INTO Mot (mot) VALUES ('chaussette');
INSERT INTO Mot (mot) VALUES ('sac');
INSERT INTO Mot (mot) VALUES ('valise');
INSERT INTO Mot (mot) VALUES ('clé');
INSERT INTO Mot (mot) VALUES ('porte');
INSERT INTO Mot (mot) VALUES ('fenêtre');
INSERT INTO Mot (mot) VALUES ('rideau');
INSERT INTO Mot (mot) VALUES ('miroir');
INSERT INTO Mot (mot) VALUES ('peigne');
INSERT INTO Mot (mot) VALUES ('brosse');
INSERT INTO Mot (mot) VALUES ('savon');
INSERT INTO Mot (mot) VALUES ('shampoing');
INSERT INTO Mot (mot) VALUES ('serviette');
INSERT INTO Mot (mot) VALUES ('dentifrice');
INSERT INTO Mot (mot) VALUES ('brosse à dents');
INSERT INTO Mot (mot) VALUES ('rasoir');
INSERT INTO Mot (mot) VALUES ('ciseaux');
INSERT INTO Mot (mot) VALUES ('colle');
INSERT INTO Mot (mot) VALUES ('trombone');
INSERT INTO Mot (mot) VALUES ('agrafeuse');
INSERT INTO Mot (mot) VALUES ('écran');
INSERT INTO Mot (mot) VALUES ('imprimante');
INSERT INTO Mot (mot) VALUES ('scanneur');
INSERT INTO Mot (mot) VALUES ('haut-parleur');
INSERT INTO Mot (mot) VALUES ('microphone');
INSERT INTO Mot (mot) VALUES ('casque');
INSERT INTO Mot (mot) VALUES ('disque');
INSERT INTO Mot (mot) VALUES ('clé USB');
INSERT INTO Mot (mot) VALUES ('cartable');
INSERT INTO Mot (mot) VALUES ('cahier');
INSERT INTO Mot (mot) VALUES ('livre');
INSERT INTO Mot (mot) VALUES ('magazine');
INSERT INTO Mot (mot) VALUES ('journal');
INSERT INTO Mot (mot) VALUES ('boîte');
INSERT INTO Mot (mot) VALUES ('bocal');
INSERT INTO Mot (mot) VALUES ('panier');
INSERT INTO Mot (mot) VALUES ('pot');
INSERT INTO Mot (mot) VALUES ('vase');
INSERT INTO Mot (mot) VALUES ('statue');
INSERT INTO Mot (mot) VALUES ('tableau');
INSERT INTO Mot (mot) VALUES ('photo');
INSERT INTO Mot (mot) VALUES ('carte');
INSERT INTO Mot (mot) VALUES ('globe');
INSERT INTO Mot (mot) VALUES ('horloge');
INSERT INTO Mot (mot) VALUES ('calendrier');
INSERT INTO Mot (mot) VALUES ('agenda');
INSERT INTO Mot (mot) VALUES ('dossier');
INSERT INTO Mot (mot) VALUES ('classeur');
INSERT INTO Mot (mot) VALUES ('étagère');
INSERT INTO Mot (mot) VALUES ('bibliothèque');
INSERT INTO Mot (mot) VALUES ('coussin');
INSERT INTO Mot (mot) VALUES ('couverture');
INSERT INTO Mot (mot) VALUES ('oreiller');
INSERT INTO Mot (mot) VALUES ('matelas');
INSERT INTO Mot (mot) VALUES ('drap');
INSERT INTO Mot (mot) VALUES ('couette');
INSERT INTO Mot (mot) VALUES ('plaid');
INSERT INTO Mot (mot) VALUES ('vêtement');
INSERT INTO Mot (mot) VALUES ('écharpe');
INSERT INTO Mot (mot) VALUES ('gants');
INSERT INTO Mot (mot) VALUES ('parapluie');
INSERT INTO Mot (mot) VALUES ('ceinture');
INSERT INTO Mot (mot) VALUES ('cravate');
INSERT INTO Mot (mot) VALUES ('casquette');
INSERT INTO Mot (mot) VALUES ('bonnet');
INSERT INTO Mot (mot) VALUES ('maillot');
INSERT INTO Mot (mot) VALUES ('costume');
INSERT INTO Mot (mot) VALUES ('bikini');
INSERT INTO Mot (mot) VALUES ('pyjama');
INSERT INTO Mot (mot) VALUES ('lunettes de soleil');
INSERT INTO Mot (mot) VALUES ('télécommande');
INSERT INTO Mot (mot) VALUES ('ventilateur');
INSERT INTO Mot (mot) VALUES ('chauffage');
INSERT INTO Mot (mot) VALUES ('climatisation');
INSERT INTO Mot (mot) VALUES ('four');
INSERT INTO Mot (mot) VALUES ('micro-ondes');
INSERT INTO Mot (mot) VALUES ('réfrigérateur');
INSERT INTO Mot (mot) VALUES ('congélateur');
INSERT INTO Mot (mot) VALUES ('lave-vaisselle');
INSERT INTO Mot (mot) VALUES ('lave-linge');
INSERT INTO Mot (mot) VALUES ('séchoir');
INSERT INTO Mot (mot) VALUES ('aspirateur');
INSERT INTO Mot (mot) VALUES ('balai');
INSERT INTO Mot (mot) VALUES ('serpillère');
INSERT INTO Mot (mot) VALUES ('seau');
INSERT INTO Mot (mot) VALUES ('éponge');
INSERT INTO Mot (mot) VALUES ('gobelet');
INSERT INTO Mot (mot) VALUES ('carafe');
INSERT INTO Mot (mot) VALUES ('plat');
INSERT INTO Mot (mot) VALUES ('moule');
INSERT INTO Mot (mot) VALUES ('poêle');
INSERT INTO Mot (mot) VALUES ('casserole');
INSERT INTO Mot (mot) VALUES ('cocotte');
INSERT INTO Mot (mot) VALUES ('autocuiseur');
INSERT INTO Mot (mot) VALUES ('mixeur');
INSERT INTO Mot (mot) VALUES ('batteur');
INSERT INTO Mot (mot) VALUES ('grille-pain');
INSERT INTO Mot (mot) VALUES ('bouilloire');
INSERT INTO Mot (mot) VALUES ('cafetière');
INSERT INTO Mot (mot) VALUES ('presse-agrumes');
INSERT INTO Mot (mot) VALUES ('râpe');
INSERT INTO Mot (mot) VALUES ('ouvre-boîte');
INSERT INTO Mot (mot) VALUES ('tire-bouchon');
INSERT INTO Mot (mot) VALUES ('passoire');
INSERT INTO Mot (mot) VALUES ('entonnoir');
INSERT INTO Mot (mot) VALUES ('écumoire');
INSERT INTO Mot (mot) VALUES ('louche');