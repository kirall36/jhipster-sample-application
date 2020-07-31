package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AudUserChangeMapperTest {

    private AudUserChangeMapper audUserChangeMapper;

    @BeforeEach
    public void setUp() {
        audUserChangeMapper = new AudUserChangeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(audUserChangeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(audUserChangeMapper.fromId(null)).isNull();
    }
}
