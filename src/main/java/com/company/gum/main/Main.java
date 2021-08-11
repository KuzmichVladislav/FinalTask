package com.company.gum.main;

import com.company.gum.dao.impl.OrderDaoImpl;
import com.company.gum.entity.Order;
import com.company.gum.entity.OrderStatus;
import com.company.gum.exception.DaoException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {

/*
        Trainer trainer = new Trainer();

        trainer.setLogin("secondLogin");
        trainer.setPassword("aloha");
        trainer.setName("Pert");
        trainer.setSurname("Petrov");
        trainer.setMail("56@jas.by");
        trainer.setPhone("9379993");
        TrainerDaoImpl.getInstance().createTrainer(trainer);
*/

        // ClientDaoImpl.getInstance().withdrawal(1, new BigDecimal(5500.5));


        //client_id, trainer_id, review, start_order_date, end_order_date, price

       //"UPDATE orders SET client_id = IFNULL(?, client_id), trainer_id = IFNULL(?, trainer_id), exercises = IFNULL(?, exercises), nutrition = IFNULL(?, nutrition), start_order_date = IFNULL(?, start_order_date), end_order_date = IFNULL(?, end_order_date), price = IFNULL(?, price), client_comment = IFNULL(?, client_comment), order_status = IFNULL(?, order_status), is_active = IFNULL(?, is_active) WHERE id = ?";

        /*Order order = new Order();
        order.setId(1);
        order.setClientId(13);
        order.setTrainerId(14);
        order.setExercises("some exc.........");
        order.setNutrition("some nutrition....");
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setActive(true);

        order.setClientComment("Hi all");
        order.setStartDate(LocalDate.of(2021,1,1));
        order.setEndDate(LocalDate.of(2021,2,2));
        order.setPrice(new BigDecimal(1000));*/

        OrderDaoImpl.getInstance().findOrder(1);

    }
}
