package co.siddharth.haptikgroupchat.favoritesinfo;

/**
 * Created by siddharth on 22/11/16.
 */

public class FavoriteInfoModel {
    String userName;
    int totalConversations;
    int numOfFavoriteConversations;
    String imageUrl;

    public FavoriteInfoModel(String userName, int totalConversations, int numOfFavoriteConversations, String imageUrl) {
        this.userName = userName;
        this.totalConversations = totalConversations;
        this.numOfFavoriteConversations = numOfFavoriteConversations;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalConversations() {
        return totalConversations;
    }

    public void setTotalConversations(int totalConversations) {
        this.totalConversations = totalConversations;
    }

    public int getNumOfFavoriteConversations() {
        return numOfFavoriteConversations;
    }

    public void setNumOfFavoriteConversations(int numOfFavoriteConversations) {
        this.numOfFavoriteConversations = numOfFavoriteConversations;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

