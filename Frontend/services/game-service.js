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
}