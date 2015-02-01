
package brooklynbridgepoker;

import java.io.IOException;
import java.util.Scanner;


public class Menus {
    	
    public static void printTitle() throws InterruptedException{
		String title = "\n\tBrooklyn Bridge Poker\n\t--------------\n";
		for (int i = 0; i < title.length();i++){
			System.out.print(title.charAt(i));
			Thread.sleep(70);
		}
	}
		
    public static void printMainMenu() throws InterruptedException{
            System.out.println("=====================================");
            Thread.sleep(20);
	    System.out.println("|        BROOKLYN BRIDGE POKER      |");
	    Thread.sleep(20);
	    System.out.println("=====================================");
	    Thread.sleep(20);
	    System.out.println("|                                   |");
	    Thread.sleep(20);
	    System.out.println("|           [1] New Game            |");
	    Thread.sleep(20);
	    System.out.println("|           [2] Exit                |");
	    Thread.sleep(20);
	    System.out.println("|                                   |");
	    Thread.sleep(20);
	    System.out.println("=====================================");
	    Thread.sleep(20);
	}
    
    public static void mainMenuSelection() throws IOException, InterruptedException{
		System.out.print("\n\t    Selection > ");
		Scanner input = new Scanner(System.in);
		String selection = input.nextLine();
		while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) {
			
                    System.out.println("\tInvalid selection! Try again...");
                    Thread.sleep(2000);
                    printMainMenu();
                    mainMenuSelection();
			
		}
		if (selection.equals("2")){
                    
		}
		else if (selection.equals("3")){
			System.exit(0);
		}
                else if (selection.equals("1")){
                    
                }
	}
}
