package com.dorapos.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.dorapos.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.dorapos.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.dorapos.domain.User.class.getName());
            createCache(cm, com.dorapos.domain.Authority.class.getName());
            createCache(cm, com.dorapos.domain.User.class.getName() + ".authorities");
            createCache(cm, com.dorapos.domain.Company.class.getName());
            createCache(cm, com.dorapos.domain.Shop.class.getName());
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".profiles");
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".productCategories");
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".productTypes");
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".systemConfigs");
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".discounts");
            createCache(cm, com.dorapos.domain.Shop.class.getName() + ".taxes");
            createCache(cm, com.dorapos.domain.ShopSection.class.getName());
            createCache(cm, com.dorapos.domain.SectionTable.class.getName());
            createCache(cm, com.dorapos.domain.SystemEventsHistory.class.getName());
            createCache(cm, com.dorapos.domain.Product.class.getName());
            createCache(cm, com.dorapos.domain.Product.class.getName() + ".variants");
            createCache(cm, com.dorapos.domain.Product.class.getName() + ".extras");
            createCache(cm, com.dorapos.domain.ProductCategory.class.getName());
            createCache(cm, com.dorapos.domain.ProductCategory.class.getName() + ".products");
            createCache(cm, com.dorapos.domain.ProductVariant.class.getName());
            createCache(cm, com.dorapos.domain.ProductExtra.class.getName());
            createCache(cm, com.dorapos.domain.ProductType.class.getName());
            createCache(cm, com.dorapos.domain.SystemConfig.class.getName());
            createCache(cm, com.dorapos.domain.EmailBalancer.class.getName());
            createCache(cm, com.dorapos.domain.Profile.class.getName());
            createCache(cm, com.dorapos.domain.EmployeeTimesheet.class.getName());
            createCache(cm, com.dorapos.domain.Orders.class.getName());
            createCache(cm, com.dorapos.domain.Orders.class.getName() + ".ordersLines");
            createCache(cm, com.dorapos.domain.OrdersLine.class.getName());
            createCache(cm, com.dorapos.domain.OrdersLine.class.getName() + ".ordersLineVariants");
            createCache(cm, com.dorapos.domain.OrdersLineVariant.class.getName());
            createCache(cm, com.dorapos.domain.OrdersLineVariant.class.getName() + ".ordersLineExtras");
            createCache(cm, com.dorapos.domain.OrdersLineExtra.class.getName());
            createCache(cm, com.dorapos.domain.Discount.class.getName());
            createCache(cm, com.dorapos.domain.Discount.class.getName() + ".products");
            createCache(cm, com.dorapos.domain.Tax.class.getName());
            createCache(cm, com.dorapos.domain.Tax.class.getName() + ".products");
            createCache(cm, com.dorapos.domain.PaymentMethod.class.getName());
            createCache(cm, com.dorapos.domain.SuspensionHistory.class.getName());
            createCache(cm, com.dorapos.domain.ShopDevice.class.getName());
            createCache(cm, com.dorapos.domain.PaymentMethodConfig.class.getName());
            createCache(cm, com.dorapos.domain.Payment.class.getName());
            createCache(cm, com.dorapos.domain.ShopChange.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
