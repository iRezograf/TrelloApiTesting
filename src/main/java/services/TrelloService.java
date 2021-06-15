package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Board;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import providers.Dp;
import providers.ParamNames;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.lessThan;
import static providers.ParamNames.BASE_URL;

public class TrelloService {

    private final Method requestMethod;

    //BEGINNING OF BUILDER PATTERN
    private static Map<String, String> parameters;
    private String cUrl;

    private TrelloService(Method method, Map<String, String> parameters) {
        this.parameters = parameters;
        this.requestMethod = method;
    }

    public static ApiRequestBuilder requestBuilder() {
        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private Map<String, String> parameters = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ApiRequestBuilder setMethod (Method method){
            requestMethod = method;
            return this;
        }


        public ApiRequestBuilder setUrl(String url) {
            if (null != parameters.get(BASE_URL)){
                parameters.put(BASE_URL,
                    parameters.get(BASE_URL).concat(url));}
            else {
                parameters.put(BASE_URL, url);
            }
            return this;
        }

        public ApiRequestBuilder setKey(String key) {
            //if (url.isEmpty()) {parameters.put(ParamNames.GET_ONE_BOARD, ParamNames.GET_ONE_BOARD);}
            parameters.put(ParamNames.KEY, key);
            return this;
        }

        public ApiRequestBuilder setToken(String token) {
            //if (url.isEmpty()) {parameters.put(ParamNames.GET_ONE_BOARD, ParamNames.GET_ONE_BOARD);}
            parameters.put(ParamNames.TOKEN, token);
            return this;
        }

        public ApiRequestBuilder setName(String name) {
            //if (url.isEmpty()) {parameters.put(ParamNames.GET_ONE_BOARD, ParamNames.GET_ONE_BOARD);}
            parameters.put(ParamNames.NAME, name);
            return this;
        }

        public TrelloService buildRequest() {
            return new TrelloService(requestMethod, parameters);
        }
    }
    //ENDING OF BUILDER PATTERN


    //public Response sendRequest() {
    //    return RestAssured
    //            .request(requestMethod , cUrl)
    //            .prettyPeek();
    //}

    public Response sendRequest() {
        return RestAssured
                .given(requestAnalise()).log().all()
                .queryParams(parameters)
                .request(requestMethod, parameters.get(BASE_URL))
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

    public static RequestSpecification requestAnalise() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBaseUri(Dp.getProperty("pathToTrello"))
                .build();
    }


    public static ResponseSpecification responseAnalise() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectHeader("Access-Control-Allow-Methods","GET, PUT, POST, DELETE")
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

}


    /*
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

     */
