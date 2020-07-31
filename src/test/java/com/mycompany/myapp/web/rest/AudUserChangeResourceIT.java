package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.AudUserChange;
import com.mycompany.myapp.repository.AudUserChangeRepository;
import com.mycompany.myapp.service.AudUserChangeService;
import com.mycompany.myapp.service.dto.AudUserChangeDTO;
import com.mycompany.myapp.service.mapper.AudUserChangeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.ChangeType;
/**
 * Integration tests for the {@link AudUserChangeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AudUserChangeResourceIT {

    private static final Long DEFAULT_AUC_ID = 1L;
    private static final Long UPDATED_AUC_ID = 2L;

    private static final ChangeType DEFAULT_AUC_CHANGE_TYPE = ChangeType.USER_CREATION;
    private static final ChangeType UPDATED_AUC_CHANGE_TYPE = ChangeType.USER_BLOCK;

    private static final Instant DEFAULT_AUC_CHANGE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AUC_CHANGE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_AUC_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_AUC_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AUC_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_AUC_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AUC_MODIFIED_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_AUC_MODIFIED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AUC_PERMISSIONS = "AAAAAAAAAA";
    private static final String UPDATED_AUC_PERMISSIONS = "BBBBBBBBBB";

    @Autowired
    private AudUserChangeRepository audUserChangeRepository;

    @Autowired
    private AudUserChangeMapper audUserChangeMapper;

    @Autowired
    private AudUserChangeService audUserChangeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAudUserChangeMockMvc;

    private AudUserChange audUserChange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AudUserChange createEntity(EntityManager em) {
        AudUserChange audUserChange = new AudUserChange()
            .aucId(DEFAULT_AUC_ID)
            .aucChangeType(DEFAULT_AUC_CHANGE_TYPE)
            .aucChangeDate(DEFAULT_AUC_CHANGE_DATE)
            .aucUserId(DEFAULT_AUC_USER_ID)
            .aucDepartmentId(DEFAULT_AUC_DEPARTMENT_ID)
            .aucModifiedUserId(DEFAULT_AUC_MODIFIED_USER_ID)
            .aucPermissions(DEFAULT_AUC_PERMISSIONS);
        return audUserChange;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AudUserChange createUpdatedEntity(EntityManager em) {
        AudUserChange audUserChange = new AudUserChange()
            .aucId(UPDATED_AUC_ID)
            .aucChangeType(UPDATED_AUC_CHANGE_TYPE)
            .aucChangeDate(UPDATED_AUC_CHANGE_DATE)
            .aucUserId(UPDATED_AUC_USER_ID)
            .aucDepartmentId(UPDATED_AUC_DEPARTMENT_ID)
            .aucModifiedUserId(UPDATED_AUC_MODIFIED_USER_ID)
            .aucPermissions(UPDATED_AUC_PERMISSIONS);
        return audUserChange;
    }

    @BeforeEach
    public void initTest() {
        audUserChange = createEntity(em);
    }

    @Test
    @Transactional
    public void createAudUserChange() throws Exception {
        int databaseSizeBeforeCreate = audUserChangeRepository.findAll().size();
        // Create the AudUserChange
        AudUserChangeDTO audUserChangeDTO = audUserChangeMapper.toDto(audUserChange);
        restAudUserChangeMockMvc.perform(post("/api/aud-user-changes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audUserChangeDTO)))
            .andExpect(status().isCreated());

        // Validate the AudUserChange in the database
        List<AudUserChange> audUserChangeList = audUserChangeRepository.findAll();
        assertThat(audUserChangeList).hasSize(databaseSizeBeforeCreate + 1);
        AudUserChange testAudUserChange = audUserChangeList.get(audUserChangeList.size() - 1);
        assertThat(testAudUserChange.getAucId()).isEqualTo(DEFAULT_AUC_ID);
        assertThat(testAudUserChange.getAucChangeType()).isEqualTo(DEFAULT_AUC_CHANGE_TYPE);
        assertThat(testAudUserChange.getAucChangeDate()).isEqualTo(DEFAULT_AUC_CHANGE_DATE);
        assertThat(testAudUserChange.getAucUserId()).isEqualTo(DEFAULT_AUC_USER_ID);
        assertThat(testAudUserChange.getAucDepartmentId()).isEqualTo(DEFAULT_AUC_DEPARTMENT_ID);
        assertThat(testAudUserChange.getAucModifiedUserId()).isEqualTo(DEFAULT_AUC_MODIFIED_USER_ID);
        assertThat(testAudUserChange.getAucPermissions()).isEqualTo(DEFAULT_AUC_PERMISSIONS);
    }

    @Test
    @Transactional
    public void createAudUserChangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = audUserChangeRepository.findAll().size();

        // Create the AudUserChange with an existing ID
        audUserChange.setId(1L);
        AudUserChangeDTO audUserChangeDTO = audUserChangeMapper.toDto(audUserChange);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAudUserChangeMockMvc.perform(post("/api/aud-user-changes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audUserChangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AudUserChange in the database
        List<AudUserChange> audUserChangeList = audUserChangeRepository.findAll();
        assertThat(audUserChangeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAudUserChanges() throws Exception {
        // Initialize the database
        audUserChangeRepository.saveAndFlush(audUserChange);

        // Get all the audUserChangeList
        restAudUserChangeMockMvc.perform(get("/api/aud-user-changes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(audUserChange.getId().intValue())))
            .andExpect(jsonPath("$.[*].aucId").value(hasItem(DEFAULT_AUC_ID.intValue())))
            .andExpect(jsonPath("$.[*].aucChangeType").value(hasItem(DEFAULT_AUC_CHANGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].aucChangeDate").value(hasItem(DEFAULT_AUC_CHANGE_DATE.toString())))
            .andExpect(jsonPath("$.[*].aucUserId").value(hasItem(DEFAULT_AUC_USER_ID)))
            .andExpect(jsonPath("$.[*].aucDepartmentId").value(hasItem(DEFAULT_AUC_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].aucModifiedUserId").value(hasItem(DEFAULT_AUC_MODIFIED_USER_ID)))
            .andExpect(jsonPath("$.[*].aucPermissions").value(hasItem(DEFAULT_AUC_PERMISSIONS)));
    }
    
    @Test
    @Transactional
    public void getAudUserChange() throws Exception {
        // Initialize the database
        audUserChangeRepository.saveAndFlush(audUserChange);

        // Get the audUserChange
        restAudUserChangeMockMvc.perform(get("/api/aud-user-changes/{id}", audUserChange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(audUserChange.getId().intValue()))
            .andExpect(jsonPath("$.aucId").value(DEFAULT_AUC_ID.intValue()))
            .andExpect(jsonPath("$.aucChangeType").value(DEFAULT_AUC_CHANGE_TYPE.toString()))
            .andExpect(jsonPath("$.aucChangeDate").value(DEFAULT_AUC_CHANGE_DATE.toString()))
            .andExpect(jsonPath("$.aucUserId").value(DEFAULT_AUC_USER_ID))
            .andExpect(jsonPath("$.aucDepartmentId").value(DEFAULT_AUC_DEPARTMENT_ID))
            .andExpect(jsonPath("$.aucModifiedUserId").value(DEFAULT_AUC_MODIFIED_USER_ID))
            .andExpect(jsonPath("$.aucPermissions").value(DEFAULT_AUC_PERMISSIONS));
    }
    @Test
    @Transactional
    public void getNonExistingAudUserChange() throws Exception {
        // Get the audUserChange
        restAudUserChangeMockMvc.perform(get("/api/aud-user-changes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAudUserChange() throws Exception {
        // Initialize the database
        audUserChangeRepository.saveAndFlush(audUserChange);

        int databaseSizeBeforeUpdate = audUserChangeRepository.findAll().size();

        // Update the audUserChange
        AudUserChange updatedAudUserChange = audUserChangeRepository.findById(audUserChange.getId()).get();
        // Disconnect from session so that the updates on updatedAudUserChange are not directly saved in db
        em.detach(updatedAudUserChange);
        updatedAudUserChange
            .aucId(UPDATED_AUC_ID)
            .aucChangeType(UPDATED_AUC_CHANGE_TYPE)
            .aucChangeDate(UPDATED_AUC_CHANGE_DATE)
            .aucUserId(UPDATED_AUC_USER_ID)
            .aucDepartmentId(UPDATED_AUC_DEPARTMENT_ID)
            .aucModifiedUserId(UPDATED_AUC_MODIFIED_USER_ID)
            .aucPermissions(UPDATED_AUC_PERMISSIONS);
        AudUserChangeDTO audUserChangeDTO = audUserChangeMapper.toDto(updatedAudUserChange);

        restAudUserChangeMockMvc.perform(put("/api/aud-user-changes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audUserChangeDTO)))
            .andExpect(status().isOk());

        // Validate the AudUserChange in the database
        List<AudUserChange> audUserChangeList = audUserChangeRepository.findAll();
        assertThat(audUserChangeList).hasSize(databaseSizeBeforeUpdate);
        AudUserChange testAudUserChange = audUserChangeList.get(audUserChangeList.size() - 1);
        assertThat(testAudUserChange.getAucId()).isEqualTo(UPDATED_AUC_ID);
        assertThat(testAudUserChange.getAucChangeType()).isEqualTo(UPDATED_AUC_CHANGE_TYPE);
        assertThat(testAudUserChange.getAucChangeDate()).isEqualTo(UPDATED_AUC_CHANGE_DATE);
        assertThat(testAudUserChange.getAucUserId()).isEqualTo(UPDATED_AUC_USER_ID);
        assertThat(testAudUserChange.getAucDepartmentId()).isEqualTo(UPDATED_AUC_DEPARTMENT_ID);
        assertThat(testAudUserChange.getAucModifiedUserId()).isEqualTo(UPDATED_AUC_MODIFIED_USER_ID);
        assertThat(testAudUserChange.getAucPermissions()).isEqualTo(UPDATED_AUC_PERMISSIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingAudUserChange() throws Exception {
        int databaseSizeBeforeUpdate = audUserChangeRepository.findAll().size();

        // Create the AudUserChange
        AudUserChangeDTO audUserChangeDTO = audUserChangeMapper.toDto(audUserChange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAudUserChangeMockMvc.perform(put("/api/aud-user-changes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(audUserChangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AudUserChange in the database
        List<AudUserChange> audUserChangeList = audUserChangeRepository.findAll();
        assertThat(audUserChangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAudUserChange() throws Exception {
        // Initialize the database
        audUserChangeRepository.saveAndFlush(audUserChange);

        int databaseSizeBeforeDelete = audUserChangeRepository.findAll().size();

        // Delete the audUserChange
        restAudUserChangeMockMvc.perform(delete("/api/aud-user-changes/{id}", audUserChange.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AudUserChange> audUserChangeList = audUserChangeRepository.findAll();
        assertThat(audUserChangeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
