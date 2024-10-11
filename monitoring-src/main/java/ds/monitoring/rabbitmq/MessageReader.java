package ds.monitoring.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.monitoring.entities.ReadDevice;
import ds.monitoring.repositories.ReadDeviceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReader {


    @Autowired
    private MessageConsumer messageConsumer;


    @Autowired
    private ReadDeviceRepository readDeviceRepository;

    @RabbitListener(queues = "testqueue")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("Message read from testQueue: " + message);
        messageConsumer.storeHourlyConsumption(message);


    }

    @RabbitListener(queues = "testqueue2")
    public void listen2(String message) throws JsonProcessingException {
        System.out.println("Message read from testQueue: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        ReadDevice readDevice = objectMapper.readValue(message, ReadDevice.class);
        readDeviceRepository.save(readDevice);
        System.out.println(readDevice);

    }
}
