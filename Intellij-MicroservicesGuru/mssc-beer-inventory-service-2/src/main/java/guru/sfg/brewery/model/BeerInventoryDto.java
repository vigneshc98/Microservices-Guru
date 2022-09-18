package guru.sfg.brewery.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jt on 2019-05-31.
 */
@Getter
@Setter 
@ToString 
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {
    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private UUID beerId;
    private Integer quantityOnHand;
}
