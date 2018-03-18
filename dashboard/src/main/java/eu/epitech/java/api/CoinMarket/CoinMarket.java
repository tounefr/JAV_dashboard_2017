package eu.epitech.java.api.CoinMarket;

/* Coinmarket methods addon for Spring - EPITECH */

import eu.epitech.java.api.CoinMarket.methods.Global;
import eu.epitech.java.api.CoinMarket.methods.Currency;

public class CoinMarket {
    private Global global;
    private Currency currency;
    public CoinMarket() {
        this.global = new Global();
        this.currency = new Currency();
    }
    public Global GlobalInfo() {
        return global;
    }
    public Currency Currency() {
        return currency;
    }
}
