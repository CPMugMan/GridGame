package au.edu.curtin.gridgame;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private int rowLocation;
    private int colLocation;
    private int cash;
    private double health;
    private double equipmentMass;
    private boolean town;
    private List<Equipment> equipmentList;

    public Player()
    {
        rowLocation = 0;
        colLocation = 0;
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
        equipmentList = new ArrayList<Equipment>();
    }

    public void moveHealth()
    {
        health = Math.max(0.0, health - 5.0 - (equipmentMass / 2.0));
    }

    public void setRowLocation(int inRowLocation)
    {
        rowLocation = inRowLocation;
    }

    public void setColLocation(int inColLocation)
    {
        colLocation = inColLocation;
    }


    public void setCash(int inCash)
    {
        cash = inCash;
    }

    public void setList(List<Equipment> inEquipmentList)
    {
        equipmentList = inEquipmentList;
    }

    public void setHealth(double inHealth)
    {
        if(inHealth > 100)
        {
            health = 100;
        }
        else
        {
            health = inHealth;
        }

    }

    public void setEquipmentMass(double inSetEquipmentMass)
    {
        equipmentMass = inSetEquipmentMass;
    }

    public void addEquipment(Equipment inEquipment)
    {
        equipmentList.add(inEquipment);
    }

    public int getRowLocation()
    {
        return rowLocation;
    }

    public int getColLocation()
    {
        return colLocation;
    }

    public int getCash()
    {
        return cash;
    }

    public double getHealth()
    {
        return health;
    }

    public double getEquipmentMass()
    {
        return equipmentMass;
    }

    public List getList()
    {
        return equipmentList;
    }
}
