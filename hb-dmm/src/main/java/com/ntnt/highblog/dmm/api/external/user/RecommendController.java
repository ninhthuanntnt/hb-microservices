package com.ntnt.highblog.dmm.api.external.user;

import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.service.neo4j.UserNodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userRecommendController")
@RequestMapping("/api/v1/user/recommendation")
public class RecommendController {

    private final UserNodeService userNodeService;

    public RecommendController(final UserNodeService userNodeService) {
        this.userNodeService = userNodeService;
    }

    @GetMapping("/posts/{postId}/mark-as-read")
    public ResponseEntity<?> markUserReadPost(@PathVariable Long postId) {
        userNodeService.createUserReadPost(SecurityHelper.getCurrentUserId(), postId);
        return ResponseEntity.ok().build();
    }
}
