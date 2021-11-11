package src.GameSystem;


import src.Character.Archetype;
import src.Character.archetype.*;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

abstract public class Game {


    public static int menu(List options){
        System.out.println("Chose :");
        for (int i=1; i<=options.size(); i++){
            System.out.println(i + " " + options.get(i-1));
        }
        Scanner sc;
        int input = -2;
        boolean isInput = false;
        do{
            try {
                sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input > 0 && input <= options.size()){
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

    public static void createForBattle(List persos){
        for (int i=0; i<2; i++){
            createCharacter(persos);
        }
    }

    public static void createCharacter(List persos) {
        System.out.println("Enter the character's name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        System.out.println("Enter " + name + "'s class");

        File folder = new File("./src/Character/archetype/");
        File[] listOfFiles = folder.listFiles();
        List archs = new ArrayList();

        for (File file : listOfFiles) {
            if (file.isFile()){
                archs.add(file.getName().substring(0, file.getName().length()-5));
            }
        }       //{Mage, Warrior, Thief}
        Archetype perso;

        Class[] c = new Class[archs.size()];

        switch (menu(archs)){
            case 1:
                perso = new Mage(name);
                break;
            case 2:
                perso = new Warrior(name);
                break;
            case 3:
                perso = new Thief(name);
                break;

            default:
                System.out.println("You got a warrior by default");
                perso = new Warrior(name);
        }
        persos.add(perso);

    };
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

    public static boolean isGameFinished(Archetype[] persos)
    {
        for(Archetype perso : persos) if(perso.getHeath() == 0) return true;
        return false;
    }
}