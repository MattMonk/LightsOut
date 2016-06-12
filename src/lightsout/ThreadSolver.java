package lightsout;

import javax.swing.JToggleButton;

public class ThreadSolver implements Runnable
{
    LightsOutSolver ls = new LightsOutSolver();
    LightsOutGUI lg = new LightsOutGUI();
    int[][] solutionArray;
    JToggleButton[][] buttonsArray;
    
    public ThreadSolver(int[][] inputArray, JToggleButton[][] inputButtonsArray)
    {
        solutionArray = inputArray;
        buttonsArray = inputButtonsArray;
    }
    
    public void run()
    {
        Thread tempThread = Thread.currentThread();
        tempThread.setName("Solver Thread");
        
        while(!Thread.currentThread().isInterrupted())
        {  
            ls.solvePuzzle(solutionArray, buttonsArray);
        }
    }
}
