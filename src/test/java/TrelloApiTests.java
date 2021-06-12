import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TrelloApiTests {

    @Test
    public void sendRequestAllBoards() {
        RestAssured
                .request(Method.GET ,"https://api.trello.com/1/members/me/boards?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4")
                .prettyPeek();
    }

    @Test
    public void sendRequestFirstBoards() {
        RestAssured
                .request(Method.GET ,"https://api.trello.com/1/boards/60b8e67dc3c9da37c1745311?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4")
                .prettyPeek();
    }

    @Test
    public void sendRequestSecondBoards() {
        RestAssured
                .request(Method.GET ,"https://api.trello.com/1/boards/60c39343e21e8c4a1ff621f2?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4")
                .prettyPeek();
    }

    @Test
    public void createNewBoards() {

        // This code sample uses the  'Unirest' library:
        // http://unirest.io/java.html
        // HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
        //        .queryString("key", "0471642aefef5fa1fa76530ce1ba4c85")
        //        .queryString("token", "9eb76d9a9d02b8dd40c2f3e5df18556c831d4d1fadbe2c45f8310e6c93b5c548")
        //        .queryString("name", "{name}")
        //        .asString();
        RestAssured.request(Method.POST, "https://api.trello.com/1/boards/?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4&name=Hello")
                .prettyPeek();
    }

    @Test
    public void getListBoard1() {
        RestAssured
                .request(Method.GET , "https://api.trello.com/1/boards/60c39a7822b9f07bd6b980af/lists?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4")
                .prettyPeek();
    }

    @Test
    public void getListBoard2() {
        RestAssured
                .request(Method.GET , "https://api.trello.com/1/boards/60c399d4cd09ed43575ee9d6/lists?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4")
                .prettyPeek();
    }

    @Test
    public void putTo() {
        RestAssured
                .request(Method.PUT , "https://api.trello.com/1/lists/60c39a7822b9f07bd6b980b0?key=15836217fb4d85ab9cd36070535d80ad&token=81c5a186bf748c41b18f0083ab281febea2cdb6a0bd35131f0a4f4e1378269c4&name=To do today")
                .prettyPeek();
    }

}
