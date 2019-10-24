package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.TestUtil;
import au.com.anz.sampleproject.sampleproject.domain.Transaction;
import au.com.anz.sampleproject.sampleproject.domain.TransactionDTO;
import au.com.anz.sampleproject.sampleproject.repositories.TransactionRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        this.transactionService = new TransactionServiceImpl(transactionRepository, modelMapper);
    }

    @Test
    public void whenGetTransactionsByUserIdAndAccountNumber_thenReturnTransactions() {
        // given
        final String userId = "userId";
        final String accountNumber = "accountNumber";
        final List<Transaction> transactions = TestUtil.generateTransactions(10, userId, accountNumber);
        Pageable pageable = PageRequest.of(1, 10);
        BDDMockito.lenient().when(transactionRepository.findByUserIdAndAccountNumber(userId, accountNumber, pageable))
                .thenReturn(transactions);

        // when
        List<TransactionDTO> transactionDTOs = transactionService.getTransactions(userId, accountNumber, pageable);

        // then
        assertThat(transactionDTOs.size(), is(10));
    }


}