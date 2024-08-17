package com.spring3.oauth.jwt.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Zeeshan Adil
 * User:mhmdz
 * Date:16-08-2024
 * Time:23:18
 */

public interface TokenBlacklistService {

    void addToBlacklist(HttpServletRequest request);
    Boolean isBlacklisted(String token);

}