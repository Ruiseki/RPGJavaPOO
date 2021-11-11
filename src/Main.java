package src;
import src.Character.Archetype;
import src.Character.archetype.*;
import src.GameSystem.Game;

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
        Game.createForBattle(fighters);

    }
    
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void attack(Archetype offenser, Archetype defenser)
    {
        int totalDamage;
        double number = Math.random();

        System.out.println(offenser.getName()+" is attacking !");

        if(offenser instanceof Mage) totalDamage = ((Mage)offenser).getTotalDamageOnTurn();
        else if(offenser instanceof Thief) 
        {
            
            if(number <= ((Thief)offenser).getCriRate())
            {
                System.out.println(offenser.getName()+ "inflicts critical Damage");
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
            if(number <=((Thief)defenser).getDodge()){
                totalDamage = 0;
            }   
        }
        System.err.println(defenser.getName()+" lose "+totalDamage+" HP !");
    }   
}