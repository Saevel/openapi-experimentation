package prv.saevel.openapi.experiments.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.OptionalInt;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    int id;

    String name;

    String surname;

    OptionalInt age;
}
