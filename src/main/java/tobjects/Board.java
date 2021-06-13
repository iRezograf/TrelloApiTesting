package tobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Board  {
    public String id, name, desc, idOrganization, url, shortUrl, shortLink;
    public boolean closed, pinned, invited, subscribed;
    public Map<String, String> labelNames;
    public List<String> invitations, powerUps;
    //public List<Membership> memberships;
    public Prefs prefs;
    public Date dateLastActivity, dateLastView;
    public List<TrelloList> lists = new ArrayList<>();

    //@JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Prefs {
        public String permissionLevel, voting, comments, invitations, cardAging;
        public boolean selfJoin, cardCovers, canBePublic, canBeOrg, canBePrivate, canInvite, calendarFeedEnabled;
    }
}