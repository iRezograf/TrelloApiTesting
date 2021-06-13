package tobjects;

import java.util.ArrayList;
import java.util.List;

public class TrelloList  {
    public String id, name, idBoard;
    public List<Card> cards = new ArrayList<>();
    public boolean closed, subscribed;
    public int pos;
}