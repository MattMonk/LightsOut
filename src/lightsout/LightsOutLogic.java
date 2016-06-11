
package lightsout;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class LightsOutLogic
{
    int gridSize = 9;
    
    JToggleButton toggleButton;
    JToggleButton[][] buttonArray;
    JButton resetButton;
      
    public int[] findButtonPos(JToggleButton currentButton)
    {
        int[] buttonPos = new int[2];
        String[] stringPos = (currentButton.getActionCommand()).split("¬");
        
        buttonPos[0] = Integer.parseInt(stringPos[0]);
        buttonPos[1] = Integer.parseInt(stringPos[1]);
        
        return buttonPos;
    }
    
    public void changeLights(ActionEvent e, JToggleButton[][] buttons)
    {    
        Object source = e.getSource();
        toggleButton = (JToggleButton)source;
        buttonArray = buttons;
        
        int xPos = findButtonPos(toggleButton)[0];
        int yPos = findButtonPos(toggleButton)[1];
        
        try
        {
            buttonArray[xPos-1][yPos].setSelected(!buttonArray[xPos-1][yPos].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonArray[xPos+1][yPos].setSelected(!buttonArray[xPos+1][yPos].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonArray[xPos][yPos-1].setSelected(!buttonArray[xPos][yPos-1].isSelected());
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            buttonArray[xPos][yPos+1].setSelected(!buttonArray[xPos][yPos+1].isSelected());
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public boolean checkWin()
    {
        boolean allSelected = true;
        for(int i=0;i<gridSize;i++)
        {
            for(int j=0;j<gridSize;j++)
            {
                if(buttonArray[i][j].isSelected() == false)
                {
                    allSelected = false;
                }
            }
        }
        return allSelected;
    }
    
    public void resetButtons(JToggleButton[][] buttons)
    {
        buttonArray = buttons;
        for(int i=0;i<gridSize;i++)
            {
                for(int j=0;j<gridSize;j++)
                {
                    buttonArray[i][j].setSelected(false);
                }
            }
    }
   
}
