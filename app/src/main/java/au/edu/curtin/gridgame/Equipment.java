package au.edu.curtin.gridgame;

public class Equipment extends Item
{
    private double mass;
    private boolean winningItem;

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

    @Override
    public boolean isThisFood()
    {
        return false;
    }


    public Equipment(String inDescription, int inValue, double inMass, boolean inWin)
    {
        super.setDescription(inDescription);
        super.setValue(inValue);
        mass = inMass;
        winningItem = inWin;
    }

    public boolean isWinningItem()
    {
        return winningItem;
    }

}
