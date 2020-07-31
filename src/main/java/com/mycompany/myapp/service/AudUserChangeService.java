package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AudUserChangeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.AudUserChange}.
 */
public interface AudUserChangeService {

    /**
     * Save a audUserChange.
     *
     * @param audUserChangeDTO the entity to save.
     * @return the persisted entity.
     */
    AudUserChangeDTO save(AudUserChangeDTO audUserChangeDTO);

    /**
     * Get all the audUserChanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AudUserChangeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" audUserChange.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AudUserChangeDTO> findOne(Long id);

    /**
     * Delete the "id" audUserChange.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
