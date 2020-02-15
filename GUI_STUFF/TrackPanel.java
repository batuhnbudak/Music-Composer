package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ButtonUI;

import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class TrackPanel extends JPanel
{
   final int TITLE_NUMBER = 6;
   final Dimension PANEL_DIMENSION = new Dimension(2000, 200);
   MiniPlayer player;
   Channels[] channels;
   GridBagConstraints constraints;
   JComboBox<String> instrumentBox;
   String[] instrumentNames;
   CS cs;
   Channels channel;
   JButton channelAdderButton;
   boolean removeCheck;
   TrackPanelLeft tPanelLeft;
   TrackPanelRight tPanelRight;
   JScrollPane scrollPane;
   JPanel combined;
   JScrollBar verticalScroll;
   
   
   
   //CONSTRUCTOR
   public TrackPanel(Channels[] channels, CS cs)     
   {
      this.player = player;
      this.channels = channels;
      this.cs = cs;
      
      setPreferredSize( PANEL_DIMENSION);
      setLayout(new BorderLayout());
      
      tPanelRight= new TrackPanelRight(channels, cs);
      
      tPanelLeft = new TrackPanelLeft(channels, cs, tPanelRight);
      
      
      combined = new JPanel();
      combined.setLayout(new BorderLayout());
      combined.add(tPanelLeft, BorderLayout.WEST);      
      combined.add(tPanelRight);
      
      scrollPane = new JScrollPane(combined);
      add(scrollPane);
   }
}

