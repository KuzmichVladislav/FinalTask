package com.company.gum.main;

import com.company.gum.dao.impl.TrainerDaoImpl;
import com.company.gum.entity.user_impl.Trainer;
import com.company.gum.exception.DaoException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {

        Trainer trainer = new Trainer();

        trainer.setLogin("secondLogin");
        trainer.setPassword("aloha");
        trainer.setName("Pert");
        trainer.setSurname("Petrov");
        trainer.setMail("56@jas.by");
        trainer.setPhone("9379993");
        TrainerDaoImpl.getInstance().createTrainer(trainer);

       // ClientDaoImpl.getInstance().withdrawal(1, new BigDecimal(5500.5));

    }

}
