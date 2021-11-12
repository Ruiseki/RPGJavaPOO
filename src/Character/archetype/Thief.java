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

        if(name.equals("")) this.setName("Thief");
        else this.setName(name);
        this.setType("Thief");
        this.setHealth(100);
        this.setInitiative(3);
        this.setAttack(4);
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
        return this.getAttack() * _critDamage;
    }

    public double getCritDamage()
    {
        return _critDamage;
    }
    
    public void setCritDamage(int newCritDamage)
    {
        _critDamage = newCritDamage;
    }

    @Override
    public String toString() {
        return "Thief{" +
                "_name='" + getName() + '\'' +
                ", _type='" + getType() + '\'' +
                ", _attack=" + getAttack() +
                ", _health=" + getHeath() +
                ", _initiative=" + getInitiative() +
                ", _dodge=" + _dodge +
                ", _critRate=" + _critRate +
                ", _critDamage=" + _critDamage +
                '}';
    }
}