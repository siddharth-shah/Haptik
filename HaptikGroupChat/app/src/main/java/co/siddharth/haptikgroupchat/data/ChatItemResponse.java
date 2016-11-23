package co.siddharth.haptikgroupchat.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siddharth on 22/11/16.
 */

public class ChatItemResponse {
    @SerializedName("messages")
    List<ChatItem> chatItemList;
    @SerializedName("count")
    int count;

    public ChatItemResponse(List<ChatItem> chatItemList, int count) {
        this.chatItemList = chatItemList;
        this.count = count;
    }

    public List<ChatItem> getChatItemList() {
        return chatItemList;
    }

    public void setChatItemList(List<ChatItem> chatItemList) {
        this.chatItemList = chatItemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
