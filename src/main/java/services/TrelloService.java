package services;

import dataproviders.ParamNames;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import services.interfaces.ITrelloClass;
import tobjects.Board;

import java.util.HashMap;
import java.util.Map;

public class TrelloService implements ITrelloClass<Board> {

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
            requestMethod = method;
            return this;
        }


        public ApiRequestBuilder setBaseUrl(String url) {
            cUrl.concat(url);
            return this;
        }

        public ApRequestBuilder setAllBoards(String url) {
            parameters.put(ParamNames.GET_ALL_BOARDS, url);
            cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setOneBoard(String url) {
            parameters.put(ParamNames.GET_ONE_BOARD, url);
            cUrl.concat(url);
            return this;
        }

        public ApiRequestBuilder setKey(String key) {
            parameters.put(ParamNames.KEY, key);
            cUrl.concat(key);
            return this;
        }

        public ApiRequestBuilder setToken(String token) {
            parameters.put(ParamNames.TOKEN, token);
            cUrl.concat(token);
            return this;
        }

        public TrelloService buildRequest() {
            return new TrelloService(requestMethod, cUrl);
        }
    }
    //ENDING OF BUILDER PATTERN


    public Response sendRequest() {
        return RestAssured
                .request(requestMethod , cUrl)
                .prettyPeek();
    }
}
