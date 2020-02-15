package GUI_STUFF;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import player.*;
import Composition_Screen.*;

public class UndoRedo extends JPanel
{
   JButton undo;
   JButton redo;
   JButton delete;
   Channels channels;
   Note n;
   CS cs;
   //constructor
   public UndoRedo(Channels channels, Note n, CS cs)
   {
      this.cs = cs;
      this.channels = channels;
      this.n = n;
   }
   public UndoRedo(Channels channels, CS cs)
   {
      this.cs = cs;
      this.channels = channels;
      this.n = n;
      
      setLayout(new FlowLayout(3,10,50));
      
      undo = new JButton( "Undo");
      undo.addActionListener(new ButtonListener() );
      
      redo = new JButton( "Redo");
      redo.setEnabled(false);
      redo.addActionListener(new ButtonListener() );
      
      delete = new JButton ("Delete");
      delete.addActionListener(new ButtonListener() );
      
      add(undo);
      add(redo);
      add(delete);
      
   }
   class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == undo)
         {
            System.out.println("UNDO");
            
            
            System.out.println(channels.getPlayer().getNotesArray() );
            channels.undo();
            cs.setChannel(channels);
            cs.repaintAll();
            System.out.println("DurationChanged :" + channels.getPlayer().getChangedDurationLast());
            System.out.println(cs.getNoteSize() );
            System.out.println(channels.getPlayer().getNotesArray() );
         }
         else if (e.getSource() == redo)
         {
            System.out.println("REDO");
            
            //System.out.println(channels.getPlayer().getNotesArray() );
            channels.redo();
            //System.out.println(channels.getPlayer().getNotesArray() );
            
         }
         else if (e.getSource() == delete)
         {
            System.out.println("DELETE");
            if( cs.getSelected() == null)
               System.out.println("You should select a note to delete");
            else
            {
               for(int i = 0; i < channels.getPlayer().getNotesArray().size(); i++)
               {
                  if(channels.getPlayer().getNotesArray().get(i).equals(cs.getSelected() ) )
                  {
                     channels.getPlayer().removeNote(channels.getPlayer().getNotesArray().get(i));
                     cs.setChannel(channels);
                  }
               }
            }
            
//            if(n != null)
//            {
//               System.out.println(channels.getPlayer().getNotesArray() );
//               channels.removeNote(n);
//               System.out.println(channels.getPlayer().getNotesArray() );
//            }
//            else
//               System.out.println("You should select a note! ");
         
         }
      }
      public void addNoteToLeftMenu(Note note)
      {
         n = note;
      }
   }
}