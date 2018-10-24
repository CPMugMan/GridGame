package au.edu.curtin.gridgame;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Area
{
    private int grass;
    private int wilderness;
    private int townP;
    private int person;
    private int star;

    private boolean town;
    private List<Item> items;
    private String description;
    private boolean starred;
    private boolean explored;
    private int x;
    private int y;
    private String stringlist;
    private static int nextId = 0;
    private final int id;

    /*public Area()
    {
        town = false;
        items = new ArrayList<Item>();
        description = "Empty";
        starred = false;
        explored = false;

    }*/

    public Area(boolean inTown,int inX, int inY)
    {
        this.id = nextId;
        nextId++;
        this.town = inTown;
        this.grass = R.drawable.ic_grass3;
        this.wilderness = R.drawable.ic_tree3;
        this.townP = R.drawable.ic_building1;
        this.person = R.drawable.ic_person;
        this.star = R.drawable.ic_star;

        items = new ArrayList<Item>();
        description = "Insert Description Here";
        starred = false;
        explored = false;
        x = inX;
        y = inY;
        stringlist = "";

    }

    public Area(int id, boolean inTown, int inX, int inY, String inDescription, boolean inStarred, boolean inExpored, String inStringlist)
    {
        this.town = inTown;
        this.grass = R.drawable.ic_grass3;
        this.wilderness = R.drawable.ic_tree3;
        this.townP = R.drawable.ic_building1;
        this.person = R.drawable.ic_person;
        this.star = R.drawable.ic_star;
        this.id = id;
        nextId = id + 1;
        items = new ArrayList<Item>();
        town = inTown;
        x = inX;
        y = inY;
        description = inDescription;
        starred = inStarred;
        explored = inExpored;
        stringlist = inStringlist;

    }


    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return  y;
    }

    public int getGrass()
    {
        return grass;
    }

    public int getWilderness()
    {
        return wilderness;
    }

    public int getTownP()
    {
        return townP;
    }

    public int getPerson()
    {
        return person;
    }

    public int getStar()
    {
        return star;
    }

    public void setTown(boolean inTown)
    {
        this.town = inTown;
    }

    public void addItem(Item inItem)
    {
        items.add(inItem);
        stringlist = stringlist + inItem.getDescription() + ":";
    }

    public void addItem2(Item inItem)
    {
        items.add(inItem);
    }

    public void removeItem(int inIndex)
    {

        items.remove(inIndex);
    }

    public void removeItem2(Item inItem)
    {

        String removed = inItem.getDescription() + ":";
        stringlist = stringlist.replaceFirst(removed,"");
        items.remove(inItem);
        Log.d(TAG, "removeItem2: " + stringlist);
    }

    public boolean getTown()
    {
        return town;
    }

    public Item getItem(int inIndex)
    {
        return items.get(inIndex);
    }

    public List<Item> getList()
    {
        return items;
    }

    public void setList(List<Item> inList)
    {
        items = inList;
    }

    public void setDescription(String inDescription)
    {
        description = inDescription;
    }

    public String getDescription()
    {
        return description;
    }

    public void setStarred(boolean inBoolean)
    {
        starred = inBoolean;
    }

    public boolean getStarred()
    {
        return starred;
    }

    public void setExplored(boolean inBoolean)
    {
        explored = inBoolean;
    }

    public boolean getExplored()
    {
        return explored;
    }

    public int getId()
    {
        return id;
    }

    public String getStringlist()
    {
        return stringlist;
    }

    public void setStringlist(String inString)
    {
        stringlist = inString;
    }


}
