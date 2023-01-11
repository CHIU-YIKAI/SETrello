package dto;

import java.util.ArrayList;
import java.util.List;

public class TrelloListinfoDTO {
    private List<TrelloCardinfoDTO> cards;
    private String id;
    private String name;

    public TrelloListinfoDTO(){cards = new ArrayList<>();}
    public TrelloListinfoDTO(List<TrelloCardinfoDTO> TrelloCardinfoDTOList){this.cards=TrelloCardinfoDTOList;};

    public void setTrelloCardinfoDTOList(List<TrelloCardinfoDTO> TrelloCardinfoDTOList) {this.cards = TrelloCardinfoDTOList;}
    public List<TrelloCardinfoDTO> getTrelloCardinfoDTOList() {return cards;}
    public void addTrelloCardinfoDTO(TrelloCardinfoDTO TrelloCardinfoDTO){ this.cards.add(TrelloCardinfoDTO); }
    public String getId(){ return id;};
    public void setId(String id){ this.id = id;};
    public String getName(){ return name;};
    public void setName(String name){ this.name = name;};


}
