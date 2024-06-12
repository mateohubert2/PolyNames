export class MenuView {
    afficher(){
        const menu = document.querySelector(".menu");

        let titre = document.createElement("h1");
        titre.innerHTML = "Bienvenue sur PolyNames";
        menu.appendChild(titre);

        let creer = document.createElement("button");
        creer.classList.add("button");
        creer.innerHTML = "Créer une partie";
        menu.appendChild(creer);
        creer.addEventListener('click', () => {
            window.location.href = 'http://localhost:8080/creation.html';
        });

        let rejoindre = document.createElement("button");
        rejoindre.classList.add("button");
        rejoindre.innerHTML = "Rejoindre une partie";
        menu.appendChild(rejoindre);
        rejoindre.addEventListener('click', () => {
            window.location.href = 'http://localhost:8080/rejoindre.html';
        });
    }
}