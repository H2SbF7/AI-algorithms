package PathFinding;

/* 
    Nguyen Tuan Dang
    Faculty of Information Technology, Saigon University
    dangnt@sgu.edu.vn
*/

public class State {
    private Point currentPosition = new Point();
    
    public State(){}
    
    public State(Point currentPosition) {
        this.currentPosition = currentPosition;
    }
}