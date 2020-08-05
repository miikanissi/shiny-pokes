
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Last modification: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/

/*
This class is the MainWindow with counter and Hunt object.
*/
public class MainWindow extends JFrame
{    
    private Container contentPane=this.getContentPane();
    private JPanel panel = new JPanel();
    private JPanel pane = new JPanel();
    private int count = 0;
    private Hunt currentHunt = null;
    private final JLabel encounterLabel;
    private final JLabel encounterCountLabel;
    private final JLabel gameLabel;
    private final JLabel gameCurrentLabel;
    private final JLabel chanceLabel;
    private final JLabel chanceCurrentLabel;
    private final JLabel areaLabel;
    private final JLabel areaCurrentLabel;
    private final JLabel methodLabel;
    private final JLabel methodCurrentLabel;
    private final JLabel nameLabel;
    private final JLabel imageLabel;

    JMenu itemSave;
    JMenu itemOpen;
    public MainWindow()
    {
        Pokemon defaultPokemon = new Pokemon();
        defaultPokemon._name = "Pikachu";
        defaultPokemon._id = 25;
        String defaultSpriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/25.png";
        Hunt defaultHunt = new Hunt(0, defaultPokemon, defaultSpriteUrl, "Red/Blue", 8192, "Route 1", "Random Encounter");
        currentHunt = defaultHunt;

        // Adding all items and action listeners to Menu. 
        JMenuBar mb = new JMenuBar();
        JMenu itemFile = new JMenu("File");
        JMenu itemEdit = new JMenu("Edit");
        JMenu itemHelp = new JMenu("Help");
        
        JMenuItem itemAbout = new JMenuItem("About");
        JMenuItem itemNew = new JMenuItem("New");
        JMenuItem itemSettings = new JMenuItem("Settings");
        JMenuItem itemExit = new JMenuItem("Exit");       
        JMenuItem itemUndo = new JMenuItem("Undo");
        
        itemSave = new JMenu("Save");
        itemOpen = new JMenu("Open");
        
        updateOpenMenu();
        updateSaveMenu();
        
        mb.add(itemFile);
        mb.add(itemEdit);
        mb.add(itemHelp);
        
        itemFile.add(itemNew);
        itemFile.add(itemOpen);
        itemFile.add(itemSave);
        itemFile.add(itemSettings);
        itemFile.add(itemExit);
        
        itemEdit.add(itemUndo);
        itemHelp.add(itemAbout);
        
        itemUndo.addActionListener(new UndoCounter());
        itemNew.addActionListener(new OpenHuntDialog());
        itemExit.addActionListener(new QuitProgram());
       
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        nameLabel = new JLabel("Pikachu", SwingConstants.CENTER);

        panel.add(nameLabel, borderLayout.PAGE_START);

        imageLabel = new JLabel(new ImageIcon());
        imageLabel.setPreferredSize(new Dimension(200, 100));
        panel.add(imageLabel, borderLayout.CENTER);
        
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        pane.setLayout(gridBag);
        pane.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        // FIRST ROW
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;

        gameLabel = new JLabel("Game:", SwingConstants.CENTER);
        gridBag.setConstraints(gameLabel, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        encounterLabel = new JLabel("Encounters: ", SwingConstants.CENTER);
        gridBag.setConstraints(encounterLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        chanceLabel = new JLabel("Chance: ", SwingConstants.CENTER);
        gridBag.setConstraints(chanceLabel, c);

        // SECOND ROW
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;

        gameCurrentLabel = new JLabel("Game", SwingConstants.CENTER);
        gridBag.setConstraints(gameCurrentLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        
        encounterCountLabel = new JLabel("0", SwingConstants.CENTER);
        gridBag.setConstraints(encounterCountLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.5;
        
        chanceCurrentLabel = new JLabel("1/8192", SwingConstants.CENTER);
        gridBag.setConstraints(chanceCurrentLabel, c);

        
        // THIRD ROW
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        areaLabel = new JLabel("Area: ", SwingConstants.CENTER);
        gridBag.setConstraints(areaLabel, c);

        c.insets = new Insets(0,10,0,10);
        c.ipady = 40;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.gridheight = 2;
        JButton button = new JButton("Counter");
        gridBag.setConstraints(button, c);

        c.insets = new Insets(0,0,0,0);
        c.ipady = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.5;
        c.gridheight = 1;
        methodLabel = new JLabel("Method: ", SwingConstants.CENTER);
        gridBag.setConstraints(methodLabel, c);

        
        // 4TH ROW
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        areaCurrentLabel = new JLabel("Route 1", SwingConstants.CENTER);
        gridBag.setConstraints(areaCurrentLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 0.5;
        methodCurrentLabel = new JLabel("Wild", SwingConstants.CENTER);
        gridBag.setConstraints(methodCurrentLabel, c);

        

        pane.add(gameLabel);
        pane.add(encounterLabel);
        pane.add(chanceLabel);
        
        pane.add(gameCurrentLabel);
        pane.add(encounterCountLabel);
        pane.add(chanceCurrentLabel);
        
        pane.add(areaLabel);
        pane.add(button);
        pane.add(methodLabel);

        pane.add(areaCurrentLabel);
        pane.add(methodCurrentLabel);
        button.addActionListener(new Counter());

        // Adding components to frame. 
        contentPane.add(BorderLayout.NORTH, mb);
        contentPane.add(pane, BorderLayout.SOUTH);
        contentPane.add(panel, BorderLayout.CENTER);
        updateUi(currentHunt);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Actionlistener to open new hunt dialog. 
    class OpenHuntDialog implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            NewHuntDialog frame = new NewHuntDialog();
            Hunt h = frame.show();
            if (h != null)
            {
                currentHunt = h;
                updateUi(currentHunt);
            }
        }
    }

    private Image getImage(String spriteUrl)
    {
        Image image = null;
        try
        {
            URL imgUrl = new URL(spriteUrl);
            Image tempImage = ImageIO.read(imgUrl);
            image = tempImage.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        }
        catch (IOException ioe)
        {
        }
        return image;
    }
    // Method to update the UI. 
    private void updateUi(Hunt selection)
    {
        if (selection != null)
        {
            nameLabel.setText(selection.pokemon.getName());
            imageLabel.setIcon(new ImageIcon(getImage(selection.spriteUrl)));
            gameCurrentLabel.setText(selection.game);
            methodCurrentLabel.setText(selection.method);
            areaCurrentLabel.setText(selection.area);
            chanceCurrentLabel.setText("1/"+selection.chance);
            encounterCountLabel.setText(String.valueOf(selection.counter));
            count = selection.counter;
        }
    }
    // Method to update open menu items and actionlisteners. 
    private void updateOpenMenu()
    {
        itemOpen.removeAll();
        for(int i = 0 ; i < Main.listHunts.length ; i++)
        {
            Hunt h = (Hunt) Main.listHunts[i];
            try 
            {
                JMenuItem item = new JMenuItem(h.pokemon.getName() +i);
                item.addActionListener(new OpenHunt(i));
                itemOpen.add(item);
            }
            catch (NullPointerException np) 
            {
                JMenuItem item = new JMenuItem("Empty " +i);
                item.addActionListener(new OpenHunt(i));
                itemOpen.add(item);
            }
        }
    }
    // Method to update save menu items and actionlisteners.
    private void updateSaveMenu()
    {
        itemSave.removeAll();
        for(int i = 0 ; i < Main.listHunts.length ; i++)
        {
            Hunt h = (Hunt) Main.listHunts[i];
            try 
            {
                JMenuItem item = new JMenuItem(h.pokemon.getName() +i);
                item.addActionListener(new SaveHunt(i));
                itemSave.add(item);
            }
            catch (NullPointerException np) 
            {
                JMenuItem item = new JMenuItem("Empty " +i);
                item.addActionListener(new SaveHunt(i));
                itemSave.add(item);
            }
        }
    }
    // Actionlistener to exit program. 
    class QuitProgram implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    // Actionlistener to increase counter. 
    class Counter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            count++;
            encounterCountLabel.setText(""+ count);
            currentHunt.counter = count;
        }
    }
    // Actionlistener to reduce counter. 
    class UndoCounter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (count > 0) 
            {
                count--;
                encounterCountLabel.setText(""+ count);
                currentHunt.counter = count;
            }
        }
    }
    // Actionlistener to save hunt object on selected spot
    class SaveHunt implements ActionListener 
    {
        private final int spot;
        private SaveHunt(int i) 
        {
            spot = i;
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Main.SaveHunt(currentHunt, spot);
            updateOpenMenu();
            updateSaveMenu();
        }
    }
    // Actionlistener to open a hunt object from selected spot. 
    class OpenHunt implements ActionListener 
    {
        private final int spot;
        private OpenHunt(int i) 
        {
            spot = i;
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            currentHunt = Main.listHunts[spot];
            updateUi(currentHunt);
        }
    }
}

