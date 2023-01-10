package adapter.TrelloBoardProject;

import usecase.TrelloBoardProject.CreateTrelloBoardProjectOutput;

public class CreateTrelloBoardProjectOutputImpl implements CreateTrelloBoardProjectOutput {
    private String BoardID;
    private String ProjectId;
    private boolean isSuccessful;
    @Override
    public boolean getIsSuccessful() {
        return this.isSuccessful;
    }
    @Override
    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    @Override
    public String getTrelloBoardId() {
        return BoardID;
    }
    @Override
    public void setTrelloBoardId(String BoardID) { this.BoardID = BoardID; }

    @Override
    public String getTrelloBoardProjectId() {
        return ProjectId;
    }
    @Override
    public void setTrelloBoardProjectId(String ProjectId) { this.ProjectId = ProjectId; }


}
