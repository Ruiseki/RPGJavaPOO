package src.Character.archetype;
import src.Character.Archetype;

public class Warrior extends Archetype
{
    private int _shieldStrength;

    public Warrior()
    {
        this("", 2);
    }

    public Warrior(String name)
    {
        this(name, 2);
    }

    public Warrior(String name, int shieldStrength)
    {
        _shieldStrength = shieldStrength;
        if(name.equals(""))
        {
            _name = "Warrior";
        }
        else
        {
            _name = name;
        }
        _health = 130;
        _initiative = 1;
        _attack = 3;
    }

    public int blockAttack(int damage)
    {
        return damage - _shieldStrength;
    }

    public int getShieldStrength()
    {
        return _shieldStrength;
    }

    public void setShieldStrength(int newShield)
    {
        newShield = _shieldStrength;
    }
}