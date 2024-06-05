package com.nikhiln.rentRead;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nikhiln.rentRead.controller.BookController;
import com.nikhiln.rentRead.controller.UserController;
import com.nikhiln.rentRead.dto.BookDto;
import com.nikhiln.rentRead.dto.UserResponseDto;
import com.nikhiln.rentRead.entity.Role;
import com.nikhiln.rentRead.service.BookService;
import com.nikhiln.rentRead.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookController bookController;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        // ModelMapper modelMapper = new ModelMapper();
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                new BookDto(1L, "Book 1", "Author 1", "Genre 1", true),
                new BookDto(2L, "Book 2", "Author 2", "Genre 2", true)
        ));
        when(bookService.getBookById(1L)).thenReturn(new BookDto(1L, "Book 1", "Author 1", "Genre 1", true));
        when(userService.getAllUsers()).thenReturn(Arrays.asList(
                new UserResponseDto(1L, "user1@example.com", "User", "One", new HashSet<>(Arrays.asList(Role.USER))),
                new UserResponseDto(2L, "user2@example.com", "User", "Two", new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER)))
        ));
        when(userService.getUserById(1L)).thenReturn(new UserResponseDto(1L, "user1@example.com", "User", "One", new HashSet<>(Arrays.asList(Role.USER))));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    
}
