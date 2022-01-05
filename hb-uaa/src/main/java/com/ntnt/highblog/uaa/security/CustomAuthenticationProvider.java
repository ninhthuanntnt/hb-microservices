package com.ntnt.highblog.uaa.security;

import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.model.entity.Account;
import com.ntnt.highblog.uaa.repository.AccountRepository;
import com.ntnt.highblog.uaa.repository.RoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider
    extends AbstractUserDetailsAuthenticationProvider {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(final AccountRepository accountRepository,
                                        final RoleRepository roleRepository,
                                        final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void additionalAuthenticationChecks(final UserDetails userDetails,
                                                  final UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(final String username,
                                       final UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        Account account = accountRepository.findByUsername(username)
                                           .filter(acc -> passwordEncoder.matches(authentication.getCredentials()
                                                                                                .toString(),
                                                                                  acc.getPassword()))
                                           .orElseThrow(() -> new ObjectNotFoundException("account"));
        List<SimpleGrantedAuthority> authorities =
            roleRepository.fetchByAccountId(account.getId())
                          .stream()
                          .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                          .collect(Collectors.toList());


        return new CustomUserDetails(account.getId(),
                                     account.getUserId(),
                                     username,
                                     null,
                                     Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
