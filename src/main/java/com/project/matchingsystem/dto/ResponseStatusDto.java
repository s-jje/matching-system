package com.project.matchingsystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseStatusDto {

    private final String httpStatus;
    private final String message;

}
