package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;  //JmsTemplate(for messaging) is pre configured like JdbcTemplate(for db)
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
//        System.out.println("I'm Sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

//        System.out.println("Message Sent!");
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndRecieveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello ")
                .build();

        Message recievedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;
                try {
                    //we have to manually configure converter using objectMapper bcz
                    // our convertor(MessageConverter from JmsConfig.class) doesnot work here
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    //set property to convert to java Object
                    helloMessage.setStringProperty("_type","guru.springframework.sfgjms.model.HelloWorldMessage");
                    System.out.println("Sending Hello");
                    return helloMessage;
                }catch (JsonProcessingException e){
                    throw new JMSException(e.getMessage());
                }
            }
        });

        System.out.println(recievedMessage.getBody(String.class));

    }
}
