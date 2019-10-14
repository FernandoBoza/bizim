package com.fernandoboza.bizim.Services;

import com.fernandoboza.bizim.Models.Employee;
import com.fernandoboza.bizim.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Employee findEmployeeById(String id) {
        return employeeRepo.findEmployeeById(id);
    }

    public Employee findEmployeeByEmail(String email)  { return employeeRepo.findEmployeeByEmail(email);}

    public void saveEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setRole("ADMIN");
        employeeRepo.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findEmployeeByEmail(email);
        if (employee != null) {
            List<GrantedAuthority> authorities = getUserAuthority(employee.getRole());
            return buildUserForAuthentication(employee, authorities);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(String EmployeeRole) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(EmployeeRole));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Employee employee, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), authorities);
    }

}
