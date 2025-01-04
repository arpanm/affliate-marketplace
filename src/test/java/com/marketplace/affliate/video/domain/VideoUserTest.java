package com.marketplace.affliate.video.domain;

import static com.marketplace.affliate.video.domain.BankDetailsTestSamples.*;
import static com.marketplace.affliate.video.domain.ContactTestSamples.*;
import static com.marketplace.affliate.video.domain.VideoUserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.marketplace.affliate.video.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VideoUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoUser.class);
        VideoUser videoUser1 = getVideoUserSample1();
        VideoUser videoUser2 = new VideoUser();
        assertThat(videoUser1).isNotEqualTo(videoUser2);

        videoUser2.setId(videoUser1.getId());
        assertThat(videoUser1).isEqualTo(videoUser2);

        videoUser2 = getVideoUserSample2();
        assertThat(videoUser1).isNotEqualTo(videoUser2);
    }

    @Test
    void bankTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        BankDetails bankDetailsBack = getBankDetailsRandomSampleGenerator();

        videoUser.setBank(bankDetailsBack);
        assertThat(videoUser.getBank()).isEqualTo(bankDetailsBack);

        videoUser.bank(null);
        assertThat(videoUser.getBank()).isNull();
    }

    @Test
    void contactsMadeTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        videoUser.addContactsMade(contactBack);
        assertThat(videoUser.getContactsMades()).containsOnly(contactBack);
        assertThat(contactBack.getSender()).isEqualTo(videoUser);

        videoUser.removeContactsMade(contactBack);
        assertThat(videoUser.getContactsMades()).doesNotContain(contactBack);
        assertThat(contactBack.getSender()).isNull();

        videoUser.contactsMades(new HashSet<>(Set.of(contactBack)));
        assertThat(videoUser.getContactsMades()).containsOnly(contactBack);
        assertThat(contactBack.getSender()).isEqualTo(videoUser);

        videoUser.setContactsMades(new HashSet<>());
        assertThat(videoUser.getContactsMades()).doesNotContain(contactBack);
        assertThat(contactBack.getSender()).isNull();
    }

    @Test
    void contactsReceivedTest() {
        VideoUser videoUser = getVideoUserRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        videoUser.addContactsReceived(contactBack);
        assertThat(videoUser.getContactsReceiveds()).containsOnly(contactBack);
        assertThat(contactBack.getReceiver()).isEqualTo(videoUser);

        videoUser.removeContactsReceived(contactBack);
        assertThat(videoUser.getContactsReceiveds()).doesNotContain(contactBack);
        assertThat(contactBack.getReceiver()).isNull();

        videoUser.contactsReceiveds(new HashSet<>(Set.of(contactBack)));
        assertThat(videoUser.getContactsReceiveds()).containsOnly(contactBack);
        assertThat(contactBack.getReceiver()).isEqualTo(videoUser);

        videoUser.setContactsReceiveds(new HashSet<>());
        assertThat(videoUser.getContactsReceiveds()).doesNotContain(contactBack);
        assertThat(contactBack.getReceiver()).isNull();
    }
}
