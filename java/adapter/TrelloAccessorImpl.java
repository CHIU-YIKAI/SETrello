package adapter;

import adapter.gson.TrelloDetailGsonAdapter;

import com.google.gson.Gson;
import dto.TrelloBoardProjectinfoDTO;
import dto.TrelloBoardProjectinfoListDTO;
import dto.TrelloCardinfoDTO;
import dto.TrelloListinfoDTO;
import dto.TrelloDetailnfoDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONArray;
import usecase.TrelloAccessor;
import usecase.PostJSONWithHttpURLConnection;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class TrelloAccessorImpl implements TrelloAccessor {
    private PostJSONWithHttpURLConnection postRequester;
    private HttpsRequester getRequester;

    private String clientId;
    private String clientSecret;

    @Override
    public TrelloBoardProjectinfoListDTO getTrelloBoardInfoList(String UserKey, String UserToken) {
        getRequester = new HttpsRequester();
        TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO = new TrelloBoardProjectinfoListDTO();
        TrelloBoardProjectinfoListDTO.setSuccessful(false);
        JSONArray response = null;
        try {
            String api = "https://api.trello.com/1/members/me/boards?key=%s&token=%s";
            String apiFormatted = String.format(api, UserKey, UserToken);
            response = getRequester.httpsGet(apiFormatted);
            TrelloBoardProjectinfoListDTO = parseUSerTrlloBoardList(response);
        } catch (IOException e) {
            TrelloBoardProjectinfoListDTO.setSuccessful(false);
            TrelloBoardProjectinfoListDTO.setresponseMsg("invalid credential");
            return TrelloBoardProjectinfoListDTO;
        }
        TrelloBoardProjectinfoListDTO.setSuccessful(true);
        TrelloBoardProjectinfoListDTO.setresponseMsg("success");
        return TrelloBoardProjectinfoListDTO;
    }
    private TrelloBoardProjectinfoListDTO parseUSerTrlloBoardList(JSONArray response) {

        TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO = new TrelloBoardProjectinfoListDTO();
        TrelloBoardProjectinfoListDTO.setTrelloBoardProjectinfoList(Arrays.asList(new Gson().fromJson(response.toString() , TrelloBoardProjectinfoDTO[].class)));
        return TrelloBoardProjectinfoListDTO;
    }
    @Override
    public TrelloDetailnfoDTO getTrelloDetailInfo(String BoardID, String UserKey, String UserToken) {
        getRequester = new HttpsRequester();
        TrelloDetailnfoDTO TrelloDetailnfoDTO = new TrelloDetailnfoDTO();
        TrelloDetailnfoDTO.setSuccessful(false);
        JSONArray response = null;
        try {
            String api = "https://api.trello.com/1/boards/%s/lists/?key=%s&token=%s&cards=all";
            String apiFormatted = String.format(api, BoardID, UserKey, UserToken);
            response = getRequester.httpsGet(apiFormatted);
            System.out.println(response.toString());
            TrelloDetailnfoDTO = parseTrelloDetailnfo(response);
        } catch (IOException e) {
            TrelloDetailnfoDTO.setSuccessful(false);
//            TrelloDetailnfoDTO.setresponseMsg("invalid credential");
            return TrelloDetailnfoDTO;
        }
        TrelloDetailnfoDTO.setSuccessful(true);
//        TrelloDetailnfoDTO.setresponseMsg("success");
        for(TrelloListinfoDTO li : TrelloDetailnfoDTO.getTrelloListinfoDTOList()) {
            System.out.println(li.getName());
            for (TrelloCardinfoDTO ca : li.getTrelloCardinfoDTOList()){
                System.out.println("    "+ca.getName());
            }
        }
        return TrelloDetailnfoDTO;
    }
    private TrelloDetailnfoDTO parseTrelloDetailnfo(JSONArray response) {

        TrelloDetailnfoDTO TrelloDetailnfoDTO = new TrelloDetailnfoDTO();
        TrelloDetailnfoDTO.setTrelloListinfoDTOList(Arrays.asList(new Gson().fromJson(response.toString() , TrelloListinfoDTO[].class)));
        return TrelloDetailnfoDTO;
    }

}
