package adapter.gson;

import java.util.List;

public class UserTrelloBoardListGsonAdapter {

    protected List<BoardInfo> BoardList;

    public class BoardInfo {
        protected String id;
        protected String name;

        public String getID() { return id; }
        public String getName() { return name; }
    }


    public List<BoardInfo> getBoardList() {
        return BoardList;
    }
    public void setBoardList() { this.BoardList = BoardList; }
}
