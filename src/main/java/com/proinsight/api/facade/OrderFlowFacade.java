package com.proinsight.api.facade;

import org.springframework.http.ResponseEntity;

public interface OrderFlowFacade {

    ResponseEntity<Void> receive(Long orderCode);

    ResponseEntity<Void> prepare(Long orderCode);

    ResponseEntity<Void> cancel(Long orderCode);

    ResponseEntity<Void> confirm(Long orderCode);

    ResponseEntity<Void> ready(Long orderCode);

    ResponseEntity<Void> delivery(Long orderCode);

}
