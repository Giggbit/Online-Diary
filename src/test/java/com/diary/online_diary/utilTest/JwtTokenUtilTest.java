package com.diary.online_diary.utilTest;

import com.diary.online_diary.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private String testToken;

//    @BeforeEach
//    void setUp() {
//        jwtTokenUtil = new JwtTokenUtil();
//        testToken = jwtTokenUtil.generateToken("test@example.com", "ROLE_USER");
//    }

    @Test
    void testGenerateToken() {
        assertNotNull(testToken, "Generated token should not be null");
    }

    @Test
    void testExtractUsername() {
        String username = jwtTokenUtil.extractEmail(testToken);
        assertEquals("test@example.com", username, "Extracted username should match the expected value");
    }

    @Test
    void testExtractRole() {
        String role = jwtTokenUtil.extractRole(testToken);
        assertEquals("ROLE_USER", role, "Extracted role should match the expected value");
    }

    @Test
    void testIsTokenExpired_ValidToken() {
        boolean isExpired = jwtTokenUtil.isTokenExpired(testToken);
        assertFalse(isExpired, "Token should not be expired");
    }
}
