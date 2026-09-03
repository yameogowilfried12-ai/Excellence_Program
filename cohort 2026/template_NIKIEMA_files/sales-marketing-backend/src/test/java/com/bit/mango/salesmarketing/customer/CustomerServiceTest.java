package com.bit.mango.salesmarketing.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * UNIT TEST for CustomerService.
 *
 * "Unit" test means: we test CustomerService completely ALONE, without
 * a real database, without Spring even starting up. We do this by
 * MOCKING the repository (@Mock) - i.e. creating a fake
 * CustomerRepository that doesn't actually talk to PostgreSQL, and
 * just returns whatever data WE tell it to return for each test.
 *
 * This is fast (no database needed, runs in milliseconds) and lets us
 * check "does my Service logic behave correctly?" in isolation from
 * "does my database connection work?" (that's a different kind of
 * test - integration tests - which we can add later).
 *
 * @ExtendWith(MockitoExtension.class) turns on Mockito's magic so the
 * @Mock and @InjectMocks annotations below actually do something.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository; // the fake repository

    @InjectMocks
    private CustomerService customerService; // the real Service, with the fake repository injected into it

    private Customer sampleCustomer;

    // This runs before EVERY test below - it sets up a fresh, known
    // Customer object we can reuse, so each test doesn't have to
    // rebuild one from scratch.
    @BeforeEach
    void setUp() {
        sampleCustomer = new Customer();
        sampleCustomer.setCustomerId(1);
        sampleCustomer.setCompanyName("BioFrucht GmbH");
        sampleCustomer.setCustomerType("distributor");
        sampleCustomer.setCountry("Germany");
        sampleCustomer.setCity("Hamburg");
        sampleCustomer.setStatus("active");
    }

    @Test
    void getAllCustomers_returnsEveryCustomerFromRepository() {
        // ARRANGE: tell the fake repository "when findAll() is called, return this list"
        when(customerRepository.findAll()).thenReturn(List.of(sampleCustomer));

        // ACT: call the real method we're testing
        List<Customer> result = customerService.getAllCustomers();

        // ASSERT: check the result is what we expect
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCompanyName()).isEqualTo("BioFrucht GmbH");
    }

    @Test
    void getCustomerById_whenCustomerExists_returnsIt() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(sampleCustomer));

        Customer result = customerService.getCustomerById(1);

        assertThat(result.getCompanyName()).isEqualTo("BioFrucht GmbH");
    }

    @Test
    void getCustomerById_whenCustomerDoesNotExist_throwsException() {
        // simulate "no row found" - the fake repository returns an empty Optional
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        // check that calling getCustomerById(999) actually throws, as CustomerService promises to do
        assertThatThrownBy(() -> customerService.getCustomerById(999))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("999");
    }

    @Test
    void createCustomer_savesAndReturnsTheNewCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setCompanyName("Nordic Organics AB");
        newCustomer.setCustomerType("retailer");

        // when the repository's save() is called with ANY Customer, just return it back
        // (this mimics what a real save() does - it returns the saved row)
        when(customerRepository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer result = customerService.createCustomer(newCustomer);

        assertThat(result.getCompanyName()).isEqualTo("Nordic Organics AB");
        assertThat(result.getCreatedAt()).isNotNull(); // CustomerService should have set this automatically
        assertThat(result.getUpdatedAt()).isNotNull();

        // VERIFY: check the repository's save() was actually called exactly once
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomer_copiesEveryFieldOntoTheExistingRow() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(sampleCustomer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer changes = new Customer();
        changes.setCompanyName("BioFrucht GmbH (Renamed)");
        changes.setCustomerType("direct_export");
        changes.setCountry("Germany");
        changes.setCity("Berlin");
        changes.setStatus("inactive");

        Customer result = customerService.updateCustomer(1, changes);

        assertThat(result.getCompanyName()).isEqualTo("BioFrucht GmbH (Renamed)");
        assertThat(result.getCity()).isEqualTo("Berlin");
        assertThat(result.getStatus()).isEqualTo("inactive");
        // the id should NOT have changed - it's still customer #1
        assertThat(result.getCustomerId()).isEqualTo(1);
    }

    @Test
    void deleteCustomer_callsRepositoryDeleteById() {
        customerService.deleteCustomer(1);

        // VERIFY: check deleteById was called with the right id, exactly once
        verify(customerRepository, times(1)).deleteById(1);
    }
}
