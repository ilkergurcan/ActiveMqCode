import org.apache.activemq.ActiveMQConnection;

import javax.jms.*;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.*;


public class Send {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        //kolay bir şekilde queue oluşturma
    private static String queueName =  "MESSAGES";

        //konsoldan mesajı çekme


    public static void main(String[] args) throws JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();


        System.out.println("url = " + url);
        while(true) {

            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();



            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);


            TextMessage message = session.createTextMessage(msg);
            System.out.println(msg);
            producer.send(message);
            System.out.println(message);
        }
        //connection.close();
        //
    }

}
