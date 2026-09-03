package com.bit.mango.salesmarketing.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * A "Repository" is how your code talks to the database for a given
 * table - it's your toolbox of "find/save/delete customers" actions.
 *
 * The magic: you don't write the implementation. JpaRepository already
 * gives you findAll(), findById(), save(), deleteById(), etc. for free,
 * just by extending it. You only add extra methods here if you need a
 * custom search that isn't covered by those defaults - like the one
 * below, findByStatus.
 *
 * <Customer, Integer> means: "this repository manages Customer objects,
 * and their ID (primary key) is an Integer".
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Spring reads the METHOD NAME itself and generates the SQL query
    // for you. This line alone becomes:
    //   SELECT * FROM customers WHERE status = ?
    // No SQL, no implementation needed - just declare what you want.
    List<Customer> findByStatus(String status);

    List<Customer> findByCountry(String country);
}
