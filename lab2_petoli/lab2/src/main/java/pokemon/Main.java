package pokemon;

import pokemon.Pokemon;

public class Main {

    public static Pokemon pokemon1;
    public static Pokemon pokemon2;
    
    public static void main(String[] args) {
        ///// Oppgave 6
        // Have two Pokémon fight until one is defeated
        pokemon1 = new Pokemon("Pikachu", 94, 12);
        pokemon2 = new Pokemon("Oddish", 100, 3);

        while (pokemon1.isAlive() == true && pokemon2.isAlive() == true) {
            pokemon1.attack(pokemon2);
            pokemon2.attack(pokemon1);
        }
    }
}
