package lightsout;

import javax.swing.JToggleButton;

public class LightsOutSolver extends Thread
{
    int[][] hardAnswer5 = new int[][]{  {999}, {1, 0}, {2, 0}, {999}, {4, 0},
                                        {999}, {1, 1}, {2, 1}, {3, 1}, {999},
                                        {999}, {999}, {2, 2}, {3, 2}, {4, 2},
                                        {0, 3}, {1, 3}, {999}, {3, 3}, {4, 3},
                                        {0, 4}, {1, 4}, {999}, {999}, {999}};
    
    int[][] hardAnswer7 = new int[][]{  {0, 0}, {1, 0}, {999}, {3, 0}, {999}, {5, 0}, {6, 0},
                                        {0, 1}, {1, 1}, {2, 1}, {999}, {4, 1}, {5, 1}, {6, 1},
                                        {999}, {1, 2}, {2, 2}, {999}, {4, 2}, {5, 2}, {999},
                                        {0, 3}, {999}, {999}, {3, 3}, {999}, {999}, {6, 3},
                                        {999}, {1, 4}, {2, 4}, {999}, {4, 4}, {5, 4}, {999},
                                        {0, 5}, {1, 5}, {2, 5}, {999}, {4, 5}, {5, 5}, {6, 5},
                                        {0, 6}, {1, 6}, {999}, {3, 6}, {999}, {5, 6}, {6, 6}};
    
    int[][] hardAnswer9 = new int[][]{  {999}, {999}, {999}, {999}, {999}, {999}, {999}, {999}, {999},
                                        {0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1},
                                        {0, 2}, {999}, {999}, {999}, {999}, {999}, {999}, {999}, {8, 2},
                                        {0, 3}, {1, 3}, {999}, {999}, {999}, {999}, {999}, {7, 3}, {8, 3},
                                        {999}, {1, 4}, {999}, {3, 4}, {4, 4}, {5, 4}, {999}, {7, 4}, {999},
                                        {0, 5}, {1, 5}, {2, 5}, {3, 5}, {999}, {5, 5}, {6, 5}, {7, 5}, {8, 5},
                                        {0, 6}, {1, 6}, {999}, {999}, {999}, {999}, {999}, {7, 6}, {8, 6},
                                        {999}, {999}, {2, 7}, {999}, {4, 7}, {999}, {6, 7}, {999}, {999},
                                        {999}, {1, 8}, {999}, {3, 8}, {999}, {5, 8}, {999}, {7, 8}, {999}}; //999 represents a blank space
    
    long solveStart = 0;
    long solveEnd = 0;
    int moveCounter = 0;
   
    public void solvePuzzle(int[][] solutionArray, JToggleButton[][] buttonsArray)
    {
        LightsOutLogic ll = new LightsOutLogic();
        ll.resetButtons(buttonsArray);
        solveStart = System.currentTimeMillis();
        int count = 0;
        boolean isRunning = true;
        while(count < solutionArray.length && isRunning == true)
        {
            if(solutionArray[count][0] != 999)
            {  
                changeLightSolver(buttonsArray[solutionArray[count][0]][solutionArray[count][1]], buttonsArray);
                try
                {
                    Thread.sleep(500);
                }
                catch(Exception exc)
                {

                }
            }
            moveCounter++;
            count ++;
            Thread[] a = new Thread[1000];
            int n = Thread.enumerate(a);
            isRunning = false;
            for(int j=0; j<n;j++)
            {
                if(a[j].getName().equals("Solver Thread"))
                {
                    isRunning = true;
                }
            }
        }
        solveEnd = System.currentTimeMillis();
        LightsOutGUI lg = new LightsOutGUI();
        lg.endGame(moveCounter, solveStart, solveEnd);
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
