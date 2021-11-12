package src.Character.archetype;
import src.Character.Archetype;

public class PunchingBall extends Archetype
{

    public PunchingBall()
    {
        this("");
    }

    public PunchingBall(String name)
    {
        if(name.equals("")) this.setName("PunchingBall");
        else this.setName(name);
        this.setType("PunchingBall");
        this.setHealth(200);
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
                ", _health=" + getHeath() +
                ", _initiative=" + getInitiative() +
                '}';
    }
}