package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class ChannelsPanel extends JPanel
{
   static int count = 0;
   
   final Dimension BUTTON_DIMENSION =  new Dimension(100, 25);
   final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
   final int MIN_VOLUME = 0;
   final int MAX_VOLUME = 100;
   int volume = 100;
   
   Channels channel;
   GridBagConstraints constraints;
   JComboBox<String> instrumentBox;
   
   
   JButton removeButton;
   JLabel emptySpace;
   JTextField textField; //
   JRadioButton radSolo; //
   JRadioButton radMute; //
   JSlider volumeSlider; //
   String channelName;
   CS cs;
   GridPanel gridPanel;
   
   boolean removeCheck;
   int index;
   
   public ChannelsPanel(Channels channel, String[] instrumentNames, CS cs, GridPanel gridPanel)
   {
      count++;
      channelName = "Track " + count;
      this.channel = channel;
      this.cs = cs;
      this.gridPanel = gridPanel;
      constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.gridy = 0;
      
      //add removeButton
      removeButton = new JButton("-");
      removeButton.addActionListener(new RemoveButtonListener() );
      // removeButton.setUI(sda);
      System.out.println(removeButton.getUI());
      //System.out.println(removeButton.getUIClassID());
      removeButton.setPreferredSize(BUTTON_DIMENSION);
      constraints.gridx = 0;
      add(removeButton, constraints);
      
      //add instrumentName
      textField = new JTextField("Track " + count);
      textField.addActionListener(new TextFieldListener() );
      textField.setPreferredSize(new Dimension(100, 26));
      constraints.gridx = 1;
      add(textField, constraints);
      
      //Empty spacing 
      constraints.gridx = 2;
      emptySpace = new JLabel("          ");
      add(emptySpace, constraints);
      
      //add dropdown menu
      instrumentBox = new JComboBox<String>(instrumentNames);
      instrumentBox.addActionListener( new ComboBoxListener() );
      constraints.gridx = 3;
      add(instrumentBox, constraints);
      
      //add Solo
      radSolo = new JRadioButton();
      radSolo.addActionListener(new RadioListener() );
      constraints.gridx = 4;
      add(radSolo, constraints);
      
      //add Mute
      radMute = new JRadioButton();
      radMute.addActionListener(new RadioListener() );
      
      constraints.gridx = 5;
      add(radMute, constraints);
      
      //add volumeSlider
      volumeSlider = new JSlider(JSlider.HORIZONTAL,
                                 MIN_VOLUME,
                                 MAX_VOLUME,
                                 volume
                                );
      volumeSlider.setMinorTickSpacing(20);
      volumeSlider.setPaintTicks(true);
      volumeSlider.addChangeListener(new SliderListener() );
      //volumeSlider.setPaintLabels(true);
      constraints.gridx = 6;
      add(volumeSlider, constraints);
      addMouseListener( new PanelListener() );
      if (count == 1)
         cs.setName(channelName);
      
   }
   
   public class RemoveButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         channel.clearAllNotes();
         cs.setChannel(cs.getChannel() );
         
         cs.repaintAll();
      }
   }
   
   public class TextFieldListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         channelName = textField.getText();
         channel.setNameForPlayer(channelName );
         cs.setName( channelName );
      }
   }
   
   public class PanelListener implements MouseListener
   {
      public void mouseClicked(MouseEvent e)
      {
         cs.setChannel(channel );
         channelName = textField.getText();
         channel.setNameForPlayer(channelName );
         cs.setName(channelName );
         //gridPanel.updateGrid();
         //setBackground(Color.yellow);
         //System.out.println(cs.getNotesArray() );
         cs.repaintAll();
      }
      public void mouseReleased(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mousePressed(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
   }
   
   public class RadioListener implements ActionListener
   {
      @Override
      public void actionPerformed ( ActionEvent e)
      {
         if(e.getSource() == radSolo)
         {
            if(((JRadioButton)e.getSource()).isSelected())
               channel.solo();
            else
               channel.unSolo();
            
         }
         else if(e.getSource() == radMute )
         {
            if(((JRadioButton)e.getSource()).isSelected())
               channel.mute();
            else
               channel.unmute();
         }
      }
   }
   
   public class SliderListener implements ChangeListener
   {
      @Override
      public void stateChanged (ChangeEvent e)
      {
         if(e.getSource() == volumeSlider)
         {
            volume = ((JSlider)e.getSource()).getValue();
            channel.setVelocity(volume);
         }
      }
   }
   
   public class ComboBoxListener implements ActionListener
   {
      @Override
      public void actionPerformed ( ActionEvent e)
      {
         if(e.getSource() == instrumentBox)
         {
            channel.setInstrument( ((JComboBox)e.getSource()).getSelectedIndex() );
         }
      }
   }
   
   
}