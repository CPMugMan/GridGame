package au.edu.curtin.gridgame;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData
{
    public static final int X = 5;
    public static final int Y = 10;
    private static GameData instance = null;

    private Area[][] grid;
    private List<Item> itemList;
    private int winCount;
    private Player player;
    private Random random;
    private List<Equipment> winningItemList;

    public GameData()
    {
        this.winCount = 0;
        this.grid = new Area[X][Y];
        this.itemList = new ArrayList<Item>();
        this.winningItemList = new ArrayList<Equipment>();
        this.random = new Random();
        this.player = new Player();
        createItems();
        randomTheMap();
        randomWinItems();

    }

    public void resetGame()
    {
        this.winCount = 0;
        this.grid = new Area[X][Y];
        this.itemList = new ArrayList<Item>();
        this.winningItemList = new ArrayList<Equipment>();
        this.random = new Random();
        this.player = new Player();
        createItems();
        randomTheMap();
        randomWinItems();

    }

    public static GameData get()
    {
        if(instance == null)
        {
            instance = new GameData();
        }
        return instance;
    }

    public boolean positionCheck(int xCheck, int yCheck)
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
        Equipment equipment4 = new Equipment("Macbook Pro",70,1.0,false);
        Equipment equipment5 = new Equipment("Chromebook",30,5.0,false);
        Equipment equipment6 = new Equipment("Portable Smell-O-Scope",40,5.0,false);
        Equipment equipment7 = new Equipment("Improbability Drive",30,10,false);
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

    public void randomTheMap()
    {

        for(int i =0; i<=X-1;i++)
        {
            for(int j =0;j<=Y-1;j++)
            {
                grid[i][j] = new Area(random.nextBoolean(),R.drawable.ic_grass3,R.drawable.ic_tree3,R.drawable.ic_building1,R.drawable.ic_person,R.drawable.ic_star,i,j);
                for(int k = 0; k <=random.nextInt(16); k++) //Only allows a max of 15 items per area
                {
                    grid[i][j].addItem(itemList.get(random.nextInt(13)));
                }

            }
        }
    }

    public void randomWinItems()
    {
        for(int i = 0; i < winningItemList.size();i++)
        {
            int x = random.nextInt(X-1);
            int y = random.nextInt(Y-1);
            grid[x][y].addItem(winningItemList.get(i));
        }

    }

    public void boughtWinningItem(Equipment inEquipment)
    {
        winningItemList.remove(inEquipment);
        winCount++;

    }

    public Area getCurrArea()
    {
        return getArea(getColPosition(),getRowPosition());
    }


    public void improbDrive()
    {
        randomTheMap();
        randomWinItems();
    }

    public int getWinCount()
    {
        return winCount;
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
            getPlayer().getList().add(addList.get(i));

        }
        getCurrArea().getList().clear();

    }





}
