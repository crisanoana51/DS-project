package ds.simulator.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    @Bean
    Queue queue() { return new Queue("testqueue", true);
    }

    @Bean
    DirectExchange exchange() {return new DirectExchange("testexchange");
    }


    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("testqueue");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqps://gull.rmq.cloudamqp.com/afzjfveu");
        connectionFactory.setUsername("afzjfveu");
        connectionFactory.setPassword("w8I1Gqac45LP-Y5DKyhngFt411dqj1v5");
        connectionFactory.setPort(5671);
        connectionFactory.setVirtualHost("afzjfveu");
        return connectionFactory;
    }

    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if(!ack){
                System.out.println("Message sent failed: " + cause);
            }
        });
        return template;
    }
    @Bean
    public MessageConverter simulatorMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
