package pe.com.bank.transfer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bank.transfer.entity.TransferEntity;
import pe.com.bank.transfer.service.TransferService;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class TransferController {

    TransferService transferService;

    @PostMapping("/transfer")
    public Mono<TransferEntity> createTransfer(@RequestBody TransferEntity transferEntity) {
        return transferService.createTransfer(transferEntity);
    }
}