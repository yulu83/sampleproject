package au.com.anz.sampleproject.sampleproject;

import au.com.anz.sampleproject.sampleproject.domain.Account;
import au.com.anz.sampleproject.sampleproject.domain.AccountType;
import au.com.anz.sampleproject.sampleproject.domain.Transaction;
import au.com.anz.sampleproject.sampleproject.domain.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtil {

    public static Account generateAccount() {
        final String userId = "abc";
        LocalDate currentDate = LocalDate.now();
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountName("accountName");
        account.setAccountNumber("accountNumber");
        account.setAccountType(AccountType.Current);
        account.setBalanceDate(currentDate);
        account.setCurrency("AUD");
        account.setOpeningAvailableBalance(1234.34);
        return account;
    }

    public static List<Transaction> generateTransactions(int size, String userId, String accountNumber) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    Transaction transaction = new Transaction();
                    transaction.setUserId(userId);
                    transaction.setAccountName("account name");
                    transaction.setAccountNumber(accountNumber);
                    transaction.setCreditAmount(Double.valueOf(i));
                    transaction.setCurrency("SGD");
                    transaction.setValueDate(LocalDate.now());
                    transaction.setTransactionType(TransactionType.Credit);
                    return transaction;
                }).collect(Collectors.toList());
    }
}
