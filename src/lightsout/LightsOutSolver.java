package lightsout;

import javax.swing.JToggleButton;

public class LightsOutSolver extends Thread
{
    int[][] hardAnswerArray = new int[][]{{0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1},
                                        {0, 2}, {999}, {999}, {999}, {999}, {999}, {999}, {999}, {8, 2},
                                        {0, 3}, {1, 3}, {999}, {999}, {999}, {999}, {999}, {7, 3}, {8, 3},
                                        {999}, {1, 4}, {999}, {3, 4}, {4, 4}, {5, 4}, {999}, {7, 4}, {999},
                                        {0, 5}, {1, 5}, {2, 5}, {3, 5}, {999}, {5, 5}, {6, 5}, {7, 5}, {8, 5},
                                        {0, 6}, {1, 6}, {999}, {999}, {999}, {999}, {999}, {7, 6}, {8, 6},
                                        {999}, {999}, {2, 7}, {999}, {4, 7}, {999}, {6, 7}, {999}, {999},
                                        {999}, {1, 8}, {999}, {3, 8}, {999}, {5, 8}, {999}, {7, 8}, {999}}; //999 represents a blank space
    
    long solveStart = 0;
    long solveEnd = 0;
   
    public void solvePuzzle(int[][] solutionArray, JToggleButton[][] buttonsArray)
    {
        
        solveStart = System.currentTimeMillis();
        for(int i=0;i<solutionArray.length;i++)
        {
            if(solutionArray[i][0] != 999)
            {  
                changeLightSolver(buttonsArray[solutionArray[i][0]][solutionArray[i][1]], buttonsArray);
                try
                {
                    Thread.sleep(500);
                }
                catch(Exception exc)
                {

                }
            }
            
        }
        solveEnd = System.currentTimeMillis();
        LightsOutGUI lg = new LightsOutGUI();
        lg.endGame(39, solveStart, solveEnd);
    }
    
    public void changeLightSolver(JToggleButton currentButton, JToggleButton[][] buttonsArray)
    {
        LightsOutLogic ll = new LightsOutLogic();
        int xPos = ll.findButtonPos(currentButton)[0];
        int yPos = ll.findButtonPos(currentButton)[1];
        
        buttonsArray[xPos][yPos].setSelected(!buttonsArray[xPos][yPos].isSelected());
        try
        {
            buttonsArray[xPos-1][yPos].setSelected(!buttonsArray[xPos-1][yPos].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonsArray[xPos+1][yPos].setSelected(!buttonsArray[xPos+1][yPos].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonsArray[xPos][yPos-1].setSelected(!buttonsArray[xPos][yPos-1].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonsArray[xPos][yPos+1].setSelected(!buttonsArray[xPos][yPos+1].isSelected());
        }
        catch(Exception ex)
        {
            
        }
    }
}
