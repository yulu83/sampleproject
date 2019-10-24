package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.domain.TransactionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    /**
     * Get Transactions by user id and Account number
     * User id is needed for authenticated user, and only
     * returned transactions belong to that user.
     * The user id filed can be from e.g. API gateway
     * @param accountNumber
     * @param pageable
     * @return List of {@link TransactionDTO}
     */
    List<TransactionDTO> getTransactions(String userId, String accountNumber, Pageable pageable);
}
