package com.simonov.blackMarket.service;

import com.simonov.blackMarket.entity.Ad;
import com.simonov.blackMarket.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdService {

    @Autowired
    private AdRepository adRepository;

    public Ad publish(Integer id) throws IllegalStateException {
        return findDoAndSave(id, Ad::publish);
    }

    public Ad expire(Integer id) throws IllegalStateException {
        return findDoAndSave(id, ad -> ad.expire());
    }

    private Ad findDoAndSave(Integer id, Action action) {
        Ad foundAd = adRepository.findOne(id);
        Ad modifiedAd = action.perform(foundAd);
        return adRepository.save(modifiedAd);
    }

    public Ad findOne(Integer id) {
        return adRepository.findOne(id);
    }

    @FunctionalInterface
    private interface Action {

        Ad perform(Ad ad);

    }

}
