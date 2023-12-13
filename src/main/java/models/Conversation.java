package models;

import java.util.Date;

public class Conversation {
    private int ID;
    private boolean isGroup;
    private Date createdAt;
    private Date updatedAt;

    public Conversation() {
    }

    public Conversation(int ID, boolean isGroup, Date createdAt, Date updatedAt) {
        this.ID = ID;
        this.isGroup = isGroup;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getID() {
        return ID;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}