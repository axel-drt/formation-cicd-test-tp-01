public final class PricingService {

    private final PricingConfig config;
    private static final float VIP_DISCOUNT = 0.9f;
    private static final float SHIPPING_COST_LIMIT = 50;
    private boolean isVip;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
        return amountExclVat*config.getVatRate();
    }

    public double applyVipDiscount(double amount, boolean vip) {
        if(vip)
        {
            amount = amount * VIP_DISCOUNT;
        }
        return amount;
    }

    public double shippingCost(double amount) {
        if(amount >= SHIPPING_COST_LIMIT)
        {
            return 0;
        }
        else
        {
            return 4.99;
        }
    }

    /**
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        return shippingCost(applyVipDiscount(applyVat(amountExclVat), isVip));
    }
}