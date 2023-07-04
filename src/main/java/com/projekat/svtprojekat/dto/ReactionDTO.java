package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Reaction;
import com.projekat.svtprojekat.entity.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReactionDTO {

    private Long id;
    @NotEmpty
    private ReactionType type;

    @NotEmpty
    private LocalDateTime timestamp;

    public ReactionDTO (Reaction reaction) {
        this.id = reaction.getId();
        this.type = reaction.getType();
        this.timestamp = reaction.getTimestamp();
    }

    public ReactionDTO() {
    }
}
