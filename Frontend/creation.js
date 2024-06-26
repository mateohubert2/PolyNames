import { GameService } from "./services/game-service.js";

function run(){
    const titrePartie = document.querySelector(".titre");
    const demande = document.createElement("div");
    titrePartie.appendChild(demande);
    demande.innerHTML = "Veuillez rentrer le nom de la partie que vous voulez créer";
    const button = document.querySelector(".button");
    button.addEventListener("click", () => {
        const code = document.querySelector(".text");
        const nom = code.value;
        GameService.createGame(nom).then((data) => {
            sessionStorage.setItem("master", JSON.stringify("true"));
            sessionStorage.setItem("codePartie", JSON.stringify(data));
            window.location.href = 'http://localhost:8080/rejoindre.html';
        }).catch(error => {
            console.log("Aucune partie crée", error);
        })
        demande.remove();
        code.remove();
        button.remove();
    });
}

window.addEventListener("load", run);