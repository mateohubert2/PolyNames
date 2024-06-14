import { SSEClient } from "../sse-client.js";

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
        let block = sessionStorage.getItem("block");
        if(block !== null){
            block = block.replace(/^"|"$/g, '');
        }
        if(block !== "true"){
            const cardMix = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24];
            const cardMixIndex = [];
            for(let i = 0; i < data["cards"].length; i++){
                let index = Math.floor(Math.random() * (cardMix.length-1));
                cardMixIndex.push(cardMix[index]);
                cardMix.splice(index, 1);
            }
            sessionStorage.setItem("game", JSON.stringify(cardMixIndex));
            let role = sessionStorage.getItem("role");
            if(role !== null){
                role = role.replace(/^"|"$/g, '');
            }
            if(role === "MDM"){
                const word = document.createElement("input");
                word.classList.add("text");
                word.value = "indice";
                const number = document.createElement("input");
                number.classList.add("text1");
                number.value = "nombre de mots";
                const button = document.createElement("button");
                button.classList.add("button");
                button.innerHTML = "Envoyer le mot";
                const body = document.querySelector("body");
                const plateau = document.querySelector(".plateau");
                body.insertBefore(word, plateau);
                body.insertBefore(number, plateau);
                body.insertBefore(button, plateau);
                button.addEventListener("click", () => {
                    let clickable = sessionStorage.getItem("clickable");
                    if(clickable !== null){
                        clickable = clickable.replace(/^"|"$/g, '');
                    }
                    if(clickable === "true"){
                    const word = document.querySelector(".text");
                    const mot = word.value;
                    const number = document.querySelector(".text1");
                    const nombre = number.value;
                    GameService.sendWord(mot);
                    GameService.sendWord(nombre);
                    GameService.sendNumber(nombre);
                    sessionStorage.setItem("clickable", JSON.stringify("false"));
                    }
                });
                for(let i = 0; i < data["cards"].length; i++){
                    const cartes = document.querySelector(".cards");
                    const carte = document.createElement("div");
                    carte.classList = "card"+data["cards"][cardMixIndex[i]].couleur;
                    carte.innerHTML = data["cards"][cardMixIndex[i]].mot;
                    cartes.appendChild(carte);
                }
                const clickable = sessionStorage.getItem("clickable");
                if(clickable === null){
                    sessionStorage.setItem("clickable", JSON.stringify("true"));
                }
            }
            else{
                sessionStorage.setItem("clickable_card", JSON.stringify("false"));
                for(let i = 0; i < data["cards"].length; i++){
                    const cartes = document.querySelector(".cards");
                    const carte = document.createElement("div");
                    carte.classList = "card";
                    carte.innerHTML = data["cards"][cardMixIndex[i]].mot;
                    let compteur = 0;
                    carte.addEventListener("click", () => {
                        let clickable_card = sessionStorage.getItem("clickable_card");
                        clickable_card = clickable_card.replace(/^"|"$/g, '');
                        if(clickable_card === "true"){
                            const mot = carte.innerHTML;
                            let guessNumber = sessionStorage.getItem("possibleFind");
                            if(guessNumber !== null){
                                guessNumber = guessNumber.replace(/^"|"$/g, '');
                            }
                            if(guessNumber >= 0){
                                let codePartie = sessionStorage.getItem("codePartie");
                                codePartie = codePartie.replace(/^"|"$/g, '');
                                GameService.checkColorAndEvoleTheGame(codePartie, mot).then((value) => {
                                    guessNumber--;
                                    sessionStorage.setItem("possibleFind", JSON.stringify(guessNumber));
                                    if(value === 1){
                                        carte.classList = "card1";
                                        console.log("point");
                                    }
                                    if(value === 2){
                                        carte.classList = "card2";
                                        console.log("pas de point");
                                    }
                                    if(value === 3){
                                        carte.classList = "card3";
                                        console.log("perdu");
                                    }
                                });
                            }
                        }
                    });
                    cartes.appendChild(carte);
                }
                const sseClient = new SSEClient("localhost:8080");
                sseClient.connect();
                let compteur = 0;
                const idSEE = setTimeout(() => {
                    sseClient.subscribe("mot", (word) => {
                        GameService.displayWord(word);
                    });
                    sseClient.subscribe("number", (number) => {
                        GameService.howManyFindPossible(number);
                    });
                    compteur++;
                    if(compteur > 5){
                        clearInterval(idSEE);
                    }
                }, 1000);
            }
        }
        else{
            let role = sessionStorage.getItem("role");
            if(role !== null){
                role = role.replace(/^"|"$/g, '');
            }
            const game = JSON.parse(sessionStorage.getItem("game"));
            if(role === "MDM"){
                const word = document.createElement("input");
                word.classList.add("text");
                word.value = "indice";
                const number = document.createElement("input");
                number.classList.add("text1");
                number.value = "nombre de mots";
                const button = document.createElement("button");
                button.classList.add("button");
                button.innerHTML = "Envoyer le mot";
                const body = document.querySelector("body");
                const plateau = document.querySelector(".plateau");
                body.insertBefore(word, plateau);
                body.insertBefore(number, plateau);
                body.insertBefore(button, plateau);
                button.addEventListener("click", () => {
                    let clickable = sessionStorage.getItem("clickable");
                    if(clickable !== null){
                        clickable = clickable.replace(/^"|"$/g, '');
                    }
                    if(clickable === "true"){
                    const word = document.querySelector(".text");
                    const mot = word.value;
                    const number = document.querySelector(".text1");
                    const nombre = number.value;
                    GameService.sendWord(mot);
                    GameService.sendWord(nombre);
                    GameService.sendNumber(nombre);
                    sessionStorage.setItem("clickable", JSON.stringify("false"));
                    }
                });
                for(let i = 0; i < data["cards"].length; i++){
                    const cartes = document.querySelector(".cards");
                    const carte = document.createElement("div");
                    carte.classList = "card"+data["cards"][game[i]].couleur;
                    carte.innerHTML = data["cards"][game[i]].mot;
                    cartes.appendChild(carte);
                }
                const clickable = sessionStorage.getItem("clickable");
                if(clickable === null){
                    sessionStorage.setItem("clickable", JSON.stringify("true"));
                }
            }
            else{
                sessionStorage.setItem("clickable_card", JSON.stringify("false"));
                for(let i = 0; i < data["cards"].length; i++){
                    const cartes = document.querySelector(".cards");
                    const carte = document.createElement("div");
                    carte.classList = "card";
                    carte.innerHTML = data["cards"][game[i]].mot;
                    carte.addEventListener("click", () => {
                        let clickable_card = sessionStorage.getItem("clickable_card");
                        clickable_card = clickable_card.replace(/^"|"$/g, '');
                        if(clickable_card === "true"){
                            const mot = carte.innerHTML;
                            let guessNumber = sessionStorage.getItem("possibleFind");
                            if(guessNumber !== null){
                                guessNumber = guessNumber.replace(/^"|"$/g, '');
                            }
                            if(guessNumber >= 0){
                                let codePartie = sessionStorage.getItem("codePartie");
                                codePartie = codePartie.replace(/^"|"$/g, '');
                                GameService.checkColorAndEvoleTheGame(codePartie, mot).then((value) => {
                                    guessNumber--;
                                    sessionStorage.setItem("possibleFind", JSON.stringify(guessNumber));
                                    if(value === 1){
                                        carte.classList = "card1";
                                        console.log("point");
                                    }
                                    if(value === 2){
                                        carte.classList = "card2";
                                        console.log("pas de point");
                                    }
                                    if(value === 3){
                                        carte.classList = "card3";
                                        console.log("perdu");
                                    }
                                });
                            }
                        }
                    });
                    cartes.appendChild(carte);
                }
                const sseClient = new SSEClient("localhost:8080");
                sseClient.connect();
                sseClient.subscribe("mot", (word) => {
                    GameService.displayWord(word);
                });
                sseClient.subscribe("number", (number) => {
                    GameService.howManyFindPossible(number);
                });
            }
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
                            const code = document.querySelector(".text");
                            const button = document.querySelector(".button");
                            code.remove();
                            button.remove();
                            GameService.getRole().then((MAJ) => {
                                if(MAJ){
                                    GameService.loadGame(codePartie).then((dataMAJ) => {
                                        GameService.afficher(dataMAJ);
                                    })
                                }
                                else{
                                    GameService.afficher(data);
                                }
                            });
                            return true;
                        }
                        let isMaster = sessionStorage.getItem("master");
                        if(isMaster !== null){
                            isMaster = isMaster.replace(/^"|"$/g, '');
                        }
                        if(isMaster === "true"){
                            const code = document.querySelector(".text");
                            const button = document.querySelector(".button");
                            code.remove();
                            button.remove();
                            let validator = 0;
                            if(block !== "true"){
                                GameService.setPartie(codePartie).then(() => {
                                    let player = sessionStorage.getItem("user");
                                    player = player.replace(/^"|"$/g, '');
                                    GameService.affectRole(codePartie, player, 1, 1).then(() => {
                                        GameService.displayWaitingScreen(data);
                                        let intervalId = setInterval((() => {
                                            GameService.askRole(codePartie).then((correctNumberOfPlayer) => {
                                                if((correctNumberOfPlayer)&&(validator === 0)){
                                                    validator = 1;
                                                    GameService.displayRole(data);
                                                    clearInterval(intervalId);
                                                }
                                            })
                                        }), 2000);
                                    })
                                });
                            }
                            else{
                                GameService.afficher(data);
                                sessionStorage.setItem("block", JSON.stringify("true"));
                            }
                        }
                        else{
                            const code = document.querySelector(".text");
                            const button = document.querySelector(".button");
                            code.remove();
                            button.remove();
                            let validator = 0;
                            if(block !== "true"){
                                GameService.setPartie(codePartie).then(() => {
                                    let player = sessionStorage.getItem("user");
                                    player = player.replace(/^"|"$/g, '');
                                    GameService.affectRole(codePartie, player, 1, 1).then(() => {
                                        sessionStorage.setItem("codePartie", JSON.stringify(codePartie));
                                        let intervalId = setInterval((() => {
                                            GameService.checkRole(codePartie).then((correctRole) => {
                                                if((correctRole)&&(validator === 0)){
                                                    validator = 1;
                                                    GameService.getRole().then((MAJ) => {
                                                        if(MAJ){
                                                            GameService.loadGame(codePartie).then((dataMAJ) => {
                                                                GameService.afficher(dataMAJ);
                                                                sessionStorage.setItem("block", JSON.stringify("true"));
                                                            })
                                                        }
                                                        else{
                                                            GameService.afficher(data);
                                                            sessionStorage.setItem("block", JSON.stringify("true"));
                                                        }
                                                        clearInterval(intervalId);
                                                    });
                                                }
                                            })
                                        }), 2000);
                                    })
                                });
                            }
                            else{
                                GameService.afficher(data);
                                sessionStorage.setItem("block", JSON.stringify("true"));
                            }
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
        random.classList.add("random");
        random.innerHTML = "Random";
        const titrePartie = document.querySelector(".titre");
        titrePartie.innerHTML = "Vous êtes sur la partie de: " + data["game"].code_perso + ". Voici le code de la partie: " + data["game"].code_numerique;
        titrePartie.innerHTML = titrePartie.innerHTML + "<br>Veuillez choisir votre role. Votre équipier aura l'autre."
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
                    console.log("Les roles sont affectés");
                    MDM.remove();
                    MDI.remove();
                    random.remove();
                    GameService.setColorCard(data).then(() => {
                        GameService.getRole().then((MAJ) => {
                            if(MAJ){
                                GameService.loadGame(codePartie).then((dataMAJ) => {
                                    GameService.afficher(dataMAJ);
                                    sessionStorage.setItem("block", JSON.stringify("true"));
                                })
                            }
                            else{
                                GameService.afficher(data);
                                sessionStorage.setItem("block", JSON.stringify("true"));
                            }
                            return true;
                        });
                    });
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
                    console.log("Les roles sont affectés");
                    MDM.remove();
                    MDI.remove();
                    random.remove();
                    GameService.setColorCard(data).then(() => {
                        GameService.getRole().then((MAJ) => {
                            if(MAJ){
                                GameService.loadGame(codePartie).then((dataMAJ) => {
                                    GameService.afficher(dataMAJ);
                                    sessionStorage.setItem("block", JSON.stringify("true"));
                                })
                            }
                            else{
                                GameService.afficher(data);
                                sessionStorage.setItem("block", JSON.stringify("true"));
                            }
                            return true;
                        });
                    });
                }
            })
        })
        random.addEventListener("click", () => {
            let codePartie = sessionStorage.getItem("codePartie");
            codePartie = codePartie.replace(/^"|"$/g, '');
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
                    GameService.setColorCard(data).then(() => {
                        GameService.getRole().then((MAJ) => {
                            if(MAJ){
                                GameService.loadGame(codePartie).then((dataMAJ) => {
                                    GameService.afficher(dataMAJ);
                                    sessionStorage.setItem("block", JSON.stringify("true"));
                                })
                            }
                            else{
                                GameService.afficher(data);
                                sessionStorage.setItem("block", JSON.stringify("true"));
                            }
                            return true;
                        });
                    });
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
    static async setColorCard(data){
        const allCardsId = [];
        for(let i = 0; i < data["cards"].length; i++){
            allCardsId.push(data["cards"][i].id);
        }
        const randomcard = [];
        const tabWithIdToSet = [];
        for(let i = 0; i < 10; i++){
            let valide = 0;
            let condition = 0;
            let temp = 0;
            while(condition == 0){
                valide = 0;
                temp = Math.floor(Math.random() * 24);
                for(let j = 0; j < randomcard.length; j++){
                    if(temp === randomcard[j]){
                        valide++;
                    }
                }
                if(valide == 0){
                    condition = 1;
                }
            }
        randomcard.push(temp);
        }
        for(let i = 0; i < randomcard.length; i++){
            tabWithIdToSet[i] = data["cards"][i].id;
        }
        return GameService.sendColor(tabWithIdToSet).then(() => {
            console.log("les cartes ont bien été coloré");
            }).catch((error) => {
            console.log("les cartes ne sont pas colorés", error);
        })
    }
    static async sendColor(tab){
        let affectation = 0;
        for(let i = 0; i < (tab.length-2); i++){
            const response = await fetch("http://localhost:8080/setcolor/"+"3/"+tab[i]);
            if(response.status === 200){
                affectation++;
            }
        }
        for(let i = (tab.length-2); i < tab.length; i++){
            const response = await fetch("http://localhost:8080/setcolor/"+"2/"+tab[i]);
            if(response.status === 200){
                affectation++;
            }
        }
        if(affectation == 10){
            return true;
        }
        else{
            return false;
        }
    }
    static async getRole(){
        let player = sessionStorage.getItem("user");
        let codePartie = sessionStorage.getItem("codePartie");
        player = player.replace(/^"|"$/g, '');
        codePartie = codePartie.replace(/^"|"$/g, '');
        const response = await fetch("http://localhost:8080/getrole/"+codePartie+"/"+player);
        if(response.status === 200){
            sessionStorage.setItem("role", JSON.stringify("MDM"));
            return true;
        }
        else{
            return false;
        }
    }
    static async sendWord(word){
        const response = await fetch("http://localhost:8080/mot/"+word, {method: "POST"});
    }
    static displayWord(word){
        const indice = document.createElement("div");
        indice.classList.add("indice");
        indice.innerHTML = word;
        const plateau = document.querySelector(".plateau");
        const body = document.querySelector("body");
        body.insertBefore(indice, plateau);
        sessionStorage.setItem("clickable_card", JSON.stringify("true"));
    }
    static async checkColorAndEvoleTheGame(codePartie, mot){
        const response = await fetch("http://localhost:8080/checkcolor/"+codePartie+"/"+mot);
        if(response.status === 200){
            const valeur = response.json();
            return valeur;
        }
    }
    static async sendNumber(number){
        const response = await fetch("http://localhost:8080/number/"+number, {method: "POST"});
    }
    static howManyFindPossible(number){
        console.log(number);
        sessionStorage.setItem("possibleFind", JSON.stringify(number));
    }
}