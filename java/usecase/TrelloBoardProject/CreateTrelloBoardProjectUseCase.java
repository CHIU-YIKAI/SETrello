package usecase.TrelloBoardProject;

import domain.TrelloBoardProject;

import java.sql.SQLException;

public class CreateTrelloBoardProjectUseCase {
    private TrelloBoardProjectRepository trelloBoardProjectRepository;

    public CreateTrelloBoardProjectUseCase(TrelloBoardProjectRepository trelloBoardProjectRepository){
        this.trelloBoardProjectRepository = trelloBoardProjectRepository;
    }

    public void execute(CreateTrelloBoardProjectInput input, CreateTrelloBoardProjectOutput output) {
        try {
            TrelloBoardProject newTrelloBoardProject = trelloBoardProjectRepository.getTrelloBoardProjectByBoardId(input.getBoardID());
            if(newTrelloBoardProject!=null){
                output.setIsSuccessful(false);
                output.setErrorMsg("Project already exists");
            }
            else{
                newTrelloBoardProject = new TrelloBoardProject(input.getUserID(), input.getBoardName(), input.getDescription(), input.getBoardID());

                try {
                    trelloBoardProjectRepository.createTrelloBoardProject(newTrelloBoardProject);
                    output.setIsSuccessful(true);
                } catch (SQLException e) {
                    output.setIsSuccessful(false);
                    e.printStackTrace();
                    output.setErrorMsg(e.getMessage());
                }

                output.setTrelloBoardId(newTrelloBoardProject.getBoardID());
                output.setTrelloBoardProjectId(newTrelloBoardProject.getID());
            }
        } catch (Exception e) {
            output.setIsSuccessful(false);
            e.printStackTrace();
            output.setErrorMsg(e.getMessage());
        }

    }
}
