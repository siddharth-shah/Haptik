package co.siddharth.haptikgroupchat.data;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.siddharth.haptikgroupchat.chat.ChatViewModel;
import co.siddharth.haptikgroupchat.events.ItemChange;

/**
 * Created by siddharth on 22/11/16.
 */

public class GroupChatContainer {
    List<FavoriteChatItem> favoriteChatItems;
    static GroupChatContainer chatContainer = null;

    private GroupChatContainer() {
    }

    public static GroupChatContainer getInstance() {
        if (chatContainer == null) {
            return new GroupChatContainer();
        } else
            return chatContainer;
    }

    public static void instantiateGroupContainer() {
        chatContainer = new GroupChatContainer();

    }

    public List<FavoriteChatItem> getFavoriteChatItems() {
        return favoriteChatItems;
    }

    public void setFavoriteChatItems(List<FavoriteChatItem> favoriteChatItems) {
        this.favoriteChatItems = favoriteChatItems;
    }

    public void changeItemAt(int index, boolean isFavorite) {
        favoriteChatItems.get(index).setFavorite(isFavorite);
        EventBus.getDefault().post(new ItemChange(1));
    }

    public void setupContainer(List<ChatItem> chatItems) {

        List<FavoriteChatItem> favoriteChatItems = new ArrayList<>();
        for (ChatItem chatItem : chatItems) {
            favoriteChatItems.add(new FavoriteChatItem(chatItem));
        }
        this.setFavoriteChatItems(favoriteChatItems);
    }

    public List<ChatViewModel> getChatViewModels() {
        List<ChatViewModel> chatViewModels = new ArrayList<>();
        for (FavoriteChatItem favoriteChatItem : favoriteChatItems) {
            ChatItem chatItem = favoriteChatItem.getChatItem();
            boolean isFavorite = favoriteChatItem.isFavorite();
            Date date = favoriteChatItem.getChatItem().getTimestamp();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,''yy hh:mm aa");

            chatViewModels.add(new ChatViewModel(chatItem.getUsername(),
                    chatItem.getBody(), "" + sdf.format(date), isFavorite));
        }
        return chatViewModels;
    }
}
