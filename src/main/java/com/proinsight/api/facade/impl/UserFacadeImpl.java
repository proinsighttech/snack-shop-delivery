package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.UserInputDisassembler;
import com.proinsight.api.assembler.UserModelAssembler;
import com.proinsight.api.facade.UserFacade;
import com.proinsight.api.model.UserModel;
import com.proinsight.api.model.input.PasswordInput;
import com.proinsight.api.model.input.UserInput;
import com.proinsight.api.model.input.UserPassInput;
import com.proinsight.domain.model.User;
import com.proinsight.domain.repository.UserRepository;
import com.proinsight.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    public CollectionModel<UserModel> listAll() {
        List<User> allUsers = userRepository.findAll();
        return userModelAssembler.toCollectionModel(allUsers);
    }

    public UserModel findUser(Long userId) {
        User user = userService.findOrThrow(userId);
        return userModelAssembler.toModel(user);
    }

    public UserModel addUser(UserPassInput userInput) {
        User user = userInputDisassembler.toDomainObject(userInput);
        user = userService.save(user);
        return userModelAssembler.toModel(user);
    }

    public UserModel updateUser(Long userId, UserInput userInput) {
        User currentUser = userService.findOrThrow(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);
        return userModelAssembler.toModel(currentUser);
    }

    public void changePassword(Long userId, PasswordInput password) {
        userService.changePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }

    public void removeUser(Long userId) {
        User user = userService.findOrThrow(userId);
        userService.delete(userId);
    }

}