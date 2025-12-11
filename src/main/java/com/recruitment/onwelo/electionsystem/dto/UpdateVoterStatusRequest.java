package com.recruitment.onwelo.electionsystem.dto;

import com.recruitment.onwelo.electionsystem.utils.VoterStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateVoterStatusRequest(@NotNull VoterStatus status) { }
