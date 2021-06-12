package services;

@ServiceDomain("${trello}")
@QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db")
@QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
public class TrelloService {
    public static final String BOARDS = "/boards";

    @ContentType(JSON)
    @GET(BOARDS)
    public static RestMethod boardsGet;

    @ContentType(JSON)
    @POST(BOARDS)
    public static RestDataMethod<Board> boardsPost;

    public static synchronized Board createBoard(Board board) {
        return boardsPost.postAsData(board);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public static RestMethod getBoardById;

    @ContentType(JSON)
    @GET("/boards/{board_id}")
    public RestMethod boardId;

    public static Board getBoard(String boardId) {
        return getBoardById.call(pathParams().add("board_id", boardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards/{short_card_id}")
    public static RestMethod getBoardCardById;

    @ContentType(JSON)
    @GET("/members/{user_name}/boards")
    public static RestMethod getAllMemberBoards;

    @ContentType(JSON)
    @GET("/members")
    public static RestMethod membersGet;

    @GET("/members/me")
    public static RestMethod membersMeGet;

    @ContentType(JSON)
    @DELETE("/cards")
    public static RestMethod deleteACardFromBoard;

    @ContentType(JSON)
    @POST("/cards")
    public static RestMethod addNewCardToBoard;

    public static Card addNewCardToBoard(Card card) {
        return addNewCardToBoard.body(card).callAsData(Card.class);
    }

    @ContentType(JSON)
    @GET("/cards/{id}/board")
    public static RestMethod getCardBoard;

    public static Board getCardBoard(String cardId) {
        return getCardBoard.call(pathParams().add("id", cardId)).getRaResponse().as(Board.class);
    }

    @ContentType(JSON)
    @GET("/boards/{board_id}/cards")
    public static RestMethod getBoardCardsList;

    @ContentType(JSON)
    @POST("/lists")
    public static RestMethod createList;

    public static synchronized TrelloList createList(TrelloList list) {
        return createList.post(list, TrelloList.class);
    }

    @ContentType(JSON)
    @POST("/cards/{card_id}/actions/comments")
    public static RestMethod postNewCommentToCard;

    @QueryParameter(name = "test", value = "test")
    @ContentType(JSON)
    @GET("/cards/{card_id}")
    public static RestMethod getCardByUniqueId;

    @ContentType(JSON)
    @POST("/organizations")
    public static RestMethod createOrganization;

    public static Organization createOrganization(Organization organization) {
        return createOrganization.post(organization, Organization.class);
    }

    @ContentType(JSON)
    @GET("/organizations/{id}/boards")
    public static RestMethod getOrganizationBoards;

    public static List<Board> getOrganizationBoards(String orgId) {
        return asList(getOrganizationBoards
                .call(pathParams().add("id", orgId))
                .getRaResponse()
                .as(Board[].class));
    }

    @ContentType(JSON)
    @DELETE("/boards/{board_id}")
    public static RestMethod deleteBoard;
    @ContentType(JSON)
    @DELETE("/organizations/{org_id}")
    public static RestMethod deleteOrg;

    public static RestResponse deleteBoard(String boardId) {
        return deleteBoard.call(pathParams().add("board_id", boardId));
    }

    public static RestResponse deleteOrg(String orgId) {
        return deleteOrg.call(pathParams().add("org_id", orgId));
    }
}
