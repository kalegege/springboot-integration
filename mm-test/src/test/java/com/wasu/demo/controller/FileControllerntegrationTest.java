package com.wasu.demo.controller;

import com.wasu.springboot.integration.TestApplication;
import com.wasu.springboot.integration.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
public class FileControllerntegrationTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    public void errorqueryByName() throws Exception{
        given(fileService.queryByName("test")).willReturn(null);

        this.mockMvc.perform(get("/queryByName").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(-1))
                .andDo(MockMvcResultHandlers.print());
    }
}
