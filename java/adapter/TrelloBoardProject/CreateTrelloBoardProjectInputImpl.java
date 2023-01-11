package adapter.TrelloBoardProject;

import usecase.TrelloBoardProject.CreateTrelloBoardProjectInput;

public class CreateTrelloBoardProjectInputImpl implements CreateTrelloBoardProjectInput {
    private String id;
    private String BoardName;
    private String description;

    private String UserID;
    private String BoardID;
    @Override
    public String getID() {
        return id;
    }
    @Override
    public void setID(String id) {
        this.id = id;
    }
    @Override
    public String getUserID() { return UserID; }
    @Override
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    @Override
    public String getBoardName() {
        return BoardName;
    }
    @Override
    public void setBoardName(String BoardName) {
        this.BoardName = BoardName;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String getBoardID() {
        return BoardID;
    }
    @Override
    public void setBoardID(String BoardID) {
        this.BoardID = BoardID;
    }
}
