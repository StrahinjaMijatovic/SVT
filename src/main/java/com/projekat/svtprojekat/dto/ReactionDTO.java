package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Reaction;
import com.projekat.svtprojekat.entity.enums.ReactionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReactionDTO {

    private Long id;
    @NotNull
    private ReactionType type;

    @NotNull
    private LocalDate timestamp;

    public ReactionDTO (Reaction reaction) {
        this.id = reaction.getId();
        this.type = reaction.getType();
        this.timestamp = reaction.getTimestamp();
    }
}
