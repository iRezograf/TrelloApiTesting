package tobjects;

import java.util.Date;
import java.util.List;

public class Card {
    public String id, name, idList, desc, url, idBoard, idShort, idAttachmentCover,
            shortLink, shortUrl;
    public boolean closed, manualCoverAttachment, subscribed;
    public Date due, dateLastActivity;
    public List<String> idMembers, idChecklists, idMembersVoted;
    public int pos;

    //@JsonIgnoreProperties(ignoreUnknown = true)
    public static class CardCheckItem {
        public String idCheckItem, state;
    }
}