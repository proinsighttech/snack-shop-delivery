package com.proinsight.domain.service;

import com.proinsight.domain.exception.SnackShopNotFoundException;
import com.proinsight.domain.model.PaymentMethod;
import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.model.User;
import com.proinsight.domain.repository.SnackShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SnackShopService {

    @Autowired
    private SnackShopRepository snackShopRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;

    @Transactional
    public SnackShop save(SnackShop snackShop) {
        return snackShopRepository.save(snackShop);
    }

    @Transactional
    public void active(Long snackShopId) {
        SnackShop curentSnackShop = findOrThrow(snackShopId);
        curentSnackShop.active();
    }

    @Transactional
    public void desactive(Long snackShopId) {
        SnackShop currentSnackShop = findOrThrow(snackShopId);
        currentSnackShop.desactive();
    }


    @Transactional
    public void removePaymentMethod(Long snackShopId, Long paymentMethodId) {
        SnackShop snackShop = findOrThrow(snackShopId);
        PaymentMethod paymentMethod = paymentMethodService.findOrThrow(paymentMethodId);
        snackShop.removePaymentMethod(paymentMethod);
    }

    @Transactional
    public void addPaymentMethod(Long snackShopId, Long paymentMethodId) {
        SnackShop snackShop = findOrThrow(snackShopId);
        PaymentMethod paymentMethod = paymentMethodService.findOrThrow(paymentMethodId);
        snackShop.addPaymentMethod(paymentMethod);
    }

    @Transactional
    public void removeAdmin(Long snackShopId, Long userId) {
        SnackShop snackShop = findOrThrow(snackShopId);
        User user = userService.findOrThrow(userId);
        snackShop.removeAdmin(user);
    }

    @Transactional
    public void addAdmin(Long snackShopId, Long userId) {
        SnackShop snackShop = findOrThrow(snackShopId);
        User user = userService.findOrThrow(userId);
        snackShop.addAdmin(user);
    }

    public SnackShop findOrThrow(Long snackShopId) {
        return snackShopRepository.findById(snackShopId)
                .orElseThrow(() -> new SnackShopNotFoundException(snackShopId));
    }

}

