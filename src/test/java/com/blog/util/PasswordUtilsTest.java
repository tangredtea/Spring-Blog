package com.blog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 密码工具类单元测试
 * @author Ryan
 */
class PasswordUtilsTest {

    @Test
    void testEncode() {
        // Given
        String rawPassword = "testPassword123";

        // When
        String encoded = PasswordUtils.encode(rawPassword);

        // Then
        assertNotNull(encoded);
        assertNotEquals(rawPassword, encoded); // 加密后应该不同
        assertTrue(encoded.startsWith("$2a$")); // BCrypt 格式
    }

    @Test
    void testMatches_WithCorrectPassword() {
        // Given
        String rawPassword = "mySecretPassword";
        String encodedPassword = PasswordUtils.encode(rawPassword);

        // When
        boolean matches = PasswordUtils.matches(rawPassword, encodedPassword);

        // Then
        assertTrue(matches);
    }

    @Test
    void testMatches_WithWrongPassword() {
        // Given
        String rawPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        String encodedPassword = PasswordUtils.encode(rawPassword);

        // When
        boolean matches = PasswordUtils.matches(wrongPassword, encodedPassword);

        // Then
        assertFalse(matches);
    }

    @Test
    void testEncode_DifferentResultsForSamePassword() {
        // Given
        String rawPassword = "samePassword";

        // When - BCrypt 每次加密结果都不同（因为随机盐）
        String encoded1 = PasswordUtils.encode(rawPassword);
        String encoded2 = PasswordUtils.encode(rawPassword);

        // Then
        assertNotEquals(encoded1, encoded2); // 两次加密结果应该不同
        assertTrue(PasswordUtils.matches(rawPassword, encoded1));
        assertTrue(PasswordUtils.matches(rawPassword, encoded2));
    }

    @Test
    void testMatches_WithEmptyPassword() {
        // Given
        String emptyPassword = "";
        String encoded = PasswordUtils.encode(emptyPassword);

        // When
        boolean matches = PasswordUtils.matches(emptyPassword, encoded);

        // Then
        assertTrue(matches);
    }

    @Test
    void testMatches_WithLongPassword() {
        // Given
        String longPassword = "a".repeat(100);
        String encoded = PasswordUtils.encode(longPassword);

        // When
        boolean matches = PasswordUtils.matches(longPassword, encoded);

        // Then
        assertTrue(matches);
    }
}
