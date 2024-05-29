package com.proinsight.domain.service;

import com.proinsight.domain.exception.SequentialCodeNotFoundException;
import com.proinsight.domain.model.SequentialCode;
import com.proinsight.domain.repository.SequentialCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequentialCodeService {

    @Autowired
    private SequentialCodeRepository sequentialCodeRepository;

    @Transactional
    public synchronized SequentialCode save(SequentialCode sequentialCode) {
        return sequentialCodeRepository.save(sequentialCode);
    }

    @Transactional
    public synchronized SequentialCode saveAndIncrement(String code) {
        SequentialCode currentSequentialCode = findOrThrow(code);
        currentSequentialCode.setCode(currentSequentialCode.getCode() + 1);
        sequentialCodeRepository.updateSequanceCode(currentSequentialCode.getCategory(), currentSequentialCode.getCode());
        return currentSequentialCode;
    }

    @Transactional
    public synchronized SequentialCode saveAndDecrement(String code) {
        SequentialCode currentSequentialCode = findOrThrow(code);
        currentSequentialCode.setCode(currentSequentialCode.getCode() - 1);
        sequentialCodeRepository.updateSequanceCode(currentSequentialCode.getCategory(), currentSequentialCode.getCode());
        return currentSequentialCode;
    }

    public synchronized SequentialCode findOrThrow(String category) {
        return sequentialCodeRepository.findByCategory(category)
                .orElseThrow(() -> new SequentialCodeNotFoundException(category));
    }
}
