package adapter.TrelloBoardProject;

import database.Database;
import domain.TrelloBoardProject;
import usecase.TrelloBoardProject.TrelloBoardProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectTrelloBoardRepositoryImpl implements TrelloBoardProjectRepository {

    private Connection conn;

    public ProjectTrelloBoardRepositoryImpl() {

        conn = Database.getConnection();
    }
    @Override
    public void createTrelloBoardProject(TrelloBoardProject TrelloBoardProject) throws SQLException {

        final String insert = " INSERT INTO trelloproject(trelloProjectID, userID, trelloBoardID, BoardName, description) VALUES(?,?,?,?,?) ";

        assert conn != null;
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setString(1, TrelloBoardProject.getID());
        preparedStatement.setString(2, TrelloBoardProject.getUserID());
        preparedStatement.setString(3, TrelloBoardProject.getBoardID());
        preparedStatement.setString(4, TrelloBoardProject.getBoardName());
        preparedStatement.setString(5, TrelloBoardProject.getDescription());
        preparedStatement.execute();

    }


    @Override
    public void deleteTrelloBoardProject(String BoardID) throws SQLException {
        final String delete = "DELETE FROM trelloproject WHERE trelloBoardID=?";

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, BoardID);
            preparedStatement.executeUpdate();

    }
    @Override
    public TrelloBoardProject getTrelloBoardProjectByTrelloBoardProjectId(String id) {
        final String query = "SELECT trelloProjectID,BoardName,description, trelloBoardID, UserID FROM trelloproject WHERE  trelloProjectID = ?";

        try {
            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.first()) return null;
            String trelloProjectID = resultSet.getString("trelloProjectID");
            String BoardName = resultSet.getString("BoardName");
            String description = resultSet.getString("description");
            String UserID = resultSet.getString("UserID");
            String trelloBoardID = resultSet.getString("trelloBoardID");

            TrelloBoardProject TrelloBoardProject = new TrelloBoardProject(
                    trelloProjectID,
                    UserID,
                    BoardName,
                    description,
                    trelloBoardID
            );

            return TrelloBoardProject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TrelloBoardProject getTrelloBoardProjectByBoardId(String BoardID) {
        final String query = "SELECT trelloProjectID,BoardName,description, trelloBoardID, UserID FROM trelloproject WHERE  trelloBoardID = ?";


        try {
            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, BoardID);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.first()) return null;
            String trelloProjectID = resultSet.getString("trelloProjectID");
            String BoardName = resultSet.getString("BoardName");
            String description = resultSet.getString("description");
            String UserID = resultSet.getString("UserID");
            String trelloBoardID = resultSet.getString("trelloBoardID");

            TrelloBoardProject TrelloBoardProject = new TrelloBoardProject(
                    trelloProjectID,
                    UserID,
                    BoardName,
                    description,
                    trelloBoardID
            );

            return TrelloBoardProject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
