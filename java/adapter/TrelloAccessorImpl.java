package adapter;

import adapter.gson.SonarIssueListGsonAdapter;
import com.google.gson.Gson;
import dto.SonarIssueInfoDTO;
import dto.SonarIssueListDTO;
import dto.TrelloBoardProjectinfoDTO;
import dto.TrelloBoardProjectinfoListDTO;
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
//            String apiFormatted = String.format(api, "911fa1860f3964239841aeb435d913ba", "ATTAf9949277794fe1fc0af3b3aa3718e50b14bb49bd3c825eab1b632b736792161f07D7AC23");
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


}
