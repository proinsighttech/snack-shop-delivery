package com.proinsight.api.facade;

import com.proinsight.api.model.GroupModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface UserGroupFacade {

    CollectionModel<GroupModel> listAllGroup(Long userId);

    ResponseEntity<Void> removeGroup(Long userId, Long groupId);

    ResponseEntity<Void> addGroup(Long userId, Long groupId);

}
