package dmit2015.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dmit2015.entity.Expense;
import dmit2015.repository.ExpenseRepository;

@Stateless
public class ExpenseService {

	@Inject
	private ExpenseRepository expenseRepository;
	
	public void createExpense(Expense newExpense) {
		expenseRepository.create(newExpense);
	}

	public void updateExpense(Expense existingExpense) {
		expenseRepository.edit(existingExpense);
	}
	
	public void deleteExpense(Expense existingExpense) {
		expenseRepository.remove(existingExpense);
	}
	
	public Expense findOneExpense(long expenseID) {
		return expenseRepository.find(expenseID);
	}
	
	public List<Expense> findAllExpense() {
		return expenseRepository.findAll();
	}
}
