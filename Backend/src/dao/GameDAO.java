package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Card;
import models.Color;
import models.Game;
import models.GameRole;
import models.Player;

public class GameDAO {
    public GameDAO(){

    }
    public ArrayList<Game> findAllGame(){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Game> games = new ArrayList<Game>();
        String sqlquery = "SELECT * FROM `Partie` ORDER BY `id` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                int code_numerique = myResults.getInt("code_numerique");
                String code_perso = myResults.getString("code_perso");
                int etat = myResults.getInt("etat");
                Game game = new Game(id, code_numerique, code_perso, etat);
                games.add(game);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return games;
    }

    public Game findGame(int partie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        Game game = null;
        String sqlquery = "SELECT * FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, partie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                int code_numerique = myResults.getInt("code_numerique");
                String code_perso = myResults.getString("code_perso");
                int etat = myResults.getInt("etat");
                game = new Game(id, code_numerique, code_perso, etat);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return game;
    }

    public ArrayList<Player> findAllPlayer(){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Player> players = new ArrayList<Player>();
        String sqlquery = "SELECT * FROM `Joueur` ORDER BY `id` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String nom = myResults.getString("nom");
                int role = myResults.getInt("role");
                Player player = new Player(id, nom, role);
                players.add(player);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return players;
    }

    public ArrayList<Player> findPlayer(int partie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Player> players = new ArrayList<Player>();
        String sqlquery = "SELECT * FROM `Joueur` WHERE `partie` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, partie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String nom = myResults.getString("nom");
                int role = myResults.getInt("role");
                Player player = new Player(id, nom, role);
                players.add(player);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return players;
    }

    public ArrayList<Color> findAllColor(){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Color> colors = new ArrayList<Color>();
        String sqlquery = "SELECT * FROM `Couleur` ORDER BY `couleur_id` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("couleur_id");
                String nom = myResults.getString("nom");
                Color color = new Color(id, nom);
                colors.add(color);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return colors;
    }

    public ArrayList<GameRole> findAllGameRole(){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<GameRole> roles = new ArrayList<GameRole>();
        String sqlquery = "SELECT * FROM `Rôle` ORDER BY `id` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String nom = myResults.getString("role");
                GameRole role = new GameRole(id, nom);
                roles.add(role);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return roles;
    }

    public ArrayList<Card> findAllCard(){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Card> cards = new ArrayList<Card>();
        String sqlquery = "SELECT * FROM `Carte` ORDER BY `id` ASC;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String mot = myResults.getString("mot");
                int couleur = myResults.getInt("couleur");
                Card card = new Card(id, mot, couleur);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cards;
    }

    public ArrayList<Card> findCard(int partie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        ArrayList<Card> cards = new ArrayList<Card>();
        String sqlquery = "SELECT * FROM `Carte` WHERE `partie` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, partie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                int id = myResults.getInt("id");
                String mot = myResults.getString("mot");
                int couleur = myResults.getInt("couleur");
                Card card = new Card(id, mot, couleur);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cards;
    }

    public boolean isGame(int code_partie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlquery = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, code_partie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int id = -1;
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                id = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if(id != -1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean createGame(int code_partie, String nom){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlquery = "INSERT INTO `Partie` (code_numerique, code_perso, etat) VALUES (?, ?, 0);";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, code_partie);
            myPreparedStatement.setString(2, nom);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        boolean request;
        try {
            request = myPreparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            request = false;
        }
        String idquery = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?";
        PreparedStatement idStatement;
        try {
            idStatement = polyNames.prepareStatement(idquery);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            idStatement = null;
        }
        try {
            idStatement.setInt(1, code_partie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int idPartie = -1;
        try {
            ResultSet myResults = idStatement.executeQuery();
            while(myResults.next()){
                idPartie = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String cardquery = "INSERT INTO `Carte` (mot, couleur, partie) SELECT `mot`, 1, ? FROM `Mot` ORDER BY RAND() LIMIT 25;";
        PreparedStatement cardStatement;
        try {
            cardStatement = polyNames.prepareStatement(cardquery);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            cardStatement = null;
        }
        try {
            cardStatement.setInt(1, idPartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        boolean card;
        try {
            card = cardStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            card = false;
        }
        return card;
    }

    public boolean connexion(String user, String hash){
        PolyNamesDatabase polyNames;
        boolean state = false;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlqueryCheck = "Select * FROM `Joueur` WHERE `nom` = ?;";
        PreparedStatement myPreparedStatementCheck;
        try {
            myPreparedStatementCheck = polyNames.prepareStatement(sqlqueryCheck);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementCheck = null;
        }
        try {
            myPreparedStatementCheck.setString(1, user);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String findhash = "";
        try {
            ResultSet myResults = myPreparedStatementCheck.executeQuery();
            while(myResults.next()){
                findhash = myResults.getString("hash");
            }
            if(findhash.equals(hash)){
                state = true;
            }
            else{
                //l'id de la partie est a 1 pour enregistrer les nouveaux joueurs en attendant qu'ils rejoignent une vraie partie
                String sqlquery = "INSERT INTO `Joueur` (nom, hash, role, partie) VALUES (?, ?, 1, 1);";
                PreparedStatement myPreparedStatement;
                try {
                    myPreparedStatement = polyNames.prepareStatement(sqlquery);
                } catch (SQLException e) {
                    System.err.println("Impossible de préparer la requête:");
                    System.err.println(e.getMessage());
                    myPreparedStatement = null;
                }
                try {
                    myPreparedStatement.setString(1, user);
                    myPreparedStatement.setString(2, hash);
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                boolean request;
                try {
                    request = myPreparedStatement.execute();
                    state = true;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    state = false;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return state;
    }
    public boolean setPartie(int codePartie, String user){
        PolyNamesDatabase polyNames;
        boolean set = false;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlqueryForId = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatementForId;
        try {
            myPreparedStatementForId = polyNames.prepareStatement(sqlqueryForId);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementForId = null;
        }
        try {
            myPreparedStatementForId.setInt(1, codePartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int idPartie = 0;
        try {
            ResultSet myResults = myPreparedStatementForId.executeQuery();
            while(myResults.next()){
                idPartie = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String sqlquery = "UPDATE `Joueur` SET `partie` = ? WHERE `nom` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, idPartie);
            myPreparedStatement.setString(2, user);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            set = myPreparedStatement.execute();
            set = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            set = false;
        }
        return set;
    }
    public boolean checkNumberOfPlayer(int codePartie){
        PolyNamesDatabase polyNames;
        boolean enough = false;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlqueryForId = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatementForId;
        try {
            myPreparedStatementForId = polyNames.prepareStatement(sqlqueryForId);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementForId = null;
        }
        try {
            myPreparedStatementForId.setInt(1, codePartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int idPartie = 0;
        try {
            ResultSet myResults = myPreparedStatementForId.executeQuery();
            while(myResults.next()){
                idPartie = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String sqlquery = "SELECT COUNT(*) AS number FROM `Joueur` WHERE `partie` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, idPartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int number = 0;
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                number = myResults.getInt("number");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if(number == 2){
            enough = true;
        }
        else{
            enough = false;
        }
        return enough;
    }
    public boolean affectRole(int codePartie, String player, int role1, int role2){
        PolyNamesDatabase polyNames;
        int totalAffect = 0;
        boolean affected = false;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlqueryForId = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatementForId;
        try {
            myPreparedStatementForId = polyNames.prepareStatement(sqlqueryForId);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementForId = null;
        }
        try {
            myPreparedStatementForId.setInt(1, codePartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int idPartie = 0;
        try {
            ResultSet myResults = myPreparedStatementForId.executeQuery();
            while(myResults.next()){
                idPartie = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<Player> players = this.findPlayer(idPartie);
        for(int i = 0; i < players.size(); i++){
            String sqlquery = "UPDATE `Joueur` SET `role` = ? WHERE `nom` = ?;";
            PreparedStatement myPreparedStatement;
            try {
                myPreparedStatement = polyNames.prepareStatement(sqlquery);
            } catch (SQLException e) {
                System.err.println("Impossible de préparer la requête:");
                System.err.println(e.getMessage());
                myPreparedStatement = null;
            }
            try {
                if(players.get(i).nom().equals(player)){
                    myPreparedStatement.setInt(1, role1);
                    myPreparedStatement.setString(2, players.get(i).nom());
                }
                else{
                    myPreparedStatement.setInt(1, role2);
                    myPreparedStatement.setString(2, players.get(i).nom());
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                affected = myPreparedStatement.execute();
                totalAffect++;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if(totalAffect == 2){
            String sqlqueryToSetReady = "UPDATE `Partie` SET `etat` = 1 WHERE `id` = ?;";
            PreparedStatement myPreparedStatementToSetReady;
            try {
                myPreparedStatementToSetReady = polyNames.prepareStatement(sqlqueryToSetReady);
            } catch (SQLException e) {
                System.err.println("Impossible de préparer la requête:");
                System.err.println(e.getMessage());
                myPreparedStatementToSetReady = null;
            }
            try {
                myPreparedStatementToSetReady.setInt(1, idPartie);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                affected = myPreparedStatementToSetReady.execute();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isReady(int codePartie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlquery = "SELECT `etat` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, codePartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int etat = 0;
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                etat = myResults.getInt("etat");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if(etat == 1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkRole(int codePartie){
        PolyNamesDatabase polyNames;
        try {
            polyNames = new PolyNamesDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            polyNames = null;
        }
        String sqlqueryForId = "SELECT `id` FROM `Partie` WHERE `code_numerique` = ?;";
        PreparedStatement myPreparedStatementForId;
        try {
            myPreparedStatementForId = polyNames.prepareStatement(sqlqueryForId);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementForId = null;
        }
        try {
            myPreparedStatementForId.setInt(1, codePartie);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int id = 0;
        try {
            ResultSet myResults = myPreparedStatementForId.executeQuery();
            while(myResults.next()){
                id = myResults.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        String sqlquery = "SELECT `id` FROM `Joueur` WHERE `partie` = ?;";
        PreparedStatement myPreparedStatement;
        try {
            myPreparedStatement = polyNames.prepareStatement(sqlquery);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatement = null;
        }
        try {
            myPreparedStatement.setInt(1, id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<Integer> playersId = new ArrayList<Integer>();
        try {
            ResultSet myResults = myPreparedStatement.executeQuery();
            while(myResults.next()){
                playersId.add(myResults.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<Integer> playersRole = new ArrayList<Integer>();
        String sqlqueryForRole = "SELECT `role` FROM `Joueur` WHERE `id` = ?;";
        PreparedStatement myPreparedStatementForRole;
        try {
            myPreparedStatementForRole = polyNames.prepareStatement(sqlqueryForRole);
        } catch (SQLException e) {
            System.err.println("Impossible de préparer la requête:");
            System.err.println(e.getMessage());
            myPreparedStatementForRole = null;
        }
        for(int i = 0; i < playersId.size(); i++){
            try {
                myPreparedStatementForRole.setInt(1, playersId.get(i));
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                ResultSet myResults = myPreparedStatementForRole.executeQuery();
                while(myResults.next()){
                    playersRole.add(myResults.getInt("role"));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if(playersRole.get(0) != playersRole.get(1)){
            return true;
        }
        else{
            return false;
        }
    }
}