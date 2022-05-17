package pe.com.bank.transfer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.bank.transfer.entity.AccountDTO;
import reactor.core.publisher.Mono;

@Component
public class AccountRestClient {

    private WebClient webClient;

    @Value("${restClient.accountUrl}")
    private String AccountUrl;

    public Mono<AccountDTO> retrieveAccountA(String accountId){
        var url = AccountUrl.concat("/{id}");
        return webClient
                .get()
                .uri(url, accountId)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }

    public Mono<AccountDTO> updateAccountA(AccountDTO accountDTO, String accountId) {
        var url = AccountUrl.concat("/update/{id}");
        return webClient
                .put()
                .uri(url, accountId)
                .body(Mono.just(accountDTO), AccountDTO.class)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }
}