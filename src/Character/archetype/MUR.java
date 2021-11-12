package src.Character.archetype;
import src.Character.Archetype;

public class MUR extends Archetype
{

    public MUR()
    {
        this("");
    }

    public MUR(String name)
    {
        if(name.equals("")) this.setName("PunchingBall");
        else this.setName(name);
        this.setType("PunchingBall");
        this.setHealth(2000);
        this.setInitiative(0);
        this.setAttack(1);
    }

    public int blockAttack(int damage)
    {
        return damage;
    }

    @Override
    public String toString() {
        return "PunchingBall{" +
                "_name='" + getName() + '\'' +
                ", _type='" +getType() + '\'' +
                ", _attack=" + getAttack() +
                ", _health=" + getHealth() +
                ", _initiative=" + getInitiative() +
                '}';
    }
}