package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.service.UserService;
import com.ntnt.highblog.dmm.service.neo4j.UserNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserListBloc {
    private final UserNodeService userNodeService;
    private final UserService userService;

    public UserListBloc(final UserNodeService userNodeService, final UserService userService) {
        this.userNodeService = userNodeService;
        this.userService = userService;
    }

    public Page<User> fetchRelatedUsers(String nickName, BasePaginationReq basePaginationReq) {
        log.info("Fetch related id #{} with page #{}", nickName, basePaginationReq);
        User user = userService.getByNickName(nickName);
        Page<UserNode> userNodePage =
            userNodeService.fetchListRelatedUser(user.getId(),
                                                 PaginationHelper.generatePageRequest(basePaginationReq));

        Map<Long, User> usersMap = userService.fetchByIdIn(userNodePage.getContent()
                                                                    .stream()
                                                                    .map(UserNode::getId)
                                                                    .collect(Collectors.toList()))
                                           .stream()
                                           .collect(Collectors.toMap(User::getId, u -> u));

        return userNodePage.map(userNode -> usersMap.get(userNode.getId()));
    }
}
