package domain;

import java.util.UUID;

public class TrelloBoardProject {
    private String id;
    private String BoardName;
    private String description;
    private String startTime;
    private String UserID;
    private String BoardID;

    public TrelloBoardProject(String UserID, String BoardName, String description, String BoardID){
        this.id = UUID.randomUUID().toString();
        this.UserID = UserID;
        this.BoardName = BoardName;
        this.description = description;
        this.BoardID = BoardID;
    }
    public TrelloBoardProject(String UserID, String BoardName, String description, String BoardID, String startTime ){
        this.id = UUID.randomUUID().toString();
        this.UserID = UserID;
        this.BoardName = BoardName;
        this.description = description;
        this.BoardID = BoardID;
        this.startTime = startTime;
    }

    public TrelloBoardProject(String id, String UserID, String BoardName, String description, String BoardID, String startTime){
        this.id = id;
        this.UserID = UserID;
        this.BoardName = BoardName;
        this.description = description;
        this.BoardID = BoardID;
        this.startTime = startTime;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getUserID() { return UserID; }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getBoardName() {
        return BoardName;
    }

    public void setBoardName(String BoardName) {
        this.BoardName = BoardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBoardID() {
        return BoardID;
    }

    public void setBoardID(String BoardID) {
        this.BoardID = BoardID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }
}
