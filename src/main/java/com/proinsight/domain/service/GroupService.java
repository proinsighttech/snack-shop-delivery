package com.proinsight.domain.service;

import com.proinsight.domain.exception.EntityInUseException;
import com.proinsight.domain.exception.GroupNotFoundException;
import com.proinsight.domain.model.Group;
import com.proinsight.domain.model.Permission;
import com.proinsight.domain.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private static final String MSG_GROUP_IN_USE
            = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService permissionService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long groupId) {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_GROUP_IN_USE, groupId));
        }
    }

    @Transactional
    public void removePermission(Long groupId, Long permissionId) {
        Group group = findOrThrow(groupId);
        Permission permissao = permissionService.findOrThrow(permissionId);

        group.removePermission(permissao);
    }

    @Transactional
    public void addPermission(Long groupId, Long permissionId) {
        Group group = findOrThrow(groupId);
        Permission permissao = permissionService.findOrThrow(permissionId);

        group.addPermission(permissao);
    }

    public Group findOrThrow(Long grupoId) {
        return groupRepository.findById(grupoId)
                .orElseThrow(() -> new GroupNotFoundException(grupoId));
    }


}
