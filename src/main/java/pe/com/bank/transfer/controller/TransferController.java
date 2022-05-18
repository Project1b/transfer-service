package pe.com.bank.transfer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bank.transfer.client.AccountRestClient;
import pe.com.bank.transfer.client.TransactionRestClient;
import pe.com.bank.transfer.entity.AccountDTO;
import pe.com.bank.transfer.entity.TransactionDTO;
import pe.com.bank.transfer.entity.TransferEntity;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class TransferController {

    AccountRestClient accountRestClient;
    TransactionRestClient transactionRestClient;

    @PostMapping("/transfer")
    public Mono<TransferEntity> createTransfer(@RequestBody TransferEntity transferEntity) {
        return accountRestClient.retrieveAccountA(transferEntity.getTransferFrom())
                .flatMap(traFro -> {
                    var a = accountRestClient.updateAccountA(new AccountDTO(traFro.getId(), traFro.getAccountNumber(),
                            traFro.getAmount() - transferEntity.getAmount(), traFro.getDateOpen(),
                            traFro.getAmounttype(), traFro.getProductId(), traFro.getCustomerId()), transferEntity.getTransferFrom());
                    return a.flatMap(updAcc -> {
                        var b = accountRestClient.retrieveAccountA(transferEntity.getTransferTo());
                        return b.flatMap(traTo -> {
                            var c = accountRestClient.updateAccountA(new AccountDTO(traTo.getId(),
                                            traTo.getAccountNumber(), traTo.getAmount() + transferEntity.getAmount(),
                                            traTo.getDateOpen(), traTo.getAmounttype(), traTo.getProductId(), traTo.getCustomerId()),
                                    transferEntity.getTransferTo());
                            return c.flatMap(updAcc2 -> {
                                var d = transactionRestClient.createTransactionA(new TransactionDTO(transferEntity.getTransferFrom(), transferEntity.getAmount(), transferEntity.getDate(), transferEntity.getType()));
                                return d.flatMap(creTra -> {
                                    return Mono.empty();
                                });
                            });
                        });
                    });
                });
    }
}