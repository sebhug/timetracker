package de.flaviait.timetracker.mapper;

import de.flaviait.timetracker.dto.TimeRecordDto;
import de.flaviait.timetracker.entity.TimeRecordEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TimeRecordMapper {

    @Mappings({
            @Mapping(source = "objectId", target = "id"),
            @Mapping(source = "employee.objectId", target = "employeeId")
    })
    TimeRecordDto entityToDto(TimeRecordEntity timeRecordEntity);

    @InheritInverseConfiguration
    @Mappings(
            @Mapping(target = "id", ignore = true)
    )
    TimeRecordEntity dtoToEntity(TimeRecordDto timeRecordDto);
}
