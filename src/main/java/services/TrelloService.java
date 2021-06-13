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
    private Map<String, String> mapUrl;
    private String cUrl;

    private TrelloService(Method method, Map<String, String> parameters) {
        this.mapUrl = parameters;
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


        public ApiRequestBuilder setBaseUrl(String url) {
            parameters.put(ParamNames.BASE_URL, url);
            return this;
        }

        public ApiRequestBuilder setAllBoards(String url) {
            //if (url.isEmpty()) {parameters.put(ParamNames.GET_ALL_BOARDS, ParamNames.GET_ALL_BOARDS);}
            parameters.put(ParamNames.GET_ALL_BOARDS, url);
            return this;
        }

        public ApiRequestBuilder setOneBoard(String url) {
            //if (url.isEmpty()) {parameters.put(ParamNames.GET_ONE_BOARD, ParamNames.GET_ONE_BOARD);}
            parameters.put(ParamNames.GET_ONE_BOARD, url);
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

        public TrelloService buildRequest() {
            return new TrelloService(requestMethod, parameters);
        }
    }
    //ENDING OF BUILDER PATTERN


    public Response sendRequest() {
        return RestAssured
                .request(requestMethod , cUrl)
                .prettyPeek();
    }
}
