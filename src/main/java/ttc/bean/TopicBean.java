package ttc.bean;

import java.util.ArrayList;

public class TopicBean implements Bean{
    private String topicId;
    private String name;
    private String createUserId;
    private String createUserName;
    private String updateDate;
    private String createDate;
    private ArrayList chat;


    /**
    * Returns value of topicId
    * @return
    */
    public String getTopicId() {
    	return topicId;
    }

    /**
    * Sets new value of topicId
    * @param
    */
    public void setTopicId(String topicId) {
    	this.topicId = topicId;
    }

    /**
    * Returns value of name
    * @return
    */
    public String getName() {
    	return name;
    }

    /**
    * Sets new value of name
    * @param
    */
    public void setName(String name) {
    	this.name = name;
    }

    /**
    * Returns value of createUserId
    * @return
    */
    public String getCreateUserId() {
    	return createUserId;
    }

    /**
    * Sets new value of createUserId
    * @param
    */
    public void setCreateUserId(String createUserId) {
    	this.createUserId = createUserId;
    }

    /**
    * Returns value of createUserName
    * @return
    */
    public String getCreateUserName() {
    	return createUserName;
    }

    /**
    * Sets new value of createUserName
    * @param
    */
    public void setCreateUserName(String createUserName) {
    	this.createUserName = createUserName;
    }

    /**
    * Returns value of updateDate
    * @return
    */
    public String getUpdateDate() {
    	return updateDate;
    }

    /**
    * Sets new value of updateDate
    * @param
    */
    public void setUpdateDate(String updateDate) {
    	this.updateDate = updateDate;
    }

    /**
    * Returns value of createDate
    * @return
    */
    public String getCreateDate() {
    	return createDate;
    }

    /**
    * Sets new value of createDate
    * @param
    */
    public void setCreateDate(String createDate) {
    	this.createDate = createDate;
    }

    /**
    * Returns value of chat
    * @return
    */
    public ArrayList getChat() {
    	return chat;
    }

    /**
    * Sets new value of chat
    * @param
    */
    public void setChat(ArrayList chat) {
    	this.chat = chat;
    }
}
