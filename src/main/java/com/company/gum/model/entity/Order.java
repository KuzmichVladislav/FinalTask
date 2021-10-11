package com.company.gum.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Class Order.
 *
 * @author Vladislav Kuzmich
 */
public class Order extends AbstractEntity {

	/**
	 * The client id.
	 */
	private Integer clientId;

	/**
	 * The client name.
	 */
	private String clientName;

	/**
	 * The client surname.
	 */
	private String clientSurname;

	/**
	 * The trainer id.
	 */
	private Integer trainerId;

	/**
	 * The trainer name.
	 */
	private String trainerName;

	/**
	 * The trainer surname.
	 */
	private String trainerSurname;

	/**
	 * The register date.
	 */
	private LocalDateTime registerDate;

	/**
	 * The exercises.
	 */
	private String exercises;

	/**
	 * The nutrition.
	 */
	private String nutrition;

	/**
	 * The start date.
	 */
	private LocalDate startDate;

	/**
	 * The end date.
	 */
	private LocalDate endDate;

	/**
	 * The price.
	 */
	private BigDecimal price;

	/**
	 * The client comment.
	 */
	private String clientComment;

	/**
	 * The order status.
	 */
	private OrderStatus orderStatus;

	/**
	 * The active.
	 */
	private Boolean active = true;

	/**
	 * The duration.
	 */
	private Duration duration;

	/**
	 * Instantiates a new order.
	 *
	 * @param builder the builder
	 */
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

	/**
	 * Instantiates a new order.
	 */
	public Order() {
	}

	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public Integer getClientId() {
		return clientId;
	}

	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	/**
	 * Gets the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * Sets the client name.
	 *
	 * @param clientName the new client name
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Gets the client surname.
	 *
	 * @return the client surname
	 */
	public String getClientSurname() {
		return clientSurname;
	}

	/**
	 * Sets the client surname.
	 *
	 * @param clientSurname the new client surname
	 */
	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
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

	/**
	 * Gets the trainer id.
	 *
	 * @return the trainer id
	 */
	public Integer getTrainerId() {
		return trainerId;
	}

	/**
	 * Sets the trainer id.
	 *
	 * @param trainerId the new trainer id
	 */
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * Gets the trainer name.
	 *
	 * @return the trainer name
	 */
	public String getTrainerName() {
		return trainerName;
	}

	/**
	 * Sets the trainer name.
	 *
	 * @param trainerName the new trainer name
	 */
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	/**
	 * Gets the trainer surname.
	 *
	 * @return the trainer surname
	 */
	public String getTrainerSurname() {
		return trainerSurname;
	}

	/**
	 * Sets the trainer surname.
	 *
	 * @param trainerSurname the new trainer surname
	 */
	public void setTrainerSurname(String trainerSurname) {
		this.trainerSurname = trainerSurname;
	}

	/**
	 * Gets the register date.
	 *
	 * @return the register date
	 */
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	/**
	 * Sets the register date.
	 *
	 * @param registerDate the new register date
	 */
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * Gets the exercises.
	 *
	 * @return the exercises
	 */
	public String getExercises() {
		return exercises;
	}

	/**
	 * Sets the exercises.
	 *
	 * @param exercises the new exercises
	 */
	public void setExercises(String exercises) {
		this.exercises = exercises;
	}

	/**
	 * Gets the nutrition.
	 *
	 * @return the nutrition
	 */
	public String getNutrition() {
		return nutrition;
	}

	/**
	 * Sets the nutrition.
	 *
	 * @param nutrition the new nutrition
	 */
	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the client comment.
	 *
	 * @return the client comment
	 */
	public String getClientComment() {
		return clientComment;
	}

	/**
	 * Sets the client comment.
	 *
	 * @param clientComment the new client comment
	 */
	public void setClientComment(String clientComment) {
		this.clientComment = clientComment;
	}

	/**
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Sets the order status.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	/**
	 * The Enum OrderStatus.
	 */
	public enum OrderStatus {

		/**
		 * The new.
		 */
		NEW,
		/**
		 * The reviewed.
		 */
		REVIEWED,
		/**
		 * The accepted.
		 */
		ACCEPTED
	}

	/**
	 * {@code Order} builder static inner class.
	 */
	public static final class Builder {

		/**
		 * The id.
		 */
		private Integer id;

		/**
		 * The client id.
		 */
		private Integer clientId;

		/**
		 * The client name.
		 */
		private String clientName;

		/**
		 * The client surname.
		 */
		private String clientSurname;

		/**
		 * The trainer id.
		 */
		private Integer trainerId;

		/**
		 * The trainer name.
		 */
		private String trainerName;

		/**
		 * The trainer surname.
		 */
		private String trainerSurname;

		/**
		 * The register date.
		 */
		private LocalDateTime registerDate;

		/**
		 * The exercises.
		 */
		private String exercises;

		/**
		 * The nutrition.
		 */
		private String nutrition;

		/**
		 * The start date.
		 */
		private LocalDate startDate;

		/**
		 * The end date.
		 */
		private LocalDate endDate;

		/**
		 * The price.
		 */
		private BigDecimal price;

		/**
		 * The client comment.
		 */
		private String clientComment;

		/**
		 * The order status.
		 */
		private OrderStatus orderStatus;

		/**
		 * The active.
		 */
		private Boolean active;

		/**
		 * The duration.
		 */
		private Duration duration;

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
