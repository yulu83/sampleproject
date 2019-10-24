package au.com.anz.sampleproject.sampleproject.services;

import au.com.anz.sampleproject.sampleproject.domain.AccountDTO;

import java.util.List;

public interface AccountService {

    /**
     *
     * @param userId
     * @return List of {@link AccountDTO}
     */
    List<AccountDTO> getUserAccounts(String userId);
}
