package ds.simulator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ds.simulator.rabbitMq.MessageSender;
import ds.simulator.rabbitMq.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class RabbitController {


    private final MessageSender messageSender;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public RabbitController(MessageSender messageSender){
        this.messageSender = messageSender;
    }

    @GetMapping("/send")
    public String send(@RequestParam String message) throws IOException {
        messageSender.sendMessage(message);
        return "Message sent to the queue: " + message;
    }

    private final String filePath = "src/sensor.csv";
    private final AtomicInteger currentLine = new AtomicInteger(0);

    @Value("${device.id}")
    private Long id;
    @Scheduled(fixedRate = 5000)
    public void sendMessage() {

        MyMessage myMessage;
        try (BufferedReader csv = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < currentLine.get(); i++) {
                csv.readLine();
            }

            String value = csv.readLine();
            if (value != null) {
                int i = currentLine.getAndIncrement() % 2 == 0 ? 1 : 2;
                myMessage = new MyMessage(Timestamp.from(Instant.now()), Double.parseDouble(value), id);
                String message = Timestamp.from(Instant.now()) + "," + i + "," + value;
                System.out.println(message);
                messageSender.sendMessage(objectMapper.writeValueAsString(myMessage));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
