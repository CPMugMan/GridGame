package au.edu.curtin.gridgame.Model;

public abstract class Item
{
    private String description;
    private int value;

    public abstract double getMassorHealth();

    public abstract String getType();

    public abstract boolean isThisFood();

    public void setDescription(String inDescription)
    {
        description = inDescription;
    }

    public void setValue(int inValue)
    {
        value = inValue;
    }

    public String getDescription()
    {
        return description;
    }

    public int getValue()
    {
        return value;
    }
}
