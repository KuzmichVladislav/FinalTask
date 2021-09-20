package com.company.gum.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order extends AbstractEntity {

    private Integer clientId;
    private String clientName;
    private String clientSurname;
    private Integer trainerId;
    private String trainerName;
    private String trainerSurname;
    private LocalDateTime registerDate;
    private String exercises;
    private String nutrition;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private String clientComment;
    private OrderStatus orderStatus;
    private Boolean active = true;
    private Duration duration;

    private Order(Builder builder) {
        setId(builder.id);
        setClientId(builder.clientId);
        setClientName(builder.clientName);
        setClientSurname(builder.clientSurname);
        setTrainerId(builder.trainerId);
        setTrainerName(builder.trainerName);
        setTrainerSurname(builder.trainerSurname);
        setRegisterDate(builder.registerDate);
        setExercises(builder.exercises);
        setNutrition(builder.nutrition);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
        setPrice(builder.price);
        setClientComment(builder.clientComment);
        setOrderStatus(builder.orderStatus);
        setActive(builder.active);
        setDuration(builder.duration);
    }

    public Order() {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("clientId=").append(clientId);
        sb.append(", clientName='").append(clientName).append('\'');
        sb.append(", clientSurname='").append(clientSurname).append('\'');
        sb.append(", trainerId=").append(trainerId);
        sb.append(", trainerName='").append(trainerName).append('\'');
        sb.append(", trainerSurname='").append(trainerSurname).append('\'');
        sb.append(", registerDate=").append(registerDate);
        sb.append(", exercises='").append(exercises).append('\'');
        sb.append(", nutrition='").append(nutrition).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", price=").append(price);
        sb.append(", clientComment='").append(clientComment).append('\'');
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", active=").append(active);
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (getClientId() != null ? !getClientId().equals(order.getClientId()) : order.getClientId() != null)
            return false;
        if (getClientName() != null ? !getClientName().equals(order.getClientName()) : order.getClientName() != null)
            return false;
        if (getClientSurname() != null ? !getClientSurname().equals(order.getClientSurname()) : order.getClientSurname() != null)
            return false;
        if (getTrainerId() != null ? !getTrainerId().equals(order.getTrainerId()) : order.getTrainerId() != null)
            return false;
        if (getTrainerName() != null ? !getTrainerName().equals(order.getTrainerName()) : order.getTrainerName() != null)
            return false;
        if (getTrainerSurname() != null ? !getTrainerSurname().equals(order.getTrainerSurname()) : order.getTrainerSurname() != null)
            return false;
        if (getRegisterDate() != null ? !getRegisterDate().equals(order.getRegisterDate()) : order.getRegisterDate() != null)
            return false;
        if (getExercises() != null ? !getExercises().equals(order.getExercises()) : order.getExercises() != null)
            return false;
        if (getNutrition() != null ? !getNutrition().equals(order.getNutrition()) : order.getNutrition() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(order.getStartDate()) : order.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(order.getEndDate()) : order.getEndDate() != null) return false;
        if (getPrice() != null ? !getPrice().equals(order.getPrice()) : order.getPrice() != null) return false;
        if (getClientComment() != null ? !getClientComment().equals(order.getClientComment()) : order.getClientComment() != null)
            return false;
        if (getOrderStatus() != order.getOrderStatus()) return false;
        if (getActive() != null ? !getActive().equals(order.getActive()) : order.getActive() != null) return false;
        return getDuration() == order.getDuration();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getClientId() != null ? getClientId().hashCode() : 0);
        result = 31 * result + (getClientName() != null ? getClientName().hashCode() : 0);
        result = 31 * result + (getClientSurname() != null ? getClientSurname().hashCode() : 0);
        result = 31 * result + (getTrainerId() != null ? getTrainerId().hashCode() : 0);
        result = 31 * result + (getTrainerName() != null ? getTrainerName().hashCode() : 0);
        result = 31 * result + (getTrainerSurname() != null ? getTrainerSurname().hashCode() : 0);
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getExercises() != null ? getExercises().hashCode() : 0);
        result = 31 * result + (getNutrition() != null ? getNutrition().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getClientComment() != null ? getClientComment().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        return result;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
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

    public String getTrainerSurname() {
        return trainerSurname;
    }

    public void setTrainerSurname(String trainerSurname) {
        this.trainerSurname = trainerSurname;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public enum OrderStatus {
        NEW,
        REVIEWED,
        REJECTED,
        ACCEPTED,
        IN_PROCESSING,
        TERMINATED
    }

    /**
     * {@code Order} builder static inner class.
     */
    public static final class Builder {

        private Integer id;
        private Integer clientId;
        private String clientName;
        private String clientSurname;
        private Integer trainerId;
        private String trainerName;
        private String trainerSurname;
        private LocalDateTime registerDate;
        private String exercises;
        private String nutrition;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal price;
        private String clientComment;
        private OrderStatus orderStatus;
        private Boolean active;
        private Duration duration;

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
         * Sets the {@code clientId} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code clientId} to set
         * @return a reference to this Builder
         */
        public Builder clientId(Integer val) {
            clientId = val;
            return this;
        }

        /**
         * Sets the {@code clientName} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code clientName} to set
         * @return a reference to this Builder
         */
        public Builder clientName(String val) {
            clientName = val;
            return this;
        }

        /**
         * Sets the {@code clientSurname} and returns a reference to this
         * Builder so that the methods can be chained together.
         *
         * @param val the {@code clientSurname} to set
         * @return a reference to this Builder
         */
        public Builder clientSurname(String val) {
            clientSurname = val;
            return this;
        }

        /**
         * Sets the {@code trainerId} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code trainerId} to set
         * @return a reference to this Builder
         */
        public Builder trainerId(Integer val) {
            trainerId = val;
            return this;
        }

        /**
         * Sets the {@code trainerName} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code trainerName} to set
         * @return a reference to this Builder
         */
        public Builder trainerName(String val) {
            trainerName = val;
            return this;
        }

        /**
         * Sets the {@code trainerSurname} and returns a reference to this
         * Builder so that the methods can be chained together.
         *
         * @param val the {@code trainerSurname} to set
         * @return a reference to this Builder
         */
        public Builder trainerSurname(String val) {
            trainerSurname = val;
            return this;
        }

        /**
         * Sets the {@code registerDate} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code registerDate} to set
         * @return a reference to this Builder
         */
        public Builder registerDate(LocalDateTime val) {
            registerDate = val;
            return this;
        }

        /**
         * Sets the {@code exercises} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code exercises} to set
         * @return a reference to this Builder
         */
        public Builder exercises(String val) {
            exercises = val;
            return this;
        }

        /**
         * Sets the {@code nutrition} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code nutrition} to set
         * @return a reference to this Builder
         */
        public Builder nutrition(String val) {
            nutrition = val;
            return this;
        }

        /**
         * Sets the {@code startDate} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code startDate} to set
         * @return a reference to this Builder
         */
        public Builder startDate(LocalDate val) {
            startDate = val;
            return this;
        }

        /**
         * Sets the {@code endDate} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code endDate} to set
         * @return a reference to this Builder
         */
        public Builder endDate(LocalDate val) {
            endDate = val;
            return this;
        }

        /**
         * Sets the {@code price} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code price} to set
         * @return a reference to this Builder
         */
        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        /**
         * Sets the {@code clientComment} and returns a reference to this
         * Builder so that the methods can be chained together.
         *
         * @param val the {@code clientComment} to set
         * @return a reference to this Builder
         */
        public Builder clientComment(String val) {
            clientComment = val;
            return this;
        }

        /**
         * Sets the {@code orderStatus} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code orderStatus} to set
         * @return a reference to this Builder
         */
        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
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
         * Sets the {@code duration} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code duration} to set
         * @return a reference to this Builder
         */
        public Builder duration(Duration val) {
            duration = val;
            return this;
        }

        /**
         * Returns a {@code Order} built from the parameters previously set.
         *
         * @return a {@code Order} built with parameters of this
         * {@code Order.Builder}
         */
        public Order build() {
            return new Order(this);
        }
    }
}
