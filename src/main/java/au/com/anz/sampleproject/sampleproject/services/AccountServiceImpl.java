package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.domain.AccountDTO;
import au.com.anz.sampleproject.sampleproject.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AccountDTO> getUserAccounts(String userId) {
        if (log.isInfoEnabled()) {
            log.info("GetUserAccounts with {}", userId);
        }
        return accountRepository.findByUserId(userId)
                .stream().map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }
}
