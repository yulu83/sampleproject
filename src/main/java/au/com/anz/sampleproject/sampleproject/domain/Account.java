package au.com.anz.sampleproject.sampleproject.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Column(name = "balance_date", columnDefinition = "DATE", nullable = false)
    private LocalDate balanceDate;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "opening_available_balance", nullable = false)
    private Double openingAvailableBalance;
}
