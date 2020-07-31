package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AudUserChangeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AudUserChange.class);
        AudUserChange audUserChange1 = new AudUserChange();
        audUserChange1.setId(1L);
        AudUserChange audUserChange2 = new AudUserChange();
        audUserChange2.setId(audUserChange1.getId());
        assertThat(audUserChange1).isEqualTo(audUserChange2);
        audUserChange2.setId(2L);
        assertThat(audUserChange1).isNotEqualTo(audUserChange2);
        audUserChange1.setId(null);
        assertThat(audUserChange1).isNotEqualTo(audUserChange2);
    }
}
