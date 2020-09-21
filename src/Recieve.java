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

public class Recieve{
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String queueName =  "MESSAGES";

    public static void main(String[] args) throws JMSException{

        System.out.println("url = " + url);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);
        while(true) {
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Message is: '" + textMessage.getText() + "'");
            }
        }
        //connection.close();
    }

}
