package com.bit.mango.salesmarketing.campaign;

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
 * Unit test for CampaignMetricService - same pattern as CustomerServiceTest
 * (read that one first if this looks unfamiliar). The repository is
 * mocked, so no real database is used here.
 */
@ExtendWith(MockitoExtension.class)
class CampaignMetricServiceTest {

    @Mock
    private CampaignMetricRepository repository;

    @InjectMocks
    private CampaignMetricService service;

    private CampaignMetric sample;

    @BeforeEach
    void setUp() {
        sample = new CampaignMetric();
        sample.setMetricId(1);
        sample.setClicks(120);
    }

    @Test
    void getAll_returnsEveryItemFromRepository() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<CampaignMetric> result = service.getAll();

        assertThat(result).hasSize(1);
    }

    @Test
    void getById_whenItemExists_returnsIt() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));

        CampaignMetric result = service.getById(1);

        assertThat(result.getMetricId()).isEqualTo(1);
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
        when(repository.save(any(CampaignMetric.class))).thenAnswer(inv -> inv.getArgument(0));

        CampaignMetric result = service.create(sample);

        assertThat(result.getMetricId()).isEqualTo(1);
        verify(repository, times(1)).save(any(CampaignMetric.class));
    }

    @Test
    void update_savesTheUpdatedItem() {
        when(repository.findById(1)).thenReturn(Optional.of(sample));
        when(repository.save(any(CampaignMetric.class))).thenAnswer(inv -> inv.getArgument(0));

        CampaignMetric changes = new CampaignMetric();
        changes.setClicks(185);

        CampaignMetric result = service.update(1, changes);

        assertThat(result).isNotNull();
        // id must stay the same after an update
        assertThat(result.getMetricId()).isEqualTo(1);
        verify(repository, times(1)).save(any(CampaignMetric.class));
    }

    @Test
    void delete_callsRepositoryDeleteById() {
        service.delete(1);

        verify(repository, times(1)).deleteById(1);
    }
}
