package com.simonov.blackMarket.controller;

import com.simonov.blackMarket.entity.Ad;
import com.simonov.blackMarket.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class AdResourceController {

    @Autowired
    private AdService adService;

    @ResponseBody
    @RequestMapping(value = "/ads/{id}/publishing", method = RequestMethod.PUT, produces = "application/hal+json")
    public PersistentEntityResource publish(@PathVariable("id") Integer id, PersistentEntityResourceAssembler assembler){
        PersistentEntityResource persistentEntityResource = assembler.toFullResource(adService.publish(id));
        return persistentEntityResource;
    }

    @RequestMapping(value = "/ads/{id}/publishing", method = RequestMethod.HEAD)
    @ResponseBody
    public void publishHead(@PathVariable("id") Integer id) {
        Ad ad = adService.findOne(id);
        if (ad == null || ad.getStatus() != Ad.Status.NEW) {
            throw new ResourceNotFoundException();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/ads/{id}/expiration", method = RequestMethod.PUT, produces = "application/hal+json")
    public PersistentEntityResource expire(@PathVariable("id") Integer id, PersistentEntityResourceAssembler assembler)
            throws IllegalStateException {
        return assembler.toFullResource(adService.expire(id));
    }

    @RequestMapping(value = "/ads/{id}/expiration", method = RequestMethod.HEAD)
    @ResponseBody
    public void expirationHead(@PathVariable("id") Integer id) {
        Ad ad = adService.findOne(id);
        if (ad == null || ad.getStatus() != Ad.Status.PUBLISHED) {
            throw new ResourceNotFoundException();
        }
    }

}