package com.smallworld.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;

public class TransactionDataFetcherTest {
    private TransactionDataFetcher dataFetcher;
    private List<Transaction> testTransactions;
  
    @Before
    void setUp() {
        // Create sample test transactions
        testTransactions = new ArrayList<>();
        testTransactions.add(new Transaction(1, 100.0, "Sender1", 30, "Beneficiary1", 25, null, false, null));
        testTransactions.add(new Transaction(2, 200.0, "Sender2", 35, "Beneficiary2", 28, 1, true, "Issue 1"));
        testTransactions.add(new Transaction(3, 300.0, "Sender3", 40, "Beneficiary3", 29, 2, false, "Issue 2"));

        // Add more test transactions as needed

        // Initialize the dataFetcher with test transactions
        dataFetcher = new TransactionDataFetcher(testTransactions);
    }
    
    
    @Test
    public void testGetTotalTransactionAmountSentBy() {
        // Specify the sender's full name for which you want to calculate the total amount.
        String senderFullName = "Sender2";

        // Call the method to calculate the total amount sent by the specified sender.
        double totalAmountSent = dataFetcher.getTotalTransactionAmountSentBy(senderFullName);

        // Define the expected total amount based on the test data.
        double expectedTotalAmount = 200.0;

        // Assert that the calculated total amount matches the expected total amount.
        assertEquals(expectedTotalAmount, totalAmountSent, 0.001);
    }
    

}
