package com.ntnt.highblog.dmm.service.neo4j;

import com.ntnt.highblog.dmm.model.entity.neo4j.TagNode;
import com.ntnt.highblog.dmm.neo4j.repository.TagNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TagNodeService {

    private final TagNodeRepository repository;

    public TagNodeService(final TagNodeRepository repository) {
        this.repository = repository;
    }

    @Transactional("neo4jTransactionManager")
    public void saveAll(List<TagNode> tagNodes) {
        log.info("Save all tagNodes #{}", tagNodes);
        saveAll(tagNodes);

        repository.saveAll(tagNodes);
    }
}
