package au.edu.curtin.gridgame.Model;

public class Food extends Item
{
    private double health;

    public Food(String inDescription, int inValue, double inHealth)
    {
        super.setDescription(inDescription);
        super.setValue(inValue);
        health = inHealth;
    }

    @Override
    public double getMassorHealth()
    {
        return health;
    }

    @Override
    public String getType()
    {
        return "Health";
    }

    @Override
    public boolean isThisFood()
    {
        return true;
    }
}
