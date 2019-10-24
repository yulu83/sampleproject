package au.com.anz.sampleproject.sampleproject.init;

import au.com.anz.sampleproject.sampleproject.domain.Account;
import au.com.anz.sampleproject.sampleproject.domain.AccountType;
import au.com.anz.sampleproject.sampleproject.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("test")
@Slf4j
@Transactional
public class AccountDataInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        initialAccountDataSetUp();
    }

    private void initialAccountDataSetUp() {
        log.info("Loading test Account data");
        accountRepository.saveAll(data());
        accountRepository.flush();
    }

    private List<Account> data() {
        Account acc1 = new Account();
        acc1.setUserId("abc");
        acc1.setAccountNumber("account number1");
        acc1.setAccountName("account name1");
        acc1.setAccountType(AccountType.Current);
        acc1.setBalanceDate(LocalDate.of(2019, 10, 23));
        acc1.setCurrency("AUD");
        acc1.setOpeningAvailableBalance(123.25);

        Account acc2 = new Account();
        acc2.setUserId("abc");
        acc2.setAccountNumber("account number2");
        acc2.setAccountName("account name2");
        acc2.setAccountType(AccountType.Savings);
        acc2.setBalanceDate(LocalDate.of(2019, 10, 23));
        acc2.setCurrency("USD");
        acc2.setOpeningAvailableBalance(1.25);

        Account acc3 = new Account();
        acc3.setUserId("def");
        acc3.setAccountNumber("account number3");
        acc3.setAccountName("account name3");
        acc3.setAccountType(AccountType.Current);
        acc3.setBalanceDate(LocalDate.of(2019, 10, 23));
        acc3.setCurrency("USD");
        acc3.setOpeningAvailableBalance(11.25);

        return Arrays.asList(
            acc1, acc2, acc3
        );
    }
}
