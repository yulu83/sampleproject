package au.com.anz.sampleproject.sampleproject.controllers.v1;

import au.com.anz.sampleproject.sampleproject.domain.AccountDTO;
import au.com.anz.sampleproject.sampleproject.domain.TransactionDTO;
import au.com.anz.sampleproject.sampleproject.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static au.com.anz.sampleproject.sampleproject.constants.AccountConstants.ACCOUNT_END_POINT_V1;
import static au.com.anz.sampleproject.sampleproject.constants.TransactionConstants.TRANSACTION_END_POINT_V1;

@RestController
@RequestMapping("/api")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * The assumption here is that the invocation of this endpoint is coming from
     * e.g. API gateway, which ensures the uid is for the correct authenticated user.
     *
     * @param userId
     * @return {@link ResponseEntity}<{@link List}<{@link AccountDTO}>>
     */
    @GetMapping(value = TRANSACTION_END_POINT_V1 + "/{uid}/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable("uid") String userId,
                                                                @PathVariable("accountNumber") String accountNumber,
                                                                Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("GetTransactions with {}, {}", userId, accountNumber);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getTransactions(userId, accountNumber, pageable));
    }
}
