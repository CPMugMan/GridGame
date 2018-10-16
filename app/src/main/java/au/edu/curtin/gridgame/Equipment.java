package au.edu.curtin.gridgame;

public class Equipment extends Item
{
    private double mass;

    @Override
    public double getMassorHealth()
    {
        return mass;
    }

    @Override
    public String getType()
    {
        return "Mass";
    }

    public Equipment(String inDescription, int inValue, double inMass)
    {
        super.setDescription(inDescription);
        super.setValue(inValue);
        mass = inMass;
    }

}
