package controllers;

import java.util.ArrayList;
import java.util.Random;

import dao.GameDAO;
import models.Game;
import models.Content;
import models.Card;
import models.Player;

import webserver.WebServerContext;
import webserver.WebServerRequest;
import webserver.WebServerResponse;

public class GameController {
    public static Content getGameContent(WebServerContext context){
        Content content;
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String id_partie = request.getParam("gameId");
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.findGame(Integer.parseInt(id_partie));
        int id = game.id();
        ArrayList<Card> cards = gameDAO.findCard(id);
        ArrayList<Player> players = gameDAO.findPlayer(id);
        content = new Content(game, cards, players);
        if(content.game() == null){
            response.json("Desole, aucune partie n'a ete trouvee");
        }
        else{
            response.json(content);
        }
        return content;
    }
    public static void sendContent(WebServerContext context){
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String file = request.getParam("file");
        response.sendFile(200, file);
    }

    public static void sendSpecificContent(WebServerContext context){
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String file = request.getPath();
        System.out.println(file);
        response.sendFile(200, file);
    }

    public static void isGame(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("code_partie");
        int codePartie = Integer.parseInt(code);
        boolean exist = gameDAO.isGame(codePartie);
        if(exist){
            response.ok("La partie existe");
        }
        else{
            response.notFound("La partie n'existe pas");
        }
    }
    
    public static void createGame(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String nom = request.getParam("nom");
        Random objTemp = new Random();
        int codePartie = objTemp.nextInt(100); 
        boolean codeAlreadyExist = gameDAO.isGame(codePartie);
        while(codeAlreadyExist){
            codePartie = objTemp.nextInt(100);
            codeAlreadyExist = gameDAO.isGame(codePartie);
        }
        gameDAO.createGame(codePartie, nom);
        boolean create = gameDAO.isGame(codePartie);
        if(create){
            response.json(codePartie);
        }
        else{
            response.notFound("La partie n'a pas été crée");
        }
    }

    public static void connexion(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String user = request.getParam("user");
        String hash = request.getParam("hash");
        boolean success = gameDAO.connexion(user, hash);
        if(success){
            response.ok("L'utilisateur à bien été chargé ou crée");
        }
        else{
            response.notFound("Impossible de charger ou de créer l'utilisateur");
        }
    }
}