package de.flaviait.timetracker.mapper;

import de.flaviait.timetracker.dto.EmployeeDto;
import de.flaviait.timetracker.entity.EmployeeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings(
            @Mapping(source = "objectId", target = "id")
    )
    EmployeeDto entityToDto(EmployeeEntity employeeEntity);

    @InheritInverseConfiguration
    @Mappings(
            @Mapping(target = "id", ignore = true)
    )
    EmployeeEntity dtoToEntity(EmployeeDto employeeDto);
}
