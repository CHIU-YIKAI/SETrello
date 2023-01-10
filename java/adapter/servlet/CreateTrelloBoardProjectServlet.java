package adapter.servlet;


import adapter.account.AccountRepositoryImpl;
import adapter.TrelloBoardProject.CreateTrelloBoardProjectInputImpl;
import adapter.TrelloBoardProject.CreateTrelloBoardProjectOutputImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import adapter.TrelloAccessorImpl;

import com.google.gson.Gson;
import domain.Account;
import dto.CreateTrelloBoardProjectServletDTO;

import dto.TrelloBoardProjectinfoListDTO;
import org.json.JSONObject;

import usecase.account.AccountRepository;
import usecase.TrelloBoardProject.CreateTrelloBoardProjectInput;
import usecase.TrelloBoardProject.CreateTrelloBoardProjectOutput;
import usecase.TrelloBoardProject.TrelloBoardProjectRepository;
import usecase.TrelloBoardProject.CreateTrelloBoardProjectUseCase;
import usecase.TrelloAccessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/CreateTrelloProject", name = "CreateTrelloProjectServlet")
public class CreateTrelloBoardProjectServlet extends HttpServlet {

    class CreateTrelloProjectException extends Exception{
        CreateTrelloProjectException(String msg) { super(msg); }
        CreateTrelloProjectException() { super(); }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestBody = request.getReader().readLine();
        System.out.println(requestBody);

        JSONObject responseJson = new JSONObject();
        Gson gson = new Gson();
        String projectId;
        String BoardID;
        boolean isSuccessful;
        CreateTrelloBoardProjectServletDTO requestDto = gson.fromJson(requestBody, CreateTrelloBoardProjectServletDTO.class);
        TrelloAccessor TrelloAccessor = new TrelloAccessorImpl();
        TrelloBoardProjectinfoListDTO getTrelloBoardInfoList = new TrelloBoardProjectinfoListDTO();

        try {
            BoardID = "tmp ID";
            getTrelloBoardInfoList = TrelloAccessor.getTrelloBoardInfoList(requestDto.getUserKey(), requestDto.getUserToken(), requestDto.getBoardName());
            projectId = createTrelloBoardProject(requestDto.getUserID(), requestDto.getBoardName(), requestDto.getDescription(), getTrelloBoardInfoList.getresponseMsg());
//            addProjectOnUserID(requestDto.getUserID(), projectId);
            isSuccessful = true;
        } catch (CreateTrelloProjectException e) {
            projectId="";
            isSuccessful= false;
        }


//        responseJson.put("projectId", projectId);
        responseJson.put("isSuccessful", getTrelloBoardInfoList.isSuccessful());
        responseJson.put("status", getTrelloBoardInfoList.getresponseMsg());
        PrintWriter out = response.getWriter();
        out.println(responseJson);
        out.close();
    }
//
//    private void createGitRepository(String githubUrl, String projectId) throws CreateProjectException  {
//        GitRepositoryRepository gitRepositoryRepository = new GitRepositoryRepositoryImpl();
//        CreateGitRepositoryInput input = new CreateGitRepositoryInputImpl();
//        CreateGitRepositoryOutput output = new CreateGitRepositoryOutputImpl();
//        CreateGitRepositoryUseCase createGitRepositoryUseCase = new CreateGitRepositoryUseCase(gitRepositoryRepository);
//
//        input.setProjectID(projectId);
//        input.setOwnerName(getRepoName(githubUrl, "github.com"));
//        input.setRepoName(getRepoOwnerName(githubUrl, "github.com"));
//        createGitRepositoryUseCase.execute(input, output);
//
//        if (!output.getIsSuccessful()) throw new CreateProjectException("create git repository fail");
//    }
//    private String getRepoOwnerName(String validUrl, String keyWord) throws CreateProjectException {
//        String[] metadatas = validUrl.split("/");
//        for (int i = 0; i < metadatas.length; i++) {
//            if (metadatas[i].equals(keyWord)) {
//                return metadatas[i+2];
//            }
//        }
//        throw new CreateProjectException("parse repository owner name fail!");
//    }
//
//    private String getRepoName(String validUrl, String keyWord) throws CreateProjectException {
//        String[] metadatas = validUrl.split("/");
//        for (int i = 0; i < metadatas.length; i++) {
//            if (metadatas[i].equals(keyWord)) {
//                return metadatas[i+1];
//            }
//        }
//        throw new CreateProjectException("parse repository name fail!");
//    }
//
//
//
    private String createTrelloBoardProject(String UserID, String BoardName, String description, String BoardID)  throws CreateTrelloProjectException{

        TrelloBoardProjectRepository TrelloBoardRepository = new ProjectTrelloBoardRepositoryImpl();
        CreateTrelloBoardProjectInput input = new CreateTrelloBoardProjectInputImpl();
        CreateTrelloBoardProjectOutput output = new CreateTrelloBoardProjectOutputImpl();

        input.setUserID(UserID);
        input.setBoardName(BoardName);
        input.setDescription(description);
        input.setBoardID(BoardID);
//        input.s(projectId);

        CreateTrelloBoardProjectUseCase createSonarProjectUseCase = new CreateTrelloBoardProjectUseCase(TrelloBoardRepository);
        createSonarProjectUseCase.execute(input,output);
        if (!output.getIsSuccessful()) throw new CreateTrelloProjectException();
        System.out.println("ProjectID===============");
        System.out.println(output.getTrelloBoardProjectId());
        System.out.println("ProjectID===============");
        return output.getTrelloBoardProjectId();
    }

    private void addProjectOnUserID(String userId,String projectId) throws CreateTrelloProjectException{
        AccountRepository accountRepository = new AccountRepositoryImpl();
        Account account = accountRepository.getAccountById(userId);
        account.addProject(projectId);
        try {
            accountRepository.updateAccountOwnProject(account);
        } catch (SQLException e) {
            throw new CreateTrelloProjectException("add project to user fail.");
        }
    }
}
