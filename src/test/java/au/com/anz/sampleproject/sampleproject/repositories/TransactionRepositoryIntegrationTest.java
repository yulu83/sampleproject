package au.com.anz.sampleproject.sampleproject.repositories;

import au.com.anz.sampleproject.sampleproject.TestUtil;
import au.com.anz.sampleproject.sampleproject.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
@Transactional
class TransactionRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void whenFindByUserIdAndAccountNumber_thenReturnTransaction() {
        // given
        final String accountNumber = "accountNumber";
        final String userId = "userId";
        final List<Transaction> transactions = TestUtil.generateTransactions(50, userId, accountNumber);

        transactions.forEach(t -> testEntityManager.persist(t));
        testEntityManager.flush();

        // when
        Pageable pageable = PageRequest.of(0, 20);
        List<Transaction> found = transactionRepository.findByUserIdAndAccountNumber(userId, accountNumber, pageable);

        // then
        assertThat(found.size(), is(20));
        assertThat(found.get(0).getCreditAmount(), is(0.0));
        assertThat(found.get(19).getCreditAmount(), is(19.0));
    }

    @Test
    public void whenFindByNonExistingUserIdAndAccountNumber_thenReturnEmptyList() {
        // given
        final String accountNumber = "accountNumber";
        final String userId = "blah";
        final List<Transaction> transactions = TestUtil.generateTransactions(10, "userId", accountNumber);

        // when
        Pageable pageable = PageRequest.of(0, 20);
        List<Transaction> found = transactionRepository.findByUserIdAndAccountNumber(userId, accountNumber, pageable);

        // then
        assertThat(found.isEmpty(), is(true));
    }
}