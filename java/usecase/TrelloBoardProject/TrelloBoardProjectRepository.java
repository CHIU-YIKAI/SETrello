package usecase.TrelloBoardProject;

import domain.TrelloBoardProject;

import java.sql.SQLException;

public interface TrelloBoardProjectRepository {
    TrelloBoardProject getTrelloBoardProjectByTrelloBoardProjectId(String id);
    TrelloBoardProject getTrelloBoardProjectByBoardId(String BoardID);
    void createTrelloBoardProject(TrelloBoardProject TrelloBoardProject) throws SQLException;
    void deleteTrelloBoardProject(String BoardID) throws SQLException;
}
