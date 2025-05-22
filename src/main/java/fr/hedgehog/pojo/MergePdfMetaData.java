package fr.hedgehog.pojo;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MergePdfMetaData {

    @Pattern(regexp = "^\\d+\\..+\\.pdf$")
    private String destination;
}
