package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.RegisterReq;
import com.ntnt.highblog.dmm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Collection<User> fetchByIdIn(final Collection<Long> ids){
        log.info("Fetch list users by id in #{}", ids);

        if(ObjectUtils.isEmpty(ids))
            return Collections.emptyList();

        return userRepository.findByIdIn(ids);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        log.info("Get user by id #{}", id);

        return userRepository.findById(id)
                             .orElseThrow(() -> new ObjectNotFoundException("user"));
    }

    @Transactional(readOnly = true)
    public User getByNickName(final String nickName) {
        log.info("Get user by id");

        return userRepository.findByNickName(nickName)
                             .orElseThrow(() -> new ObjectNotFoundException("user"));
    }
//
//    @Transactional(readOnly = true)
//    public User getByAccountId(final Long accountId){
//        log.info("Get user by accountId #{}", accountId);
//
//        return userRepository.getByAccountId(accountId)
//                             .orElseThrow(()->new ObjectNotFoundException("user"));
//    }

    @Transactional
    public void saveNew(final User user) {
        log.info("Save new user with info #{}", user);

        validateUserBeforeSaveNew(user);

        userRepository.save(user);
    }

    private void validateUserBeforeSaveNew(final User user) {
        if (ObjectUtils.isNotEmpty(user.getId())) {
            throw new ValidatorException("Invalid user id", "id");
        }
    }

    @Transactional
    public void save(final User user) {
        log.info("Save user with info #{}", user);

        validateUserBeforeSave(user);

        userRepository.save(user);
    }

    @Transactional
    public User create(final RegisterReq registerReq) {
        log.info("Create user with registerReq #{}", registerReq);

        if(userRepository.existsByNickName(registerReq.getNickName())) {
            throw new ValidatorException("Already exist", "nickname");
        }

        User user = User.builder()
                        .nickName(registerReq.getNickName())
                        .firstName(registerReq.getFirstName()).lastName(registerReq.getLastName())
                        .genderType(registerReq.getGenderType())
                        .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean existsByNickName(final String nickName){
        log.info("Exists by nick name #{}", nickName);

        return userRepository.existsByNickName(nickName);
    }

    @Transactional
    public void saveAvatar(final Long id, String path){
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("user"));
        user.setImagePath(path);
        userRepository.save(user);
    }

    @Transactional
    public void saveBackground(Long id, String path) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("user"));
        user.setBackgroundPath(path);
        userRepository.save(user);
    }

    private void validateUserBeforeSave(final  User user) {
        if (user.getId() != SecurityHelper.getCurrentUserId())
            throw new ValidatorException("Invalid user id", "userId");
    }
    @Transactional(readOnly = true)
    public Page<User> fetchUsers(final String nickname, PageRequest pageRequest) {
        return userRepository.fetchUsers(nickname,pageRequest);
    }

    @Transactional(readOnly = true)
    public Long countUsers() {
        return userRepository.count();
    }
}
