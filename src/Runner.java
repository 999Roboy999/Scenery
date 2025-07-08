// Import libraries 
import javax.swing.JFrame;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        // Set title and make library functions
        JFrame fr = new JFrame("My Scenery");
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter 1 for the Day theme and enter 2 for the Night theme: ");
        int background = scan.nextInt();
        int weather = 0;

        if (background == 1) {
            System.out.print("Enter 1 for the Sunny theme, enter 2 for the Cloudy theme, and enter 3 for the Rainy theme: ");
            weather = scan.nextInt();
        }

        else if (background == 2) {
            System.out.print("Enter 2 for the Cloudy theme, and enter 3 for the Rainy theme: ");
            weather = scan.nextInt();
        }

        Scenery scenery = new Scenery(background, weather);

        // Add the scenery to the frame
        fr.add(scenery);

        // Setup options
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);

    }
}


/*
+---------------+
|  Scenery     |
+---------------+
| -background : int  |
| -weather : int    |
| -rainX : int[]  |
| -rainY : int[]  |
| -cloudX : int[] |
| -cloudY : int[] |
| -cloudW : int[] |
| -cloudH : int[] |
| -carX : int[]   |
| -carSpeed : int[]|
| -carColors : Color[]|
| -treeX : int[]   |
| -numTrees : int    |
| -r : Random       |
| -black : Color    |
| -lightBlue : Color |
| -darkPurple : Color|
| -yellow : Color   |
| -darkBlue : Color |
| -gray : Color     |
| -grayShadowColor : Color|
| -grayHighlightColor : Color|
| -white : Color    |
| -blue : Color     |
| -brown : Color    |
| -lightBrown : Color|
| -darkMatteBrown : Color|
| -darkGray : Color |
| -tan : Color      |
| -tanShadowColor : Color|
| -darkTan : Color  |
| -mustardYellow : Color|
| -matteBrown : Color|
| -matteBrownShadowColor : Color|
| -green : Color    |
| -shadowGreen : Color|
| -brickRed : Color  |
| -mortar : Color   |
| -peach : Color    |
| -peachShadowColor : Color|
| -pink : Color     |
| -pinkShadowColor : Color|
+---------------+
| +Scenery(int, int) : void|
| +paintComponent(Graphics) : void|
| +weather(Graphics, int) : void|
| +drawGround(Graphics, int, int, int, int, Color, Color) : void|
| +drawSea(Graphics, int, int, int, int, Color, Color, Color) : void|
| +drawSun(Graphics, int, int) : void|
| +drawClouds(Graphics, Color) : void|
| +rain(Graphics) : void|
| +drawBuilding(Graphics, int, int, int, int, Color, Color, int, int) : void|
| +drawWindows(Graphics, int, int, int, int, int, int, int, int, Color, Color, Color) : void|
| +drawTree(Graphics, int, int, int, int, Color, Color, Color, Color) : void|  
| +buildingLines(Graphics, int, int, int, int, Color, Color, Color, int, int) : void|
| +brickPattern(Graphics, int, int, int, int, Color, Color, int, int, Color, Color, int, int, int, int) : void|
| +drawCar(Graphics, int, int, Color) : void|
+---------------+
*/