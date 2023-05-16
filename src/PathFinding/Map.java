package PathFinding;

/* 
    Nguyen Tuan Dang
    Faculty of Information Technology, Saigon University
    dangnt@sgu.edu.vn
*/

public class Map {
    private final static int ROWS = 4;
    private final static int COLS = 8;
        
    Point map[][];
    
    public Map(){
        map = new Point[ROWS][COLS];
        
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                map[i][j] = new Point();
    }
}