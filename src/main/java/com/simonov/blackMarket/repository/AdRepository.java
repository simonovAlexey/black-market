package com.simonov.blackMarket.repository;

import com.simonov.blackMarket.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface AdRepository extends PagingAndSortingRepository<Ad, Integer> {

    @RestResource(rel = "published", path = "published")
    @Query("select ad from Ad ad where ad.type = :#{#type} and ad.currency = :#{#currency} and ad.status = 'PUBLISHED'")
    Page<Ad> findPublishedByTypeAndCurrency(@Param("type") Ad.Type type, @Param("currency") Ad.Currency currency, Pageable pageable);

    @RestResource(rel = "my", path = "my")
    @Query("select ad from Ad ad where ad.user.phoneNumber = ?#{ principal?.username }")
    Page<Ad> findMyAds(Pageable pageable);

}
