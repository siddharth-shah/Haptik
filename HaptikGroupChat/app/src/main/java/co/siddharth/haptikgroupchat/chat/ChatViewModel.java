package co.siddharth.haptikgroupchat.chat;

/**
 * Created by siddharth on 21/11/16.
 */

public class ChatViewModel {
    String userName;
    String textMessage;
    String time;
    boolean isFavorite;

    public ChatViewModel(String userName, String textMessage, String time, boolean isFavorite) {
        this.userName = userName;
        this.textMessage = textMessage;
        this.time = time;
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getUserName() {
        return userName;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public String getTime() {
        return time;
    }
}
