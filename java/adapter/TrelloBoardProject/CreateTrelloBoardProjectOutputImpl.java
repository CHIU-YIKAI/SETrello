package adapter.TrelloBoardProject;

import domain.GitRepository;
import usecase.gitrepository.CreateGitRepositoryOutput;

public class CreateTrelloBoardProjectOutputImpl implements CreateGitRepositoryOutput {
    private GitRepository gitRepository;
    private boolean isSuccessful;

    @Override
    public GitRepository getResult() {
        return this.gitRepository;
    }

    @Override
    public void setResult(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }

    @Override
    public boolean getIsSuccessful() {
        return this.isSuccessful;
    }
    @Override
    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}
