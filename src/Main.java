package src;
import src.Character.Archetype;
import src.Character.archetype.*;
import src.GameSystem.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        clear();
        System.out.println("Application has started");
        /*
            - choisir deux combatants dans le decks
            - faire s'attaquer les deux combatants
            - s'arrêté quand l'un voit ces HP inférieur ou égale a 0
            - Afficher le vainceur

            v0.1
        */

        List temp = new ArrayList();
        Archetype[] fighters = new Archetype[2];
        Game.createForBattle(temp, fighters);
        Archetype[] deck = new Archetype[5];
        Game.menuDeck(fighters ,deck);
        // List<Archetype> temp = new ArrayList<Archetype>();
        // Game.createForBattle(temp, fighters);
        
        int[] maxHealth = new int[2]; // get the maximum health of the character for the lifebar
        maxHealth[0] = fighters[0].getHeath();
        maxHealth[1] = fighters[1].getHeath();

        int round = 1;
        boolean isEnd;
        do // battle rounds
        {
            clear();
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
    
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public static Scanner getScanner()
    {
        return sc;
    }

    public static boolean isInteger(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }  
    }
}