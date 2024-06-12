import { GameService } from "./services/game-service.js";
function run(){
    const titrePartie = document.querySelector(".titre");
    const demande = document.createElement("div");
    titrePartie.appendChild(demande);
    demande.innerHTML = "Veuillez rentrer le code numérique de la partie que vous voulez rejoindre";
    const button = document.querySelector(".sendCode");
    let codePartieStorage = sessionStorage.getItem("codePartie");
    if(codePartieStorage !== null){
        codePartieStorage = codePartieStorage.replace(/^"|"$/g, '');
        GameService.charger(codePartieStorage).then((supp) => {
            if(supp){
                demande.remove();
                code.remove();
                button.remove();
            }
            else{
                demande.remove();
                demande.innerHTML = "Impossible de rejoindre la partie";
            }
        }).catch(error => {
            demande.remove();
            demande.innerHTML = "Le code n'existe pas. Veuillez rentrer un code valide";
        });
    }
    button.addEventListener("click", () => {
        const code = document.querySelector(".code");
        const codePartie = code.value;
        sessionStorage.setItem("codePartie", JSON.stringify(codePartie));
        GameService.charger(codePartie).then((supp) => {
            if(supp){
                demande.remove();
                code.remove();
                button.remove();
            }
            else{
                demande.remove();
            }
        }).catch(error => {
            demande.remove();
            demande.innerHTML = "Le code n'existe pas. Veuillez rentrer un code valide";
        });
    });
}

window.addEventListener("load", run);