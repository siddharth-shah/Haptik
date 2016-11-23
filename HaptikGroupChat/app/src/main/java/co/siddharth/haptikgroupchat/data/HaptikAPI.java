package co.siddharth.haptikgroupchat.data;

import co.siddharth.haptikgroupchat.data.ChatItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by siddharth on 22/11/16.
 */

public interface HaptikAPI {
    @GET("/android/test_data/")
    Call<ChatItemResponse> loadChat();
}
