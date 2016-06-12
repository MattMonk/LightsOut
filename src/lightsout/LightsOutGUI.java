package lightsout;

import javax.swing.*;
import java.awt.event.*;

public class LightsOutGUI extends JFrame implements ActionListener
{
    int gridSize = 5;
    
    JTabbedPane tp = new JTabbedPane();
    JPanel pnlInfo = new JPanel(null); //Uses a null layout
    JToggleButton buttons[][] = new JToggleButton[gridSize][gridSize];
    JButton resetButton = new JButton("Reset");
    JButton solveButton = new JButton("Solve");
    JButton bfsButton = new JButton("<html>Solve with <br>brute force</html>");
    
    int moveCounter = 0;
    boolean firstPress = true;
    long startTimer = 0;
    long endTimer = 0;
    
    LightsOutSolver ls = new LightsOutSolver();
    
    
    WindowListener exitListener = new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
          Runnable r = new ThreadSolver(ls.hardAnswerArray, buttons);
          new Thread(r).interrupt();
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
        
        bfsButton.setSize(100, 50);
        bfsButton.setLocation(600, 210);
        bfsButton.addActionListener(this);
        pnlInfo.add(bfsButton);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == resetButton)
        {
            Runnable r = new ThreadSolver(ls.hardAnswerArray, buttons);
            new Thread(r).interrupt();
            LightsOutLogic ll = new LightsOutLogic();
            ll.resetButtons(buttons);
            moveCounter = 0;
            firstPress = true;
            startTimer = 0;
            endTimer = 0;
        }
        else if(e.getSource() == solveButton)
        {
            Runnable r = new ThreadSolver(ls.hardAnswerArray, buttons);
            new Thread(r).start();
        }
        else if(e.getSource() == bfsButton)
        {
            BruteForceSimulator bfs = new BruteForceSimulator();
            bfs.runAll();
            Runnable r = new ThreadSolver(bfs.solutionArray, buttons);
            new Thread(r).start();
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
        int choice = JOptionPane.showOptionDialog(null, winMessage, "Congratulations!", 0, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(choice == 0)
        {
            LightsOutLogic ll = new LightsOutLogic();
            try
            {
                Runnable r = new ThreadSolver(ls.hardAnswerArray, buttons);
                new Thread(r).interrupt();
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
}
