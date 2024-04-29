package ru.antonovmikhail.jdbc.user.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonovmikhail.jdbc.abstractions.Entity;
import ru.antonovmikhail.jdbc.util.Views;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Entity<Long> {

    @JsonView(Views.UserDetails.class)
    private Long id;

    @JsonView(Views.UserSummary.class)
    private String name;
    @JsonView(Views.UserDetails.class)
    private String email;

}
