package usecase;

import dto.TrelloBoardProjectinfoListDTO;
import dto.TrelloDetailnfoDTO;

public interface TrelloAccessor {
//    boolean isTrelloBoardValid();
//    TrelloBoardProjectinfoDTO getTrelloBoardInfo();
    TrelloBoardProjectinfoListDTO getTrelloBoardInfoList(String UserKey, String UserToken);
    TrelloDetailnfoDTO getTrelloDetailInfo(String BoardID, String UserKey, String UserToken);
    boolean createTrelloList(String BoardID, String ListName, String UserKey, String UserToken);
}
