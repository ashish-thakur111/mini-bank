package com.ashisht.mini_bank.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundResponse {
    private String message;
    private int errorCode;
}
