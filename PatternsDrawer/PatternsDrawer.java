// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 4
 * Name: Annie Cho
 * Username: choanni
 * ID: 300575457
 */

import ecs100.*;
import java.awt.Color;

/** PatternsDrawer
Draws six different repetitive patterns. */

public class PatternsDrawer{

    public static final double PATTERN_LEFT = 50.0;   // Left side of the pattern
    public static final double PATTERN_TOP = 50.0;    // Top of the pattern
    public static final double PATTERN_SIZE = 300.0;  // The size of the pattern on the window

    /** 
     * Draws a star pattern consisting of a circle containing black rays (separated by white regions)
     * Asks the user for the number of rays.
     */
    public void drawStar(){
        UI.clearGraphics();
        double num = UI.askInt("How many rays:");
        double startAngle = 0;
        for (int i=0; i<num; i++){
            UI.drawOval(PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE);
            UI.fillArc(PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE, startAngle, 360/(num*2));
            startAngle = startAngle + 360/num;
        }

    }

    /** Draw a checkered board with alternating black and white squares
     *    Asks the user for the number of squares on each side
     *
     * CORE
     */
    public void drawDraughtsBoard(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows:");
        double squareSize = PATTERN_SIZE/num;

        for (int i=0; i<num; i++) {
            for (int j=0; j<num; j++) {
                if ((i+j)%2!=0){ 
                    UI.setColor(Color.black);
                }
                else{                  
                    UI.setColor(Color.white);
                }
                UI.fillRect(PATTERN_LEFT+ i*squareSize, PATTERN_TOP+ j*squareSize, squareSize, squareSize);
            }
        }
        UI.setColor(Color.black);
        UI.drawRect(PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE);
    }



    /** TriGrid
     * a triangular grid of squares that makes dark circles appear 
     * in the intersections when you look at it.
     *
     * COMPLETION
     */
    public void drawTriGrid(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows:");
        double spaceBetween = (PATTERN_SIZE/num)/5;
        double squareSize = PATTERN_SIZE/num;
        int squaresRow = num; // numbers of squares per row
        for (int i=0; i<num; i++){ // keeps count of the rows, adds one each time a row is done
            for (int j=0; j<squaresRow; j++){ // keeps count of squares in each row
                UI.fillRect(PATTERN_LEFT + j*(spaceBetween+squareSize), PATTERN_TOP + i*(spaceBetween+squareSize), squareSize, squareSize);
            }
            squaresRow = squaresRow - 1;
        }
    }
    
    /** CHALLENGE */
    
    public void drawChallengeCircles(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows: ");
        int numCircle = num;
        double circleWidth = PATTERN_SIZE/num;
        for (int i = 0; i<num; i++){  // keeps count of rows
            for (int j = 0; j<numCircle; j++){  // keeps count of circles made and draws outer circle
                for (int ringsDrawn = 0; ringsDrawn < circleWidth/2; ringsDrawn++){
                    UI.drawOval((PATTERN_LEFT + j*circleWidth) + (ringsDrawn*2), 
                                (PATTERN_TOP + i*circleWidth) + (ringsDrawn*2), 
                                circleWidth - (2*ringsDrawn)*2, 
                                circleWidth - (2*ringsDrawn)*2);
                }
            }
        }
    }
    
    //* unfinished lol don't worry about it */
    public void drawChallengeHexagons(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows?: ");
        double unitWidth = PATTERN_SIZE/num;
        double section = unitWidth/2;
        for (int rows = 0; rows<num; rows++){
            for (int units = 0; units<num; units++){
                // draws the left line
                UI.drawLine(PATTERN_LEFT + units*unitWidth, 
                            (PATTERN_TOP+1/3*section) + rows*unitWidth, 
                            PATTERN_LEFT + units*unitWidth, 
                            (PATTERN_TOP + section) + rows*unitWidth);
                
                // draws the right side
                UI.drawLine(PATTERN_LEFT + section*units + units*unitWidth, 
                            (PATTERN_TOP+ units*section) + rows*unitWidth, 
                            PATTERN_LEFT + units*unitWidth, 
                            (PATTERN_TOP + section) + rows*unitWidth);
            }
        }
    }


    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear",UI::clearPanes);
        UI.addButton("Core: star", this::drawStar);
        UI.addButton("Core: draughts", this::drawDraughtsBoard);
        UI.addButton("Completion: TriGrid", this::drawTriGrid);
        UI.addButton("Challenge: Circles", this::drawChallengeCircles);
        //UI.addButton("Challenge: Concentric", this::drawConcentricBoard);
        //UI.addButton("Challenge: Hexagon", this::drawHexagonalBoard);
        //UI.addButton("Challenge Spiral", this::drawSpiralBoard);
        UI.addButton("Quit",UI::quit);
    }   

    public static void main(String[] arguments){
        PatternsDrawer pd = new PatternsDrawer();
        pd.setupGUI();
    }

}

