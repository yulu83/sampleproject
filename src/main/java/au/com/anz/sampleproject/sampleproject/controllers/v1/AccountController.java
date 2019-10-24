package au.com.anz.sampleproject.sampleproject.controllers.v1;

import au.com.anz.sampleproject.sampleproject.domain.AccountDTO;
import au.com.anz.sampleproject.sampleproject.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static au.com.anz.sampleproject.sampleproject.constants.AccountConstants.ACCOUNT_END_POINT_V1;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * The assumption here is that the invocation of this endpoint is coming from
     * e.g. API gateway, which ensures the uid is for the correct authenticated user.
     *
     * @param userId
     * @return {@link ResponseEntity}<{@link List}<{@link AccountDTO}>>
     */
    @GetMapping(value = ACCOUNT_END_POINT_V1 + "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDTO>> getAccounts(@PathVariable("uid") String userId) {
        if (log.isInfoEnabled()) {
            log.info("GetAccounts with {}", userId);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.getUserAccounts(userId));
    }

}
