package emp.management.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EMPLOYEES")
public class Employee implements Serializable{

	private static final long serialVersionUID = -913832730977066579L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="EMPLOYEE_SEQ", allocationSize=1)
	@Column(name="ID")
	private Integer id;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE_NUMBER")
	private String phoneNo;
	@Column(name="SALARY")
	private BigDecimal salary;
	@Column(name="HIRE_DATE")
	private Date hireDate;
	@Column(name="DEPARTMENT_ID")
	private Integer departmentId;
	@Column(name="MANAGER_ID")
	private Integer managerId;
	@Column(name="ROLE_ID")
	private Integer roleId;
	@Column(name="CREATED_AT")
	private Date createdDate;
	@Column(name="UPDATED_AT")
	private Date updatedDate;
}
