import org.apache.activemq.ActiveMQConnection;

import javax.jms.*;
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

            //hangi queue daki mesajları alacağına bakıyor
            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);

        while(true) {
            //her mesajı yazdırdıktan sonra yeni bir mesaj gelmesini bekliyor
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Message is: '" + textMessage.getText() + "'");
            }
        }
        //connection.close();
    }

}
