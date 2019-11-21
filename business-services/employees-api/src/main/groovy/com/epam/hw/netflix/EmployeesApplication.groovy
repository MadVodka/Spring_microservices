package com.epam.hw.netflix

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class EmployeesApplication {
    static void main(String[] args) {
        SpringApplication.run(EmployeesApplication, args)
    }
}
