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
        if(name.equals("")) this.setName("Warrior");
        else this.setName(name);
        this.setType("Warrior");
        this.setHealth(130);
        this.setInitiative(1);
        this.setAttack(3);
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

    @Override
    public String toString() {
        return "Warrior{" +
                "_name='" + _name + '\'' +
                ", _type='" + _type + '\'' +
                ", _attack=" + _attack +
                ", _health=" + _health +
                ", _initiative=" + _initiative +
                ", _shieldStrength=" + _shieldStrength +
                '}';
    }
}