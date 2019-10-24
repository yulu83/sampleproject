package au.com.anz.sampleproject.sampleproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class AccountDTO {

    private Long id;
    private String userId;
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private LocalDate balanceDate;
    private String currency;
    private Double openingAvailableBalance;
}
