package dto;

import java.util.ArrayList;
import java.util.List;

public class TrelloDetailnfoDTO {
    private List<TrelloListinfoDTO> TrelloListinfoDTOList;
    private String id;
    private String name;

    private  boolean isSuccessful = false;
    public TrelloDetailnfoDTO(){TrelloListinfoDTOList = new ArrayList<>();}
    public TrelloDetailnfoDTO(List<TrelloListinfoDTO> TrelloListinfoDTOList){this.TrelloListinfoDTOList=TrelloListinfoDTOList;};

    public void setTrelloListinfoDTOList(List<TrelloListinfoDTO> TrelloListinfoDTOList) {this.TrelloListinfoDTOList = TrelloListinfoDTOList;}
    public List<TrelloListinfoDTO> getTrelloListinfoDTOList() {return TrelloListinfoDTOList;}
    public void addTrelloListinfoDTO(TrelloListinfoDTO TrelloListinfoDTO){ this.TrelloListinfoDTOList.add(TrelloListinfoDTO); }
    public String getId(){ return id;};
    public void setId(String id){ this.id = id;};
    public String getName(){ return name;};
    public void setName(String name){ this.name = name;};
    public boolean isSuccessful() {
        return isSuccessful;
    }
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

}
