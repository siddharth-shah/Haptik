package co.siddharth.haptikgroupchat.events;

/**
 * Created by siddharth on 22/11/16.
 */

public class FetchData {
    int status;

    public FetchData(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
