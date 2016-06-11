package lightsout;

import javax.swing.*;
import java.awt.event.*;

public class LightsOutGUI extends JFrame implements ActionListener
{
    
    JTabbedPane tp = new JTabbedPane();
    JPanel pnlInfo = new JPanel(null); //Uses a null layout
    JToggleButton buttons[][] = new JToggleButton[7][7];
    JButton resetButton = new JButton("Reset");
    
    public void runGUI()
	{
                this.setTitle("Lights Out!");
                this.setSize(800, 800);
                this.setLocation(10, 10);
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		createInfoPanel();
		
		tp.addTab("Lights Out!", pnlInfo);
		
		this.add(tp);
		this.setVisible(true);
	}
	
	public void createInfoPanel()
	{
            int r = 100;
            int c = 0;
            for(int i=0;i<7;i++)
            {
                c=100;
                for(int j=0;j<7;j++)
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
            resetButton.setLocation(500, 150);
            resetButton.addActionListener(this);
            pnlInfo.add(resetButton);
        }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == resetButton)
        {
            for(int i=0;i<7;i++)
            {
                for(int j=0;j<7;j++)
                {
                    buttons[i][j].setSelected(false);
                }
            }
        }
        else
        {
            LightsOutLogic ll = new LightsOutLogic(e, buttons);
            ll.changeLights();
        }
        
    }
    
}
