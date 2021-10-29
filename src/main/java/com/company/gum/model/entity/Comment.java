package com.company.gum.model.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * The Class Comment.
 *
 * @author Vladislav Kuzmich
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
    private String photoBase64Image;

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
     */
    public Comment() {
    }

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
        setPhotoBase64Image(builder.photoBase64Image);
    }

    /**
     * Gets the base 64 image.
     *
     * @return the base 64 image
     */
    public String getPhotoBase64Image() {
        return photoBase64Image;
    }

    /**
     * Sets the base 64 image.
     *
     * @param photoBase64Image the new base 64 image
     */
    public void setPhotoBase64Image(String photoBase64Image) {
        this.photoBase64Image = photoBase64Image;
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
     * @param o the o
     * @return the string
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
        if (getPhotoBase64Image() != null ? !getPhotoBase64Image().equals(comment.getPhotoBase64Image()) : comment.getPhotoBase64Image() != null) {
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
        result = 31 * result + (getCommentDate() != null ? getCommentDate().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", active=" + active +
                ", id=" + getId() +
                '}';
    }

    /**
     * The Class Builder.
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
        private String photoBase64Image;

        /**
         * Instantiates a new builder.
         */
        public Builder() {
            // Instantiates a new builder.
        }

        /**
         * Id.
         *
         * @param val the val
         * @return the builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * User id.
         *
         * @param val the val
         * @return the builder
         */
        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        /**
         * User name.
         *
         * @param val the val
         * @return the builder
         */
        public Builder userName(String val) {
            userName = val;
            return this;
        }

        /**
         * User surname.
         *
         * @param val the val
         * @return the builder
         */
        public Builder userSurname(String val) {
            userSurname = val;
            return this;
        }

        /**
         * Photo.
         *
         * @param val the val
         * @return the builder
         */
        public Builder photo(byte[] val) {
            photo = val;
            return this;
        }

        /**
         * Comment date.
         *
         * @param val the val
         * @return the builder
         */
        public Builder commentDate(LocalDateTime val) {
            commentDate = val;
            return this;
        }

        /**
         * Comment text.
         *
         * @param val the val
         * @return the builder
         */
        public Builder commentText(String val) {
            commentText = val;
            return this;
        }

        /**
         * Active.
         *
         * @param val the val
         * @return the builder
         */
        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        /**
         * Base 64 image.
         *
         * @param val the val
         * @return the builder
         */
        public Builder photoBase64Image(String val) {
            photoBase64Image = val;
            return this;
        }

        /**
         * Builds the.
         *
         * @return the comment
         */
        public Comment build() {
            return new Comment(this);
        }
    }
}