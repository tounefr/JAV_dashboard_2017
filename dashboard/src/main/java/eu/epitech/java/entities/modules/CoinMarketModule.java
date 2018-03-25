package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;

@Entity
public class CoinMarketModule extends Module {
    public CoinMarketModule() {
        super("CoinMarket", new MSettings_coinmarket());
    }

    @Override
    protected Class<? extends MSettings> getMSettings() {
        return MSettings_coinmarket.class;
    }

    public static class MSettings_coinmarket extends Module.MSettings {
        public String currency = "EUR";
    }
}
