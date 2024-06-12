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
    public static void setPartie(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("codePartie");
        int codePartie = Integer.parseInt(code);
        String user = request.getParam("user");
        boolean affected = gameDAO.setPartie(codePartie, user);
        if(affected){
            response.ok("La partie a bien été affecté");
        }
        else{
            response.notFound("La partie n'a pas été affecté");
        }
    }
    public static void checkNumberOfPlayer(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("codePartie");
        int codePartie = Integer.parseInt(code);
        boolean enoughPlayer = gameDAO.checkNumberOfPlayer(codePartie);
        if(enoughPlayer){
            response.ok("Il y a assez de joueurs dans la partie");
        }
        else{
            response.notFound("Il n'y pas assez de joueurs dans la partie");
        }
    }
    public static void affectRole(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("codePartie");
        int codePartie = Integer.parseInt(code);
        String player = request.getParam("player");
        String role_1 = request.getParam("role1");
        int role1 = Integer.parseInt(role_1);
        String role_2 = request.getParam("role2");
        int role2 = Integer.parseInt(role_2);
        boolean affected = gameDAO.affectRole(codePartie, player, role1, role2);
        if(affected){
            response.ok("Il y a assez de joueurs dans la partie");
        }
        else{
            response.notFound("Il n'y pas assez de joueurs dans la partie");
        }
    }
    public static void isReady(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("codePartie");
        int codePartie = Integer.parseInt(code);
        boolean ready = gameDAO.isReady(codePartie);
        if(ready){
            response.ok("La partie est prete");
        }
        else{
            response.notFound("La parti n'est pas prete");
        }
    }
    public static void checkRole(WebServerContext context){
        GameDAO gameDAO = new GameDAO();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String code = request.getParam("codePartie");
        int codePartie = Integer.parseInt(code);
        boolean twoRole = gameDAO.checkRole(codePartie);
        if(twoRole){
            response.ok("les rôles sont différents, la partie peut commencer");
        }
        else{
            response.notFound("les deux joueurs ont le même rôle, la partie ne peut pas commencer");
        }
    }
}