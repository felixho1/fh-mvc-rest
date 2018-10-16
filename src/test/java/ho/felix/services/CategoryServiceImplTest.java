package ho.felix.services;

import ho.felix.api.v1.mapper.CategoryMapper;
import ho.felix.api.v1.model.CategoryDTO;
import ho.felix.domain.Category;
import ho.felix.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {
    public static final String NAME = "Jimmy";
    public static final long ID = 2L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        // when
        when(categoryRepository.findAll()).thenReturn(categories);

        // then
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
        assertEquals(3, categoryDTOList.size());
    }

    @Test
    public void getCategoryByName() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        // when
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        // then
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        assertEquals(NAME, categoryDTO.getName());
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
    }
}