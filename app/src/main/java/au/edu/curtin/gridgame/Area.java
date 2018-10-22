package au.edu.curtin.gridgame;

import java.util.ArrayList;
import java.util.List;

public class Area
{
    private final int grass;
    private final int wilderness;
    private final int townP;
    private final int person;
    private final int star;

    private boolean town;
    private List<Item> items;
    private String description;
    private boolean starred;
    private boolean explored;
    private int x;
    private int y;

    /*public Area()
    {
        town = false;
        items = new ArrayList<Item>();
        description = "Empty";
        starred = false;
        explored = false;

    }*/

    public Area(boolean inTown, int inGrass, int inWilderness, int inTownP, int inPerson, int inStar, int inX, int inY)
    {
        this.town = inTown;
        this.grass = inGrass;
        this.wilderness = inWilderness;
        this.townP = inTownP;
        this.person = inPerson;
        this.star = inStar;

        items = new ArrayList<Item>();
        description = "Insert Description Here";
        starred = false;
        explored = false;
        x = inX;
        y = inY;

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
    }

    public void removeItem(int inIndex)
    {
        items.remove(inIndex);
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


}
