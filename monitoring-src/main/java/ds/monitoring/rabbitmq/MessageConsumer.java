package ds.monitoring.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.monitoring.controllers.WebSocketController;
import ds.monitoring.entities.Consumption;
import ds.monitoring.entities.ReadDevice;
import ds.monitoring.repositories.ConsumptionRepository;
import ds.monitoring.repositories.ReadDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Component
@CrossOrigin(origins = "*")
public class MessageConsumer {

    @Autowired
    private ConsumptionRepository consumptionRepository;


    @Autowired
    private ReadDeviceRepository readDeviceRepository;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Long messageNumber;

    private double maxConsumption;

    @Autowired
    private ObjectMapper objectMapper;

    public MessageConsumer(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
        this.messageNumber = 0L;
    }
    Double sum = 0.0;

    public void storeHourlyConsumption(String message) throws JsonProcessingException {
            messageNumber++;

            sum = sum + objectMapper.readValue(message, Consumption.class).getConsumption();

        if (messageNumber % 6 == 0) {
            Consumption consumption = objectMapper.readValue(message, Consumption.class);
            consumption.setConsumption(sum);
            sum = 0.0;
            System.out.println(consumption.getId());
            consumptionRepository.save(consumption);
            Optional<ReadDevice> optionalReadDevice = readDeviceRepository.findById(consumption.getDeviceId());
            if (optionalReadDevice.isPresent()) {
                ReadDevice readDevice = optionalReadDevice.get();
                if (consumption.getConsumption() > readDevice.getMaximumHourlyEnergyConsumption()) {
                    System.out.println("AI DEPASIT");
                    messagingTemplate.convertAndSend("/topic/notification","AI DEPASIT");

                }else {
                    System.out.println("NAIDEPASIT!");
                    messagingTemplate.convertAndSend("/topic/notification","N-AI DEPASIT");
                }
            } else {
                System.out.println("nuexista");
            }
        }

    }
}
