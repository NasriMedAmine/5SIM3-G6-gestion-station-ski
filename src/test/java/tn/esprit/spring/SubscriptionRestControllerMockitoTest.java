package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISubscriptionServices;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class SubscriptionRestControllerMockitoTest {
    @InjectMocks
    private SubscriptionRestController subscriptionRestController;

    @Mock
    private ISubscriptionServices subscriptionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSubscription() {
        Subscription subscription = new Subscription();
        when(subscriptionServices.addSubscription(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionRestController.addSubscription(subscription);

        assertEquals(subscription, result);
    }

    @Test
    void testGetById() {
        Subscription subscription = new Subscription();
        when(subscriptionServices.retrieveSubscriptionById(1L)).thenReturn(subscription);

        Subscription result = subscriptionRestController.getById(1L);

        assertEquals(subscription, result);
    }

    @Test
    void testGetSubscriptionsByType() {
        Set<Subscription> subscriptions = new HashSet<>();
        when(subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY)).thenReturn(subscriptions);

        Set<Subscription> result = subscriptionRestController.getSubscriptionsByType(TypeSubscription.MONTHLY);

        assertEquals(subscriptions, result);
    }

    @Test
    void testUpdateSubscription() {
        Subscription subscription = new Subscription();
        when(subscriptionServices.updateSubscription(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionRestController.updateSubscription(subscription);

        assertEquals(subscription, result);
    }

    @Test
    void testGetSubscriptionsByDates() {
        List<Subscription> subscriptions = Collections.emptyList();
        when(subscriptionServices.retrieveSubscriptionsByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(subscriptions);

        List<Subscription> result = subscriptionRestController.getSubscriptionsByDates(LocalDate.now(), LocalDate.now().plusDays(1));

        assertEquals(subscriptions, result);
    }
}

