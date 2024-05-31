package controllers;

import java.util.ArrayList;

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
        ArrayList<Card> cards = gameDAO.findAllCard();
        ArrayList<Player> players = gameDAO.findAllPlayer();
        content = new Content(game, cards, players);
        response.json(content);
        return content;
    }
}