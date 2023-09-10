package com.smallworld;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

public class TransactionDataViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Transaction> transactionsDataList = loadTransactionData();
		TransactionDataFetcher dataFetcher=new TransactionDataFetcher(transactionsDataList);
		
		// 1. Get the total transaction amount
		double tatalTransactionAmount = dataFetcher.getTotalTransactionAmount();
        System.out.println("Total Transaction Amount: " + tatalTransactionAmount);
        
        // 2. Get the total transaction amount sent by a specific sender
        String senderName = "Tom Shelby"; // Can Replace with the desired sender name
        double totalAmountSentBySender = dataFetcher.getTotalTransactionAmountSentBy(senderName);
        System.out.println("Total Amount Sent by " + senderName + ": " + totalAmountSentBySender);

        // 3. Get the maximum transaction amount
        double maxAmount = dataFetcher.getMaxTransactionAmount();
        System.out.println("Maximum Transaction Amount: " + maxAmount);
        
        // 4. Count the number of unique clients
        long uniqueClients = dataFetcher.countUniqueClients();
        System.out.println("Number of Unique Clients: " + uniqueClients);

        // 5 case 1. Check if a sender has open compliance issues
        String client1 = "Billy Kimber"; 
        if (dataFetcher.hasOpenComplianceIssues(client1)) {
            System.out.println(client1 + " has open compliance issues.");
        } else {
            System.out.println(client1 + " does not have open compliance issues.");
        }

        
        // 5 case 2. Check if a sender has open compliance issues
        String client2 = "Michael Gray"; 
        if (dataFetcher.hasOpenComplianceIssues(client2)) {
            System.out.println(client2 + " has open compliance issues.");
        } else {
            System.out.println(client2 + " does not have open compliance issues.");
        }

		
		  // 6. Get transactions indexed by beneficiary name Map<String, Transaction>
		 System.out.println("\n************Transaction By Beneficiary Name************");

        Map<String, Transaction> transactionsByBeneficiary = dataFetcher.getTransactionsByBeneficiaryName();
		  for (Map.Entry<String, Transaction> entry :
		  transactionsByBeneficiary.entrySet()) {
			String beneficiaryName =
		  entry.getKey(); Transaction transaction = entry.getValue();
		  System.out.println("Beneficiary: " + beneficiaryName);
		  System.out.println( transaction); 
		  }
		 
        
        // 7. Get the set of unsolved issue IDs
        Set<Integer> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
        System.out.println("\n***Unsolved Issue ID***");
        for (Integer issueId : unsolvedIssueIds) {
            System.out.println("Unsolved Issue ID: " + issueId);
        }
        
        // 8. Get the list of all solved issue messages
        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
        System.out.println("\n***Solved Issue Message***");
        for (String issueMessage : solvedIssueMessages) {
            System.out.println("Solved Issue Message: " + issueMessage);
        }
        
        List<Transaction> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
        System.out.println("\nTop 3 Transactions");
        // 9. Now you can work with the top3Transactions
        for (Transaction transaction : top3Transactions) {
        	System.out.println("***************");
        	System.out.println("Transaction ID: " + transaction.getMtn());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Sender Name: " + transaction.getSenderFullName());
            System.out.println("Sender Age: " + transaction.getSenderAge());
            System.out.println("Beneficiary Name: " + transaction.getBeneficiaryFullName());
            System.out.println("Beneficiary Age: " + transaction.getBeneficiaryAge());
            System.out.println("Issue Id: " + transaction.getIssueId());
            System.out.println("Issue Solved: " + (transaction.isIssueSolved()?"Yes":"No"));
            System.out.println("Issue Message: " + transaction.getIssueMessage()+"\n");
            
        }
        
        // 10. Get the sender with the most total sent amount
        Optional<String> topSender = dataFetcher.getTopSender();
        if (topSender.isPresent()) {
            System.out.println("Top Sender: " + topSender.get());
        } else {
            System.out.println("No top sender found.");
        }
    }




	private static List<Transaction> loadTransactionData() {
	    ObjectMapper objectMapper = new ObjectMapper();

	    try {
	        // Load the JSON file from the project's root folder
	        File jsonFile = new File("transactions.json");

	        // You can also get the absolute path to the file if needed
	        // String absolutePath = jsonFile.getAbsolutePath();

	        return objectMapper.readValue(
	            jsonFile,
	            objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class)
	        );
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to load transaction data", e);
	    }
	}
}
