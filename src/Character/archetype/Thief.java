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
        return "Thief {\n" +
                "\t_name='" + getName() + "\'\n" +
                "\t_type='" + getType() + "\'\n" +
                "\t_attack=" + getAttack() +"\n"+
                "\t_health=" + getHeath() +"\n"+
                "\t_initiative=" + getInitiative() +"\n"+
                "\t_dodge=" + _dodge +"\n"+
                "\t_critRate=" + _critRate +"\n"+
                "\t_critDamage=" + _critDamage +"\n"+
                '}';
    }
}