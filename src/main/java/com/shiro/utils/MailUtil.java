package com.shiro.utils;

import java.util.Set;

public class MailUtil {

    /**
     * 邮件的主体
     */
    private String subject;

    /**
     * 邮件信息
     */

    private String message;

    /**
     * 收件人邮箱(可能有多个)
     */
    private Set<String>  receivers;

    public MailUtil() {
    }

    public MailUtil(String subject, String message, Set<String> receivers) {
        this.subject = subject;
        this.message = message;
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<String> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}