package adapter.gson;

import java.util.List;

public class TrelloDetailGsonAdapter {

    protected String id;
    protected String name;
    protected List<CardInfo> cards;

    protected class CardInfo {
        protected String id;
        protected String name;
        protected String description;

        public String getid() { return id; }
        public String getname() { return name;}
        public String getdescription() { return description;}
    }
    public List<CardInfo> getCards() {return cards;}
    public String getid() { return id; }
    public String getname() { return name;}
}


