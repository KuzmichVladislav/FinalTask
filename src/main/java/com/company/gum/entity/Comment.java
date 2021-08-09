package com.company.gum.entity;

import java.time.LocalDateTime;

public class Comment extends AbstractEntity {
    private Integer clientId;
    private String clientName;
    private String clientLastName;
    private String clientProfileImagePath;
    private Integer trainerId;
    private String trainerName;
    private String trainerLastName;
    private LocalDateTime registerDate;
    private String commentText;
    private Boolean active;

    public Comment() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientProfileImagePath() {
        return clientProfileImagePath;
    }

    public void setClientProfileImagePath(String clientProfileImagePath) {
        this.clientProfileImagePath = clientProfileImagePath;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
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

        if (getClientId() != null ? !getClientId().equals(comment.getClientId()) : comment.getClientId() != null)
            return false;
        if (getClientName() != null ? !getClientName().equals(comment.getClientName()) : comment.getClientName() != null)
            return false;
        if (getClientLastName() != null ? !getClientLastName().equals(comment.getClientLastName()) : comment.getClientLastName() != null)
            return false;
        if (getClientProfileImagePath() != null ? !getClientProfileImagePath().equals(comment.getClientProfileImagePath()) : comment.getClientProfileImagePath() != null)
            return false;
        if (getTrainerId() != null ? !getTrainerId().equals(comment.getTrainerId()) : comment.getTrainerId() != null)
            return false;
        if (getTrainerName() != null ? !getTrainerName().equals(comment.getTrainerName()) : comment.getTrainerName() != null)
            return false;
        if (getTrainerLastName() != null ? !getTrainerLastName().equals(comment.getTrainerLastName()) : comment.getTrainerLastName() != null)
            return false;
        if (getRegisterDate() != null ? !getRegisterDate().equals(comment.getRegisterDate()) : comment.getRegisterDate() != null)
            return false;
        if (getCommentText() != null ? !getCommentText().equals(comment.getCommentText()) : comment.getCommentText() != null)
            return false;
        return getActive() != null ? getActive().equals(comment.getActive()) : comment.getActive() == null;
    }

    @Override
    public int hashCode() {
        int result = getClientId() != null ? getClientId().hashCode() : 0;
        result = 31 * result + (getClientName() != null ? getClientName().hashCode() : 0);
        result = 31 * result + (getClientLastName() != null ? getClientLastName().hashCode() : 0);
        result = 31 * result + (getClientProfileImagePath() != null ? getClientProfileImagePath().hashCode() : 0);
        result = 31 * result + (getTrainerId() != null ? getTrainerId().hashCode() : 0);
        result = 31 * result + (getTrainerName() != null ? getTrainerName().hashCode() : 0);
        result = 31 * result + (getTrainerLastName() != null ? getTrainerLastName().hashCode() : 0);
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getCommentText() != null ? getCommentText().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("clientId=").append(clientId);
        sb.append(", clientName='").append(clientName).append('\'');
        sb.append(", clientLastName='").append(clientLastName).append('\'');
        sb.append(", clientProfileImagePath='").append(clientProfileImagePath).append('\'');
        sb.append(", trainerId=").append(trainerId);
        sb.append(", trainerName='").append(trainerName).append('\'');
        sb.append(", trainerLastName='").append(trainerLastName).append('\'');
        sb.append(", registerDate=").append(registerDate);
        sb.append(", commentText='").append(commentText).append('\'');
        sb.append(", active=").append(active);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
