package tk.tagirkhamitov.chromelinksserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tk.tagirkhamitov.chromelinksserver.data.Record;
import tk.tagirkhamitov.chromelinksserver.exception.RecordNotFoundException;
import tk.tagirkhamitov.chromelinksserver.repo.RecordRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecordService {
    private final RecordRepository repository;

    @Autowired
    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    public List<Record> getAllRecords() {
        return repository.findAll(Sort.by("area").and(Sort.by("name")));
    }

    public Record getRecord(String area, String name) throws RecordNotFoundException {
        Optional<Record> record = repository.findByAreaAndName(area, name);
        return record.orElseThrow(RecordNotFoundException::new);
    }

    public void addRecord(String area, String name, String target) {
        Optional<Record> oldRecord = repository.findByAreaAndName(area, name);

        Record record;

        if (oldRecord.isPresent()) {
            record = oldRecord.get();
            record.setTarget(target);
        } else {
            record = new Record(area, name, target);
        }

        repository.save(record);
    }

    public void deleteRecord(String area, String name) {
        repository.deleteByAreaAndName(area, name);
    }

    public void deleteRecordsByArea(String area) {
        repository.deleteByArea(area);
    }
}
