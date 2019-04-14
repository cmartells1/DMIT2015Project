package dmit2015.repository;

import dmit2015.entity.Expense;


public class ExpenseRepository extends AbstractJpaRepository<Expense> {

	public ExpenseRepository() {
		super(Expense.class);
	}

}
