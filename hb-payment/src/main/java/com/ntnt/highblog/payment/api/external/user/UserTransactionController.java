package com.ntnt.highblog.payment.api.external.user;

import com.ntnt.highblog.payment.bloc.UserTransactionBloc;
import com.ntnt.highblog.payment.helper.PaginationHelper;
import com.ntnt.highblog.payment.mapper.UserTransactionMapper;
import com.ntnt.highblog.payment.model.dto.request.BasePaginationReq;
import com.ntnt.highblog.payment.model.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user/user-transactions")
public class UserTransactionController {

    private final UserTransactionBloc userTransactionBloc;

    public UserTransactionController(final UserTransactionBloc userTransactionBloc) {
        this.userTransactionBloc = userTransactionBloc;
    }

    @GetMapping
    public ResponseEntity<?> fetchTransactions(BasePaginationReq basePaginationReq) {
        Page<UserTransaction> userTransactions = userTransactionBloc.fetchUserTransactions(basePaginationReq);
        return ResponseEntity.ok(PaginationHelper
                                     .buildBasePaginationRes(userTransactions
                                                                 .map(UserTransactionMapper.INSTANCE::toUserTransactionRes)));
    }
}