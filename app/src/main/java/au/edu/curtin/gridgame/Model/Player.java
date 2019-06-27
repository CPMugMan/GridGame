package au.edu.curtin.gridgame.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import au.edu.curtin.gridgame.Model.Equipment;

import static android.content.ContentValues.TAG;

public class Player
{
    private int rowLocation;
    private int colLocation;
    private int cash;
    private int winCount;
    private double health;
    private double equipmentMass;
    private List<Equipment> equipmentList;
    private String stringList;

    private static final int id = 200;

    public Player()
    {
        winCount = 0;
        rowLocation = 0;
        colLocation = 0;
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
        equipmentList = new ArrayList<Equipment>();
        stringList = ""; //String of the names of all items player currently has separated by :
    }

    public Player(int inRow, int inCol, int inCash, double inHealth, double inMass,String inList, int inWinCount)
    {
        rowLocation = inRow;
        colLocation = inCol;
        cash = inCash;
        health = inHealth;
        equipmentMass = inMass;
        stringList = inList;
        equipmentList = new ArrayList<Equipment>();
        inWinCount = winCount;



    }

    public void resetPlayer()
    {
        winCount = 0;
        rowLocation = 0;
        colLocation = 0;
        cash = 100;
        health = 100.0;
        equipmentMass = 0.0;
        equipmentList = new ArrayList<Equipment>();
        stringList = "";

    }

    public void setWinCount(int inWin)
    {
        winCount = inWin;
    }

    public int getWinCount()
    {
        return winCount;
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

    public String getStringList()
    {
        return stringList;
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
        stringList = stringList + inEquipment.getDescription() + ":";
        Log.d(TAG, "removeItem4 " + stringList);

    }

    public void addEquipment2(Equipment inEquipment)
    {
        equipmentList.add(inEquipment);
    }


    public void removeEquipment(Equipment inEquipment)
    {
        String removed = inEquipment.getDescription() + ":";
        stringList = stringList.replaceFirst(removed,"");
        equipmentList.remove(inEquipment);
        Log.d(TAG, "removeItem3: " + stringList);
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

    public int getID()
    {
        return id;
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
