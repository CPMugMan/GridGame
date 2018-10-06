package au.edu.curtin.gridgame;

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
}
