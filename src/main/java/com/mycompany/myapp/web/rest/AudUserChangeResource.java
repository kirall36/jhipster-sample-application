package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AudUserChangeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.AudUserChangeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.AudUserChange}.
 */
@RestController
@RequestMapping("/api")
public class AudUserChangeResource {

    private final Logger log = LoggerFactory.getLogger(AudUserChangeResource.class);

    private static final String ENTITY_NAME = "jhipsterSampleApplicationAudUserChange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AudUserChangeService audUserChangeService;

    public AudUserChangeResource(AudUserChangeService audUserChangeService) {
        this.audUserChangeService = audUserChangeService;
    }

    /**
     * {@code POST  /aud-user-changes} : Create a new audUserChange.
     *
     * @param audUserChangeDTO the audUserChangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new audUserChangeDTO, or with status {@code 400 (Bad Request)} if the audUserChange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aud-user-changes")
    public ResponseEntity<AudUserChangeDTO> createAudUserChange(@RequestBody AudUserChangeDTO audUserChangeDTO) throws URISyntaxException {
        log.debug("REST request to save AudUserChange : {}", audUserChangeDTO);
        if (audUserChangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new audUserChange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AudUserChangeDTO result = audUserChangeService.save(audUserChangeDTO);
        return ResponseEntity.created(new URI("/api/aud-user-changes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aud-user-changes} : Updates an existing audUserChange.
     *
     * @param audUserChangeDTO the audUserChangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated audUserChangeDTO,
     * or with status {@code 400 (Bad Request)} if the audUserChangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the audUserChangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aud-user-changes")
    public ResponseEntity<AudUserChangeDTO> updateAudUserChange(@RequestBody AudUserChangeDTO audUserChangeDTO) throws URISyntaxException {
        log.debug("REST request to update AudUserChange : {}", audUserChangeDTO);
        if (audUserChangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AudUserChangeDTO result = audUserChangeService.save(audUserChangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, audUserChangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aud-user-changes} : get all the audUserChanges.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of audUserChanges in body.
     */
    @GetMapping("/aud-user-changes")
    public ResponseEntity<List<AudUserChangeDTO>> getAllAudUserChanges(Pageable pageable) {
        log.debug("REST request to get a page of AudUserChanges");
        Page<AudUserChangeDTO> page = audUserChangeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aud-user-changes/:id} : get the "id" audUserChange.
     *
     * @param id the id of the audUserChangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the audUserChangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aud-user-changes/{id}")
    public ResponseEntity<AudUserChangeDTO> getAudUserChange(@PathVariable Long id) {
        log.debug("REST request to get AudUserChange : {}", id);
        Optional<AudUserChangeDTO> audUserChangeDTO = audUserChangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(audUserChangeDTO);
    }

    /**
     * {@code DELETE  /aud-user-changes/:id} : delete the "id" audUserChange.
     *
     * @param id the id of the audUserChangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aud-user-changes/{id}")
    public ResponseEntity<Void> deleteAudUserChange(@PathVariable Long id) {
        log.debug("REST request to delete AudUserChange : {}", id);
        audUserChangeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
