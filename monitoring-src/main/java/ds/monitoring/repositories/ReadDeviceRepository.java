package ds.monitoring.repositories;

import ds.monitoring.entities.ReadDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReadDeviceRepository extends JpaRepository<ReadDevice, Long> {
}
