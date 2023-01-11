package usecase.TrelloBoardProject;

public interface CreateTrelloBoardProjectOutput {

    void setIsSuccessful(boolean isSuccessful);

    boolean getIsSuccessful();

    void setTrelloBoardId(String BoardID);

    String getTrelloBoardId();
    void setTrelloBoardProjectId(String BoardProjectID);

    String getTrelloBoardProjectId();

    void setErrorMsg(String setErrorMsg);

    String getsetErrorMsg();

}
