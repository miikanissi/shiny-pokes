/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/
/*
This class holds the variables for a given hunt.
*/
public class Hunt implements java.io.Serializable 
{
    public int counter;
    public Pokemon pokemon;
    public String spriteUrl;
    public String game;
    public int chance;
    public String area;
    public String method;
    
    public Hunt(int counter, Pokemon pokemon, String spriteUrl, String game, int chance, String area, String method) 
    {
        this.counter = counter;
        this.pokemon = pokemon;
        this.spriteUrl = spriteUrl;
        this.game = game;
        this.chance = chance;
        this.area = area;
        this.method = method;    
    }
}
