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

        */
    }
    
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void attack(Archetype offenser, Archetype defenser)
    {
        int totalDamage;

        System.out.println(offenser.getName()+" is attacking !");

        if(offenser instanceof Mage) totalDamage = ((Mage)offenser).getTotalDamageOnTurn();
        // else if(offenser instanceof Thief) Faudra calculer l'attaque du voleur
        else totalDamage = offenser.getAttack();
        
        // calcule du taux crit du voleur
        //if()
        //else
        System.out.println(offenser.getName()+" give "+totalDamage+" damage to "+defenser.getName()+" !");

        if(defenser instanceof Warrior)
        {
            System.out.println(defenser.getName()+" attempt to parry the attack !");
            totalDamage = ((Warrior)defenser).blockAttack(totalDamage);
        }

        System.err.println(defenser.getName()+" lose "+totalDamage+" HP !");
    }   
}