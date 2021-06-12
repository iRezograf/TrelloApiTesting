package utils;

import tobjects.Board;

public class TrelloDataGen {
public static Board generateBoard() {
        return new Board().set(b -> b.name = "JDI Test Board number " + new Random().nextInt(Integer.MAX_VALUE));
        }

public static TrelloList generateList(Board board) {
        return new TrelloList().set(tList -> {
        tList.name = "JDI Test List number " + new Random().nextInt(Integer.MAX_VALUE);
        tList.idBoard = board.id;
        });
        }

public static Card generateCard(Board board, TrelloList tList) {
        return new Card().set(c -> {
        c.name = "JDI Test Card number " + new Random().nextInt(Integer.MAX_VALUE);
        c.idBoard = board.id;
        c.idList = tList.id;
        });
        }

public static Organization generateOrganization() {
        return new Organization().set(o -> {
        o.name = "JDI Org " + new Random().nextInt(Integer.MAX_VALUE);
        o.displayName = o.name;
        });
        }
        }
}