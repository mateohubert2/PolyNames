import controllers.GameController;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
       WebServer webserver = new WebServer();
       webserver.listen(8080);
       webserver.getRouter().get("/content/:gameId", (WebServerContext context) -> { GameController.getGameContent(context); });
    }
}