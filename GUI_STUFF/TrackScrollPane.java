package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ButtonUI;

import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class TrackScrollPane extends JScrollPane
{
   final int TITLE_NUMBER = 6;
   final Dimension PANEL_DIMENSION = new Dimension(800, 200);
   
   Channels[] channels;
   GridBagConstraints constraints;
   JComboBox<String> instrumentBox;
   String[] instrumentNames;
   CS cs;
   Channels channel;
   JButton channelAdderButton;
   boolean removeCheck;
   TrackPanelLeft tPanelLeft;
   TrackPanelLeft tPanelRight;
   JScrollPane scrollPane;
   JPanel combined;
   JScrollBar verticalScroll;
   
   
   
   //CONSTRUCTOR
   public TrackScrollPane(Channels[] channels, CS cs)     
   {
      this.channels = channels;
      this.cs = cs;
      
      setPreferredSize( PANEL_DIMENSION);
      setLayout(new BorderLayout());
      
      tPanelLeft = new TrackPanelLeft(channels, cs);
      //trackRight = new TrackPanelRight();
      
      combined = new JPanel();
      combined.setLayout(new BorderLayout());
      combined.add(tPanelLeft, BorderLayout.WEST);
      //combined.add(tPanelRight);
      
      scrollPane = new JScrollPane(combined);
      add(scrollPane);
   }
}

