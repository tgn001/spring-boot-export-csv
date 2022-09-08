package com.techgeeknext.controller;

import com.techgeeknext.repository.EmployeeRepository;
import com.techgeeknext.util.CSVWriterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/csv")
    public void employeeDetailsReport(HttpServletResponse response) throws IOException {

        Random random = new Random();
        String fileType = "attachment; filename=employee_details_" + random.nextInt(1000) + ".csv";
        response.setHeader("Content-Disposition", fileType);
        response.setContentType("text/csv");

        CSVWriterUtility.employeeDetailReport(response,
                employeeRepository.findAll());
    }
}
