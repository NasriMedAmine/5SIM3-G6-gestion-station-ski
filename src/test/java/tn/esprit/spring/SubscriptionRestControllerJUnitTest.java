package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionRestController.class)
public class SubscriptionRestControllerJUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISubscriptionServices subscriptionServices;

    @Test
    void testAddSubscription() throws Exception {
        Subscription subscription = new Subscription();
        when(subscriptionServices.addSubscription(subscription)).thenReturn(subscription);

        mockMvc.perform(post("/subscription/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        Subscription subscription = new Subscription();
        when(subscriptionServices.retrieveSubscriptionById(1L)).thenReturn(subscription);

        mockMvc.perform(get("/subscription/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSubscriptionsByType() throws Exception {
        Set<Subscription> subscriptions = new HashSet<>();
        when(subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY)).thenReturn(subscriptions);

        mockMvc.perform(get("/subscription/all/MONTHLY"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateSubscription() throws Exception {
        Subscription subscription = new Subscription();
        when(subscriptionServices.updateSubscription(subscription)).thenReturn(subscription);

        mockMvc.perform(put("/subscription/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSubscriptionsByDates() throws Exception {
        when(subscriptionServices.retrieveSubscriptionsByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/subscription/all/2023-01-01/2023-12-31"))
                .andExpect(status().isOk());
    }
}
