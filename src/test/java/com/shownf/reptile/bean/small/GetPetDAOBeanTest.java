package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.repository.PetRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPetDAOBeanTest {

    @Mock
    private PetRepositoryJPA petRepositoryJPA;

    @InjectMocks
    private GetPetDAOBean getPetDAOBean;

    @Test
    void exec_withPetId_shouldReturnPetDAOIfExists() {
        // Given
        Long petId = 1L;
        PetDAO expectedPetDAO = new PetDAO();
        expectedPetDAO.setPetId(petId);

        // Mock the behavior of PetRepositoryJPA
        when(petRepositoryJPA.findById(petId)).thenReturn(Optional.of(expectedPetDAO));

        // When
        PetDAO result = getPetDAOBean.exec(petId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPetId()).isEqualTo(petId);

        // Verify that the findById method of PetRepositoryJPA was called with the correct argument
        verify(petRepositoryJPA, times(1)).findById(petId);
    }

    @Test
    void exec_withNonExistingPet_shouldReturnNull() {
        // Given
        Long nonExistingPetId = 2L;

        // Mock the behavior of PetRepositoryJPA
        when(petRepositoryJPA.findById(nonExistingPetId)).thenReturn(Optional.empty());

        // When
        PetDAO result = getPetDAOBean.exec(nonExistingPetId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of PetRepositoryJPA was called with the correct argument
        verify(petRepositoryJPA, times(1)).findById(nonExistingPetId);
    }
}