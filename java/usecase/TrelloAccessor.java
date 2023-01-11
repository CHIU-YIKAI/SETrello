package usecase;

import dto.TrelloBoardProjectinfoListDTO;
import dto.TrelloDetailnfoDTO;

public interface TrelloAccessor {
//    boolean isTrelloBoardValid();
//    TrelloBoardProjectinfoDTO getTrelloBoardInfo();
    TrelloBoardProjectinfoListDTO getTrelloBoardInfoList(String UserKey, String UserToken);
    TrelloDetailnfoDTO getTrelloDetailInfo(String BoardID, String UserKey, String UserToken);
}
