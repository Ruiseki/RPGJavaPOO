package src.Character;

public abstract class Archetype {

    private String _name, _type;
    private int _attack, _health, _initiative;

    public Archetype() {};

    public Archetype(String name)
    {
        name = _name;
    }

    public void takeDamage(int damage)
    {
        _health -= damage;
        if(_health < 0) _health = 0;
    }

    public int getAttack()
    {
        return _attack;
    }
    public int getHeath()
    {
        return _health;
    }
    public int getInitiative()
    {
        return _initiative;
    }
    public String getName()
    {
        return _name;
    }
    public String getType()
    {
        return _type;
    }

    public void setAttack(int newAttack)
    {
        _attack = newAttack;
    }
    public void setHealth(int newHealth)
    {
        _health = newHealth;
    }
    public void setInitiative(int newInitiative)
    {
        _initiative = newInitiative;
    }
    public void setName(String newName)
    {
        _name = newName;
    }
    public void setType(String newType)
    {
        _type = newType;
    }

    @Override
    public String toString()
    {
        return "Archetype {\n" +
                "\t_name='" + _name + "\'\n" +
                "\t_type='" + _type + "\'\n" +
                "\t_attack=" + _attack +"\n"+
                "\t_health=" + _health +"\n"+
                "\t_initiative=" + _initiative +"\n"+
                '}';
    }
}