package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyNamesDatabase;
import models.Game;

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
}