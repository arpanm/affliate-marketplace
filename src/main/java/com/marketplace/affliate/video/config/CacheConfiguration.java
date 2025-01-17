package com.marketplace.affliate.video.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.marketplace.affliate.video.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.marketplace.affliate.video.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.marketplace.affliate.video.domain.User.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Authority.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.User.class.getName() + ".authorities");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".posts");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".reviews");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".aiSessions");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".affinityVectors");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".contactsMades");
            createCache(cm, com.marketplace.affliate.video.domain.VideoUser.class.getName() + ".contactsReceiveds");
            createCache(cm, com.marketplace.affliate.video.domain.BankDetails.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Contact.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.VideoPost.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.VideoPost.class.getName() + ".reviews");
            createCache(cm, com.marketplace.affliate.video.domain.VideoPost.class.getName() + ".changesHistories");
            createCache(cm, com.marketplace.affliate.video.domain.VideoPost.class.getName() + ".tags");
            createCache(cm, com.marketplace.affliate.video.domain.VideoPost.class.getName() + ".affinityVectors");
            createCache(cm, com.marketplace.affliate.video.domain.VideoTag.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.VideoTag.class.getName() + ".posts");
            createCache(cm, com.marketplace.affliate.video.domain.Sponsor.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Sponsor.class.getName() + ".adminUsers");
            createCache(cm, com.marketplace.affliate.video.domain.Sponsor.class.getName() + ".sponsoredCompetitions");
            createCache(cm, com.marketplace.affliate.video.domain.Competition.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Competition.class.getName() + ".prizes");
            createCache(cm, com.marketplace.affliate.video.domain.Competition.class.getName() + ".paymentsFromSponsors");
            createCache(cm, com.marketplace.affliate.video.domain.Competition.class.getName() + ".competitionPosts");
            createCache(cm, com.marketplace.affliate.video.domain.Prize.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Prize.class.getName() + ".winners");
            createCache(cm, com.marketplace.affliate.video.domain.CompetitionWinner.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.CompetitionPaymentFromSponsor.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.CompetitionPaymentToWinner.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Review.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Review.class.getName() + ".changesHistories");
            createCache(cm, com.marketplace.affliate.video.domain.Affinity.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.Affinity.class.getName() + ".posts");
            createCache(cm, com.marketplace.affliate.video.domain.Affinity.class.getName() + ".users");
            createCache(cm, com.marketplace.affliate.video.domain.VideoPostChangeHistory.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.ReviewChangeHistory.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.AiTool.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.AiTool.class.getName() + ".aiSessions");
            createCache(cm, com.marketplace.affliate.video.domain.AiToolSession.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.AiToolSession.class.getName() + ".chats");
            createCache(cm, com.marketplace.affliate.video.domain.AiToolSession.class.getName() + ".payments");
            createCache(cm, com.marketplace.affliate.video.domain.AiToolChat.class.getName());
            createCache(cm, com.marketplace.affliate.video.domain.AiToolPayment.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
