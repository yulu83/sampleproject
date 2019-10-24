package au.com.anz.sampleproject.sampleproject.repositories;

import au.com.anz.sampleproject.sampleproject.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Allows the user to input page, if the transaction history is long.
     * @param userId
     * @param accountNumber
     * @param pageable
     * @return List of {@link Transaction}
     */
    List<Transaction> findByUserIdAndAccountNumber(String userId, String accountNumber, Pageable pageable);
}
