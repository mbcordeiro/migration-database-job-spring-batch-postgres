package com.matheuscordeiro.domain;

public class BankData {
	private int id;
	private int personId;
	private int agency;
	private int account; 
	private int bank;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getAgency() {
		return agency;
	}
	public void setAgency(int agency) {
		this.agency = agency;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getBank() {
		return bank;
	}
	public void setBank(int bank) {
		this.bank = bank;
	}
	
}
