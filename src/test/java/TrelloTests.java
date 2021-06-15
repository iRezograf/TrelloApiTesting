import entity.Board;
import entity.Card;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import providers.Dp;

import java.util.ArrayList;
import java.util.List;

import static services.TrelloService.*;

public class TrelloTests {

    List<Board> boards;
    List<Card> cards;
    List<String> boardList;
    List<String> listList;
    String newBoardId;

    @BeforeClass
    public void setUp() {
        boards = new ArrayList<>();
        cards  = new ArrayList<>();
        boardList = new ArrayList<>();
        listList = new ArrayList<>();
    }

    @AfterClass
    public void tearDown() {
        boards = null;
        cards = null;
        boardList = null;
        listList = null;

    }

    @Test
    public void allTest() {

        //check200Ok();

        //allOfTheBoardsAssociatedWithMe();

        //testBoardsById();

        createNewBoards(Dp.getProperty("boardName"));

        //deleteBoards(board.id);

    }


    public void check200Ok() {
        requestBuilder()
                .setMethod(Method.GET)
                .setUrl(Dp.getProperty("pathToTrello"))
                .setUrl(Dp.getProperty("allBoards"))
                .setKey(Dp.getProperty("key"))
                .setToken(Dp.getProperty("token"))
                .buildRequest()
                .sendRequest()
                .then().assertThat()
                .spec(responseAnalise());
    }

    public void allOfTheBoardsAssociatedWithMe() {

        boards = getAllBoards(
                requestBuilder()
                        .setMethod(Method.GET)
                        .setUrl(Dp.getProperty("pathToTrello"))
                        .setUrl(Dp.getProperty("allBoards"))
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .buildRequest()
                        .sendRequest());
        Assert.assertNotNull(boards);
    }

    public void testBoardsById() {
        for (Board board : boards) {
            Board actualBoard = GetOneBoardById(board.id);
            Assert.assertNotNull(actualBoard);
            Assert.assertEquals(actualBoard.id, board.id);
        }
    }

    public void createNewBoards(String name) {
        getBoard(
                requestBuilder()
                        .setMethod(Method.POST)
                        .setUrl(Dp.getProperty("pathToTrello"))
                        .setUrl(Dp.getProperty("oneBoard"))
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .setName(name)
                        .buildRequest()
                        .sendRequest());

        //Assert.assertNotNull(board);


        //response.then()
        //        .assertThat()
        //        .spec(responseAnalize());
    }

    public void deleteBoards(String id) {
        Board actual = getBoard(
                requestBuilder()
                        .setMethod(Method.DELETE)
                        .setUrl(Dp.getProperty("pathToTrello"))
                        .setUrl(Dp.getProperty("oneBoard"))
                        .setUrl(id)
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .buildRequest()
                        .sendRequest());
    }


    public Board GetOneBoardById(String id) {
            return getBoard(
                    requestBuilder()
                            .setMethod(Method.GET)
                            .setUrl(Dp.getProperty("pathToTrello"))
                            .setUrl(Dp.getProperty("oneBoard"))
                            .setUrl(id)
                            .setKey(Dp.getProperty("key"))
                            .setToken(Dp.getProperty("token"))
                            .buildRequest()
                            .sendRequest());
    }
}