package models;

import java.util.Date;

public class Message {
    private int ID;
    private int conversationID;
    private int senderID;
    private int receiverID;
    private String content;
    private Date sendTime;
    private boolean isRead;
    private Date attachment;

    public Message() {
    }

    public Message(int ID, int conversationID, int senderID, int receiverID, String content, Date sendTime, boolean isRead, Date attachment) {
        this.ID = ID;
        this.conversationID = conversationID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
        this.sendTime = sendTime;
        this.isRead = isRead;
        this.attachment = attachment;
    }

    public int getID() {
        return ID;
    }


    public int getConversationID() {
        return conversationID;
    }

    public void setConversationID(int conversationID) {
        this.conversationID = conversationID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Date getAttachment() {
        return attachment;
    }

    public void setAttachment(Date attachment) {
        this.attachment = attachment;
    }
}