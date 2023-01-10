package dto;

import java.util.ArrayList;
import java.util.List;

public class TrelloBoardProjectinfoListDTO {
    private List<TrelloBoardProjectinfoDTO> TrelloBoardProjectinfoDTOList;
    private  boolean isSuccessful = false;
    private String responseMsg = "add board failed";

    public TrelloBoardProjectinfoListDTO(){TrelloBoardProjectinfoDTOList = new ArrayList<>();}
    public TrelloBoardProjectinfoListDTO(List<TrelloBoardProjectinfoDTO> TrelloBoardProjectinfoDTOList){this.TrelloBoardProjectinfoDTOList=TrelloBoardProjectinfoDTOList;};

    public void setTrelloBoardProjectinfoList(List<TrelloBoardProjectinfoDTO> TrelloBoardProjectinfoDTOList) {this.TrelloBoardProjectinfoDTOList = TrelloBoardProjectinfoDTOList;}
    public List<TrelloBoardProjectinfoDTO> getTrelloBoardProjectinfoList() {return TrelloBoardProjectinfoDTOList;}
    public void addTrelloBoardProjectinfoDTO(TrelloBoardProjectinfoDTO TrelloBoardProjectinfoDTO){ this.TrelloBoardProjectinfoDTOList.add(TrelloBoardProjectinfoDTO); }
    public boolean isSuccessful() {
        return isSuccessful;
    }
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getresponseMsg() {
        return responseMsg;
    }
    public void setresponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

}
