package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.TestUtil;
import au.com.anz.sampleproject.sampleproject.domain.Account;
import au.com.anz.sampleproject.sampleproject.domain.AccountDTO;
import au.com.anz.sampleproject.sampleproject.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        accountService = new AccountServiceImpl(accountRepository, modelMapper);
    }

    @Test
    public void whenGetAccountByUserId_thenReturnAccountDTO() {
        // given
        final Account account = TestUtil.generateAccount();
        final AccountDTO expected = modelMapper.map(account, AccountDTO.class);
        List<Account> accountList = Arrays.asList(account);
        BDDMockito.lenient().when(accountRepository.findByUserId("abc")).thenReturn(accountList);

        // when
        List<AccountDTO> accountDTOS = accountService.getUserAccounts(expected.getUserId());

        // then
        assertThat(accountDTOS.size(), is(1));
        AccountDTO actual = accountDTOS.get(0);
        assertThat(actual.getAccountName(), is(expected.getAccountName()));
        assertThat(actual.getAccountType(), is(expected.getAccountType()));
        // etc...
    }
}