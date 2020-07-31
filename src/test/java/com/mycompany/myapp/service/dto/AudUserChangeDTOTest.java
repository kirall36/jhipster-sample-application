package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AudUserChangeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AudUserChangeDTO.class);
        AudUserChangeDTO audUserChangeDTO1 = new AudUserChangeDTO();
        audUserChangeDTO1.setId(1L);
        AudUserChangeDTO audUserChangeDTO2 = new AudUserChangeDTO();
        assertThat(audUserChangeDTO1).isNotEqualTo(audUserChangeDTO2);
        audUserChangeDTO2.setId(audUserChangeDTO1.getId());
        assertThat(audUserChangeDTO1).isEqualTo(audUserChangeDTO2);
        audUserChangeDTO2.setId(2L);
        assertThat(audUserChangeDTO1).isNotEqualTo(audUserChangeDTO2);
        audUserChangeDTO1.setId(null);
        assertThat(audUserChangeDTO1).isNotEqualTo(audUserChangeDTO2);
    }
}
