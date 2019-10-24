package au.com.anz.sampleproject.sampleproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class TransactionDTO {

    private Long id;
    private String userId;
    private String accountNumber;
    private String accountName;
    private LocalDate valueDate;
    private String currency;
    private Double debitAmount;
    private Double creditAmount;
    private TransactionType transactionType;
    private String transactionNarrative;
}
