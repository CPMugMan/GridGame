package au.edu.curtin.gridgame.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import au.edu.curtin.gridgame.Model.Area;
import au.edu.curtin.gridgame.Model.Equipment;
import au.edu.curtin.gridgame.Model.Food;
import au.edu.curtin.gridgame.Model.GridGameCursor;
import au.edu.curtin.gridgame.Model.GridGameDbHelper;
import au.edu.curtin.gridgame.Model.GridGameSchema.PlayerTable;
import au.edu.curtin.gridgame.Model.GridGameSchema.AreaTable;
import au.edu.curtin.gridgame.Model.Item;
import au.edu.curtin.gridgame.Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameData
{
    public static final int X = 5;
    public static final int Y = 10;
    private static GameData instance = null;

    private Area[][] grid;
    private List<Item> itemList; //List of winning items
    private Player player;
    private Random random;
    private List<Equipment> winningItemList; //List of 3 winning items
    private SQLiteDatabase db;

    public GameData(Context context)
    {
        this.db  = new GridGameDbHelper(context.getApplicationContext()
        ).getWritableDatabase();
        this.grid = new Area[X][Y];
        this.itemList = new ArrayList<Item>();
        this.winningItemList = new ArrayList<Equipment>();
        this.random = new Random();

    }

    //Called when you win/lose or user presses restart: Resets player to default values and randomises the map again
    public void resetGame()
    {
        db.delete(AreaTable.NAME, null, null);
        db.delete(PlayerTable.NAME,null,null);
        this.grid = new Area[X][Y];
        this.itemList = new ArrayList<Item>();
        this.winningItemList = new ArrayList<Equipment>();
        this.random = new Random();
        player = new Player();
        addPlayer();
        updatePlayer();
        createItems();
        randomTheMap();
        randomWinItems();

    }

    //Load Player
    public void load()
    {
        GridGameCursor cursor = new GridGameCursor(
                db.query(PlayerTable.NAME, // FROM our table
                        null, // SELECT all columns
                        null, // WHERE clause (null = all rows)
                        null,
                        null,
                        null,
                        null)
        );
        try
        {
            if(cursor.getCount() > 0)  //Checks if there is an existing person in the database
            {
                cursor.moveToFirst();
                player = cursor.getPlayer();
                String[] parts = player.getStringList().split(":"); //Does a String split on the players StringList to get the names of the all the items the player should have
                if(!parts[0].equals(""))
                {
                    for(int i = 0; i< parts.length;i++)
                    {
                        player.addEquipment2((Equipment)getItem(parts[i]));
                    }

                }
            }
            else
            {
                player = new Player();
                addPlayer();
            }


        }
        finally
        {
            cursor.close();
        }



    }

    //Load Areas
    public void load2()
    {
        GridGameCursor cursor = new GridGameCursor(
                db.query(AreaTable.NAME, // FROM our table
                        null, // SELECT all columns
                        null, // WHERE clause (null = all rows)
                        null,
                        null,
                        null,
                        null)
        );
        try
        {
            if(cursor.getCount() > 0) //Checks to see if there are existing areas in the database
            {
                cursor.moveToFirst();
                for(int i =0; i<=X-1;i++)
                {
                    for(int j =0;j<=Y-1;j++)
                    {
                        grid[i][j] = cursor.getArea();
                        String[] parts = grid[i][j].getStringlist().split(":"); //Does a string split on areas string list to get the names of all items that area should have
                        if(!parts[0].equals(""))
                        {
                            for(int k = 0; k< parts.length;k++)
                            {
                                grid[i][j].addItem2(getItem2(parts[k]));
                            }

                        }
                        cursor.moveToNext();
                    }

                }

            }
            else
            {
                randomTheMap();
                randomWinItems();
            }
        }
        finally
        {
            cursor.close();
        }



    }

    public void addArea(Area inArea)
    {
        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.ID,inArea.getId());
        cv.put(AreaTable.Cols.TOWN,inArea.getTown());
        cv.put(AreaTable.Cols.XCo,inArea.getX());
        cv.put(AreaTable.Cols.YCo,inArea.getY());
        cv.put(AreaTable.Cols.DESC,inArea.getDescription());
        cv.put(AreaTable.Cols.STAR,inArea.getStarred());
        cv.put(AreaTable.Cols.EXPLORED,inArea.getExplored());
        cv.put(AreaTable.Cols.LIST2,inArea.getStringlist());
        db.insert(AreaTable.NAME,null,cv);

    }

    public void updateArea(Area inArea)
    {
        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.ID,inArea.getId());
        cv.put(AreaTable.Cols.TOWN,inArea.getTown());
        cv.put(AreaTable.Cols.XCo,inArea.getX());
        cv.put(AreaTable.Cols.YCo,inArea.getY());
        cv.put(AreaTable.Cols.DESC,inArea.getDescription());
        cv.put(AreaTable.Cols.STAR,inArea.getStarred());
        cv.put(AreaTable.Cols.EXPLORED,inArea.getExplored());
        cv.put(AreaTable.Cols.LIST2,inArea.getStringlist());
        String[] whereValue = {};
        db.update(AreaTable.NAME, cv, AreaTable.Cols.ID + " = " + inArea.getId(), whereValue);

    }

    public void removeArea(Area inArea)
    {
        String[] whereValue = {};
        db.delete(AreaTable.NAME,  AreaTable.Cols.ID + " = " + inArea.getId(), whereValue);

    }

    public void addPlayer()
    {
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.ID, player.getID());
        cv.put(PlayerTable.Cols.ROW,player.getRowLocation());
        cv.put(PlayerTable.Cols.COL,player.getColLocation());
        cv.put(PlayerTable.Cols.CASH,player.getCash());
        cv.put(PlayerTable.Cols.HEALTH,player.getHealth());
        cv.put(PlayerTable.Cols.MASS,player.getEquipmentMass());
        cv.put(PlayerTable.Cols.LIST,player.getStringList());
        db.insert(PlayerTable.NAME,null,cv);

    }

    public void updatePlayer()
    {
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.ID, getPlayer().getID());
        cv.put(PlayerTable.Cols.ROW,getPlayer().getRowLocation());
        cv.put(PlayerTable.Cols.COL,getPlayer().getColLocation());
        cv.put(PlayerTable.Cols.CASH,getPlayer().getCash());
        cv.put(PlayerTable.Cols.HEALTH,getPlayer().getHealth());
        cv.put(PlayerTable.Cols.MASS,getPlayer().getEquipmentMass());
        cv.put(PlayerTable.Cols.LIST,getPlayer().getStringList());
        String[] whereValue = {};
        db.update(PlayerTable.NAME, cv, PlayerTable.Cols.ID + " = " + player.getID(), whereValue);

    }

    public void removePlayer()
    {
        String[] whereValue = { String.valueOf(player.getID()) };
        db.delete(PlayerTable.NAME,
                PlayerTable.Cols.ID + " = " + player.getID(), whereValue);

    }

    public static GameData get(Context context)
    {

        if(instance == null)
        {
            instance = new GameData(context);
        }
        return instance;
    }

    public boolean positionCheck(int xCheck, int yCheck) //Checks to see if player will be out of bounds after they move
    {
        int xCheck2 = player.getColLocation() + xCheck;
        int yCheck2 = player.getRowLocation() + yCheck;

        return xCheck2 <= X-1 && yCheck2 <= Y-1 && xCheck2 >= 0 && yCheck2 >= 0;
    }

    public void setArea(int x, int y, Area inArea)
    {
        grid[x][y] = inArea;
    }


    public Area getArea(int x, int y)
    {
        return grid[x][y];
    }

    public Player getPlayer()
    {
        return player;
    }

    public void movePlayer(int x, int y)
    {
        player.setColLocation(player.getColLocation() + x);
        player.setRowLocation(player.getRowLocation() + y);
        player.moveHealth();
        getCurrArea().setExplored(true);

    }

    public int getColPosition()
    {
        return getPlayer().getColLocation();
    }

    public int getRowPosition()
    {
        return getPlayer().getRowLocation();
    }

    public void movePlayerHealth()
    {
        player.moveHealth();
    }

    public void createItems()
    {
        Equipment equipment1 = new Equipment("(づ￣ ³￣)づ",10,10.0,false);
        Equipment equipment2 = new Equipment("iPhone",15,4.0,false);
        Equipment equipment3 = new Equipment("Dell XPS 15",40,2.0,false);
        Equipment equipment4 = new Equipment("Macbook Pro",40,1.0,false);
        Equipment equipment5 = new Equipment("Chromebook",30,5.0,false);
        Equipment equipment6 = new Equipment("Portable Smell-O-Scope",40,5.0,false);
        Equipment equipment7 = new Equipment("Improbability Drive",30,-3.14,false);
        Equipment equipment8 = new Equipment("Ben Kenobi",30,0.0,false);

        Food food1 = new Food("Big Mac",7,30.0);
        Food food2 = new Food("Coffee",4,50.0);
        Food food3 = new Food("Chicken Nuggets",20,40);
        Food food4 = new Food("Ramen",25,33);
        Food food5 = new Food("Mints",3,2.0);

        Equipment equipmentWin1 = new Equipment("Jade Monkey",30,30.0,true);
        Equipment equipmentWin2 = new Equipment("Roadmap",45,2.0,true);
        Equipment equipmentWin3 = new Equipment("Ice Scraper",20,23.0,true);

        itemList.add(equipment1);
        itemList.add(equipment2);
        itemList.add(equipment3);
        itemList.add(equipment4);
        itemList.add(equipment5);
        itemList.add(equipment6);
        itemList.add(equipment7);
        itemList.add(equipment8);
        itemList.add(food1);
        itemList.add(food2);
        itemList.add(food3);
        itemList.add(food4);
        itemList.add(food5);

        winningItemList.add(equipmentWin1);
        winningItemList.add(equipmentWin2);
        winningItemList.add(equipmentWin3);

    }

    public void randomTheMap() //Called when there are no areas in database, randomises the map
    {

        for(int i =0; i<=X-1;i++)
        {
            for(int j =0;j<=Y-1;j++)
            {
                grid[i][j] = new Area(random.nextBoolean(),i,j);
                for(int k = 0; k <=random.nextInt(16); k++) //Only allows a max of 15 items per area
                {
                    grid[i][j].addItem(itemList.get(random.nextInt(13)));
                }
                addArea(grid[i][j]);

            }
        }
    }

    public void randomWinItems() //randomises where the winning items will go
    {
        for(int i = 0; i < winningItemList.size();i++)
        {
            int x = random.nextInt(X-1);
            int y = random.nextInt(Y-1);
            grid[x][y].addItem(winningItemList.get(i));
            updateArea(grid[x][y]);
        }

    }

    public void boughtWinningItem(Equipment inEquipment) //called when player has bought a winning item
    {
        winningItemList.remove(inEquipment);
        player.setWinCount(player.getWinCount()+ 1);
        updatePlayer();

    }

    public Area getCurrArea()
    {
        return getArea(getColPosition(),getRowPosition());
    }


    public void improbDrive()
    {
        db.delete(AreaTable.NAME, null, null);
        randomTheMap();
        randomWinItems();
    }


    public Item getItem(String name) //Returns the items with the same name as the String passed in
    {
        Item returnItem = null;
        for(int i =0; i<itemList.size();i++)
        {
            if(itemList.get(i).getDescription().equals(name))
            {
                returnItem = itemList.get(i);
            }
        }
        return returnItem;
    }

    public Item getItem2(String name) //Same as getItem but checks winning items as well
    {
        Item returnItem = null;
        for(int i =0; i<itemList.size();i++)
        {
            if(itemList.get(i).getDescription().equals(name))
            {
                returnItem = itemList.get(i);
            }
        }
        for(int j=0; j<winningItemList.size();j++)
        {
            if(winningItemList.get(j).getDescription().equals(name))
            {
                returnItem = winningItemList.get(j);
            }
        }
        return returnItem;
    }

    public void benKen()
    {
        List<Equipment> addList = new ArrayList<Equipment>();

        for(int j = 0; j < getCurrArea().getList().size();j++)
        {
            if(getCurrArea().getList().get(j) instanceof Equipment)
            {
                getPlayer().setEquipmentMass(getPlayer().getEquipmentMass() + ((Equipment) getCurrArea().getList().get(j)).getMassorHealth());
                addList.add((Equipment)getCurrArea().getList().get(j));
            }
            if(getCurrArea().getList().get(j) instanceof Food)
            {
                getPlayer().setHealth(getPlayer().getHealth() + ((Food) getCurrArea().getList().get(j)).getMassorHealth());
            }

        }
        for(int i =0; i< addList.size();i++)
        {
            getPlayer().addEquipment(addList.get(i));

        }
        getCurrArea().getList().clear();
        getCurrArea().setStringlist("");

    }



}
