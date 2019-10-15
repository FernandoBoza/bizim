package com.fernandoboza.bizim.Controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import com.fernandoboza.bizim.Configs.JwtTokenProvider;
import com.fernandoboza.bizim.Models.Employee;
import com.fernandoboza.bizim.Repositories.EmployeeRepo;
import com.fernandoboza.bizim.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService employeeService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Employee data) {
        try {
            String email = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
            Employee e = this.employeeRepo.findEmployeeByEmail(email);
            String token = jwtTokenProvider.createToken(email, e.getRole());
            Map<Object, Object> model = new HashMap<>();
            model.put("employee", e);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Employee employee) {
        System.out.println(employee.getFirst_name());
        Employee employeeExist = employeeService.findEmployeeByEmail(employee.getEmail());
        if (employeeExist != null) {
            throw new BadCredentialsException("User with email: " + employee.getEmail() + " already exists");
        }
        employeeService.saveEmployee(employee);
        Map<Object, Object> model = new HashMap<>();
        model.put("employee", employee);
        model.put("message", "User registered successfully");
        return ok(model);
    }
}
