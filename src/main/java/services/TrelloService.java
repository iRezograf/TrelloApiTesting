package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Board;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.lessThan;

public class TrelloService {

    private final Method requestMethod;

    private final String cUrl;

    private TrelloService(Method method, String cUrl) {

        this.requestMethod = method;
        this.cUrl = cUrl;
    }

    public static ApiRequestBuilder requestBuilder() {
        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private Method requestMethod = Method.GET;
        private String cUrl = "";

        public ApiRequestBuilder setMethod (Method method){
            requestMethod = method;
            return this;
        }


        public ApiRequestBuilder setBaseUrl(String url) {
            cUrl = cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setAllBoards(String url) {
            cUrl = cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setOneBoard(String url) {
            cUrl = cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setId(String url) {
            cUrl = cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setKey(String key) {
            cUrl = cUrl.concat(key);
            return this;
        }

        public ApiRequestBuilder setToken(String token) {
            cUrl = cUrl.concat(token);
            return this;
        }

        public ApiRequestBuilder setName(String name) {
            cUrl = cUrl.concat(name);
            System.out.println(cUrl);
            return this;
        }

        public TrelloService buildRequest() {
            return new TrelloService(requestMethod, cUrl);
        }
    }

    public Response sendRequest() {
        return RestAssured
                .request(requestMethod , cUrl)
                .prettyPeek();
    }

    public static List<Board> getAllBoards(Response response){
        return new Gson()
                .fromJson(response.asString().trim(), new TypeToken<List<Board>>() {
                }.getType());
    }

    public static String getAllBoardsAsString(Response response){
        return new Gson()
                .fromJson(response.asString().trim(), new TypeToken<String>() {
                }.getType());
    }

    public static Board getBoard(Response response){
        return new Gson()
                .fromJson(response.asString().trim(), new TypeToken<Board>() {
                }.getType());
    }

    public static List<String> getStringResult(Response response) {
        return getAllBoards(response)
                .stream()
                .map(Board -> Board
                        .name)
                .collect(Collectors.toList());
    }

    public static ResponseSpecification responseAnalize() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectHeader("Access-Control-Allow-Methods","GET, PUT, POST, DELETE")
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

}
