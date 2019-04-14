package dmit2015.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dmit2015.entity.Expense;
import dmit2015.service.ExpenseService;
import lombok.Getter;
import lombok.Setter;


@Named
@ViewScoped
public class ExpenseCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ExpenseCRUDController.class.getName());
	
	@Inject
	private ExpenseService expenseService;
	
	private List<Expense> expenses;					// +getter
	
	/** The current Expense to create, edit, update, or delete */
	@Getter @Setter 
	private  Expense expenseDetail = new Expense();	// +getter +setter	
	
	@Getter @Setter 
	private boolean editMode = false;
	
	@Getter @Setter 
	private Integer editId = null;
	
	@PostConstruct
	void init() {
		try {
			expenses = expenseService.findAllExpense();
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving expenses");
			log.fine(e.getMessage());
		}
	}
	
	public String create() {
		String outcome = null;
		try {
			expenseService.createExpense(expenseDetail);
			expenseDetail = new Expense();
			Messages.addFlashGlobalInfo("Create was succesful");
			outcome = "list?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Create was not succesful");
			log.fine(e.getMessage());
		}
		
		return outcome;
	}
	
	public String update() {
		String outcome = null;
		try {
			expenseService.updateExpense(expenseDetail);
			expenseDetail = new Expense();
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Update was succesful");
			outcome = "list?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Update was not successful");
			log.fine(e.getMessage());
		}
		
		return outcome;
	}
	
	public void delete(Expense existingExpense) {
		try {
			expenseService.deleteExpense(existingExpense);
			expenses.remove(existingExpense);
			Messages.addGlobalInfo("Delete was successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");
			log.fine(e.getMessage());
		}
	}

	public String delete() {
		String nextUrl = null;
		try {
			expenseService.deleteExpense(expenseDetail);
			expenses.remove(expenseDetail);
			expenseDetail = null;
			editId = null;
			Messages.addFlashGlobalInfo("Delete was successful");			
			nextUrl = "list?faces-redirect=true";
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");			
			log.fine(e.getMessage());
		}
		return nextUrl;
	}
	
	
	public void edit() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					expenseDetail = expenseService.findOneExpense(editId);
					if (expenseDetail != null) {
						editMode = true;
					} else {
						Messages.addFlashGlobalError("{0} is not a valid id value", editId);
						Faces.navigate("list?faces-redirect=true");						
					}
				} catch (Exception e) {
					Messages.addGlobalError("Query unsucessful");
					log.fine(e.getMessage());	
				}	
			} else {
				Faces.navigate("list?faces-redirect=true");	
			}
		} 
	}
	
	public String cancel() {
		expenseDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
	
	
	public List<Expense> getExpenses() {
		return expenses;
	}	
}
