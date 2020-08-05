import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Last modification: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/

/*
This class creates the dialog when creating a new hunt and on
OK creates a new Hunt object with given variables.
*/
public final class NewHuntDialog
{
    private final List<JComponent> components;
    JTextField areaTextField;
    JSpinner chanceSpinner;

    private String title;
    private int messageType;
    private JRootPane rootPane;
    private String[] options;
    private int optionIndex;
    private int counter = 0;
    private Pokemon pokemon;
    private String spriteUrl;
    private String game;
    private int defaultChance = 8192;
    private int chance;
    private String area;
    private String method;
    
    private String[] gameStrings = 
    {
        "Red/Blue", "Yellow", "Gold/Silver", "Crystal",
        "Ruby/Sapphire", "Emerald", "Fire Red/Leaf Green",
        "Diamond/Pearl", "Platinum", "HeartGold/SoulSilver",
        "Black/White", "Black2/White2", "X/Y",
        "Omega Ruby/Alpha Sapphire", "Sun/Moon",
        "Ultra Sun/Ultra Moon", "Let's Go Pikachu/Eevee",
        "Colosseum", "XD: Gale of Darkness"
    };
    private String[] methodStrings = 
    {
        "Soft Reset", "Random Encounter", "Egg Hatching",
        "Chaining"
    };
    
    public NewHuntDialog()
    {
        components = new ArrayList<>();

        setTitle("Custom dialog");
        setMessageType(JOptionPane.PLAIN_MESSAGE);
        setRootPane(null);
        
        // Creates a new combo box with list of pokemon objects
        JComboBox pokemonList = new JComboBox();
        for (Pokemon p : Main.listPokemon) 
        {
            pokemonList.addItem(p);
        }
        // Gets the selected item from combo box as pokemon
        Pokemon poke = (Pokemon) pokemonList.getSelectedItem();
        pokemon = poke;
        spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + pokemon.getId() +".png";
        pokemonList.addItemListener(new ItemListener() 
        {
           @Override
           public void itemStateChanged(ItemEvent e) 
           {
               Pokemon poke = (Pokemon) pokemonList.getSelectedItem();
               pokemon = poke;
               spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + pokemon.getId() +".png";
           }
        });
        // sets the renderer for Pokemon objects to give out the pokemons name
        pokemonList.setRenderer(new ObjectListCellRenderer());
        
        // Creates a new combo box with a list of games
        JComboBox gameList = new JComboBox();
        for (String s : gameStrings) 
        {
            gameList.addItem(s);
        }
        game = (String) gameList.getSelectedItem();
        gameList.addItemListener(new ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
               game = (String) gameList.getSelectedItem(); 
            }
        });
        
        // Creates a new combo box with a list of methods
        JComboBox methodList = new JComboBox();
        for (String s : methodStrings) 
        {
            methodList.addItem(s);
        }
        method = (String) methodList.getSelectedItem();
        methodList.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                method = (String) methodList.getSelectedItem();
            }
        });
        
        // Creates a new Text field for the area
        String areaText = "Area of the hunt: ";
        areaTextField = new JTextField(20);
        areaTextField.setDocument(new TextFieldLimit(20));
        
        // Creates a new Spinner field for the chance
        String chanceText = "Odds of the hunt 1 /";
        SpinnerModel chanceSpinnerModel  = new SpinnerNumberModel(defaultChance, 1, defaultChance, 1);
        chanceSpinner = new JSpinner(chanceSpinnerModel);
        
        addComponent(pokemonList);
        addComponent(gameList);
        addComponent(methodList);
        addMessageText(areaText);
        addComponent(areaTextField);
        addMessageText(chanceText);
        addComponent(chanceSpinner);
        
        
        setOptions(new String[] { "OK", "Cancel" });
        setOptionSelection(0);
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setMessageType(int messageType)
    {
        this.messageType = messageType;
    }

    public void addComponent(JComponent component)
    {
        components.add(component);
    }

    public void addMessageText(String messageText)
    {
        JLabel label = new JLabel("<html>" + messageText + "</html>");

        components.add(label);
    }

    public void setRootPane(JRootPane rootPane)
    {
        this.rootPane = rootPane;
    }

    public void setOptions(String[] options)
    {
        this.options = options;
    }

    public void setOptionSelection(int optionIndex)
    {
        this.optionIndex = optionIndex;
    }

    public Hunt show()
    {
        int optionType = JOptionPane.OK_CANCEL_OPTION;
        Object optionSelection = null;

        if(options.length != 0)
        {
            optionSelection = options[optionIndex];
        }

        int selection = JOptionPane.showOptionDialog(rootPane,
                components.toArray(), title, optionType, messageType, null,
                options, optionSelection);
        
        if (selection != 1) 
        {
            area = areaTextField.getText();
            chance = (int) chanceSpinner.getValue();
            Hunt hunt = new Hunt(counter, pokemon, spriteUrl, game, chance, area, method);
            return hunt;
        } else 
        {
            return null;
        }
    }

    public static String getLineBreak()
    {
        return "<br>";
    }
    
    // Class to limit the textfield input
    class TextFieldLimit extends PlainDocument 
    {
        private int limit;
        TextFieldLimit(int limit)
        {
            super();
            this.limit = limit;
        }
        
        TextFieldLimit(int limit, boolean up) 
        {
            super();
            this.limit = limit;
        }
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException 
        {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) 
            {
                super.insertString(offset, str, attr);
            }
        }
    }
}