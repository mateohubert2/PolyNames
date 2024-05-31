package models;

import java.util.ArrayList;

public record Content(
    Game game,
    ArrayList<Card> cards,
    ArrayList<Player> players
){};