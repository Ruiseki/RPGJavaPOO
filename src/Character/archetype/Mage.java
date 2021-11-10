package src.Character.archetype;

import src.Character.Archetype;

public class Mage extends Archetype {
    private double _magicDamage;
    private int _magicStamina = 1; // magic damage will be divided by 2 each turn. magicStamina will be multiply by 2 each turn to make magicDamage / magicStamina = total magicdamage
    
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

    public int getTotalDamageOnTurn()
    {   
        int damage = (int)(_magicDamage / _magicStamina + _attack);
        _magicDamage *= 2;
        return damage;
    }
    
    public double getMagicDamage()
    {
        return _magicDamage;
    }
    public int getMagicStamina()
    {
        return _magicStamina;
    }

    public void setMagicDamage(double newMagicDamage)
    {
        _magicDamage = newMagicDamage;
    }
    public void setMagicStamina(int newMagicStamina)
    {
        _magicStamina = newMagicStamina;
    }
}