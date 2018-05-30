package com.github.zcmee.excelutil.controllers;

import com.github.zcmee.excelutil.utils.DateOperations;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexTest() throws Exception {
        mockMvc.perform(get("/textxls/index"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"));
    }

    @Test
    public void indexNotFoundTest() throws Exception {
        mockMvc.perform(get("/textxls/indexxx"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void uploadTest() throws Exception {
        InputStream uploadStream =  getClass().getClassLoader().getResourceAsStream("testfiles/users.xls");
        MockMultipartFile file = new MockMultipartFile("file", uploadStream);
        Assert.assertNotEquals(null, file);

        this.mockMvc.perform(MockMvcRequestBuilders
               .fileUpload("/textxls/upload")
               .file(file)
               .param("channel", "USER"))
               .andDo(print())
               .andExpect(status().is2xxSuccessful())
               .andExpect(jsonPath("$", Matchers.hasSize(2)))
               .andExpect(jsonPath("$[0].firstName", is("Piotr")))
               .andExpect(jsonPath("$[0].lastName", is("Kowalski")))
               .andExpect(jsonPath("$[0].age", is(33)))
               .andExpect(jsonPath("$[0].dateSigningContract", is(DateOperations.getInstance().getDateFromString("yyyy-MM-dd", "2018-09-10").getTime())))
               .andExpect(jsonPath("$[1].firstName", is("Tomasz")))
               .andExpect(jsonPath("$[1].lastName", is("Terka")))
               .andExpect(jsonPath("$[1].age", is(29)))
               .andExpect(jsonPath("$[1].dateSigningContract", is(DateOperations.getInstance().getDateFromString("yyyy-MM-dd", "2018-09-11").getTime())));

    }

    @Test
    public void uploadWrongChannelParameter() throws Exception {
        InputStream uploadStream =  getClass().getClassLoader().getResourceAsStream("testfiles/users.xls");
        MockMultipartFile file = new MockMultipartFile("file", uploadStream);
        Assert.assertNotEquals(null, file);

        this.mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/textxls/upload")
                .file(file)
                .param("channel", "NOT_EXISTS_CHANNE"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void uploadWrongEmptyFile() throws Exception {
        InputStream uploadStream = null;
        MockMultipartFile file = new MockMultipartFile("file", uploadStream);

        this.mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/textxls/upload")
                .file(file)
                .param("channel", "USER"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}