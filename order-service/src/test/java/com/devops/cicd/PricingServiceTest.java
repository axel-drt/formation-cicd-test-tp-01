package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    private final PricingConfig fakeConfig = new PricingConfig(20, 50.0);
    private final PricingService service = new PricingService(fakeConfig);
    
    @Test
    public void applyVatTest() {
        assertEquals(120, service.applyVat(100));
    }

    @Test
    public void applyVipDiscountTest() {
        assertEquals(90, service.applyVipDiscount(100, true), 0.01);
        assertEquals(100, service.applyVipDiscount(100, false), 0.01);
    }
    
    @Test
    public void shippingCostTest() {
        assertEquals(0, service.shippingCost(50));
        assertEquals(0, service.shippingCost(51));
        assertEquals(4.99, service.shippingCost(49));
    }
}
