package src;
import src.Character.Archetype;
import src.Character.archetype.*;
import src.GameSystem.Game;

import java.util.ArrayList;
import java.util.List;

class Main
{
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

        /* 
        
        Archetype[] fighters = new Archetype[2];
        Game.menu();
        Game.createCharacter();

        */
        
        Archetype[] fighters = new Archetype[2];
        List<Archetype> temp = new ArrayList<Archetype>();
        Game.createForBattle(temp, fighters);

        int round = 1;
        boolean isEnd;
        do
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

            round++;
        }while(!isEnd);
        System.out.println("Battle terminate");
    }
    
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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