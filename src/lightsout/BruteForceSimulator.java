package lightsout;

import java.util.Random;

public class BruteForceSimulator
{   
    int gridSize = 5;
    int maxMoves = 30;
    int count = 0;
    
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
    
    public int[][] fixArray(int count, int[][] solutionArray)
    {
        for(int i=count;i<solutionArray.length;i++)
        {
            solutionArray[i][0] = 999;
        }
        return solutionArray;
    }
    
    public void runAll()
    {
        Random random = new Random();
        boolean[][] actualGrid = getArray();
        
        boolean isOver = false;
        while(isOver == false)
        {
            initialiseArray();
            solutionArray = new int[maxMoves][2];
            isOver = false;
            count = 0;
            while(isOver == false && count < maxMoves)
            {
                boolean alreadyPressed = false;
                int xPos = random.nextInt(gridSize);
                int yPos = random.nextInt(gridSize);
                for(int j=0;j<maxMoves;j++)
                {
                    if((solutionArray[j][0] == xPos) && (solutionArray[j][1] == yPos))
                    {
                        alreadyPressed = true;
                    }
                }
                if(alreadyPressed == false)
                {
                    changeArea(xPos, yPos);
                    solutionArray[count][0] = xPos;
                    solutionArray[count][1] = yPos;
                }
               
                count++;
                isOver = checkWin();
            }
           
        }
        if(isOver == true)
        {
            /*System.out.println("Solution found after "+ String.valueOf(i)+" moves");
            printActualGrid();*/
            solutionArray = fixArray(count, solutionArray);
            printSolution(solutionArray);
        }
    }
}
