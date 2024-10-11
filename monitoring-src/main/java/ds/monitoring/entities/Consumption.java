package ds.monitoring.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "consumption")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;
    private Double consumption;
    private Long deviceId;

    public Consumption(Timestamp timestamp, Double consumption, Long deviceId) {
        this.timestamp = timestamp;
        this.consumption = consumption;
        this.deviceId = deviceId;
    }

    public Consumption() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString(){
        return "The Message: [deviceId=" + deviceId +", consumption=" + consumption + ", timestamp=" + timestamp +"]";
    }
}
