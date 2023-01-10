package adapter.servlet;


import adapter.account.AccountRepositoryImpl;
import adapter.TrelloBoardProject.CreateTrelloBoardProjectInputImpl;
import adapter.TrelloBoardProject.CreateTrelloBoardProjectOutputImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import adapter.TrelloAccessorImpl;

import com.google.gson.Gson;
import domain.Account;
import dto.CreateTrelloBoardProjectServletDTO;

import dto.TrelloBoardProjectinfoDTO;
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
        String response_status;
        CreateTrelloBoardProjectServletDTO requestDto = gson.fromJson(requestBody, CreateTrelloBoardProjectServletDTO.class);
        TrelloBoardProjectinfoListDTO getTrelloBoardInfoList = new TrelloBoardProjectinfoListDTO();

        try {
            getTrelloBoardInfoList = getUserBoardList(requestDto.getUserKey(), requestDto.getUserToken());
            BoardID = BoardNameMatching(getTrelloBoardInfoList, requestDto.getBoardName());

            createTrelloBoardProject(requestDto.getUserID(), requestDto.getBoardName(), requestDto.getDescription(), BoardID);
            isSuccessful = true;
            response_status = getTrelloBoardInfoList.getresponseMsg();
        } catch (CreateTrelloProjectException e) {
            projectId="";
            isSuccessful= false;
            response_status = e.getMessage();
        }

        responseJson.put("isSuccessful", isSuccessful);
        responseJson.put("status", response_status);
        PrintWriter out = response.getWriter();
        out.println(responseJson);
        out.close();
    }
    private String BoardNameMatching(TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO, String BoardName)throws CreateTrelloProjectException{
        String BoardID = null;
        System.out.println("board name input");
        System.out.println(BoardName);
        for ( dto.TrelloBoardProjectinfoDTO TrelloBoardProjectinfoDTO : TrelloBoardProjectinfoListDTO.getTrelloBoardProjectinfoList()) {
            System.out.println(TrelloBoardProjectinfoDTO.getName());
            if( BoardName.equals(TrelloBoardProjectinfoDTO.getName())){
                System.out.println("Found matching board name");
                System.out.println(TrelloBoardProjectinfoDTO.getId());
                System.out.println(TrelloBoardProjectinfoDTO.getName());
                BoardID = TrelloBoardProjectinfoDTO.getId();
                return BoardID;
            }
        }
        throw new CreateTrelloProjectException("No matching Board Name");


    }

    private TrelloBoardProjectinfoListDTO getUserBoardList(String UserKey, String UserToken)throws CreateTrelloProjectException{
        TrelloAccessor TrelloAccessor = new TrelloAccessorImpl();
        TrelloBoardProjectinfoListDTO getTrelloBoardInfoList = new TrelloBoardProjectinfoListDTO();
        getTrelloBoardInfoList = TrelloAccessor.getTrelloBoardInfoList(UserKey, UserToken);
        if(!getTrelloBoardInfoList.isSuccessful()){
            throw new CreateTrelloProjectException(getTrelloBoardInfoList.getresponseMsg());
        }
        return getTrelloBoardInfoList;
    }
    private void createTrelloBoardProject(String UserID, String BoardName, String description, String BoardID)  throws CreateTrelloProjectException{

        TrelloBoardProjectRepository TrelloBoardRepository = new ProjectTrelloBoardRepositoryImpl();
        CreateTrelloBoardProjectInput input = new CreateTrelloBoardProjectInputImpl();
        CreateTrelloBoardProjectOutput output = new CreateTrelloBoardProjectOutputImpl();

        input.setUserID(UserID);
        input.setBoardName(BoardName);
        input.setDescription(description);
        input.setBoardID(BoardID);

        CreateTrelloBoardProjectUseCase createTrelloBoardProjectUseCase = new CreateTrelloBoardProjectUseCase(TrelloBoardRepository);
        createTrelloBoardProjectUseCase.execute(input,output);

        if (!output.getIsSuccessful()) throw new CreateTrelloProjectException(output.getsetErrorMsg());

    }

//    private void addCredentialstoUser(String UeserKey,String UserToken) throws CreateTrelloProjectException{
//        AccountRepository accountRepository = new AccountRepositoryImpl();
//        Account account = accountRepository.getAccountById(userId);
//        account.addProject(projectId);
//        try {
//            accountRepository.updateAccountOwnProject(account);
//        } catch (SQLException e) {
//            throw new CreateTrelloProjectException("add project to user fail.");
//        }
//    }
}
