package de.flaviait.timetracker.rest;

import de.flaviait.timetracker.dto.TimeRecordDto;
import de.flaviait.timetracker.entity.TimeRecordEntity;
import de.flaviait.timetracker.mapper.TimeRecordMapper;
import de.flaviait.timetracker.service.TimeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/timerecords")
@RequiredArgsConstructor
public class TimeRecordRestController implements BasicRestController {

    private final TimeRecordService timeRecordService;
    private final TimeRecordMapper timeRecordMapper;

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid TimeRecordDto request) {
        TimeRecordEntity entity = timeRecordService.createTimeRecord(timeRecordMapper.dtoToEntity(request));
        return ResponseEntity.created(getLocationHeader(entity.getObjectId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRecordDto> get(@PathVariable String id) {
        return ResponseEntity.of(timeRecordService.getTimeRecord(id)
                .map(timeRecordMapper::entityToDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeRecordDto> update(@PathVariable String id, @RequestBody TimeRecordDto request) {
        request.setId(id);
        TimeRecordEntity entity = timeRecordService.updateTimeRecord(timeRecordMapper.dtoToEntity(request));
        return ResponseEntity.ok(timeRecordMapper.entityToDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        timeRecordService.deleteTimeRecordByObjectId(id);
        return ResponseEntity.noContent().build();
    }
}
