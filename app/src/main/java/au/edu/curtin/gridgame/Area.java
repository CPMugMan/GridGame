package au.edu.curtin.gridgame;

import java.util.ArrayList;
import java.util.List;

public class Area
{
    private boolean town;
    private List<Item> items;
    private String description;
    private boolean starred;
    private boolean explored;

    public Area()
    {
        town = false;
        items = new ArrayList<Item>();
        description = "Empty";
        starred = false;
        explored = false;

    }

    public Area(boolean inTown)
    {
        this.town = inTown;
        items = new ArrayList<Item>();
        description = "Empty";
        starred = false;
        explored = false;
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
