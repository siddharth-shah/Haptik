package co.siddharth.haptikgroupchat.chat;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.siddharth.haptikgroupchat.HaptikApplication;
import co.siddharth.haptikgroupchat.data.ChatItem;
import co.siddharth.haptikgroupchat.data.ChatItemResponse;
import co.siddharth.haptikgroupchat.data.GroupChatContainer;
import co.siddharth.haptikgroupchat.data.HaptikAPI;
import co.siddharth.haptikgroupchat.events.FetchData;
import co.siddharth.haptikgroupchat.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by siddharth on 23/11/16.
 */

public class ChatPresenter {

    private GroupChatContainer groupChatContainer;


    public void loadChats() {
        HaptikApplication.getRetrofit().create(HaptikAPI.class).loadChat().enqueue(new Callback<ChatItemResponse>() {
            @Override
            public void onResponse(Call<ChatItemResponse> call, Response<ChatItemResponse> response) {
                ChatItemResponse chatItemResponse = response.body();
                if (chatItemResponse == null || chatItemResponse.getCount() == 0) {
                    EventBus.getDefault().post(new FetchData(Constants.NO_DATA));
                }
                List<ChatItem> chatItems = sortChatItemsByTime(chatItemResponse);
                groupChatContainer = GroupChatContainer.getInstance();
                groupChatContainer.setupContainer(chatItems);
                EventBus.getDefault().post(new FetchData(Constants.FETCH_DATA_SUCCESS));
            }

            @Override
            public void onFailure(Call<ChatItemResponse> call, Throwable t) {
                if(t != null && t.getCause().getLocalizedMessage().
                        toLowerCase().contains("network unreachable")) ;
                EventBus.getDefault().post(new FetchData(Constants.NO_INTERNET));

            }
        });
    }

    public List<ChatViewModel> getChatItems() {
        return groupChatContainer.getChatViewModels();

    }

    @NonNull
    private List<ChatItem> sortChatItemsByTime(ChatItemResponse chatItemResponse) {
        List<ChatItem> chatItems = chatItemResponse.getChatItemList();
        Collections.sort(chatItems, new Comparator<ChatItem>() {
            @Override
            public int compare(ChatItem chatItem, ChatItem t1) {
                return chatItem.getTimestamp().compareTo(t1.getTimestamp());
            }
        });
        return chatItems;
    }

    public void changeItemAt(int position, ChatViewModel chatViewModel) {

        groupChatContainer.changeItemAt(position, chatViewModel.isFavorite());
    }
}
