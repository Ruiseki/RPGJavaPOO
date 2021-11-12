package src;
import src.Character.Archetype;
// import src.Character.archetype.*;
import src.GameSystem.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        clear();
        System.out.println("Application has started");

        List<Archetype> deck = new ArrayList<Archetype>();
        Archetype[] fighters = new Archetype[2];
        int[] maxHealth = new int[2]; // get the maximum health of the character for the lifebar

        Game.mainMenu(deck, fighters, maxHealth);
    }
    
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Scanner getScanner()
    {
        return sc;
    }

    public static boolean isInteger(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }  
    }
}