package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ButtonUI;

import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class TrackPanelRight extends JPanel
{
   final int TITLE_NUMBER = 6;
   Channels[] channels;
   GridBagConstraints constraints;
   JComboBox<String> instrumentBox;
   String[] instrumentNames;
   CS cs;
   Channels channel;
   JButton channelAdderButton;
   boolean removeCheck;
   TrackPanel trackPanel;
   JScrollBar verticalScroll;
   GridPanel[] gridPanelArray;
   
   
   //Constructor
   public TrackPanelRight(Channels[] channels, CS cs)
   {
      this.channels = channels;
      this.cs = cs;
      this.trackPanel = trackPanel;
      setLayout(new GridBagLayout());
      removeCheck = false;
      constraints = new GridBagConstraints();
      gridPanelArray = new GridPanel[16];
           
      //add titles
      constraints.fill = GridBagConstraints.HORIZONTAL;      
      
      constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.HORIZONTAL;  
      constraints.gridwidth = 26;
      constraints.gridy = 1;
      
      JPanel blockPanel = new JPanel();
      blockPanel.setPreferredSize(new Dimension(10,25));
      add( blockPanel, constraints);
      for (int j = 2; j < channels.length + 2; j++)
      {
         constraints.gridy = j;
         gridPanelArray[j - 2] = new GridPanel(channels[j - 2], cs);
         add(gridPanelArray[j -2], constraints);
         
      }
      
      if (channels.length != 16)
      {
         //add channelAdderButton
         constraints.gridy = channels.length + 1;
         add(channelAdderButton, constraints);
      }
      
      repaint();
   }
   
   //Methods
   public void paintComponent( Graphics g)
   {
      if (channelAdderButton != null)
         remove(channelAdderButton);
      
      super.paintComponent(g);
      
   }
   public GridPanel[] getGridPanelArray()
   {
      return gridPanelArray;
   }
   
   
   
   public class ChannelRemoveListener implements ActionListener
   {
      @Override
      public void actionPerformed ( ActionEvent e) 
      {
        // e.getSource().getParent().getChannel().deleteNotes();
         repaint();
      }
   }
}

