package tobjects;

public class Card extends DataClass<Card> {
    public String id, name, idList, desc, url, idBoard, idShort, idAttachmentCover,
            shortLink, shortUrl;
    public boolean closed, manualCoverAttachment, subscribed;
    public Date due, dateLastActivity;
    public List<String> idMembers, idChecklists, idMembersVoted;
    public int pos;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CardCheckItem extends DataClass<CardCheckItem> {
        public String idCheckItem, state;
    }
}