package src.Character.archetype;
import src.Character.Archetype;

public class Thief extends Archetype {
    private double _dodge, _critRate;
    private int _critDamage;
    
    public Thief()
    {
        this("", 0.15, 0.3);
    }

    public Thief(String name)
    {
        this(name, 0.15, 0.3);
    }

    public Thief(String name, double dodge, double critRate)
    {
        _dodge = dodge;
        _critRate = critRate;
        _critDamage = 2; // 200%

        if(name.equals("")) _name = "Thief";
        else _name = name;
        _type = "Thief";
        _health = 100;
        _initiative = 3;
        _attack = 4;
    }



    public double getDodge()
    {
        return _dodge;
    }

    public void setDodge()
    {

    }
    
    public double getCriRate()
    {
        return _critRate;
    }

    public void setCritRate(double newCritRate)
    {
        _critRate = newCritRate;
    }


    public int critDamage(){
        return _attack * _critDamage;
    }

    public double getCritDamage()
    {
        return _critDamage;
    }
    
    public void setCritDamage(int newCritDamage)
    {
        _critDamage = newCritDamage;
    }

}