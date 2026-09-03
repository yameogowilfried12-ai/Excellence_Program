package com.bit.mango.salesmarketing.delivery;

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
 * Unit test for ShipmentService - same pattern as CustomerServiceTest
 * (read that one first if this looks unfamiliar). The repository is
 * mocked, so no real database is used here.
 */
@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentRepository repository;

    @InjectMocks
    private ShipmentService service;

    private Shipment sample;

    @BeforeEach
    void setUp() {
        sample = new Shipment();
        sample.setShipmentId(1);
        sample.setShipmentCode("SHP-992-BFA-GER");
        sample.setStatus("in_transit");
    }

    @Test
    void getAll_returnsEveryItemFromRepository() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<Shipment> result = service.getAll();

        assertThat(result).hasSize(1);
    }

    @Test
    void getById_whenItemExists_returnsIt() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));

        Shipment result = service.getById(1);

        assertThat(result.getShipmentId()).isEqualTo(1);
    }

    @Test
    void getById_whenItemDoesNotExist_throwsException() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(999))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("999");
    }

    @Test
    void create_savesAndReturnsTheNewItem() {
        when(repository.save(any(Shipment.class))).thenAnswer(inv -> inv.getArgument(0));

        Shipment result = service.create(sample);

        assertThat(result.getShipmentId()).isEqualTo(1);
        verify(repository, times(1)).save(any(Shipment.class));
    }

    @Test
    void update_savesTheUpdatedItem() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));
        when(repository.save(any(Shipment.class))).thenAnswer(inv -> inv.getArgument(0));

        Shipment changes = new Shipment();
        changes.setShipmentCode("SHP-992-BFA-GER-R");
        changes.setStatus("delivered");

        Shipment result = service.update(1, changes);

        assertThat(result).isNotNull();
        // id must stay the same after an update
        assertThat(result.getShipmentId()).isEqualTo(1);
        verify(repository, times(1)).save(any(Shipment.class));
    }

    @Test
    void delete_callsRepositoryDeleteById() {
        service.delete(1);

        verify(repository, times(1)).deleteById(1);
    }
}
