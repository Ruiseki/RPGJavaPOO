package src.GameSystem;

import src.*;
import src.Character.Archetype;
import src.Character.archetype.*;

import java.io.File;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static src.Main.getScanner;

abstract public class Game {

    public static int GenerateMenuAndReturnChoice(String title, List<String> options)
    {
        System.out.println(title);
        for (int i=1; i<=options.size(); i++){
            System.out.println(i + " -> " + options.get(i-1));
        }
        int input = -2;
        boolean isInput = false;
        do{
            try {
                input = getScanner().nextInt();
                getScanner().nextLine();
                if (input > 0 && input <= options.size()){;
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

    public static int GenerateMenuAndReturnChoice(List<String> options)
    {
        return GenerateMenuAndReturnChoice("Chose : ", options);
    }

    public static void createForBattle(Archetype[] forBattle){
        for (int i=0; i<2; i++){
            List <Archetype> persos= new ArrayList<Archetype>();
            createCharacter(persos);
            forBattle[i] = (Archetype) persos.get(persos.size()-1);
        }
    }

    public static void createCharacter(List<Archetype> persos) {
        System.out.println("Enter the character's name");
        String name; name = getScanner().nextLine();

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

    public static void mainMenu(List<Archetype> deck, Archetype[] fighters, int[] maxHealth)
    {
        Main.clear();
        int select;
        List<String> options = new ArrayList<String>();
        options.add("Quick Battle");
        options.add("Start a Battle");
        options.add("Manage Deck");
        options.add("Exit");

        select = GenerateMenuAndReturnChoice("Main menu", options);
        Main.clear();

        switch(select)
        {
            case 1:
                createForBattle(fighters);
                break;

            case 2:
                battleInit(deck, fighters, maxHealth);
                Main.clear();
                battle(fighters, maxHealth);
                break;

            case 3:
                menuDeck(fighters, deck);
                break;

            case 4:
                Main.getScanner().close();
                System.exit(0);
                break;
        }
    }

    public static void menuDeck(Archetype[] fighters,List<Archetype> deck)
    {
        int select;
        boolean menuExit = false;
        do
        {
            Main.clear();
            List<String> options = new ArrayList<String>();
            options.add("Create character");
            options.add("Delete character");
            options.add("Rename character");
            options.add("Back to menu");

            select = GenerateMenuAndReturnChoice(showDeck(deck), options);

            switch(select)
            {
                case 1:
                    Main.clear();
                    // addToDeck(deck);
                    createCharacter(deck);
                    break;
    
                case 2:
                    Main.clear();
                    deleteToDeck(deck);
                    Main.clear();
                    break;
    
                case 3:
                    Main.clear();
                    renameFighter(deck);
                    break;
    
                case 4:
                    menuExit = true;
                    break;

                default:
                    menuExit = false;
            }
        }while(!menuExit);
    }

    public static String showDeck(List<Archetype> deck)
    {
        String text = "\n";
        System.out.println();
        if(deck.size() == 0)
        {
            text += "[ empty ]\n";
        }
        else
        {
            for(int i=0; i < deck.size(); i++)
            {
                text += i+" : ["+deck.get(i).getType()+"] - "+deck.get(i).getName()+"\n";
            }
        }
        text += "\n";
        return text;
    }

    public static void addToDeck(List<Archetype> deck)
    {
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
                "\nsyntaxe tips : Integer [optional name for your hero]\n"
            );

            select = getScanner().nextLine();
            args = select.split(" ");
        }while(!Main.isInteger(args[0]) || Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 3);

        switch(Integer.parseInt(args[0]))
        {
            case 1:
                try
                {
                    deck.add(new Warrior(args[1]));
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck.add(new Warrior());
                }
                break;

            case 2:
                try
                {
                    deck.add(new Mage(args[1]));
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck.add(new Mage());
                }
                break;

            case 3:
                try
                {
                    deck.add(new Thief(args[1]));
                }
                catch(ArrayIndexOutOfBoundsException ex)
                {
                    deck.add(new Thief());
                }
                break;
        }
    }

    public static void deleteToDeck(List<Archetype> deck)
    {
        deck.remove(findFighterIndexFromDeckByName(deck, "Enter the name of the hero you want to delete from your deck :"));
    }

    public static void renameFighter(List<Archetype> deck)
    {
        int index = findFighterIndexFromDeckByName(deck, "Enter the name of the hero you want to rename :");
        System.out.println("New name : ");
        deck.get(index).setName(Main.getScanner().nextLine().split(" ")[0]);
    }

    public static int findFighterIndexFromDeckByName(List<Archetype> deck, String indication)
    {
        while(true)
        {
            System.out.println(showDeck(deck));
            System.out.println(indication);
            String select = Main.getScanner().nextLine();
            for(int i=0; i < deck.size(); i++)
            {
                if(deck.get(i).getName().equals(select)) return i;
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

    public static void battleInit(List<Archetype> deck, Archetype[] fighters, int[] maxHealth)
    {
        int player1 = findFighterIndexFromDeckByName(deck, "Enter the name of your your first character");
        int player2 = findFighterIndexFromDeckByName(deck, "Enter the name of your your second character");

        fighters[0] = deck.get(player1);
        fighters[1] = deck.get(player2);
        maxHealth[0] = fighters[0].getHeath();
        maxHealth[1] = fighters[1].getHeath();
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