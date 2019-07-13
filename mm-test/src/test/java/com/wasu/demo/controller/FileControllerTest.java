package com.wasu.demo.controller;

import com.wasu.springboot.integration.controller.Filecontroller;
import com.wasu.springboot.integration.entity.message.JsonResult;
import com.wasu.springboot.integration.service.FileService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {

    @InjectMocks
    private Filecontroller filecontroller;

    @Mock
    private FileService fileService;

    @Test
    public void successQueryByName(){
        Mockito.when(fileService.queryByName("test")).thenReturn(null);

        JsonResult actual = filecontroller.queryByName("test");

        Assert.assertThat(actual, CoreMatchers.notNullValue());
        Assert.assertThat(actual.getCode(),CoreMatchers.equalTo(JsonResult.CODE_SUCCESS));
    }
}
