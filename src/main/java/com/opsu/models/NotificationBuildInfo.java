package com.opsu.models;
/**
 * In this class we store information about notification
 * @author group 183
 * @version 2.1
 */
public class NotificationBuildInfo {
    /** field notification's title*/
    private String title;
    /** field notification's body*/
    private String body;

    /**
     * This constructor creates new  notification
     * @param title notification's title
     * @param body notification's body
     */
    public NotificationBuildInfo(String title, String body) {
        this.title = title;
        this.body = body;
    }
    /** receives information about notification's title
     * @return   notification's title*/
    public String getTitle() {
        return title;
    }
    /**inserts information  about notification's title
     * @param title notification's title*/
    public void setTitle(String title) {
        this.title = title;
    }
    /** receives information about notification's body
     * @return notification's body*/
    public String getBody() {
        return body;
    }
   /** inserts information  about notification's body
    * @param body notification's body*/
    public void setBody(String body) {
        this.body = body;
    }
}
