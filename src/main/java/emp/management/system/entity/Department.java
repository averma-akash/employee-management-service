package emp.management.system.entity;

import java.io.Serializable;
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

@Entity(name = "DEPARTMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="DEPARTMENTS_SEQ",initialValue = 100, allocationSize=1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String deptName;

	@Column(name = "CREATED_AT")
	private Date createdAt;

}
