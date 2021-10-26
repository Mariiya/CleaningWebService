package com.opsu.services;

import com.opsu.models.NotificationBuildInfo;
import com.opsu.models.Order;
import com.opsu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;


@Service
public class NotificationService {
    @Autowired
    private MailService mailService;

    private NotificationBuildInfo orderCreatedNotification;
    private NotificationBuildInfo orderClosedNotification;
    private NotificationBuildInfo accountRegistrationNotification;
    private NotificationBuildInfo changePasswordNotification;

    public NotificationService() {
        orderCreatedNotification = new NotificationBuildInfo(
                "New Order created.",
                "A new order was submitted."
        );

        orderClosedNotification = new NotificationBuildInfo(
                "Order is closed.",
                "For more info follow go to your account in Sky-clean service."
        );

        accountRegistrationNotification = new NotificationBuildInfo(
                "Sky-clean account created.",
                "Please, follow this link."
        );

        changePasswordNotification = new NotificationBuildInfo(
                "Your password has changed.",
                "For more info follow go to your account in Sky-clean service."
        );
    }

    public synchronized void sendOrderCreatedNotification(Order order)
            throws IOException, MessagingException {
        String body = "New order created for this services: " + order.getServices()
                + "\n" + "Address: " + order.getAddress()
                + "\n" + "Consumer: " + order.getConsumer().getFirstName() + " " + order.getConsumer().getLastName()
                + "\n" + "Email: " + order.getConsumer().getEmail() + " Phone number: " + order.getConsumer().getPhoneNumber()
                + "\n" + "Vendor: " + order.getVendor().getFirstName() + " " + order.getVendor().getLastName()
                + "\n" + "Email: " + order.getVendor().getEmail() + " Phone number: " + order.getVendor().getPhoneNumber()
                + "\n \n Total price: " + order.getPrice()
                + "\n  Start date: " + order.getStartDate()
                + "\n  End date: " + order.getEndDate()
                + " \n\n" + "Best regard, Sky-clean service";

        mailService.sendMessage(order.getConsumer().getEmail(),
                orderCreatedNotification.getTitle() + " " + order.getTitle(), body);
        mailService.sendMessage(order.getVendor().getEmail(),
                orderCreatedNotification.getTitle() + " " + order.getTitle(), body);
    }

    public synchronized void sendOrderClosedNotification(Order order) {
        String body = "Order is closed! "
                + "\n" + "Consumer: " + order.getConsumer().getFirstName() + " " + order.getConsumer().getLastName()
                + "\n" + "Email: " + order.getConsumer().getEmail() + " Phone number: " + order.getConsumer().getPhoneNumber()
                + "\n" + "Vendor: " + order.getVendor().getFirstName() + " " + order.getVendor().getLastName()
                + "\n" + "Email: " + order.getVendor().getEmail() + " Phone number: " + order.getVendor().getPhoneNumber()
                + "\n \n Total price: " + order.getPrice()
                + " \n\n" + "Thank you for your cooperation." +
                "\nBest regard, Sky-clean service";

        mailService.sendMessage(order.getConsumer().getEmail(),
                orderClosedNotification.getTitle() + " " + order.getTitle(), body);
        mailService.sendMessage(order.getVendor().getEmail(),
                orderClosedNotification.getTitle() + " " + order.getTitle(), body);
    }

    public synchronized void sendRegistrationNotification(User user)
            throws IOException, MessagingException {
        mailService.sendMessage(user.getEmail(), accountRegistrationNotification.getTitle(),
                "Very nice to meet you in our service! Have a great cooperation!");
    }

    public synchronized void changePasswordNotification(User user) {
        mailService.sendMessage(user.getEmail(), changePasswordNotification.getTitle(),
                changePasswordNotification.getBody());
    }
}
