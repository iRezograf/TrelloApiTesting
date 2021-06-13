import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.TrelloService;
import tobjects.Board;

public class TrelloTests {
    private String createdBoardId, createdBoardId2, createdOrgId;

    @BeforeClass
    public void initService() {
        // create object
    }

    @Test
    public void createCardInBoard() {
        //Create board

        //Create list

        //Check that list was added
    }

    @Test
    public void assignBoardToOrganization() {
        //Create board

        //Check that organization contains created board
    }

    @AfterClass
    public void clearBoards() {
        //delete created objects
    }
}
