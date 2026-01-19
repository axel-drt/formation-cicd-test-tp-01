package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {
    
    @Test
    void testPasswordNull() {
        assertFalse(PasswordPolicy.isStrong(null));
    }
    
    @Test
    void testPasswordTooShort() {
        assertFalse(PasswordPolicy.isStrong("Abc1@"));
    }
    
    @Test
    void testPasswordWithoutUppercase() {
        assertFalse(PasswordPolicy.isStrong("abcdef1@"));
    }
    
    @Test
    void testPasswordWithoutLowercase() {
        assertFalse(PasswordPolicy.isStrong("ABCDEF1@"));
    }
    
    @Test
    void testPasswordWithoutDigit() {
        assertFalse(PasswordPolicy.isStrong("Abcdefgh@"));
    }
    
    @Test
    void testPasswordWithoutSpecialChar() {
        assertFalse(PasswordPolicy.isStrong("Abcdefgh1"));
    }
    
    @Test
    void testStrongPassword() {
        assertTrue(PasswordPolicy.isStrong("Abcdef1@"));
    }
    
    @Test
    void testAnotherStrongPassword() {
        assertTrue(PasswordPolicy.isStrong("MyP@ssw0rd"));
    }
    
    @Test
    void testLongStrongPassword() {
        assertTrue(PasswordPolicy.isStrong("VeryStr0ng!Password"));
    }
    
    @Test
    void testPasswordWithMultipleSpecialChars() {
        assertTrue(PasswordPolicy.isStrong("P@ssw0rd!#"));
    }
}