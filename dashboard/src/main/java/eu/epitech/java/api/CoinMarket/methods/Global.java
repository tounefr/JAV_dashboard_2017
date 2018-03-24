package eu.epitech.java.api.CoinMarket.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Global extends AMethod {
    private GlobalData data;

    public Global() {
        this.data = new GlobalData();
    }

    private void load() {
        if (this.refresh()) {
            System.out.println("[COINMARKET API] refreshing Global data...");
            this.data = this.restTemplate.getForObject("https://api.coinmarketcap.com/v1/global/?convert={Currency}", GlobalData.class, "EUR");
            this.refreshed();
        }
    }
    static private class GlobalData {
        @JsonProperty("active_currencies")
        public Integer nbCurrencies;
        @JsonProperty("bitcoin_percentage_of_market_cap")
        public Double bitcoinShares;
        @JsonProperty("total_market_cap_eur")
        public String marketCapEUR;
        @JsonProperty("total_market_cap_usd")
        public String marketCapUSD;
        @JsonProperty("total_24h_volume_eur")
        public String dailyVolEUR;
        @JsonProperty("total_24h_volume_usd")
        public String dailyVolUSD;
    }
    public Integer getNbCurrencies() {
        this.load();
        return data.nbCurrencies;
    }
    public Double getBitcoinShares() {
        this.load();
        return data.bitcoinShares;
    }
    public String getMarketCapEUR() {
        this.load();
        return data.marketCapEUR;
    }
    public String getMarketCapUSD() {
        this.load();
        return data.marketCapUSD;
    }
    public String getDailyVolEUR() {
        this.load();
        return data.dailyVolEUR;
    }
    public String getDailyVolUSD() {
        this.load();
        return data.dailyVolUSD;
    }
}
