package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.domain.TransactionDTO;
import au.com.anz.sampleproject.sampleproject.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<TransactionDTO> getTransactions(String userId,
                                                String accountNumber,
                                                Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("GetTransactions {}, {}, {}", userId, accountNumber, pageable);
        }
        return transactionRepository.findByUserIdAndAccountNumber(userId, accountNumber, pageable)
                .stream()
                .map(t -> modelMapper.map(t, TransactionDTO.class))
                .collect(Collectors.toList());
    }
}
