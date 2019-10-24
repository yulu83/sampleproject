package au.com.anz.sampleproject.sampleproject.init;

import au.com.anz.sampleproject.sampleproject.TestUtil;
import au.com.anz.sampleproject.sampleproject.domain.Account;
import au.com.anz.sampleproject.sampleproject.domain.AccountType;
import au.com.anz.sampleproject.sampleproject.domain.Transaction;
import au.com.anz.sampleproject.sampleproject.repositories.AccountRepository;
import au.com.anz.sampleproject.sampleproject.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Profile("test")
@Slf4j
@Transactional
public class TransactionDataInitializer implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        initialTransactionDataSetUp();
    }

    private void initialTransactionDataSetUp() {
        log.info("Loading test Transaction data");
        transactionRepository.saveAll(data());
        transactionRepository.flush();
    }

    private List<Transaction> data() {
        List<Transaction> user1Transactions = TestUtil.generateTransactions(50, "abc", "account number1");
        List<Transaction> user2Transactions = TestUtil.generateTransactions(50, "def", "account number2");
        return Stream.concat(user1Transactions.stream(), user2Transactions.stream())
                .collect(Collectors.toList());
    }
}
