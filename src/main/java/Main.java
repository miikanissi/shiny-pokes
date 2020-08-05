
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Last modification: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/
public class Main 
{
    // listPokemon is initiliaized and filled with Pokemon objects
    // Taken from pokeAPI. 
    public static List<Pokemon> listPokemon = new ArrayList<Pokemon>();
    // listHunts Array is initialized with 10. 
    public static Hunt[] listHunts = new Hunt[10];
    
    public static void main(String[] args) throws Exception 
    {
        openHunts();
        MainWindow frame = new MainWindow();
        frame.setSize(600,400);
        frame.setTitle("Shiny Counter");
        frame.show();
        
        String json = readUrl("https://pokeapi.co/api/v2/pokemon?limit=807");
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("results");
        for (int i = 0; i < arr.length(); i++) 
        {
            Pokemon pokemon = new Pokemon();
            pokemon._id = i + 1;
            pokemon._name = capitalize(arr.getJSONObject(i).getString("name"));
            listPokemon.add(pokemon);
        }
    }
    // Method to capitalize a string
    private static String capitalize(String str)
    {
        if(str == null || str.length() == 0)
            return "";
        
        if(str.length() == 1)
            return str.toUpperCase();
        
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    // Method to save a serialized file of hunt object
    // Output location is currently tmp when debugging
    // In the future user can select a save location from settings. 
    public static void SaveHunt(Hunt hunt, int spot) 
    {
        listHunts[spot] = hunt;
        try 
        {
            FileOutputStream fileOut = new FileOutputStream("SaveHunts.sav");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Hunt h : listHunts)
            {
                out.writeObject(h);
            }
            out.close();
            fileOut.close();    
        }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }   
    }
    //Method to load all hunts from temp.
    public static void openHunts()
    {
        try 
        {
            FileInputStream fileIn = new FileInputStream("SaveHunts.sav");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            for(int i = 0 ; i < listHunts.length ; i++)
            {
                Hunt h;
                h = (Hunt) in.readObject();
                listHunts[i] = h;
            }
            in.close();
            fileIn.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Method to read an url and make it a string
    private static String readUrl(String urlString) throws Exception 
    {
        BufferedReader reader = null;
        try 
        {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) 
            {
                buffer.append(chars, 0, read); 
            }
            return buffer.toString();
        } 
        finally 
        {
            if (reader != null)
            {
                reader.close();
            }
        }   
    }
}

