package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.repository.PetRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPetsDAOBeanTest {

    @Mock
    private PetRepositoryJPA petRepositoryJPA;

    @InjectMocks
    private GetPetsDAOBean getPetsDAOBean;

    @Test
    void exec_withUserId_shouldReturnPetsDAOs() {
        // Given
        Long userId = 1L;
        List<PetDAO> expectedPetDAOs = new ArrayList<>();
        expectedPetDAOs.add(new PetDAO());
        expectedPetDAOs.add(new PetDAO());

        // Mock the behavior of PetRepositoryJPA
        when(petRepositoryJPA.findByUserId(userId)).thenReturn(expectedPetDAOs);

        // When
        List<PetDAO> result = getPetsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull().hasSize(2);

        // Verify that the findByUserId method of PetRepositoryJPA was called with the correct argument
        verify(petRepositoryJPA, times(1)).findByUserId(userId);
    }

    @Test
    void exec_withUserIdAndPageable_shouldReturnPetsDAOsPage() {
        // Given
        Long userId = 1L;
        Pageable pageable = mock(Pageable.class);
        Page<PetDAO> expectedPetsDAOPage = mock(Page.class);

        // Mock the behavior of PetRepositoryJPA
        when(petRepositoryJPA.findByUserId(userId, pageable)).thenReturn(expectedPetsDAOPage);

        // When
        Page<PetDAO> result = getPetsDAOBean.exec(userId, pageable);

        // Then
        assertThat(result).isNotNull();

        // Verify that the findByUserId method of PetRepositoryJPA was called with the correct argument
        verify(petRepositoryJPA, times(1)).findByUserId(userId, pageable);
    }
}