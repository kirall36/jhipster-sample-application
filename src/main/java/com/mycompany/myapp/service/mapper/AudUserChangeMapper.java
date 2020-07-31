package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AudUserChangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AudUserChange} and its DTO {@link AudUserChangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AudUserChangeMapper extends EntityMapper<AudUserChangeDTO, AudUserChange> {



    default AudUserChange fromId(Long id) {
        if (id == null) {
            return null;
        }
        AudUserChange audUserChange = new AudUserChange();
        audUserChange.setId(id);
        return audUserChange;
    }
}
