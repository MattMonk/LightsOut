package lightsout;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

public class LightsOutGUI extends JFrame implements ActionListener
{
    int gridSize = 5;
    
    JTabbedPane tp = new JTabbedPane();
    JPanel pnlInfo = new JPanel(null); //Uses a null layout
    JToggleButton buttons[][];
    JButton resetButton = new JButton("Reset");
    JButton solveButton = new JButton("Solve");
    JButton bfsButton = new JButton("<html>Solve with <br>brute force</html>");
    
    int moveCounter = 0;
    boolean firstPress = true;
    long startTimer = 0;
    long endTimer = 0;
    
    LightsOutSolver ls = new LightsOutSolver();
    int[][] hardAnswerArray;
    
    
    WindowListener exitListener = new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
          Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
          Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
          threadArray[threadArray.length-1].interrupt();
          System.exit(0);
      }
    };
    
    
    public void runGUI()
    {
                this.setTitle("Lights Out!");
                this.setSize(800, 800);
                this.setLocation(10, 10);
                this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.addWindowListener(exitListener);
                
                gridSize = gridInput();
                buttons = new JToggleButton[gridSize][gridSize];
                if(gridSize == 5)
                {
                    hardAnswerArray = ls.hardAnswer5;
                }
                else if(gridSize == 7)
                {
                    hardAnswerArray = ls.hardAnswer7;
                }
                if(gridSize == 9)
                {
                    hardAnswerArray = ls.hardAnswer9;
                }
                
		createInfoPanel();
		
		tp.addTab("Lights Out!", pnlInfo);
		
		this.add(tp);
		this.setVisible(true);
    }
    
    public void createInfoPanel()
    {
        int r = 100;
        int c = 0;
        for(int i=0;i<gridSize;i++)
        {
            c=100;
            for(int j=0;j<gridSize;j++)
            {
                String buttonLabel = String.valueOf(j)+"¬"+String.valueOf(i);
                buttons[j][i] = new JToggleButton();
                buttons[j][i].setActionCommand(buttonLabel);
                buttons[j][i].setSize(50, 50);
                buttons[j][i].setLocation(c, r);
                buttons[j][i].setEnabled(true);
                buttons[j][i].addActionListener(this);
                pnlInfo.add(buttons[j][i]);

                c=c+51;
            }
            r=r+51;
        }
        resetButton.setSize(100, 20);
        resetButton.setLocation(600, 150);
        resetButton.addActionListener(this);
        pnlInfo.add(resetButton);
        
        solveButton.setSize(100, 20);
        solveButton.setLocation(600, 180);
        solveButton.addActionListener(this);
        pnlInfo.add(solveButton);
        
        if(gridSize == 5)
        {
            bfsButton.setSize(100, 50);
            bfsButton.setLocation(600, 210);
            bfsButton.addActionListener(this);
            pnlInfo.add(bfsButton);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        int[][] arrayToThread = new int[][]{};
        
        if(e.getSource() == resetButton)
        {
            LightsOutLogic ll = new LightsOutLogic();
            ll.resetButtons(buttons);
            moveCounter = 0;
            firstPress = true;
            startTimer = 0;
            endTimer = 0;
        }
        else if(e.getSource() == solveButton)
        {
            arrayToThread = hardAnswerArray;   
            ThreadSolver obj = new ThreadSolver(arrayToThread, buttons);
            Thread tobj = new Thread(obj);
            tobj.start();
        }
        else if(e.getSource() == bfsButton)
        {
            BruteForceSimulator bfs = new BruteForceSimulator();
            bfs.runAll();
            arrayToThread = bfs.solutionArray;
            ThreadSolver obj = new ThreadSolver(arrayToThread, buttons);
            Thread tobj = new Thread(obj);
            tobj.start();
        }
        else
        {
            if(firstPress == true)
            {
                startTimer = System.currentTimeMillis();
                firstPress = false;
            }
        
            moveCounter++;
            
            LightsOutLogic ll = new LightsOutLogic();
            ll.changeLights(e, buttons);
            if(ll.checkWin() == true)
            {
                endGame(moveCounter, startTimer, endTimer);
            }
        }     
    }
    
    public void endGame(int moveCounter, long startTimer, long endTimer)
    {
        endTimer = System.currentTimeMillis();
        long timeTaken = (endTimer - startTimer)/1000;
        String winMessage = "Congratulations! You have won the game! It took you "+(String.valueOf(timeTaken))+" seconds and "+(String.valueOf(moveCounter))+" moves";
        Object[] options = {"Reset", "Close message"};
        int choice = JOptionPane.showOptionDialog(null, winMessage, "Congratulations!", 0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(choice == 0)
        {
            LightsOutLogic ll = new LightsOutLogic();
            try
            {
                ll.resetButtons(buttons);
            }
            catch(Exception exc)
            {
                
            }
            moveCounter = 0;
            firstPress = true;
            startTimer = 0;
            endTimer = 0;
        }
    }
    
    public int gridInput()
    {
        int gridArea = 5;
        int[] choiceToArea = {5, 7, 9};
        String mainText = "<html>Please select the grid area.<br>Note that the brute force solve<br>option is only available in 5x5<br>mode as it would took to long<br>for bigger grids.</html>";
        
        Object[] options = {"5x5", "7x7", "9x9"};
        int choice = JOptionPane.showOptionDialog(null, mainText, "Grid Size", 0, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        gridArea = choiceToArea[choice];
        return gridArea;
    }
}
