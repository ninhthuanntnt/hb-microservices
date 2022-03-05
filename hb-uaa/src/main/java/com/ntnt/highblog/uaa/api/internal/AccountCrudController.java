package com.ntnt.highblog.uaa.api.internal;

import com.ntnt.highblog.uaa.model.dto.response.AccountRes;
import com.ntnt.highblog.uaa.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("internalAccountCrudController")
@RequestMapping("/api/v1/internal/accounts")
public class AccountCrudController {

    private final AccountService accountService;

    public AccountCrudController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(params = "userIds")
    public ResponseEntity<List<AccountRes>> fetchByIds(@RequestParam List<Long> userIds) {
        return ResponseEntity.ok(accountService.fetchByUserIdIn(userIds)
                                               .stream()
                                               .map(account -> new AccountRes(account.getId(),
                                                                              account.getUsername()))
                                               .collect(Collectors.toList()));
    }
}
