package com.simonov.blackMarket.repository;

import com.simonov.blackMarket.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository extends CrudRepository<User, Integer> {

    @RestResource(exported = false)
    User findByPhoneNumber(String phoneNumber);

    @RestResource(rel = "current-user", path = "current-user")
    @Query("select user from User user where user.phoneNumber = ?#{ principal?.username }")
    User findMyself();

    @Override
    <S extends User> S save(S entity);

    @RestResource(exported = false)
    @Override
    <S extends User> Iterable<S> save(Iterable<S> entities);

    @Override
    User findOne(Integer integer);

//    @RestResource(exported = false)
    @Override
    boolean exists(Integer aLong);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Iterable<User> findAll();

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Iterable<User> findAll(Iterable<Integer> longs);

//    @RestResource(exported = false)
    @Override
    long count();

    @Override
    void delete(Integer aLong);

//    @RestResource(exported = false)
    @Override
    void delete(User entity);

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends User> entities);

    @RestResource(exported = false)
    @Override
    void deleteAll();

    @RestResource(rel = "my", path = "my")
    @Modifying
    @Transactional
    @Query("UPDATE User u  SET u.phoneNumber='modifid' WHERE u.id = 1")
    Integer update();


}
