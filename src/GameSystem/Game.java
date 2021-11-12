package src.GameSystem;

import src.*;
import src.Character.Archetype;
import src.Character.archetype.*;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static src.Main.getScanner;

abstract public class Game {


    public static int GenerateMenuAndReturnChoice(List<String> options){
        System.out.println("Chose :");
        for (int i=1; i<=options.size(); i++){
            System.out.println(i + " " + options.get(i-1));
        }
        int input = -2;
        boolean isInput = false;
        do{
            try {
                input = getScanner().nextInt();
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

    public static void createForBattle(List<Archetype> persos, Archetype[] forBattle){
        for (int i=0; i<2; i++){
            createCharacter(persos);
            forBattle[i] = (Archetype) persos.get(persos.size()-1);
        }
    }

    public static void createCharacter(List<Archetype> persos) {
        System.out.println("Enter the character's name");
        String name = getScanner().nextLine();

        System.out.println("Enter " + name + "'s class");

        File folder = new File("./src/Character/archetype/");
        File[] listOfFiles = folder.listFiles();
        List<String> archs = new ArrayList<String>();

        for (File file : listOfFiles) {
            if (file.isFile()){
                archs.add(file.getName().substring(0, file.getName().length()-5));
            }
        }       //{Mage, Warrior, Thief}
        Archetype perso;

        String[] c = new String[archs.size()];
        for(int i=0; i<archs.size(); i++){
            c[i] = "src.Character.archetype." + archs.get(i);
        }
        try {
            Class arch = Class.forName(c[GenerateMenuAndReturnChoice(archs)-1]);
            perso = (Archetype) arch.newInstance();

            perso.setName(name);

        } catch (ClassNotFoundException e){
            System.out.println(name + "is a warrior by default");
            perso = new Warrior(name);
        } catch (InstantiationException e){
            System.out.println(name + "is a warrior by default");
            perso = new Warrior(name);
        } catch (IllegalAccessException e){
            System.out.println(name + "is a warrior by default");
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

    public static void menuDeck(Archetype[] fighters,Archetype[] deck)
    {
        int select;
        boolean menuExit = false;
        do
        {
            List options = new ArrayList();
            options.add("Start a battle");
            options.add("Create character");
            options.add("Delete character");
            options.add("Exit");

            select = GenerateMenuAndReturnChoice(options);

    
            switch(select)
            {
                case 1:
                    startABattle();
                    menuExit = true;
                    break;
    
                case 2:
                    addToDeck(deck);
                    break;
    
                case 3:
                    deleteToDeck(deck);
                    break;
    
                case 4:
                    getScanner().close();
                    System.exit(0);
                    break;

                default:
                    menuExit = false;
            }
        }while(!menuExit);
    }

    public static void showDeck(Archetype[] deck)
    {
        System.out.println();
        for(int i=0; i<deck.length; i++)
        {
            if(deck[i] == null) System.out.println(i+" : [ empty ]");
            else System.out.println(i+" : ["+deck[i].getType()+"] - "+deck[i].getName());
        }
        System.out.println();
    }

    public static void addToDeck(Archetype[] deck)
    {
        int emplacement = -1;
        for(int i=0; i < deck.length; i++) // check if the deck is full, and if not choose the next free case
        {
            if(deck[i] == null)
            {
                emplacement = i;
                break;
            }
        }

        if(emplacement == -1)
        {
            System.out.println("There is no free emplacement !");
            try
            {
                Thread.sleep(3000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            return;
        }

        String select;
        String[] args;
        do
        {
            Main.clear();
            System.out.println(
                "Type : \n"+
                "1 -> Warrior\n"+
                "2 -> Mage\n"+
                "3 -> Thief\n"+
                "\n syntaxe tips : Integer [optional name for your hero]"
            );

            select = getScanner().nextLine();
            args = select.split(" ");
        }while(!Main.isInteger(args[0]) || Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 3);

        switch(Integer.parseInt(args[0]))
        {
            case 1:
                try
                {
                    deck[emplacement] = new Warrior(args[1]);
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck[emplacement] = new Warrior();
                }
                break;

            case 2:
                try
                {
                    deck[emplacement] = new Mage(args[1]);
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck[emplacement] = new Mage();
                }
                break;

            case 3:
                try
                {
                    deck[emplacement] = new Thief(args[1]);
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck[emplacement] = new Thief();
                }
                break;
        }
    }

    public static void deleteToDeck(Archetype[] deck)
    {
        Main.clear();
        String select;
        while(true)
        {
            Main.clear();
            showDeck(deck);
            System.out.println("Enter the name of the hero you want to delete from your deck :");
            select = getScanner().nextLine();
            for(int i=0; i < deck.length; i++)
            {
                if(deck[i] != null && deck[i].getName().equals(select))
                {
                    deck[i] = null;
                    return;
                }
            }

            Main.clear();
            System.out.println("Can't find the hero !");
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void renameFighter(Archetype[] deck)
    {

    }

    public static void startABattle()
    {

    }


    public static void battle(Archetype[] fighters, int[] maxHealth)
    {
        int round = 1;
        boolean isEnd;
        do // battle rounds
        {
            Main.clear();
            System.out.println("----- ROUND "+round+" -----\n");

            attack(fighters[0], fighters[1]); // player 1 attack playe 2

            isEnd = Game.isGameFinished(fighters);

            if(!isEnd)
            {
                attack(fighters[1], fighters[0]); // player 2 attack player 1
                isEnd = Game.isGameFinished(fighters);
            }

            healthBar(fighters, maxHealth);

            round++;

            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        }while(!isEnd);
        System.out.println("Battle terminate");
    }
    
    public static boolean isGameFinished(Archetype[] persos)
    {
        for(Archetype perso : persos) if(perso.getHeath() == 0) return true;
        return false;
    }

    public static void healthBar(Archetype[] fighters, int[] maxHealth)
    {
        int fighterIndex = 0; // index of maxhealth
        for(Archetype fighter : fighters)
        {
            String healthBar = fighter.getName()+"\n";
            healthBar += "HP: [";
            int numberOfLine = (int)100*fighter.getHeath()/maxHealth[fighterIndex]/10;

            for(int i = 0; i < 10; i++)
            {
                if(i < numberOfLine) healthBar += "-";
                else healthBar += " ";
            }
            healthBar += "] "+fighter.getHeath()+"/"+maxHealth[fighterIndex];
            System.out.println(healthBar+"\n");
            fighterIndex++;
        }
        System.out.println();
    }

    public static void attack(Archetype offenser, Archetype defenser)
    {
        int totalDamage;
        double thiefLuck = Math.random();

        System.out.println(offenser.getName()+" is attacking !");

        if(offenser instanceof Mage) totalDamage = ((Mage)offenser).getTotalDamageOnTurn();
        else if(offenser instanceof Thief) 
        {
            
            if(thiefLuck <= ((Thief)offenser).getCriRate())
            {
                System.out.println(offenser.getName()+ " inflicts critical damage !");
                totalDamage = ((Thief)offenser).critDamage();
            }
            else totalDamage = offenser.getAttack();
        }
        else totalDamage = offenser.getAttack();
        
       
        System.out.println(offenser.getName()+" give "+totalDamage+" damage to "+defenser.getName()+" !");

        if(defenser instanceof Warrior)
        {
            System.out.println(defenser.getName()+" attempt to parry the attack !");
            totalDamage = ((Warrior)defenser).blockAttack(totalDamage);
        }
        
        else if(defenser instanceof Thief)
        {
            thiefLuck = Math.random();
            if(thiefLuck <= ((Thief)defenser).getDodge()){
                System.out.println(defenser.getName()+" dodged the attack !");
                totalDamage = 0;
            }   
        }
        System.err.println(defenser.getName()+" lose "+totalDamage+" HP !\n");
        defenser.takeDamage(totalDamage);
    }
}