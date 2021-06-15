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

        check200Ok();
        allOfTheBoardsAssociatedWithMe();
        testBoardsById();
        Board board = createNewBoards();

        deleteBoards(board.id);

    }

    public void check200Ok() {
        requestBuilder()
                .setMethod(Method.GET)
                .setBaseUrl(Dp.getProperty("pathToTrello"))
                .setAllBoards(Dp.getProperty("allBoards"))
                .setKey(Dp.getProperty("key"))
                .setToken(Dp.getProperty("token"))
                .buildRequest()
                .sendRequest()
                .then().assertThat()
                .spec(responseAnalize());
    }


    public void allOfTheBoardsAssociatedWithMe() {

        boards = getAllBoards(
                requestBuilder()
                        .setMethod(Method.GET)
                        .setBaseUrl(Dp.getProperty("pathToTrello"))
                        .setAllBoards(Dp.getProperty("allBoards"))
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


    public Board createNewBoards() {
        //RestAssured.request(Method.POST, "https://api.trello.com"
        //        + "/1/boards/"
        //        + "?key=15836217fb4d85ab9cd36070535d80ad
        //        +  &token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4"
        //        + "&name=Hello")
        //        .prettyPeek();
        Board board = getBoard(
                requestBuilder()
                        .setMethod(Method.POST)
                        .setBaseUrl(Dp.getProperty("pathToTrello"))
                        .setAllBoards(Dp.getProperty("oneBoard"))
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .setName(Dp.getProperty("boardName"))
                        .buildRequest()
                        .sendRequest());

        Assert.assertNotNull(board);


        //response.then()
        //        .assertThat()
        //        .spec(responseAnalize());
        return board;
    }


    public void deleteBoards(String id) {
        //DELETE \
        //  --url 'https://api.trello.com
        //  /1/boards/
        //  {id}
        //  ?key=0471642aefef5fa1fa76530ce1ba4c85&token=9eb76d9a9d02b8dd40c2f3e5df18556c831d4d1fadbe2c45f8310e6c93b5c548'
        Board actual = getBoard(
                requestBuilder()
                        .setMethod(Method.DELETE)
                        .setBaseUrl(Dp.getProperty("pathToTrello"))
                        .setAllBoards(Dp.getProperty("oneBoard"))
                        .setId(id)
                        .setKey(Dp.getProperty("key"))
                        .setToken(Dp.getProperty("token"))
                        .buildRequest()
                        .sendRequest());
    }


    public Board GetOneBoardById(String id) {
            return  getBoard(
                    requestBuilder()
                            .setMethod(Method.GET)
                            .setBaseUrl(Dp.getProperty("pathToTrello"))
                            .setOneBoard(Dp.getProperty("oneBoard"))
                            .setId(id)
                            .setKey(Dp.getProperty("key"))
                            .setToken(Dp.getProperty("token"))
                            .buildRequest()
                            .sendRequest());
    }
}