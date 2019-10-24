package au.com.anz.sampleproject.sampleproject.repositories;

import au.com.anz.sampleproject.sampleproject.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(String userId);
}
