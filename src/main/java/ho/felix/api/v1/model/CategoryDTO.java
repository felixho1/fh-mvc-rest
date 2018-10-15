package ho.felix.api.v1.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CategoryDTO {
    private Long id;
    private String name;
}
