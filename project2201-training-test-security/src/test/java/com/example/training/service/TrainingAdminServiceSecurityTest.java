package com.example.training.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Sql("TrainingAdminServiceTest.sql")
public class TrainingAdminServiceSecurityTest {
    @Autowired
    TrainingAdminService trainingAdminService;

    @Test
    @WithMockUser(roles = { "GUEST" })
    void test_delete_GUESTユーザは呼び出せない() {
        assertThatThrownBy(() -> {
            trainingAdminService.delete("t01");
        }).isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void test_delete_ADMINユーザは呼び出せる() {
        trainingAdminService.delete("t01");
    }

    @Test
    @WithAnonymousUser
    void test_delete_認証してない場合は呼出せない() {
        assertThatThrownBy(() -> {
            trainingAdminService.delete("t01");
        }).isInstanceOf(AccessDeniedException.class);
    }
}
