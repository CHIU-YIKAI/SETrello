package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private String name;
    private String id;
    private String password;
    private String account;
    private String trelloKey;
    private String trelloToken;
    private String githubId;
    private List<String> projects;
    private List<String> TrelloBoardprojects;

    public Account(String account, String password) {
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.account = account;
        this.githubId = "";
        projects = new ArrayList<>();
        TrelloBoardprojects = new ArrayList<>();
    }

    public Account(String name, String account, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.account = account;
        this.githubId = "";
        projects = new ArrayList<>();
        TrelloBoardprojects = new ArrayList<>();

    }

    public Account(String id, String name, String account, String password) {
        this.id =id;
        this.name = name;
        this.password = password;
        this.account = account;
        this.githubId = "";
        projects = new ArrayList<>();
        TrelloBoardprojects = new ArrayList<>();
    }
    public Account(String id, String name, String account, String password, String trelloKey, String trelloToken) {
        this.id =id;
        this.name = name;
        this.password = password;
        this.account = account;
        this.githubId = "";
        this.trelloKey = trelloKey;
        this.trelloToken = trelloToken;
        projects = new ArrayList<>();
        TrelloBoardprojects = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }
    public String getTrelloKey() {
        return trelloKey;
    }
    public String getTrelloToken() {
        return trelloToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProject(String id){
        projects.add(id);
    }
    public List<String> getProjects(){ return this.projects;}
    public void addTrelloBoardProject(String id){
        TrelloBoardprojects.add(id);
    }
    public List<String> getTrelloBoardProject(){ return this.TrelloBoardprojects;}

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }
    public void setTrelloKey(String trelloKey) {
        this.trelloKey = trelloKey;
    }
    public void setTrelloToken(String trelloToken) {
        this.trelloToken = trelloToken;
    }
}
