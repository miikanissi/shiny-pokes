/*************************************
Author: Miika Nissi
Date started: 14.6.2020
Date submitted: 
Last modification: 
Final Project for Java Programming class AVE1017/OJ/3003
*************************************/

/*
This Class creates the pokemon object, with name and id
*/
public class Pokemon implements java.io.Serializable
{
    public int _id;
    public String _name;
    
    private Pokemon(int id, String name)
    {
        this._id = id;
        this._name = name;
    }
    
    public String getName() 
    {
        return _name;
    }
    public int getId() 
    {
        return _id;
    }
    public Pokemon()
    {     
    }
}
