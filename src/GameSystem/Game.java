package src.GameSystem;


import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class Game {


    public static int menu(String[] options){
        System.out.println("Chose :");
        for (int i=1; i<=options.length; i++){
            System.out.println(i + " " + options[i-1]);
        }
        Scanner sc;
        int input = -2;
        boolean isInput = false;
        do{
            try {
                sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input > 0 && input <= options.length){
                    isInput = true;
                }else{
                    System.out.println("Please enter a valid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
        } while (!isInput);
        return input;
    }

    public static void createCharacter() {};
    /*
        Permet d'ajouter un personnage jouable dans une liste restreinte (5 perso par personne max)
        Le joueur doit lire le nom du personnage et ça classe. (quelque chose comme Robert - Guerrier)
        Le joueur vas pouvoir choisir une configuration simple (il dois juste renseigné le nom de perso)
        et une configuration avancé ou il vas choisir toute les infos spécifique ou non au personnage
    */

    public static void deleteCharacter() {};
    /* 
        Supprime un perso de la list des perso jouable par le joueur.
        Doit pouvoir afficher "[ empty ]" lors d'un balayage lorsque la place est libre.
    */
        
    public static void watchCharacterDeck() {};
    /*
        Affiche le deck du joueur qui joue
        Pourrais ressemblé à ça :

        LeLache, Thief
        Arthur, Warrior
        Goliath, Warrior (av.)
        [ empty ]
        [ empty ]
    */
}