import providers.Dp;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tobjects.Board;

import java.util.List;

import static services.TrelloService.getAllBoards;
import static services.TrelloService.requestBuilder;

public class TrelloTests {

    @Test
    public void checkMisspelledTexts() {

        List<Board> result = getAllBoards(
                requestBuilder()
                        .setMethod(Method.GET)
                        .setBaseUrl(Dp.getProperty("pathToTrello"))
                        .setAllBoards(Dp.getProperty("allBoards"))
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .buildRequest()
                        .sendRequest());

    }


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
