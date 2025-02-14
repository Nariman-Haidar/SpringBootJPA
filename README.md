# Övning: Enkel Spring/JPA integration 

## Skapa projektet
Gå till [Spring Initializr](https://start.spring.io/) och generera ett Java-projekt med följande inställningar:

### Grundinställningar
- **Project**: Maven
- **Language**: Java
- **Spring Boot**: 3.4.2 (förvalt)
- **Group**: com.example
- **Artifact**: springjpatest
- **Packaging**: JAR
- **Version**: 21

### Dependencies
- Spring Web
- Spring Data JPA
- MySQL Driver

Öppna det genererade projektet och testa att bygga och göra Run. Notera att starten misslyckas pga av saknad databas-konfiguration.

## Skapa en databas
Skapa en databas med en tabell “customer”, med id, name och email.

## Konfigurera JDBC/Hibernate koppling
Öppna `application.properties` och lägg till följande konfiguration:
```properties
# JDBC
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

# SQL logging
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
## Projektstruktur

Spring Boot följer vanligtvis denna projektstruktur:

│── controller/         <-- Hanterar HTTP-anrop

│── service/            <-- Innehåller affärslogik

│── repository/         <-- Ansvarar för datalageråtkomst

│── model/              <-- Innehåller entitetsklasser (JPA)

│── [Application].java  <-- Huvudklass för Spring Boot

# Skapa de fyra paketen controller, service, repository och model. 
# Model 
Skapa en JPA-entitet i model kallad Customer.  
Customer { 
id, 
name, 
email 
} 
Lägg även till getters och setters. Id behöver ingen setter. 
Annotera id som en primary key med @Id och @GeneratedValue. 
# Repository 
Skapa en repository-klass för Customer inuti repository-paketet: 
package com.example.springjpatest.repository; 
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.JpaRepository; 
import com.example.springjpatest.model.Customer; 
@Repository 
public interface CustomerRepository extends JpaRepository<Customer, Long> { 
// Produces methods for CRUD, e.g. 
//      
findById(Long id) 
//      
findAll() 
// Custom method generating query based on naming convention 
Customer findByEmail(String email); 
} 
# Service 
 
Skapa service-klassen CustomerService i paketet service: 
 
package com.example.springjpatest.service; 
 
@Service 
public class CustomerService { 
 
   private final CustomerRepository customerRepository; 
 
   public CustomerService(CustomerRepository customerRepository) { 
       this.customerRepository = customerRepository; 
   } 
} 
 
 
Lägg till och implementera metoder för att hämta customer-data från service-lagret: 
 
public List<Customer> getAllCustomers(); 
public Customer getCustomerById(Long id); 
 
 
# Controller 
 
Lägg till controller-klassen CustomerController i paketet controller. 
 
package com.example.springjpatest.controller; 
import com.example.springjpatest.model.Customer; 
import com.example.springjpatest.service.CustomerService; 
 
import java.util.List; 
 
@RestController 
public class CustomerController { 
 
   private final CustomerService customerService; 
 
   public CustomerController(CustomerService customerService) { 
       this.customerService = customerService; 
   } 
} 
 
Lägg till och implementera get-mappningar för att lista samtliga customers eller enbart en 
customer via dess id: 
 
   @GetMapping("/customers") 
   public List<Customer> getAllCustomers(); 
   public Customer getCustomerById(@PathVariable Long id); 
 
# Testa 
Populera tabellen med testkunder: 
```
INSERT INTO customer (name, email) VALUES 
('Alice Johnson', 'alice.johnson@example.com'), 
('Bob Smith', 'bob.smith@example.com'), 
('Charlie Brown', 'charlie.brown@example.com'), 
('David Wilson', 'david.wilson@example.com'), 
('Emma Davis', 'emma.davis@example.com'), 
('Frank Miller', 'frank.miller@example.com'), 
('Grace Taylor', 'grace.taylor@example.com'), 
('Henry White', 'henry.white@example.com'), 
('Isabel Thomas', 'isabel.thomas@example.com'), 
('Jack Harris', 'jack.harris@example.com'); 
```

Kör igång spring-appen och testa get-mappningarna via en browser, t.ex: 
http://localhost:8080/customers 
## Bonusar

-Koppla findByEmail() i repostiry-klassen genom resten av kedjan och gör en 
get-mappning som hittar kunder via epost.  
Experimentera med mappning via namnkonvention i repository-funktioner. T.ex, lägg 
till ‘country’ i customer-tabellen och skapa en get-mappning som visar kunder 
filtrerade på land. 

## Avancerat

Lägg till en Order-entitet och koppla ihop med Customer… 
..med JPA annotationer @OneToMany och @ManyToOne 
..med CustomerRepository och OrderRepository som har en findByCustomerId() 
..med en OrderService som innehåller getOrdersByCustomerId() 
..med en OrderController som har en get-mappning för att lista alla ordrar lagda av 
en specifik kund: 
@GetMapping("/{id}/orders") 
public List<Order> getOrdersByCustomer(@PathVariable Long id) 
{ 
return orderService.getOrdersByCustomerId(id); 
}

