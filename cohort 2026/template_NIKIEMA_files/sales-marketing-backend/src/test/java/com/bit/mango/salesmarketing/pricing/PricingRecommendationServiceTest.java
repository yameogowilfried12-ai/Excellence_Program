package com.bit.mango.salesmarketing.pricing;

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
 * Unit test for PricingRecommendationService - same pattern as CustomerServiceTest
 * (read that one first if this looks unfamiliar). The repository is
 * mocked, so no real database is used here.
 */
@ExtendWith(MockitoExtension.class)
class PricingRecommendationServiceTest {

    @Mock
    private PricingRecommendationRepository repository;

    @InjectMocks
    private PricingRecommendationService service;

    private PricingRecommendation sample;

    @BeforeEach
    void setUp() {
        sample = new PricingRecommendation();
        sample.setRecommendationId(1);
        sample.setMessage("Demand is projected to surge");
    }

    @Test
    void getAll_returnsEveryItemFromRepository() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<PricingRecommendation> result = service.getAll();

        assertThat(result).hasSize(1);
    }

    @Test
    void getById_whenItemExists_returnsIt() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));

        PricingRecommendation result = service.getById(1);

        assertThat(result.getRecommendationId()).isEqualTo(1);
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
        when(repository.save(any(PricingRecommendation.class))).thenAnswer(inv -> inv.getArgument(0));

        PricingRecommendation result = service.create(sample);

        assertThat(result.getRecommendationId()).isEqualTo(1);
        verify(repository, times(1)).save(any(PricingRecommendation.class));
    }

    @Test
    void update_savesTheUpdatedItem() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));
        when(repository.save(any(PricingRecommendation.class))).thenAnswer(inv -> inv.getArgument(0));

        PricingRecommendation changes = new PricingRecommendation();
        changes.setMessage("Demand pressure easing");

        PricingRecommendation result = service.update(1, changes);

        assertThat(result).isNotNull();
        // id must stay the same after an update
        assertThat(result.getRecommendationId()).isEqualTo(1);
        verify(repository, times(1)).save(any(PricingRecommendation.class));
    }

    @Test
    void delete_callsRepositoryDeleteById() {
        service.delete(1);

        verify(repository, times(1)).deleteById(1);
    }
}
