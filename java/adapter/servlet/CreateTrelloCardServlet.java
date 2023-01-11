package adapter.servlet;

import adapter.TrelloAccessorImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import adapter.account.AccountRepositoryImpl;
import domain.Account;
import dto.TrelloDetailnfoDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import usecase.TrelloAccessor;
import usecase.TrelloBoardProject.TrelloBoardProjectRepository;
import usecase.account.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/createTrelloCard", name = "CreateTrelloCardServlet")
public class CreateTrelloCardServlet extends HttpServlet {
    class GetTrelloProjectException extends Exception {
        GetTrelloProjectException(String msg) {
            super(msg);
        }

        GetTrelloProjectException() {
            super();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        JSONObject returnJson = new JSONObject();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        TrelloBoardProjectRepository TrelloBoardProjectRepository = new ProjectTrelloBoardRepositoryImpl();
        System.out.println(requestBody);
        String userId = String.valueOf(requestBody.get("userId"));
        String listId = String.valueOf(requestBody.get("listId"));
        String cardName = String.valueOf(requestBody.get("cardName"));
        String desc = String.valueOf(requestBody.get("desc"));
        boolean isSuccessful = false;
        Account account = accountRepository.getAccountById(userId);
        JSONArray jsonArray = new JSONArray();
        try {
            TrelloAccessor TrelloAccessor = new TrelloAccessorImpl();
            isSuccessful = TrelloAccessor.createTrelloCard( listId,  cardName, desc , account.getTrelloKey(),  account.getTrelloToken());
            if (isSuccessful) {
                System.out.println("cannot create card");
                throw new CreateTrelloCardServlet.GetTrelloProjectException("cannot get info");
            }

        } catch (CreateTrelloCardServlet.GetTrelloProjectException e) {

        }
        returnJson.put("isSuccessful", isSuccessful);
        PrintWriter out = response.getWriter();
        out.println(returnJson);
        out.close();
    }


}