package models;

public class User1 {
    private int ID;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private boolean isOnline;

    public User1() {
    }

    public User1(int ID, String username, String password, String nickname, String avatar, boolean isOnline) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.isOnline = isOnline;
    }

    public User1(int ID, String nickname, boolean isOnline) {
        this.ID = ID;
        this.nickname = nickname;
        this.isOnline = isOnline;
    }

    public User1(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}