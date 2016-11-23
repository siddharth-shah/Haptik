package co.siddharth.haptikgroupchat.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by siddharth on 22/11/16.
 */

public class ChatItem {

    String username;
    @SerializedName("Name")
    String name;
    String body;
    @SerializedName("message-time")
    Date timestamp;
    @SerializedName("image-url")
    String imageUrl;

    public ChatItem(String username, String name, String body, Date timestamp, String imageUrl) {
        this.username = username;
        this.name = name;
        this.body = body;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

