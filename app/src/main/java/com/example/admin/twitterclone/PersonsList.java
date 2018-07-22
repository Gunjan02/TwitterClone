package com.example.admin.twitterclone;

public class PersonsList {
     private String username;
    private int image;
    private String message;

    public PersonsList( String username,int image) {
        this.image = image;
        this.username = username;
    }
    public PersonsList(String username,String message){
        this.username=username;
        this.message=message;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNewImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
