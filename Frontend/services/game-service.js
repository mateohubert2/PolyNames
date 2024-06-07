export class GameService {
    static async loadGame(id_partie){
        const response = await fetch("http://localhost:8080/content/"+id_partie);
        if(response.status === 200){
            const data = response.json();
            return data;
        }
        else{
            return null;
        }
    }
    static async findGame(code_partie){
        const response = await fetch("http://localhost:8080/findgame/"+code_partie);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
    static async createGame(nom){
        const response = await fetch("http://localhost:8080/creategame/"+nom);
        if(response.status === 200){
            const data = response.json();
            return data;
        }
        else{
            console.log("La partie n'a pas été créer");
        }
    }
    static afficher(data){
        const titrePartie = document.querySelector(".titre");
        titrePartie.innerHTML = "Vous êtes sur la partie de: " + data["game"].code_perso;
        for(let i = 0; i < data["cards"].length; i++){
            const cartes = document.querySelector(".cards");
            const carte = document.createElement("div");
            carte.classList.add("card");
            carte.innerHTML = data["cards"][i].mot;
            cartes.appendChild(carte);
        }
    }
    static async charger(codePartie){
        GameService.findGame(codePartie).then((isGame) => {
            if(isGame){
                GameService.loadGame(codePartie).then((data) => {
                    if(data){
                        GameService.afficher(data);
                        const code = document.querySelector(".code");
                        const button = document.querySelector(".sendCode");
                        code.remove();
                        button.remove();
                        return true;
                    }
                }).catch(error => {
                    console.log("Aucune partie trouvee", error);
                })
            }
            else{
                const refus = document.querySelector(".titre");
                refus.innerHTML = "La partie demandée n'existe pas veuillez rentrer un code valide";
                return false;
            }
        }).catch(error => {
            console.log("La partie n'existe pas", error);
        })
    }
    static async createOrLoadPlayer(utilisateur, hash){
        const response = await fetch("http://localhost:8080/connect/"+utilisateur+"/"+hash);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
}