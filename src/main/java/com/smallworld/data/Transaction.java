package com.smallworld.data;

public class Transaction {
	
		private int mtn;
	    private double amount;
	    private String senderFullName;
	    private int senderAge;
	    private String beneficiaryFullName;
	    private int beneficiaryAge;
	    private Integer issueId;
	    private boolean issueSolved;
	    private String issueMessage;
		
	    
	    public int getMtn() {
			return mtn;
		}
		public double getAmount() {
			return amount;
		}
		public String getSenderFullName() {
			return senderFullName;
		}
		
		public int getSenderAge() {
			return senderAge;
		}
		
		public String getBeneficiaryFullName() {
			return beneficiaryFullName;
		}
		
		public int getBeneficiaryAge() {
			return beneficiaryAge;
		}

		public Integer getIssueId() {
			return issueId;
		}

		public boolean isIssueSolved() {
			return issueSolved;
		}

		public String getIssueMessage() {
			return issueMessage;
		}
		public Transaction(int mtn, double amount, String senderFullName, int senderAge, String beneficiaryFullName,
				int beneficiaryAge, Integer issueId, boolean issueSolved, String issueMessage) {
			this.mtn = mtn;
			this.amount = amount;
			this.senderFullName = senderFullName;
			this.senderAge = senderAge;
			this.beneficiaryFullName = beneficiaryFullName;
			this.beneficiaryAge = beneficiaryAge;
			this.issueId = issueId;
			this.issueSolved = issueSolved;
			this.issueMessage = issueMessage;
		}
		public Transaction() {

		}
		@Override
		public String toString() {
			return "Transaction [mtn=" + mtn + ", amount=" + amount + ", senderFullName=" + senderFullName
					+ ", senderAge=" + senderAge + ", beneficiaryFullName=" + beneficiaryFullName + ", beneficiaryAge="
					+ beneficiaryAge + ", issueId=" + issueId + ", issueSolved=" + issueSolved + ", issueMessage="
					+ issueMessage + "]";
		}
	
	    
	    
}
