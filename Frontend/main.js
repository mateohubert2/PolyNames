import { GameService } from "./services/game-service.js";
import { MenuView } from "./view/menu-view.js";

function run(){
    const menu = new MenuView();
    menu.afficher();
    GameService.loadGame(1).then((data) => {
        if (data) {
            console.log(data);
        }
    }).catch(error => {
        console.log("Aucune partie trouvee");
    })
}

window.addEventListener("load", run);