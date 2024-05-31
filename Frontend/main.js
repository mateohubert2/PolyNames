import { GameService } from "./services/game-service.js";

function run(){
    GameService.loadGame(1).then((data) => {
        if (data) {
            console.log(data);
        }
    }).catch(error => {
        console.log("Aucune partie trouvee");
    })
}

window.addEventListener("load", run);