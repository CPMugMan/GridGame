package au.edu.curtin.gridgame;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData
{
    private static GameData instance = null;

    private Area[][] grid;
    private List<Item> itemList;
    private int x;
    private int y;
    private Player player;
    private Random random;

    public GameData()
    {
        this.x = 50;
        this.y = 50;
        this.grid = new Area[x][y];
        this.itemList = new ArrayList<Item>();
        this.random = new Random();
        this.player = new Player();
        createItems();
        randomTheMap();

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

        return xCheck2 <= x-1 && yCheck2 <= y-1 && xCheck2 >= 0 && yCheck2 >= 0;
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
        Equipment equipment1 = new Equipment("(づ￣ ³￣)づ",10,10.0);
        Equipment equipment2 = new Equipment("Ordinary Shield",15,4.0);
        Equipment equipment3 = new Equipment("Dell XPS 15",40,2.0);
        Equipment equipment4 = new Equipment("Macbook Pro",70,1.0);
        Equipment equipment5 = new Equipment("Chromebook",30,5.0);

        Food food1 = new Food("Big Mac aka sean",7,30.0);
        Food food2 = new Food("Coffee",4,50.0);
        Food food3 = new Food("Chicken Nuggets",20,40);
        Food food4 = new Food("Ramen",25,33);
        Food food5 = new Food("Mints",3,2.0);

        Equipment equipmentWin1 = new Equipment("Jade Monkey",30,30.0);
        Equipment equipmentWin2 = new Equipment("Roadmap",45,2.0);
        Equipment equipmentWin3 = new Equipment("Ice Scraper",20,23.0);

        itemList.add(equipment1);
        itemList.add(equipment2);
        itemList.add(equipment3);
        itemList.add(equipment4);
        itemList.add(equipment5);
        itemList.add(food1);
        itemList.add(food2);
        itemList.add(food3);
        itemList.add(food4);
        itemList.add(food5);

    }

    public void randomTheMap()
    {

        for(int i =0; i<=49;i++)
        {
            for(int j =0;j<=49;j++)
            {
                grid[i][j] = new Area(random.nextBoolean());
                for(int k = 0; k <=random.nextInt(16); k++) //Only allows a max of 15 items per area
                {
                    grid[i][j].addItem(itemList.get(random.nextInt(10)));
                }

            }
        }
    }

    public Area getCurrArea()
    {
        return getArea(getColPosition(),getRowPosition());
    }





}
