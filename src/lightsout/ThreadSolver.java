package lightsout;

import javax.swing.JToggleButton;

public class ThreadSolver implements Runnable
{
    LightsOutSolver ls = new LightsOutSolver();
    LightsOutGUI lg = new LightsOutGUI();
    int[][] solutionArray;
    JToggleButton[][] buttonsArray;
    private volatile boolean running = true;
    
    public ThreadSolver(int[][] inputArray, JToggleButton[][] inputButtonsArray)
    {
        solutionArray = inputArray;
        buttonsArray = inputButtonsArray;
    }
    
    public void terminate()
    {
        running = false;
    }
    
    @Override
    public void run()
    {
        while(running)
        {
            try
            {
                Thread tempThread = Thread.currentThread();
                tempThread.setName("Solver Thread");

                ls.solvePuzzle(solutionArray, buttonsArray);
            }
            catch(Exception exc)
            {
                running = false;
            }
        }
        
        /*while(!Thread.currentThread().isInterrupted())
        {  
            ls.solvePuzzle(solutionArray, buttonsArray);
        }*/
    }
}
