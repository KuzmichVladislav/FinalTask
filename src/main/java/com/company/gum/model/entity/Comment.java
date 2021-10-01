package com.company.gum.model.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Comment.
 */
public class Comment extends AbstractEntity {

    /**
     * The user id.
     */
    private Integer userId;

    /**
     * The user name.
     */
    private String userName;

    /**
     * The user surname.
     */
    private String userSurname;

    /**
     * The photo.
     */
    private byte[] photo;

    /**
     * The base 64 image.
     */
    private String base64Image;

    /**
     * The comment date.
     */
    private LocalDateTime commentDate;

    /**
     * The comment text.
     */
    private String commentText;

    /**
     * The active.
     */
    private Boolean active;

    /**
     * Instantiates a new comment.
     *
     * @param builder the builder
     */
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

    /**
     * Gets the base 64 image.
     *
     * @return the base 64 image
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * Sets the base 64 image.
     *
     * @param base64Image the new base 64 image
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user surname.
     *
     * @return the user surname
     */
    public String getUserSurname() {
        return userSurname;
    }

    /**
     * Sets the user surname.
     *
     * @param userSurname the new user surname
     */
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    /**
     * Gets the photo.
     *
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets the photo.
     *
     * @param photo the new photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * Gets the comment date.
     *
     * @return the comment date
     */
    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    /**
     * Sets the comment date.
     *
     * @param commentDate the new comment date
     */
    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * Gets the comment text.
     *
     * @return the comment text
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment text.
     *
     * @param commentText the new comment text
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets the active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Comment{"
                + "userId=" + userId
                + ", userName='" + userName + '\''
                + ", userSurname='" + userSurname + '\''
                + ", photo=" + Arrays.toString(photo)
                + ", base64Image='" + base64Image + '\''
                + ", commentDate=" + commentDate
                + ", commentText='" + commentText + '\''
                + ", active=" + active
                + '}';
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Comment comment = (Comment) o;

        if (getUserId() != null ? !getUserId().equals(comment.getUserId()) : comment.getUserId() != null) {
            return false;
        }
        if (getUserName() != null ? !getUserName().equals(comment.getUserName()) : comment.getUserName() != null) {
            return false;
        }
        if (getUserSurname() != null ? !getUserSurname().equals(comment.getUserSurname()) : comment.getUserSurname() != null) {
            return false;
        }
        if (!Arrays.equals(getPhoto(), comment.getPhoto())) {
            return false;
        }
        if (getBase64Image() != null ? !getBase64Image().equals(comment.getBase64Image()) : comment.getBase64Image() != null) {
            return false;
        }
        if (getCommentDate() != null ? !getCommentDate().equals(comment.getCommentDate()) : comment.getCommentDate() != null) {
            return false;
        }
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText()) : comment.getCommentText() != null) {
            return false;
        }
        return getActive() != null ? getActive().equals(comment.getActive()) : comment.getActive() == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
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

        /**
         * The id.
         */
        private Integer id;

        /**
         * The user id.
         */
        private Integer userId;

        /**
         * The user name.
         */
        private String userName;

        /**
         * The user surname.
         */
        private String userSurname;

        /**
         * The photo.
         */
        private byte[] photo;

        /**
         * The comment date.
         */
        private LocalDateTime commentDate;

        /**
         * The comment text.
         */
        private String commentText;

        /**
         * The active.
         */
        private Boolean active;

        /**
         * The base 64 image.
         */
        private String base64Image;

        /**
         * Instantiates a new builder.
         */
        public Builder() {
            // Instantiates a new builder.
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
