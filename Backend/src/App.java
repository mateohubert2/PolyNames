import java.util.ArrayList;

import dao.GameDAO;
import models.Card;
import models.Color;
import models.Game;
import models.GameRole;
import models.Player;

public class App {
    public static void main(String[] args) throws Exception {
       GameDAO gameDAO = new GameDAO();
       ArrayList<Game> allGame = gameDAO.findAllGame();
       ArrayList<Player> allPlayer = gameDAO.findAllPlayer();
       ArrayList<Color> allColor = gameDAO.findAllColor();
       ArrayList<GameRole> allRole = gameDAO.findAllGameRole();
       ArrayList<Card> allCard = gameDAO.findAllCard();
       System.out.println("Voici toutes les parties de PolyNames:");
       for(int i = 0; i < allGame.size(); i++){
            System.out.println("Le partie ayant pour id: " + allGame.get(i).id() + " a pour code numérique: " + allGame.get(i).code_numerique() + " est pour code perso: "
            + allGame.get(i).code_perso());
       }
       System.out.println("Voici la liste des joueurs:");
       for(int i = 0; i < allPlayer.size(); i++){
            System.out.println("Le joueur ayant pour id: " + allPlayer.get(i).id() + " a pour nom: " + allPlayer.get(i).nom() + " et pour role: " + allPlayer.get(i).role());
       }
       System.out.println("Voici la liste des couleurs:");
       for(int i = 0; i < allColor.size(); i++){
            System.out.println("Le couleur ayant pour id: " + allColor.get(i).id() + " a pour nom: " + allColor.get(i).nom());
       }
       System.out.println("Voici la liste des roles:");
       for(int i = 0; i < allRole.size(); i++){
            System.out.println("Le role ayant pour id: " + allRole.get(i).id() + " a pour nom: " + allRole.get(i).role());
       }
       System.out.println("Voici la liste des cartes:");
       for(int i = 0; i < allCard.size(); i++){
            System.out.println("Le carte ayant pour id: " + allCard.get(i).id() + " contient le mot: " + allCard.get(i).mot() + " est de la langue: " + allCard.get(i).langue() + " et est de couleur: " + allCard.get(i).couleur());
       }
    }
}