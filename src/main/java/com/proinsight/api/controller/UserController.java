package com.proinsight.api.controller;

import com.proinsight.api.facade.UserFacade;
import com.proinsight.api.model.UserModel;
import com.proinsight.api.model.input.PasswordInput;
import com.proinsight.api.model.input.UserInput;
import com.proinsight.api.model.input.UserPassInput;
import com.proinsight.api.security.CheckSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping
    public CollectionModel<UserModel> listAll() {
        return userFacade.listAll();
    }

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel findUser(@PathVariable Long userId) {
        return userFacade.findUser(userId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel addUser(@RequestBody @Valid UserPassInput userInput) {
        return userFacade.addUser(userInput);
    }

    @CheckSecurity.UserGroupsPermissions.PodeAlterarUsuario
    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel updateUser(@PathVariable Long userId,
                                  @RequestBody @Valid UserInput userInput) {
        return userFacade.updateUser(userId, userInput);
    }

    @CheckSecurity.UserGroupsPermissions.PodeAlterarPropriaSenha
    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        userFacade.changePassword(userId, password);
    }

    @CheckSecurity.UserGroupsPermissions.PodeAlterarUsuario
    @DeleteMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long userId) {
        userFacade.removeUser(userId);
    }

}
