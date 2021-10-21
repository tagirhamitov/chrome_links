package tk.tagirkhamitov.chromelinksserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.tagirkhamitov.chromelinksserver.data.Record;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> findByAreaAndName(String area, String name);
    void deleteByAreaAndName(String area, String name);
    void deleteByArea(String area);
}
