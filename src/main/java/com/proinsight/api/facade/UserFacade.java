package com.proinsight.api.facade;

import com.proinsight.api.model.UserModel;
import com.proinsight.api.model.input.PasswordInput;
import com.proinsight.api.model.input.UserInput;
import com.proinsight.api.model.input.UserPassInput;
import org.springframework.hateoas.CollectionModel;

public interface UserFacade {

    CollectionModel<UserModel> listAll();

    UserModel findUser(Long userId);

    UserModel addUser(UserPassInput userInput);

    UserModel updateUser(Long userId, UserInput userInput);

    void changePassword(Long userId, PasswordInput password);

    void removeUser(Long userId);

}
