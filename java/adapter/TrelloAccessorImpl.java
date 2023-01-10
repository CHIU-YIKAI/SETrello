package adapter;

import adapter.gson.SonarIssueListGsonAdapter;
import adapter.gson.UserTrelloBoardListGsonAdapter;
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

    public TrelloAccessorImpl() {
//        clientId = System.getenv("Github_Client_Id");
//        client_secret = System.getenv("Github_Client_Secret");
        clientId = "27ea0af1aeabd39a7d5d";
        clientSecret = "73982553f4ca36536646a033e1595dc126694b2e";


    }


    @Override
    public TrelloBoardProjectinfoListDTO getTrelloBoardInfoList(String UserKey, String UserToken, String BoardName) {
        getRequester = new HttpsRequester();
//        getRequester.addHTTPSGetProperty("Content-Type", "application/json");
//        getRequester.addHTTPSGetProperty("Authorization", "Bearer " + token);
        TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO = new TrelloBoardProjectinfoListDTO();
        JSONArray response = null;
        try {
            String api = "https://api.trello.com/1/members/me/boards?key=%s&token=%s";
//            String apiFormatted = String.format(api, "911fa1860f3964239841aeb435d913ba", "ATTAf9949277794fe1fc0af3b3aa3718e50b14bb49bd3c825eab1b632b736792161f07D7AC23");
            String apiFormatted = String.format(api, UserKey, UserToken);
//          response = getRequester.httpsGet(apiFormatted).getJSONObject(0);
            response = getRequester.httpsGet(apiFormatted);

//            System.out.println("addproject response");
//            System.out.println(response.toString());
//            System.out.println("addproject response end");
            TrelloBoardProjectinfoListDTO = parseUSerTrlloBoardList(response);
        } catch (IOException e) {
            TrelloBoardProjectinfoListDTO.setSuccessful(false);
            TrelloBoardProjectinfoListDTO.setresponseMsg(e.getMessage());
            return TrelloBoardProjectinfoListDTO;
        }
//        System.out.println("addproject response");
//        System.out.println(TrelloBoardProjectinfoListDTO.getTrelloBoardProjectinfoList());
//        System.out.println("addproject response end");
//        githubUserDTO.setId(response.get("id").toString());
//        githubUserDTO.setName(response.get("login").toString());
//        githubUserDTO.setAccount(response.get("email").toString());
//        githubUserDTO.setAvatarUrl(response.get("avatar_url").toString());
        TrelloBoardProjectinfoListDTO.setSuccessful(true);
//        TrelloBoardProjectinfoListDTO.setresponseMsg("success");
        return TrelloBoardProjectinfoListDTO;
    }
    private TrelloBoardProjectinfoListDTO parseUSerTrlloBoardList(JSONArray response) {

        TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO = new TrelloBoardProjectinfoListDTO();
        TrelloBoardProjectinfoListDTO.setTrelloBoardProjectinfoList(Arrays.asList(new Gson().fromJson(response.toString() , TrelloBoardProjectinfoDTO[].class)));

//        for ( TrelloBoardProjectinfoDTO TrelloBoardProjectinfoDTO : TrelloBoardProjectinfoListDTO.getTrelloBoardProjectinfoList()) {
////            TrelloBoardProjectinfoDTO TrelloBoardProjectinfoDTO = new TrelloBoardProjectinfoDTO();
////            TrelloBoardProjectinfoDTO.setId(BoardInfo.getID());
////            TrelloBoardProjectinfoDTO.setName(BoardInfo.getName());
////
////            TrelloBoardProjectinfoListDTO.addTrelloBoardProjectinfoDTO(TrelloBoardProjectinfoDTO);
//            System.out.println(TrelloBoardProjectinfoDTO.getId());
//            System.out.println(TrelloBoardProjectinfoDTO.getName());
//            System.out.println("board id list ends");
//        }
        return TrelloBoardProjectinfoListDTO;
    }

    private TrelloBoardProjectinfoListDTO BoardNameMatching(TrelloBoardProjectinfoListDTO TrelloBoardProjectinfoListDTO, String BoardName) {

        for ( TrelloBoardProjectinfoDTO TrelloBoardProjectinfoDTO : TrelloBoardProjectinfoListDTO.getTrelloBoardProjectinfoList()) {
            if( BoardName==TrelloBoardProjectinfoDTO.getName()){
                System.out.println(TrelloBoardProjectinfoDTO.getId());
                System.out.println(TrelloBoardProjectinfoDTO.getName());
                TrelloBoardProjectinfoListDTO.setresponseMsg(TrelloBoardProjectinfoDTO.getId());
            }

        }
        return TrelloBoardProjectinfoListDTO;
    }
}
