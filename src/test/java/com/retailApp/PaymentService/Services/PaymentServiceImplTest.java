////package com.retailApp.PaymentService.Services;
////
////public class PaymentServiceImplTest {
////
////}
//
//
//package com.retailApp.PaymentService.Services;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.retailApp.PaymentService.POJO.Payment;
//import com.retailApp.PaymentService.PaymentServiceDao.PaymentDao;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PaymentServiceImplTest {
//
//    @Mock
//    private PaymentDao paymentDao;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    private PaymentService paymentService;
//
//    @Before
//    public void setup() {
//        paymentService = new PaymentServiceImpl();
//    }
//
//    @Test
//    public void testAddSuccessfulPayment() {
//        // given
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put("orderId", "123");
//        requestMap.put("amount", "500");
//        requestMap.put("date", "2023-05-04");
//        requestMap.put("time", "10:00:00");
//
//        Payment payment = new Payment();
//        payment.setOrderId(123);
//        payment.setAmount(500);
//        payment.setDate("2023-05-04");
//        payment.setTime("10:00:00");
//
//        when(paymentDao.save(payment)).thenReturn(payment);
//
//        // when
//        ResponseEntity<String> responseEntity = paymentService.add(requestMap);
//
//        // then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Payment Done", responseEntity.getBody());
//    }
//
//    @Test
//    public void testAddFailedPayment() {
//        // given
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put("orderId", "123");
//        requestMap.put("amount", "500");
//        requestMap.put("date", "2023-05-04");
//        requestMap.put("time", "10:00:00");
//
//        Payment payment = new Payment();
//        payment.setOrderId(123);
//        payment.setAmount(500);
//        payment.setDate("2023-05-04");
//        payment.setTime("10:00:00");
//
//        when(paymentDao.save(payment)).thenThrow(new RuntimeException());
//
//        // when
//        ResponseEntity<String> responseEntity = paymentService.add(requestMap);
//
//        // then
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Payment Failed", responseEntity.getBody());
//    }
//}


package com.retailApp.PaymentService.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.retailApp.PaymentService.POJO.Payment;
import com.retailApp.PaymentService.PaymentServiceDao.PaymentDao;

@SpringBootTest
public class PaymentServiceImplTest {

    @Mock
    private PaymentDao paymentDao;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl();
    }

    @Test
    public void addPaymentTest() {
      Map<String, String> requestMap = new HashMap<>();
        requestMap.put("orderId", "123");
        requestMap.put("amount", "100");
        requestMap.put("date", "2023-05-12");
        requestMap.put("time", "14:30:00");

        Payment payment = new Payment();
        payment.setOrderId(Integer.parseInt(requestMap.get("orderId")));
        payment.setAmount(Integer.parseInt(requestMap.get("amount")));
        payment.setDate(requestMap.get("date"));
        payment.setTime(requestMap.get("time"));

        when(paymentDao.save(any(Payment.class))).thenReturn(payment);

        ResponseEntity<String> responseEntity = paymentService.add(requestMap);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), "Payment Done");
    }
}

