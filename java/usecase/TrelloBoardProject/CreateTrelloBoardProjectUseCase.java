package usecase.TrelloBoardProject;

import domain.TrelloBoardProject;

import java.sql.SQLException;

public class CreateTrelloBoardProjectUseCase {
    private TrelloBoardProjectRepository trelloBoardProjectRepository;

    public CreateTrelloBoardProjectUseCase(TrelloBoardProjectRepository trelloBoardProjectRepository){
        this.trelloBoardProjectRepository = trelloBoardProjectRepository;
    }

    public void execute(CreateTrelloBoardProjectInput input, CreateTrelloBoardProjectOutput output) {
        TrelloBoardProject newTrelloBoardProject = new TrelloBoardProject(input.getUserID(), input.getBoardName(), input.getDescription(), input.getBoardID());

        try {
            trelloBoardProjectRepository.createTrelloBoardProject(newTrelloBoardProject);
            output.setIsSuccessful(true);
        } catch (SQLException e) {
            output.setIsSuccessful(false);
            e.printStackTrace();
        }

        output.setTrelloBoardId(newTrelloBoardProject.getBoardID());
        output.setTrelloBoardProjectId(newTrelloBoardProject.getID());
    }
}
