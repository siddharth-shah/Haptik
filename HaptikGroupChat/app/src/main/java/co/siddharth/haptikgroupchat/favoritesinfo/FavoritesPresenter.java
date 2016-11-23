package co.siddharth.haptikgroupchat.favoritesinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.siddharth.haptikgroupchat.data.FavoriteChatItem;
import co.siddharth.haptikgroupchat.data.GroupChatContainer;
import co.siddharth.haptikgroupchat.events.FetchData;

/**
 * Created by siddharth on 23/11/16.
 */

public class FavoritesPresenter {
    GroupChatContainer groupChatContainer;

    public List<FavoriteInfoModel> getFavoriteInfo() {
        groupChatContainer = GroupChatContainer.getInstance();
        Map<String, FavoriteInfoModel> favoriteMap = new HashMap<>();
        List<FavoriteChatItem> favoriteChatItems = groupChatContainer.getFavoriteChatItems();
        for (FavoriteChatItem favoriteChatItem : favoriteChatItems) {
            String username = favoriteChatItem.getChatItem().getUsername();
            if (favoriteMap.containsKey(username)) {
                FavoriteInfoModel favoriteInfoModel = favoriteMap.get(username);
                convAndFavCalculation(favoriteChatItem, favoriteInfoModel);
                favoriteMap.put(username, favoriteInfoModel);
            } else {
                FavoriteInfoModel favoriteInfoModel = new FavoriteInfoModel(username, 0, 0, favoriteChatItem.getChatItem().getImageUrl());
                convAndFavCalculation(favoriteChatItem, favoriteInfoModel);
                favoriteMap.put(username, favoriteInfoModel);
                List<FavoriteChatItem> userChatItemList = new ArrayList<>();
                userChatItemList.add(favoriteChatItem);
                favoriteMap.put(username, favoriteInfoModel);
            }

        }

        ArrayList<FavoriteInfoModel> favoriteInfoModels = new ArrayList<>(favoriteMap.values());
        return favoriteInfoModels;
    }

    private void convAndFavCalculation(FavoriteChatItem favoriteChatItem, FavoriteInfoModel favoriteInfoModel) {
        int x = favoriteInfoModel.getTotalConversations();
        favoriteInfoModel.setTotalConversations(x + 1);
        if (favoriteChatItem.isFavorite()) {
            int y = favoriteInfoModel.getNumOfFavoriteConversations();
            favoriteInfoModel.setNumOfFavoriteConversations(y + 1);
        }
    }
}
