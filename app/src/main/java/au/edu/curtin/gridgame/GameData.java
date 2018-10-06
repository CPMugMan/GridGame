package au.edu.curtin.gridgame;

public class GameData
{
    private Area[][] grid;
    private int x;
    private int y;
    private Player player;

    public GameData()
    {
        x = 50;
        y = 50;
        grid = new Area[x+1][y+1];
        player = new Player();
    }

    public boolean positionCheck(int xCheck, int yCheck)
    {
        int xCheck2 = player.getColLocation() + xCheck;
        int yCheck2 = player.getRowLocation() + yCheck;

        return xCheck2 <= x && yCheck2 <= y && xCheck2 >= 0 && yCheck2 >= 0;
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


}
