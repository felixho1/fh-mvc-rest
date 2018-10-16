package ho.felix.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListDTO {
    private final List<CategoryDTO> categories;
}
