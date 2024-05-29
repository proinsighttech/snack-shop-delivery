package com.proinsight.domain.service;

import com.proinsight.domain.exception.EntityInUseException;
import com.proinsight.domain.exception.PaymentMethodNotFoundException;
import com.proinsight.domain.model.PaymentMethod;
import com.proinsight.domain.repository.PaymentMethodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {
    private static final String MSG_PAYMENT_METHOD_IN_USE
            = "Forma de Pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void delete(Long paymentMethodId) {
        try {
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new PaymentMethodNotFoundException(paymentMethodId);
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_PAYMENT_METHOD_IN_USE, paymentMethodId));
        }
    }

    public PaymentMethod findOrThrow(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }
}
