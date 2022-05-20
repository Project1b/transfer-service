package pe.com.bank.transfer.service;

import pe.com.bank.transfer.entity.TransferEntity;
import reactor.core.publisher.Mono;

public interface TransferService {

    public Mono<TransferEntity> createTransfer(TransferEntity transferEntity);

}