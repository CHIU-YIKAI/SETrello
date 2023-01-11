package adapter.servlet;

import adapter.TrelloAccessorImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import adapter.account.AccountRepositoryImpl;
import domain.Account;
import dto.TrelloCardinfoDTO;
import dto.TrelloDetailnfoDTO;
import dto.TrelloListinfoDTO;
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

@WebServlet(urlPatterns = "/createTrelloList", name = "CreateTrelloListServlet")
public class CreateTrelloListServlet extends HttpServlet {
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
        String BoardId = String.valueOf(requestBody.get("boardId"));
        String listName = String.valueOf(requestBody.get("listName"));
        boolean isSuccessful = false;
        Account account = accountRepository.getAccountById(userId);
        JSONArray jsonArray = new JSONArray();
        try {
            TrelloAccessor TrelloAccessor = new TrelloAccessorImpl();
            isSuccessful = TrelloAccessor.createTrelloList( BoardId,  listName,  account.getTrelloKey(),  account.getTrelloToken());
            if (isSuccessful) {
                System.out.println("cannot create list");
                throw new CreateTrelloListServlet.GetTrelloProjectException("cannot get info");
            }

        } catch (CreateTrelloListServlet.GetTrelloProjectException e) {

        }
        returnJson.put("isSuccessful", isSuccessful);
        PrintWriter out = response.getWriter();
        out.println(returnJson);
        out.close();
    }

    private TrelloDetailnfoDTO showTrelloDetail(String BoardID, String UserKey, String UserToken) throws CreateTrelloListServlet.GetTrelloProjectException {
        TrelloAccessor TrelloAccessor = new TrelloAccessorImpl();
        TrelloDetailnfoDTO TrelloDetailnfoDTO = new TrelloDetailnfoDTO();
        TrelloDetailnfoDTO = TrelloAccessor.getTrelloDetailInfo(BoardID, UserKey, UserToken);
        if (!TrelloDetailnfoDTO.isSuccessful()) {
            System.out.println("cannot get info");
            throw new CreateTrelloListServlet.GetTrelloProjectException("cannot get info");
        }
        System.out.println("get info");
        return TrelloDetailnfoDTO;
    }
}