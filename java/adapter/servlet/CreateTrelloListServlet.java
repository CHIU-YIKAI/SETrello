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
            TrelloDetailnfoDTO TrelloDetailnfoDTO = showTrelloDetail(BoardId, account.getTrelloKey(), account.getTrelloToken());
            for (TrelloListinfoDTO li : TrelloDetailnfoDTO.getTrelloListinfoDTOList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("listId", li.getId());
                jsonObject.put("listName", li.getName());
                JSONArray cardjsonArray = new JSONArray();
                for (TrelloCardinfoDTO ca : li.getTrelloCardinfoDTOList()) {
                    JSONObject cardjsonObject = new JSONObject();
                    cardjsonObject.put("cardId",ca.getId());
                    cardjsonObject.put("cardName",ca.getName());
                    cardjsonObject.put("cardDescription",ca.getDescription());
                    cardjsonArray.put( cardjsonObject);
                }
                jsonObject.put("cards",cardjsonArray);
                jsonArray.put(jsonObject);
            }

        } catch (CreateTrelloListServlet.GetTrelloProjectException e) {

        }

        System.out.println(jsonArray);
        PrintWriter out = response.getWriter();
        out.println(jsonArray);
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