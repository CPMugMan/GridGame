package au.edu.curtin.gridgame;

public class GameData
{
    private Area[][] grid;
    private int x;
    private int y;

    public GameData()
    {
        x = 50;
        y = 50;
        grid = new Area[x+1][y+1];
    }

    public boolean positionCheck(int xCheck, int yCheck)
    {
        return xCheck <= x && yCheck <= y && xCheck >= 0 && yCheck >= 0;
    }

    public void setArea(int x, int y, Area inArea)
    {
        grid[x][y] = inArea;
    }


    public Area getArea(int x, int y)
    {
        return grid[x][y];
    }


}
