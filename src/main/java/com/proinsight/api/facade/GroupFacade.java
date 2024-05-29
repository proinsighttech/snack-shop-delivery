package com.proinsight.api.facade;

import com.proinsight.api.model.GroupModel;
import com.proinsight.api.model.input.GroupInput;
import org.springframework.hateoas.CollectionModel;

public interface GroupFacade {

    CollectionModel<GroupModel> listAll();

    GroupModel findGroup(Long groupId);

    GroupModel addGroup(GroupInput groupInput);

    GroupModel updateGroup(Long groupId, GroupInput groupInput);

    void removeGroup(Long groupId);
}
