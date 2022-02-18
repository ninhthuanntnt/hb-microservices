package com.ntnt.highblog.dmm.api.internal;

import com.ntnt.highblog.dmm.mapper.SubscriptionMapper;
import com.ntnt.highblog.dmm.model.response.SubscriptionRes;
import com.ntnt.highblog.dmm.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("internalSubscriptionListController")
@RequestMapping("/api/v1/internal/subscriptions")
public class SubscriptionListController {

    private final SubscriptionService subscriptionService;

    public SubscriptionListController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<SubscriptionRes>> fetchSubscriptionsByUserId(Long userId) {
        return ResponseEntity.ok(SubscriptionMapper
                                     .INSTANCE
                                     .toListSubscriptionRes(subscriptionService.fetchFollowerIdsByUserId(userId)));
    }
}
