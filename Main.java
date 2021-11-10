import Character.Archetype;
import Character.archetype.*;

class Main
{
    public static void main(String[] args)
    {
        System.out.println("Application has started");
        Mage attaquantTest = new Mage();
        Warrior defenseurTest = new Warrior();

        attack(attaquantTest, defenseurTest);
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