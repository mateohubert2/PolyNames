import controllers.GameController;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
       WebServer webserver = new WebServer();
       webserver.listen(8080);
       webserver.getRouter().get("/:file", (WebServerContext context) -> { GameController.sendContent(context); });
       webserver.getRouter().get("/favicon.ico", (WebServerContext context) -> { GameController.sendSpecificContent(context); });
       webserver.getRouter().get("/fond.png", (WebServerContext context) -> { GameController.sendSpecificContent(context); });
       webserver.getRouter().get("/style.css", (WebServerContext context) -> { GameController.sendSpecificContent(context); });
       webserver.getRouter().get("/creategame/:nom", (WebServerContext context) -> { GameController.createGame(context); });
       webserver.getRouter().get("/findgame/:code_partie", (WebServerContext context) -> { GameController.isGame(context); });
       webserver.getRouter().get("/view/menu-view.js", (WebServerContext context) -> { GameController.sendSpecificContent(context); });
       webserver.getRouter().get("/services/game-service.js", (WebServerContext context) -> { GameController.sendSpecificContent(context); });
       webserver.getRouter().get("/content/:gameId", (WebServerContext context) -> { GameController.getGameContent(context); });
       webserver.getRouter().get("/connect/:user/:hash", (WebServerContext context) -> { GameController.connexion(context); });
       webserver.getRouter().get("/setpartie/:codePartie/:user", (WebServerContext context) -> { GameController.setPartie(context); });
       webserver.getRouter().get("/askrole/:codePartie", (WebServerContext context) -> { GameController.checkNumberOfPlayer(context); });
       webserver.getRouter().get("/affectrole/:codePartie/:player/:role1/:role2", (WebServerContext context) -> { GameController.affectRole(context); });
       webserver.getRouter().get("/ready/:codePartie", (WebServerContext context) -> { GameController.isReady(context); });
       webserver.getRouter().get("/checkrole/:codePartie", (WebServerContext context) -> { GameController.checkRole(context); });
       webserver.getRouter().get("/setcolor/:color/:idCarte", (WebServerContext context) -> { GameController.setColor(context); });
       webserver.getRouter().get("/getrole/:codePartie/:player", (WebServerContext context) -> { GameController.getRole(context); });
       webserver.getRouter().get("/checkcolor/:codePartie/:mot", (WebServerContext context) -> { GameController.checkColor(context); });

       webserver.getRouter().post("/mot/:word", (WebServerContext context) -> { GameController.sendWord(context); });
       webserver.getRouter().post("/number/:number", (WebServerContext context) -> { GameController.sendNumber(context); });
    }
}