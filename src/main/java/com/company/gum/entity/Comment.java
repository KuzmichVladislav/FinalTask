package com.company.gum.entity;

import java.time.LocalDateTime;

public class Comment extends AbstractEntity {
    private Integer userId;
    private String userName;
    private String userSurname;
    private String profileImage;
    private LocalDateTime commentDate;
    private String commentText;
    private Boolean active;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getUserId() != null ? !getUserId().equals(comment.getUserId()) : comment.getUserId() != null) return false;
        if (getUserName() != null ? !getUserName().equals(comment.getUserName()) : comment.getUserName() != null)
            return false;
        if (getUserSurname() != null ? !getUserSurname().equals(comment.getUserSurname()) : comment.getUserSurname() != null)
            return false;
        if (getProfileImage() != null ? !getProfileImage().equals(comment.getProfileImage()) : comment.getProfileImage() != null)
            return false;
        if (getCommentDate() != null ? !getCommentDate().equals(comment.getCommentDate()) : comment.getCommentDate() != null)
            return false;
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText()) : comment.getCommentText() != null)
            return false;
        return getActive() != null ? getActive().equals(comment.getActive()) : comment.getActive() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getUserSurname() != null ? getUserSurname().hashCode() : 0);
        result = 31 * result + (getProfileImage() != null ? getProfileImage().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userSurname='").append(userSurname).append('\'');
        sb.append(", profileImage='").append(profileImage).append('\'');
        sb.append(", registerDate=").append(commentDate);
        sb.append(", commentText='").append(commentText).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
