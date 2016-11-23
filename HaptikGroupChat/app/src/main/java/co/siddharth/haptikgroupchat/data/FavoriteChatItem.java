package co.siddharth.haptikgroupchat.data;

/**
 * Created by siddharth on 22/11/16.
 */

public class FavoriteChatItem {
    ChatItem chatItem;
    boolean isFavorite;

    public FavoriteChatItem(ChatItem chatItem, boolean isFavorite) {
        this.chatItem = chatItem;
        this.isFavorite = isFavorite;
    }

    public FavoriteChatItem(ChatItem chatItem) {
        this.chatItem = chatItem;
        this.isFavorite = false;
    }

    public ChatItem getChatItem() {
        return chatItem;
    }

    public void setChatItem(ChatItem chatItem) {
        this.chatItem = chatItem;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
