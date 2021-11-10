package Character.archetype;
import Character.Archetype;

public class Mage extends Archetype {
    private int _magicDamage;
    private double _magicStamina; // magic damage will be divided by 2 each turn
    
    public Mage()
    {
        this("", 16);
    }

    public Mage(String name)
    {
        this(name, 16);
    }

    public Mage(String name, int magicDamage)
    {
        _magicDamage = magicDamage;
        _magicStamina = _magicDamage;
        if(name.equals(""))
        {
            _name = "Warrior";
        }
        else
        {
            _name = name;
        }
        _health = 70;
        _initiative = 0;
        _attack = 5;
    }

    public int getMagicDamage()
    {
        return _magicDamage;
    }
    public double getMagiStamina()
    {
        return _magicStamina;
    }

    public void setMagicDamage(int newMagicDamage)
    {
        newMagicDamage = _magicDamage;
    }
    public void setMagicStamina(double newMagicStamina)
    {
        newMagicStamina = _magicStamina;
    }
}