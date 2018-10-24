package au.edu.curtin.gridgame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import au.edu.curtin.gridgame.GridGameSchema.PlayerTable;
import au.edu.curtin.gridgame.GridGameSchema.AreaTable;

public class GridGameDbHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "grid.db";

    public GridGameDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + PlayerTable.NAME + "(" + "_id integer primary key autoincrement, " +
                PlayerTable.Cols.ID + "," +
                PlayerTable.Cols.ROW + "," +
                PlayerTable.Cols.COL + "," +
                PlayerTable.Cols.CASH + "," +
                PlayerTable.Cols.HEALTH + "," +
                PlayerTable.Cols.MASS + "," +
                PlayerTable.Cols.LIST + "," +
                PlayerTable.Cols.WIN + ")");

        db.execSQL("CREATE TABLE " + AreaTable.NAME + "(" + "_id integer primary key autoincrement, " +
                AreaTable.Cols.ID + "," +
                AreaTable.Cols.TOWN + "," +
                AreaTable.Cols.XCo + "," +
                AreaTable.Cols.YCo + "," +
                AreaTable.Cols.DESC + "," +
                AreaTable.Cols.STAR + "," +
                AreaTable.Cols.EXPLORED + "," +
                AreaTable.Cols.LIST2 + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
