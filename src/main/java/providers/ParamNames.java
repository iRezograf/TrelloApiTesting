package providers;

public class ParamNames {

    public static final String BASE_URL = "https://api.trello.com/";
    public static final String GET_ALL_BOARDS = "1/members/me/boards";
    public static final String GET_ONE_BOARD = "1/boards/";
    public static final String KEY = "?key=" + Dp.getProperty("key");
    public static final String TOKEN = "?token=" + Dp.getProperty("token");

}
