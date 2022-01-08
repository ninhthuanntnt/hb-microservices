package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.File;
import com.ntnt.highblog.dmm.model.request.BasePaginationReq;
import com.ntnt.highblog.dmm.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileListBloc {
    private final FileService fileService;

    public FileListBloc(final FileService fileService) {
        this.fileService = fileService;
    }

    public Page<File> fetchListImagesForCurrentUser(final BasePaginationReq basePaginationReq) {
        log.info("Fetch list images for current user");

        PageRequest pageRequest = PaginationHelper.generatePageRequest(basePaginationReq);

        return fileService.fetchListImagesByUserIdWithPageRequest(SecurityHelper.getCurrentUserId(), pageRequest);
    }
}
