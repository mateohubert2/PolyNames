import java.util.ArrayList;

import dao.GameDAO;
import models.Game;

public class App {
    public static void main(String[] args) throws Exception {
       GameDAO gameDAO = new GameDAO();
       ArrayList<Game> allGame = gameDAO.findAllGame();
       System.out.println("Voici toutes les parties de PolyNames:");
       for(int i = 0; i < allGame.size(); i++){
            System.out.println("Le partie ayant pour id: " + allGame.get(i).id() + " a pour code numérique: " + allGame.get(i).code_numerique() + " est pour code perso: "
            + allGame.get(i).code_perso());
       }
    }
}