package com.company.gum.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Comment extends AbstractEntity {

    private Integer userId;
    private String userName;
    private String userSurname;
    private byte[] photo;
    private String base64Image;
    private LocalDateTime commentDate;
    private String commentText;
    private Boolean active;

    private Comment(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setUserName(builder.userName);
        setUserSurname(builder.userSurname);
        setPhoto(builder.photo);
        setCommentDate(builder.commentDate);
        setCommentText(builder.commentText);
        setActive(builder.active);
        setBase64Image(builder.base64Image);
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userSurname='").append(userSurname).append('\'');
        sb.append(", commentDate=").append(commentDate);
        sb.append(", commentText='").append(commentText).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;

        Comment comment = (Comment) o;

        if (getUserId() != null ? !getUserId().equals(comment.getUserId()) : comment.getUserId() != null) return false;
        if (getUserName() != null ? !getUserName().equals(comment.getUserName()) : comment.getUserName() != null)
            return false;
        if (getUserSurname() != null ? !getUserSurname().equals(comment.getUserSurname()) : comment.getUserSurname() != null)
            return false;
        if (!Arrays.equals(getPhoto(), comment.getPhoto())) return false;
        if (getBase64Image() != null ? !getBase64Image().equals(comment.getBase64Image()) : comment.getBase64Image() != null)
            return false;
        if (getCommentDate() != null ? !getCommentDate().equals(comment.getCommentDate()) : comment.getCommentDate() != null)
            return false;
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText()) : comment.getCommentText() != null)
            return false;
        return getActive() != null ? getActive().equals(comment.getActive()) : comment.getActive() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getUserSurname() != null ? getUserSurname().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getPhoto());
        result = 31 * result + (getBase64Image() != null ? getBase64Image().hashCode() : 0);
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        return result;
    }

    /**
     * {@code Comment} builder static inner class.
     */
    public static final class Builder {

        private Integer id;
        private Integer userId;
        private String userName;
        private String userSurname;
        private byte[] photo;
        private LocalDateTime commentDate;
        private String commentText;
        private Boolean active;
        private String base64Image;

        public Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code userId} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code userId} to set
         * @return a reference to this Builder
         */
        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code userName} to set
         * @return a reference to this Builder
         */
        public Builder userName(String val) {
            userName = val;
            return this;
        }

        /**
         * Sets the {@code userSurname} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code userSurname} to set
         * @return a reference to this Builder
         */
        public Builder userSurname(String val) {
            userSurname = val;
            return this;
        }

        /**
         * Sets the {@code photo} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code photo} to set
         * @return a reference to this Builder
         */
        public Builder photo(byte[] val) {
            photo = val;
            return this;
        }

        /**
         * Sets the {@code commentDate} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code commentDate} to set
         * @return a reference to this Builder
         */
        public Builder commentDate(LocalDateTime val) {
            commentDate = val;
            return this;
        }

        /**
         * Sets the {@code commentText} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code commentText} to set
         * @return a reference to this Builder
         */
        public Builder commentText(String val) {
            commentText = val;
            return this;
        }

        /**
         * Sets the {@code active} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code active} to set
         * @return a reference to this Builder
         */
        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        /**
         * Sets the {@code base64Image} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code base64Image} to set
         * @return a reference to this Builder
         */
        public Builder base64Image(String val) {
            base64Image = val;
            return this;
        }

        /**
         * Returns a {@code Comment} built from the parameters previously set.
         *
         * @return a {@code Comment} built with parameters of this
         * {@code Comment.Builder}
         */
        public Comment build() {
            return new Comment(this);
        }
    }
}
