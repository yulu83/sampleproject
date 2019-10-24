package au.com.anz.sampleproject.sampleproject.repositories;

import au.com.anz.sampleproject.sampleproject.TestUtil;
import au.com.anz.sampleproject.sampleproject.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
public class AccountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByUserId_thenReturnAccount() {
        // given
        final Account expected = TestUtil.generateAccount();

        testEntityManager.persist(expected);
        testEntityManager.flush();

        // when
        List<Account> found = accountRepository.findByUserId(expected.getUserId());

        // then
        assertThat(found.size(), is(1));
        Account actual = found.get(0);
        assertThat(actual.getUserId(), is(expected.getUserId()));
        assertThat(actual.getAccountName(), is(expected.getAccountName()));
        assertThat(actual.getBalanceDate(), is(expected.getBalanceDate()));
        assertThat(actual.getCurrency(), is(expected.getCurrency()));
        assertThat(actual.getOpeningAvailableBalance(), is(expected.getOpeningAvailableBalance()));
    }

    @Test
    public void whenFindByNonExistingUserId_thenReturnEmptyList() {
        // when
        List<Account> found = accountRepository.findByUserId("nonexistingid");

        // then
        assertThat(found.isEmpty(), is(true));
    }
}