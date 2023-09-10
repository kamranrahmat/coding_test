package com.smallworld;

import com.smallworld.data.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

	private List<Transaction> transactions;
	
	
    public TransactionDataFetcher(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
    	return transactions.stream()
    			.mapToDouble(Transaction::getAmount)
    			.sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
    		return transactions.stream()
    				.filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
    				.mapToDouble(Transaction::getAmount)
    				.sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
    	return transactions.stream()
    	.mapToDouble(Transaction::getAmount)
    	.max()
    	.orElse(0.0);//In case if their is empty amount
        	
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
    		return transactions.stream()
    				.flatMap(transaction->Stream.of(transaction.getSenderFullName(),transaction.getBeneficiaryFullName()))
    				.distinct()
    				.count();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
	/*
	 * public boolean hasOpenComplianceIssues(String clientFullName) { return
	 * transactions.stream() .anyMatch(transaction ->
	 * (transaction.getSenderFullName().equals(clientFullName) ||
	 * transaction.getBeneficiaryFullName().equals(clientFullName)) &&
	 * !transaction.isIssueSolved()); }
	 */
    
    public boolean hasOpenComplianceIssues(String clientFullName) {
        // Step 1: Filter transactions related to the specified clientFullName
        Stream<Transaction> clientTransactions = transactions.stream()
                .filter(transaction ->
                        transaction.getSenderFullName().equals(clientFullName) ||
                        transaction.getBeneficiaryFullName().equals(clientFullName));

        // Step 2: Check if any of the filtered transactions have unsolved compliance issues
        boolean hasUnsolvedIssues = clientTransactions.anyMatch(transaction -> !transaction.isIssueSolved());

        // Step 3: Return the result
        return hasUnsolvedIssues;
    }

    /**
     * Returns all transactions indexed by beneficiary name, choosing one in case of duplicates
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        return transactions.stream()
                .collect(Collectors.toMap(
                        Transaction::getBeneficiaryFullName,
                        transaction -> transaction,
                        (existingTransaction, newTransaction) -> existingTransaction // Choose the existing transaction
                ));
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        return transactions.stream()
        		.filter(transaction->!transaction.isIssueSolved())
        		.filter(transaction->transaction.getIssueId()!=null)
        		.map(Transaction::getIssueId)
        		.collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        return transactions.stream()
        		.filter(transaction->transaction.isIssueSolved())
        		.filter(transaction->transaction.getIssueMessage()!=null)
        		.map(transaction->transaction.getIssueMessage())
        		.collect(Collectors.toList());
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
    		return transactions.stream()
    				.sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
    				.limit(3)
    				//.toList();
    				.collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
       Map<String, Double> senderAmountMap =transactions.stream()
    		   .collect(Collectors.groupingBy(
    				   transaction->transaction.getSenderFullName(),
    				   Collectors.summingDouble(transaction->transaction.getAmount())
    				   ));
       return senderAmountMap.entrySet().stream()
       .max(Map.Entry.comparingByValue())
       .map(Map.Entry::getKey);
    }

}
