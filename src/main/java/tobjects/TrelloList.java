package tobjects;

public class TrelloList extends DataClass<TrelloList> {
    public String id, name, idBoard;
    public List<Card> cards = new ArrayList<>();
    public boolean closed, subscribed;
    public int pos;
}