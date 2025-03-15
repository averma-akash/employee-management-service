package emp.management.system.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import emp.management.system.emp.pojo.SearchEmployee;
import emp.management.system.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class EmployeeDao {
	
	@PersistenceContext
	EntityManager em;

	public List<Employee> searchEmployee(SearchEmployee searchEmp) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(null != searchEmp.getDepartmentId()) {
			predicates.add(cb.equal(root.get("departmentId"), searchEmp.getDepartmentId()));
		}
		if(StringUtils.isNotBlank(searchEmp.getEmail())) {
			predicates.add(cb.equal(root.get("email"), searchEmp.getEmail()));
		}
		if(StringUtils.isNotBlank(searchEmp.getFirstName())) {
			predicates.add(cb.like(root.get("firstName"), searchEmp.getFirstName()));
		}
		if(StringUtils.isNotBlank(searchEmp.getLastName())) {
			predicates.add(cb.like(root.get("lastName"), searchEmp.getLastName()));
		}
		if(null != searchEmp.getManagerId()) {
			predicates.add(cb.equal(root.get("managerId"), searchEmp.getManagerId()));
		}
		if(null != searchEmp.getHireDate()) {
			predicates.add(cb.equal(root.get("hireDate"), searchEmp.getHireDate()));
		}
		
		cq.where(cb.and(predicates.toArray(new Predicate[0])));
		return em.createQuery(cq).getResultList();
	}
	
	

}
