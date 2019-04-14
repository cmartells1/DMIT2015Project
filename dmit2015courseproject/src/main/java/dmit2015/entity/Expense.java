package dmit2015.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Expense implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@PastOrPresent
	@Column(nullable = false)
	private LocalDate date = LocalDate.now();
	
	@DecimalMin(value = "1.00", message="Amount must be greater then $1.00")
	@Column(nullable = false)
	private BigDecimal amount;	
	
	@NotBlank(message="Description cannot be empty")
	@Size(min=3,max=128, message="Description must contain at least 3 characters and up to 128 characters")
	@Column(nullable = false, length=128)
	private String description;
	
	public Expense(long id, LocalDate date, BigDecimal amount, String description) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.description = description;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
