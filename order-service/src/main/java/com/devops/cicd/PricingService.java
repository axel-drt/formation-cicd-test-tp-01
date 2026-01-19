package com.devops.cicd;

public final class PricingService {

    private final PricingConfig config;
    private static final double VIP_DISCOUNT = 0.9f;
    private static final double SHIPPING_COST_LIMIT = 50;
    private boolean isVip;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
    return amountExclVat * (1 + config.getVatRate() / 100);
    }


    public double applyVipDiscount(double amount, boolean vip) {
        if(vip)
        {
            amount = amount * VIP_DISCOUNT;
        }
        return amount;
    }

    public double shippingCost(double amount) {
    return amount >= config.getFreeShippingThreshold() ? 0 : 4.99;
    }


    /**
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        double ttc = applyVat(amountExclVat);               // HT -> TTC
        double discounted = applyVipDiscount(ttc, vip);    // appliquer remise VIP
        double shipping = shippingCost(discounted);        // calcul frais livraison
        return discounted + shipping;                       // total final
    }

}