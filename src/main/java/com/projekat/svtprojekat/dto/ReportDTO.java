package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Report;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.entity.enums.ReportReason;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReportDTO {

    private Long id;
    @NotNull
    private ReportReason reason;
    @NotNull
    private LocalDate timestamp;

    @NotNull
    private User byUser;

    private boolean isAccepted;

    public ReportDTO (Report report) {
        this.id = report.getId();
        this.reason = report.getReason();
        this.timestamp = report.getTimestamp();
        this.isAccepted = report.isAccepted();
    }
}
