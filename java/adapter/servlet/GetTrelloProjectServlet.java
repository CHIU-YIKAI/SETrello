package adapter.servlet;

import adapter.account.AccountRepositoryImpl;
//import adapter.gitrepository.GitRepositoryRepositoryImpl;
//import adapter.project.ProjectRepositoryImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import domain.Account;
//import domain.Project;
import domain.TrelloBoardProject;
import org.json.JSONArray;
import org.json.JSONObject;
import usecase.account.AccountRepository;
//import usecase.gitrepository.GitRepositoryRepository;
//import usecase.project.ProjectRepository;
import usecase.TrelloBoardProject.TrelloBoardProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getUserTrello", name = "GetTrelloProjectServlet")
public class GetTrelloProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        JSONObject returnJson = new JSONObject();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        TrelloBoardProjectRepository TrelloBoardProjectRepository = new ProjectTrelloBoardRepositoryImpl();

        String userId = String.valueOf(requestBody.get("userId"));
        Account account = accountRepository.getAccountById(userId);
        JSONArray jsonArray = new JSONArray();

        for(String TrelloBoardprojectId : account.getTrelloBoardProject()){
            TrelloBoardProject TrelloBoardproject = TrelloBoardProjectRepository.getTrelloBoardProjectByTrelloBoardProjectId(TrelloBoardprojectId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("projectId", TrelloBoardproject.getID());
            jsonObject.put("projectName", TrelloBoardproject.getBoardName());
            jsonObject.put("projectDescription", TrelloBoardproject.getDescription());
            jsonObject.put("projectStartTime", TrelloBoardproject.getStartTime());
            jsonObject.put("boardId", TrelloBoardproject.getBoardID());
            jsonArray.put(jsonObject);
        }
        System.out.println(jsonArray);
        PrintWriter out = response.getWriter();
        out.println(jsonArray) ;
        out.close();
    }
}