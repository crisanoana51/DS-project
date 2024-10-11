package ds.monitoring.controllers;


import ds.monitoring.entities.Consumption;
import ds.monitoring.repositories.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumptionRepository consumptionRepository;


    @GetMapping("/getConsumption")
    private List<Consumption> consumptions(){
        return consumptionRepository.findAll();

    }


}
