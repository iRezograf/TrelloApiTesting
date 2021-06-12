public class TrelloTests {
    private String createdBoardId, createdBoardId2, createdOrgId;

    @BeforeClass
    public void initService() {
        init(TrelloService.class);
        // Create organization to use.
        // It's impossible to create a board in case of no organizations exists
        Organization organization = generateOrganization();
        Organization createOrg = createOrganization(organization);
        createdOrgId = createOrg.id;
    }

    @Test
    public void createCardInBoard() {
        //Create board
        Board board = generateBoard();
        Board createdBoard = createBoard(board);
        createdBoardId = createdBoard.id;
        Board gotBoard = getBoard(createdBoardId);
        assertEquals(gotBoard.name, createdBoard.name, "Name of created board is incorrect");

        //Create list
        TrelloList tList = generateList(createdBoard);
        TrelloList createdList = createList(tList);

        //Create Card
        Card card = generateCard(createdBoard, createdList);
        Card createdCard = addNewCardToBoard(card);

        //Check that card was added
        Board cardBoard = getCardBoard(createdCard.id);
        assertEquals(cardBoard.name, board.name, "Card wasn't added to board");
    }

    @Test
    public void assignBoardToOrganization() {
        //Create board
        Board board = generateBoard();
        board.idOrganization = createdOrgId;
        Board createdBoard = createBoard(board);
        createdBoardId2 = createdBoard.id;

        //Check that organization contains created board
        List<Board> boards = getOrganizationBoards(createdOrgId);
        assertTrue(map(boards, b -> b.name).contains(board.name), "Board wasn't added to organization");
    }

    @AfterClass
    public void clearBoards() {
        deleteBoard(createdBoardId);
        deleteBoard(createdBoardId2);
        deleteOrg(createdOrgId);
    }
}
