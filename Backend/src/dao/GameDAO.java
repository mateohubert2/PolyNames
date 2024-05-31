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
                Game product = new Game(id, code_numerique, code_perso);
                games.add(product);
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
        String sqlquery = "SELECT * FROM `Partie` WHERE `id` = ?;";
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
                game = new Game(id, code_numerique, code_perso);
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
                String langue = myResults.getString("langue");
                int couleur = myResults.getInt("couleur");
                Card card = new Card(id, mot, langue, couleur);
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
                String langue = myResults.getString("langue");
                int couleur = myResults.getInt("couleur");
                Card card = new Card(id, mot, langue, couleur);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cards;
    }
}