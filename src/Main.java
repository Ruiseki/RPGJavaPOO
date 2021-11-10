package src;
import src.Character.Archetype;
import src.Character.archetype.*;

class Main
{
    public static void main(String[] args)
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Application has started");
        Mage attaquantTest = new Mage();
        Warrior defenseurTest = new Warrior();
        attack(attaquantTest, defenseurTest);
    }

    public static void clear()
    {
        
    }

    public static void attack(Archetype offenser, Archetype defenser)
    {
        int totalDamage;

        System.out.println(offenser.getName()+" is attacking !");

        if(offenser instanceof Mage) totalDamage = ((Mage)offenser).getTotalDamageOnTurn();
        else if(offenser instanceof Thief) 
        {
            double number = Math.random();
            if(number <= ((Thief)offenser).getCriRate())
            {
                System.out.println(offenser.getName()+ "inflicts critical Damage");
                totalDamage = ((Thief)offenser).critDamage();
            }
            else totalDamage = offenser.getAttack();
        }
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

        else if(defenser instanceof Thief)
        {
            
        }
        System.err.println(defenser.getName()+" lose "+totalDamage+" HP !");
    }   
}