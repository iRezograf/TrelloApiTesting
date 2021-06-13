package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import services.interfaces.ITrelloClass;
import tobjects.Board;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TrelloService implements ITrelloClass<Board> {

    private static Response response;
    private Method requestMethod;

    //BEGINNING OF BUILDER PATTERN
    private String cUrl;

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
            System.out.println(method);
            requestMethod = method;
            return this;
        }


        public ApiRequestBuilder setBaseUrl(String url) {

            cUrl = cUrl.concat(url);
            System.out.println(cUrl);
            return this;
        }

        public ApiRequestBuilder setAllBoards(String url) {
            cUrl = cUrl.concat(url);
            System.out.println(cUrl);
            return this;
        }

        public ApiRequestBuilder setOneBoard(String url) {
            cUrl = cUrl.concat(url);
            System.out.println(cUrl);
            return this;
        }

        public ApiRequestBuilder setKey(String key) {
            cUrl = cUrl.concat(key);
            System.out.println(cUrl);
            return this;
        }

        public ApiRequestBuilder setToken(String token) {
            cUrl = cUrl.concat(token);
            System.out.println(cUrl);
            return this;
        }

        public TrelloService buildRequest() {
            System.out.println("---------------");
            System.out.println(requestMethod);
            System.out.println(cUrl);
            System.out.println("---------------");
            return new TrelloService(requestMethod, cUrl);
        }
    }
    //ENDING OF BUILDER PATTERN


    public Response sendRequest() {
        return RestAssured
                .request(requestMethod , cUrl)
                .prettyPeek();
    }

    public static List<Board> getAllBoards(Response response){
        TrelloService.response = response;
        List<Board> answers = new Gson()
                .fromJson(response.asString().trim(), new TypeToken<List<Board>>() {
                }.getType());
        assertThat ("We expect to get one answer, but got " + answers.size(), answers, hasSize(1));
        return answers;
    }
}
