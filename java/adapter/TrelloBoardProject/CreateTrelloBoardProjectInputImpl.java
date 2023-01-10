package adapter.TrelloBoardProject;

import usecase.gitrepository.CreateGitRepositoryInput;

public class CreateTrelloBoardProjectInputImpl implements CreateGitRepositoryInput {
    private String ownerName;
    private String repoName;
    private String projectID;

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Override
    public String getProjectID() {
        return projectID;
    }

    @Override
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
