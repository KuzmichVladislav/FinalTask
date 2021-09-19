package com.company.gum._main;

import java.time.LocalDate;
import java.time.Period;

public class Main {

    public static void main(String[] args) throws Exception {

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

 /*Comment comment = new Comment();
        comment.setCommentText("hi!!!!");
        comment.setUserId(13);
        CommentDaoImpl.getInstance().createComment(comment);*/

 /*        Comment comment = new Comment();
        comment.setCommentText("her");
        comment.setUserId(13);
        comment.setActive(false);
        comment.setId(3);
        CommentDaoImpl.getInstance().updateComment(comment);*/
        //Comment comment = CommentDaoImpl.getInstance().findComment(3);
        //System.out.println(CommentDaoImpl.getInstance().commentCount(true));
        // System.out.println(JBCryptPasswordEncoder.encode("admindfgdf22"));
        // JBCryptPasswordEncoder jb = new JBCryptPasswordEncoder();
        //System.out.println(BCrypt.gensalt());
        //  Properties properties = PropertyLoader.loadProperty("mail/mail.properties");
        //System.out.println(properties);
        // String mailTo = "kyzmenoid@gmail.com";
        // String subject = "Sample Mail";
        // String body = "Hello java mail";
        // MailSender sender = new MailSender();
        // sender.send(1, "kyzmenoid@gmail.com");
//        Client client = new Client();
//        client.setLogin("Kuzia");
//        client.setName("Vlad");
//        client.setSurname("Vlad");
//        client.setPassword("Absba");
//        client.setPhone("554554");
//        client.setId(1);
//        client.setMail("375259740288@yandex.by");
//
//        ClientService service = ClientServiceImpl.getInstance();
//        service.createClient(client);
//        System.out.println(client.getId());
//        System.out.println(client.getMail());
//        ClientDaoImpl cd = new ClientDaoImpl();
//        cd.verification(57);
//        UserService userService = UserServiceImpl.getInstance();
//        User user = userService.findUserByLoginAndPassword("firstLogin", "aloha");
//        System.out.println(user);
//
//        ClientService clientService = ClientServiceImpl.getInstance();
//        Client client = clientService.findClientById(user.getId());
//        System.out.println(client);
//

        LocalDate start = LocalDate.parse("2020-09-18");
        LocalDate end = LocalDate.now();
        Period period = Period.between(start, end);
        System.out.println(period.getYears());      // 4
        System.out.println(period.getMonths());     // 1
        System.out.println(period.getDays());       // 5
    }
}
