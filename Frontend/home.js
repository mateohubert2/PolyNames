import { GameService } from "./services/game-service.js";
import { MenuView } from "./view/menu-view.js";

function run(){
    const menuName = document.querySelector(".menu");
    const titre = document.createElement("h1");
    titre.innerHTML = "Veuillez entrer votre nom, votre mot de passe et cocher la case pour le stocker";
    const text = document.createElement("input");
    const mdp = document.createElement("input");
    mdp.type = "password";
    const button = document.createElement("button");
    button.innerHTML = "connexion"
    const checkboxMDP = document.createElement("input");
    checkboxMDP.type = "checkbox";
    menuName.appendChild(titre);
    menuName.appendChild(text);
    text.classList.add("user");
    mdp.classList.add("mdp");
    menuName.appendChild(mdp);
    menuName.appendChild(button);
    menuName.appendChild(checkboxMDP);
    let loadUser = localStorage.getItem("user");
    let loadHash = localStorage.getItem("mdp");
    if((loadUser !== null)&&(loadHash !== null)){
        loadUser = loadUser.replace(/^"|"$/g, '');
        loadHash = loadHash.replace(/^"|"$/g, '');
    }
    text.value = loadUser;
    mdp.value = loadHash;
    button.addEventListener("click", () => {
        const user = document.querySelector(".user");
        const mdpUser = document.querySelector(".mdp");
        const utilisateur = user.value;
        const motDePasse = mdpUser.value;
        const hashed = hash(motDePasse);
        const checked = checkboxMDP.checked;
        GameService.createOrLoadPlayer(utilisateur, hashed).then((success) => {
            if(success){
                if(checked){
                    localStorage.setItem("user", JSON.stringify(utilisateur));
                    localStorage.setItem("mdp", JSON.stringify(motDePasse));
                    sessionStorage.setItem("user", JSON.stringify(utilisateur));
                }
                else{
                    sessionStorage.setItem("user", JSON.stringify(utilisateur));
                }
                titre.remove();
                text.remove();
                mdp.remove();
                button.remove();
                checkboxMDP.remove();
                const menu = new MenuView();
                menu.afficher();
            }
            else{
                titre.innerHTML = "Impossible de jouer pour l'instant";
            }
        }).catch((error) => {
            console.log("Impossible de créer ou de charger un joueur", error);
        })
        
    })
    
}
function hash(mdp) {
    let hash = 0;
    for (let i = 0; i < mdp.length; i++) {
        hash += mdp.charCodeAt(i);
    }
    return hash.toString(16);
}
window.addEventListener("load", run);