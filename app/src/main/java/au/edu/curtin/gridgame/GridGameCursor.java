package au.edu.curtin.gridgame;

import android.database.Cursor;
import android.database.CursorWrapper;
import au.edu.curtin.gridgame.GridGameSchema.PlayerTable;
import au.edu.curtin.gridgame.GridGameSchema.AreaTable;

public class GridGameCursor extends CursorWrapper
{
    public GridGameCursor(Cursor cursor)
    {
        super(cursor);
    }

    public Player getPlayer()
    {
        int id = getInt(getColumnIndex(PlayerTable.Cols.ID));
        int col = getInt(getColumnIndex(PlayerTable.Cols.COL));
        int row = getInt(getColumnIndex(PlayerTable.Cols.ROW));
        int cash = getInt(getColumnIndex(PlayerTable.Cols.CASH));
        double health = getDouble(getColumnIndex(PlayerTable.Cols.HEALTH));
        double mass = getDouble(getColumnIndex(PlayerTable.Cols.MASS));
        String list = getString(getColumnIndex(PlayerTable.Cols.LIST));
        int win = getInt(getColumnIndex(PlayerTable.Cols.WIN));

        return new Player(row,col,cash,health,mass,list,win);
    }

    public Area getArea()
    {
        int id = getInt(getColumnIndex(AreaTable.Cols.ID));
        boolean inTown = getInt(getColumnIndex(AreaTable.Cols.TOWN)) > 0;
        int inX = getInt(getColumnIndex(AreaTable.Cols.XCo));
        int inY = getInt(getColumnIndex(AreaTable.Cols.YCo));
        String inDesc = getString(getColumnIndex(AreaTable.Cols.DESC));
        boolean inStarred = getInt(getColumnIndex(AreaTable.Cols.STAR)) > 0;
        boolean inExplored = getInt(getColumnIndex(AreaTable.Cols.EXPLORED)) > 0;
        String inList = getString(getColumnIndex(AreaTable.Cols.LIST2));

        return new Area(id,inTown,inX,inY,inDesc,inStarred,inExplored,inList);


    }

}
