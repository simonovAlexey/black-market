package com.simonov.blackMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.simonov.blackMarket.entity.User;
import com.simonov.blackMarket.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byPhoneNumber =  new User(333,username);
        return new CustomUserDetails(byPhoneNumber);
    }

    public static class CustomUserDetails implements UserDetails {

        private final SimpleGrantedAuthority USER_ROLE = new SimpleGrantedAuthority("ROLE_USER");

        private final SimpleGrantedAuthority USER_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

        private final Collection<? extends GrantedAuthority> ROLES_USER =
                Collections.singletonList(USER_ROLE);
        private final Collection<? extends GrantedAuthority> ROLES_ADMIN =
                Collections.singletonList(USER_ADMIN);

        private final Collection<? extends GrantedAuthority> ROLES_USER_AND_ADMIN =
                Arrays.asList(USER_ROLE, USER_ADMIN);

        private final Integer id;

        private final String username;

        private final String password;

        private final Collection<? extends GrantedAuthority> roles;

        public CustomUserDetails(final User user) {
            this.id = user.getId();
            this.username = user.getPhoneNumber();
            roles =  ROLES_ADMIN;
            this.password= "821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a";
//            this.password= "jwtpass";
        }

        public Integer getId() {
            return id;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return roles;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


    }

}
