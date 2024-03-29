package eu.epitech.java.api.CoinMarket.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency extends AMethod {
    private String cacheTarget = "";
    private CurrencyData data;
    public Currency() {
        this.data = new CurrencyData();
    }
    private void load(final String target) {
        if (!target.equals(this.cacheTarget) || this.refresh()) {
            System.out.println("[COINMARKET API] refreshing Currency data...");
            this.cacheTarget = target;
            CurrencyData[] buf = restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/{Target}?convert={Currency}", CurrencyData[].class, target, "EUR");
            if (buf.length > 0)
                this.data = buf[0];
            this.refreshed();
        }
    }
    static private class CurrencyData {
        @JsonProperty("name")
        public String name;
        @JsonProperty("symbol")
        public String symbol;
        @JsonProperty("rank")
        public Integer rank;
        @JsonProperty("price_usd")
        public Double priceUSD;
        @JsonProperty("price_btc")
        public Double priceBTC;
        @JsonProperty("price_eur")
        public Double priceEUR;
        @JsonProperty("total_supply")
        public String totalSupply;
        @JsonProperty("available_supply")
        public String availableSupply;
        @JsonProperty("max_supply")
        public String maxSupply;
        @JsonProperty("market_cap_eur")
        public String marketCapEUR;
        @JsonProperty("market_cap_usd")
        public String marketCapUSD;
        @JsonProperty("24h_volume_eur")
        public String volDayEUR;
        @JsonProperty("24h_volume_usd")
        public String volDayUSD;
        @JsonProperty("percent_change_1h")
        public Double jitterHour;
        @JsonProperty("percent_change_24h")
        public Double jitterDay;
        @JsonProperty("percent_change_7d")
        public Double jitterWeek;
    }
    public String getName(final String target) {
        this.load(target);
        return data.name;
    }
    public String getSymbol(final String target) {
        this.load(target);
        return data.symbol;
    }
    public Integer getRank(final String target) {
        this.load(target);
        return data.rank;
    }
    public Double getPriceUSD(final String target) {
        this.load(target);
        return data.priceUSD;
    }
    public Double getPriceEUR(final String target) {
        this.load(target);
        return data.priceEUR;
    }
    public Double getPriceBTC(final String target) {
        this.load(target);
        return data.priceBTC;
    }
    public String getTotalSupply(final String target) {
        this.load(target);
        return data.totalSupply;
    }
    public String getAvailableSupply(final String target) {
        this.load(target);
        return data.availableSupply;
    }
    public String getMaxSupply(final String target) {
        this.load(target);
        return data.maxSupply;
    }
    public Double getJitterHour(final String target) {
        this.load(target);
        return data.jitterHour;
    }
    public Double getJitterDay(final String target) {
        this.load(target);
        return data.jitterDay;
    }
    public Double getJitterWeek(final String target) {
        this.load(target);
        return data.jitterWeek;
    }
    public String getMarketCapEUR(final String target) {
        this.load(target);
        return data.marketCapEUR;
    }
    public String getMarketCapUSD(final String target) {
        this.load(target);
        return data.marketCapUSD;
    }
    public String getVolDayEUR(final String target) {
        this.load(target);
        return data.volDayEUR;
    }
    public String getVolDayUSD(final String target) {
        this.load(target);
        return data.volDayUSD;
    }
}
