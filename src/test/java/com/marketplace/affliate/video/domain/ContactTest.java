package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.ContactTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contact.class);
        Contact contact1 = getContactSample1();
        Contact contact2 = new Contact();
        assertThat(contact1).isNotEqualTo(contact2);

        contact2.setId(contact1.getId());
        assertThat(contact1).isEqualTo(contact2);

        contact2 = getContactSample2();
        assertThat(contact1).isNotEqualTo(contact2);
    }

    @Test
    void senderTest() {
        Contact contact = getContactRandomSampleGenerator();
        VideoUser videoUserBack = getVideoUserRandomSampleGenerator();

        contact.setSender(videoUserBack);
        assertThat(contact.getSender()).isEqualTo(videoUserBack);

        contact.sender(null);
        assertThat(contact.getSender()).isNull();
    }
}
