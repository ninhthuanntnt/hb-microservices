package com.ntnt.highblog.dmm.service.neo4j;

import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import com.ntnt.highblog.dmm.neo4j.repository.UserNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
