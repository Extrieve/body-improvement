package com.body.improvement.club.error;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserNotFoundError extends RuntimeException {

    private final String username;

    @Override
    public String getMessage() {
        return "User not found with username: " + username;
    }

    public Map<String, String> getErrorMap(){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "User not found");
        errorMap.put("message", getMessage());
        return errorMap;
    }
}
