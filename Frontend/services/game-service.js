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
        titrePartie.innerHTML = "Vous êtes sur la partie de: " + data["game"].code_perso + ". Voici le code de la partie: " + data["game"].code_numerique;
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
                        let block = sessionStorage.getItem("block");
                        if(block !== null){
                            block = block.replace(/^"|"$/g, '');
                        }
                        if(block === "true"){
                            const code = document.querySelector(".code");
                            const button = document.querySelector(".sendCode");
                            code.remove();
                            button.remove();
                            GameService.afficher(data);
                            return true;
                        }
                        let isMaster = sessionStorage.getItem("master");
                        if(isMaster !== null){
                            isMaster = isMaster.replace(/^"|"$/g, '');
                        }
                        if(isMaster === "true"){
                            const code = document.querySelector(".code");
                            const button = document.querySelector(".sendCode");
                            code.remove();
                            button.remove();
                            let validator = 0;
                            GameService.setPartie(codePartie).then(() => {
                                let player = sessionStorage.getItem("user");
                                player = player.replace(/^"|"$/g, '');
                                GameService.affectRole(codePartie, player, 1, 1).then(() => {
                                    GameService.displayWaitingScreen(data);
                                    let affected = false;
                                    setInterval((() => {
                                        GameService.askRole(codePartie).then((correctNumberOfPlayer) => {
                                            if((correctNumberOfPlayer)&&(validator === 0)){
                                                validator = 1;
                                                GameService.displayRole(data);
                                            }
                                        })
                                    }), 2000);
                                })
                            })
                        }
                        else{
                            const code = document.querySelector(".code");
                            const button = document.querySelector(".sendCode");
                            code.remove();
                            button.remove();
                            let validator = 0;
                            GameService.setPartie(codePartie).then(() => {
                                let player = sessionStorage.getItem("user");
                                player = player.replace(/^"|"$/g, '');
                                GameService.affectRole(codePartie, player, 1, 1).then(() => {
                                    sessionStorage.setItem("codePartie", JSON.stringify(codePartie));
                                    setInterval((() => {
                                        GameService.checkRole(codePartie).then((correctRole) => {
                                            if((correctRole)&&(validator === 0)){
                                                validator = 1;
                                                GameService.afficher(data);
                                                clearInterval();
                                            }
                                        })
                                    }), 2000);
                                })
                            })
                        }
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
    static async setPartie(codePartie){
        let user = sessionStorage.getItem("user");
        user = user.replace(/^"|"$/g, '');
        const response = await fetch("http://localhost:8080/setpartie/"+codePartie+"/"+user);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
    static async askRole(codePartie){
        const response = await fetch("http://localhost:8080/askrole/"+codePartie);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
    static async checkRole(codePartie){
        const response = await fetch("http://localhost:8080/checkrole/"+codePartie);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
    static async startAskRole(){
        setInterval(async () => {
            const result = await GameService.askRole();
            return result;
        }, 2000);
    }
    static displayWaitingScreen(data){
        const titrePartie = document.querySelector(".titre");
        titrePartie.innerHTML = "En attente du deuxième joueur";
        titrePartie.innerHTML = titrePartie.innerHTML + "<br>Vous êtes sur la partie de: " + data["game"].code_perso + ". Voici le code de la partie: " + data["game"].code_numerique;
    }
    static async wait(){
        try {
            const titrePartie = document.querySelector(".titre");
            titrePartie.innerHTML = "En attente du choix des roles";
            const codePartie = sessionStorage.getItem("codePartie");
            const response = await fetch("http://localhost:8080/ready/"+codePartie);
            if (response.status === 200){
                return true;
            } 
            else {
                return false;
            }
            } catch (error) {
                console.error("Erreur dans wait:", error);
                return false;
            }
        }     
    static async displayRole(data){
        let affected = false;
        const previousMDM = document.querySelector(".mdm");
        const previousMDI = document.querySelector(".mdi");
        const previousrandom = document.querySelector(".random");
        if((previousMDI !== null)&&(previousMDM !== null)&&(previousrandom !== null)){
            previousMDI.remove();
            previousMDM.remove();
            previousrandom.remove();
        }
        const plateau = document.querySelector(".plateau");
        const MDM = document.createElement("button");
        MDM.classList.add("mdm");
        MDM.innerHTML = "Maitre des mots";
        const MDI = document.createElement("button");
        MDI.classList.add("mdi");
        MDI.innerHTML = "Maitre des intuitions";
        const random = document.createElement("button");
        random.classList.add("Random");
        random.innerHTML = "Random";
        const titrePartie = document.querySelector(".titre");
        titrePartie.innerHTML = "Vous êtes sur la partie de: " + data["game"].code_perso + ". Voici le code de la partie: " + data["game"].code_numerique;
        titrePartie.innerHTML = titrePartie.innerHTML + "<br>Veuillez choisir votre role. Votre équipier aura l'autre ou role."
        plateau.appendChild(MDM);
        plateau.appendChild(MDI);
        plateau.appendChild(random);
        MDM.addEventListener("click", () => {
            const codePartie = sessionStorage.getItem("codePartie");
            let player = sessionStorage.getItem("user");
            player = player.replace(/^"|"$/g, '');
            console.log(player);
            const role1 = 2;
            const role2 = 1;
            GameService.affectRole(codePartie, player, role1, role2).then((isSet) => {
                if(isSet){
                    sessionStorage.setItem("block", JSON.stringify("true"));
                    console.log("Les roles sont affectés");
                    MDM.remove();
                    MDI.remove();
                    random.remove();
                    GameService.afficher(data);
                    return true;
                }
            })
        })
        MDI.addEventListener("click", () => {
            const codePartie = sessionStorage.getItem("codePartie");
            let player = sessionStorage.getItem("user");
            player = player.replace(/^"|"$/g, '');
            const role1 = 1;
            const role2 = 2;
            GameService.affectRole(codePartie, player, role1, role2).then((isSet) => {
                if(isSet){
                    sessionStorage.setItem("block", JSON.stringify("true"));
                    console.log("Les roles sont affectés");
                    MDM.remove();
                    MDI.remove();
                    random.remove();
                    GameService.afficher(data);
                    return true;
                }
            })
        })
        random.addEventListener("click", () => {
            sessionStorage.setItem("block", JSON.stringify("true"));
            const codePartie = sessionStorage.getItem("codePartie");
            let player = sessionStorage.getItem("user");
            player = player.replace(/^"|"$/g, '');
            const role1 = Math.floor(Math.random() * 2) + 1;
            let role2 = 0;
            if(role1 == 1){
                role2 = 2;
            }
            else{
                role2 = 1;
            }
            GameService.affectRole(codePartie, player, role1, role2).then((isSet) => {
                if(isSet){
                    console.log("Les roles sont affectés");
                    MDM.remove();
                    MDI.remove();
                    random.remove();
                    GameService.afficher(data);
                    return true;
                }
            })
        })
    }
    static async affectRole(codePartie, player, role1, role2){
        const response = await fetch("http://localhost:8080/affectrole/"+codePartie+"/"+player+"/"+role1+"/"+role2);
        if(response.status === 200){
            return true;
        }
        else{
            return false;
        }
    }
}