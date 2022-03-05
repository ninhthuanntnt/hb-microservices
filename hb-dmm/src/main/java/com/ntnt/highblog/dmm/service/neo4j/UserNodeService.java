package com.ntnt.highblog.dmm.service.neo4j;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import com.ntnt.highblog.dmm.neo4j.repository.UserNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserNodeService {
    private final UserNodeRepository repository;

    public UserNodeService(final UserNodeRepository repository) {
        this.repository = repository;
    }

    @Transactional("neo4jTransactionManager")
    public void saveAll(List<UserNode> userNodes) {
        log.info("Save all userNodes #{}", userNodes);

        repository.saveAll(userNodes);
    }

    public Page<UserNode> fetchListRelatedUser(final Long id, final PageRequest pageRequest) {
        log.info("Fetch list related user by id #{}", id);
        Long currentUserId = SecurityHelper.getNullableCurrentUserId().orElse(null);
        return new PageImpl<>(repository.fetchRelatedUsersById(id,
                                                               currentUserId,
                                                               pageRequest.getOffset(),
                                                               pageRequest.getPageSize()),
                              pageRequest,
                              repository.countRelatedUserById(id,
                                                              currentUserId));
    }

    public void createFollowsRelationship(final Long followerId, final Long followedUserId) {
        log.info("Create FOLLOWS relationship with followerId #{} and followedUserId #{}", followerId, followedUserId);

        if (repository.existsFollowRelationShip(followerId, followedUserId))
            return;
        else {
            repository.createFollowsRelationship(followerId, followedUserId);
        }
    }

    public void deleteFollowsRelationship(final Long followerId, final Long followedUserId) {
        log.info("Delete FOLLOWS relationship with followerId #{} and followedUserId #{}", followerId, followedUserId);

        if (repository.existsFollowRelationShip(followerId, followedUserId)) {
            repository.deleteFollowsRelationship(followerId, followedUserId);
        }
    }
}
