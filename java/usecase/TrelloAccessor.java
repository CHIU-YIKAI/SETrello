package usecase;

import dto.TrelloBoardProjectinfoDTO;
import dto.TrelloBoardProjectinfoListDTO;

public interface TrelloAccessor {
//    boolean isTrelloBoardValid();
//    TrelloBoardProjectinfoDTO getTrelloBoardInfo();
    TrelloBoardProjectinfoListDTO getTrelloBoardInfoList(String UserKey, String UserToken);
}
