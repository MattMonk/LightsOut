package lightsout;

import javax.swing.JToggleButton;
import javax.swing.SwingWorker;

public class SolverWorker extends SwingWorker<Integer, Integer>
{
    LightsOutSolver ls;
    int[][] solutionArray;
    JToggleButton[][] buttonsArray;
    
    public SolverWorker(int[][] inputArray, JToggleButton[][] inputButtonsArray)
    {
        solutionArray = inputArray;
        buttonsArray = inputButtonsArray;
        ls  = new LightsOutSolver();
    }
    
    @Override
    protected Integer doInBackground() throws Exception
    {
        ls.solvePuzzle(solutionArray, buttonsArray);
        return 0;
    }
    
    
    protected void done()
    {
        
    }
}
