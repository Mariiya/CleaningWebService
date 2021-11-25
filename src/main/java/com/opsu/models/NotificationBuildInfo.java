package com.opsu.models;
/**
 * Class notification
 * @author group 183
 * @version 2.1
 */
public class NotificationBuildInfo {
    /** field notification's title*/
    private String title;
    /** field notification's body*/
    private String body;

    /**
     * constructor- create new notification
     * @param title notification's title
     * @param body notification's body
     */
    public NotificationBuildInfo(String title, String body) {
        this.title = title;
        this.body = body;
    }
    /** @return   notification's title*/
    public String getTitle() {
        return title;
    }
    /** @param title notification's title*/
    public void setTitle(String title) {
        this.title = title;
    }
    /** @return notification's body*/
    public String getBody() {
        return body;
    }
   /** @param body notification's body*/
    public void setBody(String body) {
        this.body = body;
    }
}
