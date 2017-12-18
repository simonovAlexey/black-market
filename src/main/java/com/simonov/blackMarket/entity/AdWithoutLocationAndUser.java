package com.simonov.blackMarket.entity;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigInteger;

@Projection(name = "nacked", types = {Ad.class})
public interface AdWithoutLocationAndUser {

     Ad.Type getType();

     BigInteger getAmount();

     String getComment();

     Ad.Status getStatus();
}
