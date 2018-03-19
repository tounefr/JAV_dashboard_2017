package eu.epitech.java.service;

import eu.epitech.java.api.CoinMarket.CoinMarket;
import eu.epitech.java.controller.CoinMarketController;
import eu.epitech.java.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class CoinMarketService extends AService {

    private CoinMarket coinMarket;

    public CoinMarketService() {
        this.coinMarket = new CoinMarket();
    }

    @Override
    public Class getController() {
        return CoinMarketController.class;
    }

    public HashMap<String, Double> getCurrencies(String[] currencies)
    {
        HashMap<String, Double> currenciesValues = new HashMap<String, Double>();
        List<String> currenciensList = Arrays.asList(currencies);
        Iterator<String> it = currenciensList.iterator();
        while (it.hasNext()) {
            String currency = (String) it.next();
            double priceEuro = this.coinMarket.Currency().getPriceEUR(currency);
            currenciesValues.put(currency, priceEuro);
        }
        return currenciesValues;
    }
}
