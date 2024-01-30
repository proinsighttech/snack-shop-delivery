package com.proinsight.domain.service;

import com.proinsight.domain.exception.PermissionNotFoundException;
import com.proinsight.domain.model.Permission;
import com.proinsight.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findOrThrow(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }
}
