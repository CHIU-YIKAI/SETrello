package adapter.servlet;

import adapter.account.AccountRepositoryImpl;
import adapter.TrelloBoardProject.ProjectTrelloBoardRepositoryImpl;
import org.json.JSONObject;
import usecase.account.AccountRepository;
import usecase.TrelloBoardProject.TrelloBoardProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteTrelloBoardProject", name = "DeleteTrelloBoardProjectServlet")
public class DeleteTrelloBoardProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        TrelloBoardProjectRepository TrelloBoardProjectRepository = new ProjectTrelloBoardRepositoryImpl();
        String projectId = String.valueOf(requestBody.get("projectId"));
        boolean isSuccessful =true;

        try {
            TrelloBoardProjectRepository.deleteTrelloBoardProject(projectId);
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccessful =false;
        }
        jsonObject.put("isSuccess", isSuccessful?"true":"false");

        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }
}
