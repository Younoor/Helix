/* Name: Yousef Noor
 * Date: 11/20/23
 * Class: CS1083
 * Section: 005
 * Program: Helix spins in drawing panel
 */

import java.awt.*;
import java.util.*;
import static java.awt.Color.*;
public class Helix {

    //Calls for correct color of line when uncommenting the input
    public static Color getColor(int userCol){
        Color userChoice = null;
        if(userCol == 0){
            userChoice = green;
        }
        else if(userCol == 1){
            userChoice = gray;
        }
        else if(userCol == 2){
            userChoice = yellow;
        }
        else if(userCol == 3){
            userChoice = red;
        }
        else if(userCol == 4){
            userChoice = orange;
        }
        else if(userCol == 5){
            userChoice = pink;
        }
        else if(userCol == 6){
            userChoice = darkGray;
        }
        else if(userCol == 7){
            userChoice = blue;
        }
        else if(userCol == 8){
            userChoice = black;
        }
        return userChoice;
    }

    //This method is creating the new position for the lines beginning and end points
    public static int[] newPos(boolean clockW, int direc, int[] thisTime){

        //Calcuating coordinates if clockwise
        if(clockW){
            if((thisTime[0] == 300) && (thisTime[1] >= 100 && thisTime[1]< 300)){
                direc++;
            }
            else if((thisTime[0] > 100 && thisTime[0] <=300) && (thisTime[1] == 300)){
                direc += 2;
            }
            else if((thisTime[0] == 100) && (thisTime[1] <= 300 && thisTime[1] > 100)){
                direc += 3;
            }
            if(direc == 0){
                thisTime[0] += 10;
                thisTime[2] -= 10;
            }
            else if(direc == 1){
                thisTime[1] += 10;
                thisTime[3] -= 10;
            }
            else if(direc == 2){
                thisTime[0] -= 10;
                thisTime[2] += 10;
            }
            else if(direc == 3){
                thisTime[1] -= 10;
                thisTime[3] += 10;
            }
        }
        //Calculating if counterclockwise
        else{
            if((thisTime[0] >= 100 && thisTime[0] <300) && (thisTime[1] == 300)){
                direc++;
            }
            else if((thisTime[0] == 300) && (thisTime[1] <= 300 && thisTime[1] > 100)){
                direc += 2;
            }
            else if((thisTime[0] <= 300) && (thisTime[0] > 100) && (thisTime[1] == 100)){
                direc += 3;
            }
            if(direc == 0){
                thisTime[1] += 10;
                thisTime[3] -= 10;
            }
            else if(direc == 1){
                thisTime[0] += 10;
                thisTime[2] -= 10;
            }
            else if(direc == 2){
                thisTime[1] -= 10;
                thisTime[3] += 10;
            }
            else if(direc == 3){
                thisTime[0] -= 10;
                thisTime[2] += 10;
            }
        }

        return thisTime;
    }
    public static void drawHelix() {
        DrawingPanel panel = new DrawingPanel(400,400);
        Graphics g = panel.getGraphics();
        panel.setBackground(black);
        g.setColor(white);
        g.fillRect(100,100,200,200);
        g.drawString("UTSA - CS1083 - Section 005 - Prj 3 - Helix - Yousef Noor", 50, 50);
        Scanner scnr = new Scanner(System.in);
        int userColor;
        int userSpeed;
        int userTimes;
        int clockWay = 0;
        boolean clockOrNot = true;
        int startStop[] = {100,100,300,300};
        int lastPos = 0;
        int i;
        Random rand = new Random(); // This is my random object for the extra credit

        //Opening lines

        //This is asking how fast they would like their helix to spin
        do{
            System.out.print("Please, input the speed (1 - 10): ");
            userSpeed = scnr.nextInt();
            if(userSpeed < 1 || userSpeed> 10){
                System.out.println("Invalid Value.");  //Validating numbers
            }
        }while(userSpeed<1 || userSpeed>10);
        System.out.println();
        //This is asking how many times they would like the helix to move
        System.out.print("Please, input the number of times the line will be shown: ");
        userTimes = scnr.nextInt();
        System.out.println();
        //This is asking the user which way they would like the helix to spin
        do{
            System.out.println("Would you like your helix to spin clockwise?");
            System.out.print("0 - Yes, 1 - No ");
            clockWay = scnr.nextInt();
            if(clockWay < 0 || clockWay > 1){
                System.out.println("Invalid value."); // Validating numbers
            }
            else if(clockWay == 1){
                clockOrNot = false;
            }
            System.out.println();
        }while(clockWay <0 || clockWay > 1);
        // This is where you can ask the user for the color they would like
//        do {
//            System.out.println("Please, input the number corresponding to the color you want to show the helix");
//            System.out.print("0 - Green, 1 - Gray, 2 - Yellow, 3 - Red, 4 - Orange, 5 - Pink, 6 - Dark Gray, 7 - Blue, and 8 - Black ");
//            userColor = scnr.nextInt();
//            if(userColor < 0 || userColor > 8){
//                System.out.println("Invalid value.");
//            }
//        }while(userColor <0 || userColor > 8);
//        System.out.println();

        //String of speed above helix
        g.drawString("Speed: " + userSpeed, 175, 75);

        //Drawing the actual helix over every iteration
        for(i = 0; i<=userTimes; i++){
            //Erasing set iteration and coordinates
            g.setColor(black);
            g.fillRect(100,200,400,400);
            //Creating blank canvas
            g.setColor(white);
            g.fillRect(100,100,200,200);


            //Randomly assigning color
            //If you ask user for color comment out this line
            userColor = rand.nextInt(9);


            g.setColor(getColor(userColor));
            //Drawing line with coordinates
            g.drawLine(startStop[0], startStop[1], startStop[2], startStop[3]);
            //Drawing string with iteration and coordinates
            g.setColor(white);
            g.drawString("i: " + i, 200, 325);
            g.drawString("(" + startStop[0] + "," + startStop[1] + "),(" + startStop[2] + "," + startStop[3] + ")", 150, 355);
            //Assigning new coordinates and time delay
            startStop = newPos(clockOrNot, lastPos, startStop);
            panel.sleep(100 / userSpeed);
        }
    }

    //Main method
    public static void main(String[] args){
        System.out.println("UTSA – Fall 2023 – CS1083 - Section 005 – Prj 3 - Helix - written by Yousef Noor");

        //Call helix method
        drawHelix();

    }
}
