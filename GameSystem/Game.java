package GameSystem;


import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class Game {


    public static int menu(String[] options){
        System.out.println("Chose :");
        for (int i=1; i<=options.length; i++){
            System.out.println(i + " " + options[i-1]);
        }
        Scanner sc;
        int input = -2;
        boolean isInput = false;
        do{
            try {
                sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input > 0 && input <= options.length){
                    isInput = true;
                }else{
                    System.out.println("Please enter a valid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
        } while (!isInput);
        return input;
    }
}