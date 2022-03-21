//package ru.kondrashov.accountservice.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.kondrashov.accountservice.controllers.dto.BillRequestDTO;
//import ru.kondrashov.accountservice.entities.Account;
//import ru.kondrashov.accountservice.services.interfaces.BillsService;
//import ru.kondrashov.accountservice.utils.interfaces.AdjustmentMapping;
//import ru.kondrashov.accountservice.utils.interfaces.BillsMapping;
//import ru.kondrashov.accountservice.utils.interfaces.PaymentsMapping;
//import ru.kondrashov.accountservice.utils.interfaces.TransferMapping;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Currency;
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = BillsController.class)
//class BillsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private BillsService billsService;
//
//    @MockBean
//    private BillsMapping billsMapping;
//
//    @MockBean
//    private PaymentsMapping paymentsMapping;
//
//    @MockBean
//    private AdjustmentMapping adjustmentMapping;
//
//    @MockBean
//    private TransferMapping transferMapping;
//
//    @Test
//    void whenValidUrlAndMethodAndContentType_thenReturns201() throws Exception {
//
//        BillRequestDTO bill = new BillRequestDTO(Currency.getInstance("USD"), BigDecimal.TEN, false, new Account("name", LocalDate.now(), UUID.randomUUID()));
//
//        mockMvc.perform(post("/v1/people/{personId}/accounts/{accountId}/bills", UUID.randomUUID(), UUID.randomUUID() )
//                .content(objectMapper.writeValueAsString(bill))
//                .contentType("application/json"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void whenNullValue_thenReturns400() throws Exception {
//
//        BillRequestDTO bill = new BillRequestDTO(null, BigDecimal.TEN, false, new Account("name", LocalDate.now(), UUID.randomUUID()));
//
//        mockMvc.perform(post("/v1/people/{personId}/accounts/{accountId}/bills", UUID.randomUUID(), UUID.randomUUID())
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(bill)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenIncorrectValue_thenReturns400() throws Exception {
//
//        String request = "{\n" +
//                "  \"account\": {\n" +
//                "    \"creationDate\": \"2021-04-23\",\n" +
//                "    \"id\": \""+UUID.randomUUID()+"\",\n" +
//                "    \"name\": \"string\",\n" +
//                "    \"personId\": \""+UUID.randomUUID()+"\"\n" +
//                "  },\n" +
//                "  \"amount\": 0,\n" +
//                "  \"currency\": \"string\",\n" +
//                "  \"isOverdraft\": true\n" +
//                "}";
//
//        mockMvc.perform(post("/v1/people/{personId}/accounts/{accountId}/bills", UUID.randomUUID(), UUID.randomUUID())
//                .contentType("application/json")
//                .content(request))
//                .andExpect(status().isBadRequest());
//    }
//
//}