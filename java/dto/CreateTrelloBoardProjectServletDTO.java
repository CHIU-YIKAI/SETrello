package dto;

public class CreateTrelloBoardProjectServletDTO {
    private String UserID;
    private String BoardName;
    private String description;
    private String UserKey;
    private String UserToken;

    public CreateTrelloBoardProjectServletDTO(String UserID, String BoardName, String description, String UserKey, String UserToken) {
        this.UserID = UserID;
        this.BoardName = BoardName;
        this.description = description;
        this.UserKey = UserKey;
        this.UserToken = UserToken;
    }

    public CreateTrelloBoardProjectServletDTO() {}

    public String getUserID() { return UserID; }
    public String getBoardName() { return BoardName; }
    public String getDescription() { return description; }
    public String getUserKey() { return UserKey; }
    public String getUserToken() { return UserToken; }

    public void setUserID(String UserID) { this.UserID = UserID; }
    public void setBoardName(String BoardName) { this.BoardName = BoardName; }
    public void setDescription(String description) { this.description = description; }
    public void setUserKey(String UserKey) { this.UserKey = UserKey; }
    public void setUserToken(String UserToken) { this.UserToken = UserToken; }

}
