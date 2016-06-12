package lightsout;

import java.util.Random;

public class BruteForceSimulator
{   
    int maxMoves = 30;
    int gridSize = 5;
    
    boolean[][] actualGrid = new boolean[gridSize][gridSize];
    int[][] solutionArray = new int[maxMoves][2];
    
    public boolean[][] getArray()
    {
        return actualGrid;
    }
    
    public void changeArea(int xPos, int yPos)
    {
        try
        {
            actualGrid[xPos][yPos] = !actualGrid[xPos][yPos];
        }
        catch(Exception exc)
        {
            
        }
        try
        {
            actualGrid[xPos-1][yPos] = !actualGrid[xPos-1][yPos];
        }
        catch(Exception exc)
        {
            
        }
        try
        {
            actualGrid[xPos+1][yPos] = !actualGrid[xPos+1][yPos];
        }
        catch(Exception exc)
        {
            
        }
        try
        {
            actualGrid[xPos][yPos-1] = !actualGrid[xPos][yPos-1];
        }
        catch(Exception exc)
        {
            
        }
        try
        {
            actualGrid[xPos][yPos+1] = !actualGrid[xPos][yPos+1];
        }
        catch(Exception exc)
        {
            
        }
    }
    
    public boolean checkWin()
    {
        boolean isAllTrue = true;
        for(int i=0;i<actualGrid.length;i++)
        {
            for(int j=0;j<actualGrid.length;j++)
            {
                if(actualGrid[i][j] == false)
                {
                    isAllTrue = false;
                }
            }
        }
        return isAllTrue;
    }
    
    public void printActualGrid()
    {
        for(int i=0;i<actualGrid.length;i++)
        {
            for(int j=0;j<actualGrid.length;j++)
            {
                System.out.print((actualGrid[i][j])+" ");
            }
            System.out.println();
        }
    }
    
    public void printSolution(int[][] answerArray)
    {
        for(int i=0;i<answerArray.length;i++)
        {
            for(int j=0;j<2;j++)
            {
                System.out.print((answerArray[i][j])+" ");
            }
            System.out.println();
        }
    }
    
    public void initialiseArray()
    {
        for(int i=0;i<actualGrid.length;i++)
        {
            for(int j=0;j<actualGrid.length;j++)
            {
                actualGrid[i][j] = false;
            }
        }
    }
    
    public void runAll()
    {
        Random random = new Random();
        boolean[][] actualGrid = getArray();
        
        boolean isOver = false;
        int i = 0;
        while(isOver == false)
        {
            initialiseArray();
            solutionArray = new int[maxMoves][2];
            isOver = false;
            i = 0;
            while(isOver == false && i < maxMoves)
            {
                int xPos = random.nextInt(gridSize);
                int yPos = random.nextInt(gridSize);
                changeArea(xPos, yPos);
                solutionArray[i][0] = xPos;
                solutionArray[i][1] = yPos;      
                i++;
                isOver = checkWin();
            }
        }
        if(isOver == true)
        {
            /*System.out.println("Solution found after "+ String.valueOf(i)+" moves");
            printActualGrid();
            printSolution(solutionArray);*/
        }
    }
}
