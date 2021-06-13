package services.interfaces;

import dataproviders.Dp;
import dataproviders.Method;

public interface ITrelloClass<T> {
    String KEY = Dp.getProperty("key");
    String TOKEN = Dp.getProperty("token");
    String BASE_URL = Dp.getProperty("pathToTrello");

    //T trelloRequest(Method method, String url);
}
