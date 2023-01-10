package domain;

import java.util.UUID;

public class TrelloBoardProject {
    private String id;
    private String repoName;
    private String ownerName;

    public TrelloBoardProject(String repoName, String ownerName) {
        this.id = UUID.randomUUID().toString();
        this.repoName = repoName;
        this.ownerName = ownerName;
    }

    public TrelloBoardProject(String id, String repoName, String ownerName) {
        this.id = id;
        this.repoName = repoName;
        this.ownerName = ownerName;
    }

    public String getId() {
        return id;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

}
