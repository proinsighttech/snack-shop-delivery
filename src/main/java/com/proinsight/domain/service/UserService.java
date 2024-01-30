package com.proinsight.domain.service;

import com.proinsight.domain.exception.BusinessException;
import com.proinsight.domain.exception.EntityInUseException;
import com.proinsight.domain.exception.UserNotFoundException;
import com.proinsight.domain.model.Group;
import com.proinsight.domain.model.User;
import com.proinsight.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private GroupService groupService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final String MSG_USER_IN_USE
            = "Usuário de código %d não pode ser removido, pois está em uso";

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        userRepository.detach(user);

        Optional<User> usuarioExistente = userRepository.findByEmail(user.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(user)) {
            throw new BusinessException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", user.getEmail()));
        }

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(Long userId, String currentPass, String newPass) {
        User user = findOrThrow(userId);

        if (!passwordEncoder.matches(currentPass, user.getPassword())) {
            throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
        }

        user.setPassword(passwordEncoder.encode(newPass));
    }

    @Transactional
    public void delete(Long userId) {
        try {
            userRepository.deleteById(userId);
            userRepository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(userId);
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_USER_IN_USE, userId));
        }
    }

    @Transactional
    public void removeGroup(Long userId, Long groupID) {
        User user = findOrThrow(userId);
        Group group = groupService.findOrThrow(groupID);

        user.removeGroup(group);
    }

    @Transactional
    public void addGroup(Long userId, Long groupID) {
        User user = findOrThrow(userId);
        Group group = groupService.findOrThrow(groupID);

        user.addGroup(group);
    }

    public User findOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User findByCpfOrThrow(String userCpf) {
        return userRepository.findByCpf(userCpf)
                .orElseThrow(() -> new UserNotFoundException(userCpf));
    }

}
