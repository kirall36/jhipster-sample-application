package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AudUserChangeService;
import com.mycompany.myapp.domain.AudUserChange;
import com.mycompany.myapp.repository.AudUserChangeRepository;
import com.mycompany.myapp.service.dto.AudUserChangeDTO;
import com.mycompany.myapp.service.mapper.AudUserChangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AudUserChange}.
 */
@Service
@Transactional
public class AudUserChangeServiceImpl implements AudUserChangeService {

    private final Logger log = LoggerFactory.getLogger(AudUserChangeServiceImpl.class);

    private final AudUserChangeRepository audUserChangeRepository;

    private final AudUserChangeMapper audUserChangeMapper;

    public AudUserChangeServiceImpl(AudUserChangeRepository audUserChangeRepository, AudUserChangeMapper audUserChangeMapper) {
        this.audUserChangeRepository = audUserChangeRepository;
        this.audUserChangeMapper = audUserChangeMapper;
    }

    @Override
    public AudUserChangeDTO save(AudUserChangeDTO audUserChangeDTO) {
        log.debug("Request to save AudUserChange : {}", audUserChangeDTO);
        AudUserChange audUserChange = audUserChangeMapper.toEntity(audUserChangeDTO);
        audUserChange = audUserChangeRepository.save(audUserChange);
        return audUserChangeMapper.toDto(audUserChange);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AudUserChangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AudUserChanges");
        return audUserChangeRepository.findAll(pageable)
            .map(audUserChangeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AudUserChangeDTO> findOne(Long id) {
        log.debug("Request to get AudUserChange : {}", id);
        return audUserChangeRepository.findById(id)
            .map(audUserChangeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AudUserChange : {}", id);
        audUserChangeRepository.deleteById(id);
    }
}
