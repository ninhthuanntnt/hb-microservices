package com.ntnt.highblog.uaa.security;

import com.ntnt.highblog.uaa.enums.RoleType;
import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.uaa.error.exception.ValidatorException;
import com.ntnt.highblog.uaa.model.entity.Account;
import com.ntnt.highblog.uaa.model.entity.Role;
import com.ntnt.highblog.uaa.repository.AccountRepository;
import com.ntnt.highblog.uaa.repository.RoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public CustomUserDetailService(final AccountRepository accountRepository, final RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return accountRepository.findByUsername(username)
                                .map(this::buildSpringSecurityUser)
                                .orElseThrow(() -> new ObjectNotFoundException("account"));
    }

    private UserDetails buildSpringSecurityUser(final Account account) {
        Set<Role> roles = new HashSet<>(roleRepository.fetchByAccountId(account.getId()));

        for (Role role : roles) {
            if (role.getRoleType() == RoleType.ROLE_LOCKED_USER) {
                throw new ValidatorException("Locked account", "account");
            }
            if(role.getRoleType() == RoleType.ROLE_INACTIVE_USER){
                throw new ValidatorException("Inactive account", "account");
            }
        }

        Set<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                                                              .map(role -> new SimpleGrantedAuthority(role.getRoleType()
                                                                                                          .name()))
                                                              .collect(Collectors.toSet());

        return new CustomUserDetails(account.getId(),
                                     account.getUserId(),
                                     account.getUsername(),
                                     account.getPassword(),
                                     grantedAuthorities);
    }
}
