package com.example.training.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.training.config.SecurityConfig;
import com.example.training.service.TrainingAdminService;

@WebMvcTest(TrainingAdminController.class)
@Import(SecurityConfig.class)
public class TrainingAdminControllerSecurityTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainingAdminService trainingAdminService;

    @MockBean
    TrainingAdminSession trainingAdminSession;

    @Test
    @WithMockUser(roles = { "GUEST" })
    void test_displayList_GUESTユーザーはアクセスできない() throws Exception {
        mockMvc.perform(
                get("/admin/training/display-list"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = { "STAFF" })
    void test_displayList_STAFFユーザーはアクセスできる() throws Exception {
        mockMvc.perform(
                get("/admin/training/display-list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = { "STAFF" })
    void test_validateUpdateInput_STAFFユーザはアクセスできない() throws Exception {
        mockMvc.perform(
                post("/admin/training/validate-update-input")
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void test_validateUpdateInput_ADMINユーザはアクセスできる() throws Exception {
        mockMvc.perform(
                post("/admin/training/validate-update-input")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
