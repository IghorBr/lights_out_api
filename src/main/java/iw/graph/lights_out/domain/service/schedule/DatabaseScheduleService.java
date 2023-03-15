package iw.graph.lights_out.domain.service.schedule;

import iw.graph.lights_out.domain.model.Progresso;
import iw.graph.lights_out.domain.repository.ProgressoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseScheduleService {

    private final ProgressoRepository progressoRepository;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseScheduleService.class);

    @Scheduled(cron = "0 0 0 ? * SUN")
//    @Scheduled(fixedDelay = 10000)
    public void cleanDatabase() {
        List<Progresso> done = progressoRepository.findByDone(true);

        List<Progresso> moreThan3Days = done.stream()
                .filter(
                        d -> Duration.between(d.getSolvedAt(), OffsetDateTime.now()).abs().toDays() >= 3
                )
                .toList();

        moreThan3Days.forEach(p -> logger.info(String.format("Deleting progress of code %s", p.getCode())));

        progressoRepository.deleteAllInBatch(moreThan3Days);
    }
}
