package Character.archetype;
import Character.Archetype;

public class Thief extends Archetype {
    private double _dodge, _critRate, _critDamage;

    public Thief()
    {
        this("", 0.25, 0.3);
    }

    public Thief(String name)
    {
        this(name, 0.25, 0.3);
    }

    public Thief(String name, double dodge, double critRate)
    {
        _dodge = dodge;
        _critRate = critRate;
        _critDamage = 2; // 200%

        if(name.equals(""))
        {
            _name = "Thief";
        }
        else
        {
            name = _name;
        }
        _health = 100;
        _initiative = 3;
        _attack = 4;
    }

    public double getDodge(){
        return _dodge;
    }
    public void setDodge(){

    }

    public double getCriRate(){
        return _critRate;
    }
    public void setCritRate(double newCritRate){
        _critRate = newCritRate;
    }

    public double getCritDamage(){
        return _dodge;
    }
    public void setCritDamage(double newCritDamage){
        _critDamage = newCritDamage;
    }

}