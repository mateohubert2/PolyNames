import { GameService } from "./services/game-service.js";
function run(){
    const titrePartie = document.querySelector(".titre");
    const demande = document.createElement("div");
    titrePartie.appendChild(demande);
    demande.innerHTML = "Veuillez rentrer le code numérique de la partie que vous voulez rejoindre";
    const button = document.querySelector(".sendCode");
    button.addEventListener("click", () => {
        const code = document.querySelector(".code");
        const code_partie = code.value;
        charger(code_partie);
        demande.remove();
        code.remove();
        button.remove();
    });
}

function afficher(data){
    const titrePartie = document.querySelector(".titre");
    const titre = document.createElement("div");
    titre.innerHTML = "Vous êtes sur la partie de: " + data["game"].code_perso;
    titrePartie.appendChild(titre);
    for(let i = 0; i < data["cards"].length; i++){
        const cartes = document.querySelector(".cards");
        const carte = document.createElement("div");
        carte.classList.add("card");
        carte.innerHTML = data["cards"][i].mot;
        cartes.appendChild(carte);
    }
}

function charger(code_partie){
    GameService.findGame(code_partie).then((isGame) => {
        if(isGame){
            GameService.loadGame(code_partie).then((data) => {
                if(data){
                    afficher(data);
                }
            }).catch(error => {
                console.log("Aucune partie trouvee", error);
            })
        }
        else{
            const titrePartie = document.querySelector(".titre");
            const refus = document.createElement("div");
            refus.innerHTML = "La partie demandée n'existe pas veuillez rentrer un code valide";
            titrePartie.appendChild(refus);
        }
    }).catch(error => {
        console.log("La partie n'existe pas", error);
    })
}


window.addEventListener("load", run);