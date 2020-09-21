import org.apache.activemq.ActiveMQConnection;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.*;


public class Send {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        //kolay bir şekilde queue oluşturma
    private static String queueName =  "MESSAGES";




    public static void main(String[] args) throws JMSException{
        //http://localhost:8161/ adresine bağlanıyor
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();


        System.out.println("url = " + url);
        //devamlı mesaj gönderebilmesi için sonsuz bir loop içine aldım
        while(true) {

            //en son mesajı gönderdiğinde loopun başına dönüp yeni bir mesaj bekleyecek
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();



            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //hangi queue'ya gideceğini belirliyor.
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);


            TextMessage message = session.createTextMessage(msg);
            producer.send(message);
            System.out.println("Message Sent Successfully");
        }
        //connection.close();

    }

}
