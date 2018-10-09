package au.edu.curtin.gridgame;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameData
{
    private Area[][] grid;
    private List<Item> itemList;
    private int x;
    private int y;
    private Player player;
    private Random random;

    public GameData()
    {
        x = 50;
        y = 50;
        grid = new Area[x][y];
        itemList = new ArrayList<Item>();
        random = new Random();
        player = new Player();
        createItems();
        randomTheMap();

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
    }

    public void movePlayerHealth()
    {
        player.moveHealth();
    }

    public void createItems()
    {
        Equipment equipment1 = new Equipment("Rune Sword",10,10.0);
        Equipment equipment2 = new Equipment("Ordinary Shield",15,4.0);
        Equipment equipment3 = new Equipment("Dell XPS 15",40,2.0);
        Equipment equipment4 = new Equipment("Macbook Pro",70,1.0);
        Equipment equipment5 = new Equipment("Chromebook",30,5.0);

        Food food1 = new Food("Big Mac",7,30.0);
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
                grid[i][j] = new Area(obtainRandom());
            }
        }
    }

    public boolean obtainRandom()
    {
        return random.nextBoolean();
    }



}
