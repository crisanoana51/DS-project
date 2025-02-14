package ds.simulator.rabbitMq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        System.out.println(message);
        rabbitTemplate.convertAndSend("testqueue", message);
        }

}
