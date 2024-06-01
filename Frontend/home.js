import { MenuView } from "./view/menu-view.js";

function run(){
    const menu = new MenuView();
    menu.afficher();
}

window.addEventListener("load", run);