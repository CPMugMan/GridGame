package au.edu.curtin.gridgame;

public class GridGameSchema
{
    public static class PlayerTable
    {
        public static final String NAME = "player";
        public static class Cols
        {
            public static final String ID = "player_id";
            public static final String ROW = "row";
            public static final String COL = "col";
            public static final String CASH = "cash";
            public static final String HEALTH = "health";
            public static final String MASS = "mass";
            public static final String LIST = "list";
            public static final String WIN = "winCount";
        }
    }

    public static class AreaTable
    {
        public static final String NAME = "area";
        public static class Cols
        {
            public static final String ID = "area_id";
            public static final String TOWN = "town";
            public static final String XCo = "x";
            public static final String YCo = "y";
            public static final String DESC = "description";
            public static final String STAR = "starred";
            public static final String EXPLORED = "explored";
            public static final String LIST2 = "areaList";
        }
    }
}
