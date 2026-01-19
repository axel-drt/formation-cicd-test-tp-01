package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingIntegrationTest {

    @Test
    void fullPricingFlow_withRealConfigFile() {
        PricingConfig config = new PricingConfigLoader().load();

        PricingService service = new PricingService(config);

        double amountExclVat = 100.0;

        boolean isVip = true;
        double totalVip = service.finalTotal(amountExclVat, isVip);

        double ttc = amountExclVat * (1 + config.getVatRate() / 100);   
        double discounted = ttc * 0.9;                                  
        double shipping = discounted >= config.getFreeShippingThreshold() ? 0 : 4.99;
        double expectedVipTotal = discounted + shipping;

        assertEquals(expectedVipTotal, totalVip, 0.0001);

        boolean isRegular = false;
        double totalRegular = service.finalTotal(amountExclVat, isRegular);

        discounted = ttc;
        shipping = discounted >= config.getFreeShippingThreshold() ? 0 : 4.99;
        double expectedRegularTotal = discounted + shipping;

        assertEquals(expectedRegularTotal, totalRegular, 0.0001);
    }
}
