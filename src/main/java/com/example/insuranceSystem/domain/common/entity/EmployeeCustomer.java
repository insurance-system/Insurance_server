package com.example.insuranceSystem.domain.common.entity;


import com.example.insuranceSystem.domain.customerService.repository.entity.Customer;
import com.example.insuranceSystem.domain.employeeService.repository.entity.Employee;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class EmployeeCustomer extends DateBaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_cus_id")
    private Long empCusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private Integer satisfaction;

    public void addEmployee(Employee employee){
        this.employee = employee;
        employee.addEmployeeCustomer(this);
    }

    public void addCustomer(Customer customer){
        this.customer = customer;
        customer.addEmployeeCustomer(this);
    }

    public EmployeeCustomer(Customer customer) {
        EmployeeCustomer employeeCustomer = new EmployeeCustomer();
        employeeCustomer.addCustomer(customer);
    }

    public void evaluateSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
}
