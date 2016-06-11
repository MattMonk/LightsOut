
package lightsout;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class LightsOutLogic
{
    
    JToggleButton toggleButton;
    JToggleButton[][] buttonArray;
    JButton resetButton;
    
    public LightsOutLogic(ActionEvent e, JToggleButton[][] buttons)
    {
        Object source = e.getSource();
        toggleButton = (JToggleButton)source;
        buttonArray = buttons;
    }
    
    public int[] findButtonPos()
    {
        int[] buttonPos = new int[2];
        String[] stringPos = (toggleButton.getActionCommand()).split("¬");
        
        buttonPos[0] = Integer.parseInt(stringPos[0]);
        buttonPos[1] = Integer.parseInt(stringPos[1]);
        
        return buttonPos;
    }
    
    public void changeLights()
    {
        /*String test = toggleButton.getText();
        System.out.println(test);*/
        int xPos = findButtonPos()[0];
        int yPos = findButtonPos()[1];
        
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
    
}
