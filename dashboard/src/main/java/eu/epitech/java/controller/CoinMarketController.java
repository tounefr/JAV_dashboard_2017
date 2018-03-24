package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import eu.epitech.java.api.CoinMarket.CoinMarket;

@RestController
public class CoinMarketController
{
    private CoinMarket coinmarket;

    @Autowired
    public CoinMarketController()
    {
        this.coinmarket = new CoinMarket();
    }

    @RequestMapping(value = "/coinmarket", params = "currency", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @Cacheable("coinmarketcap.currency")
    public Double getCoinmarket(@RequestParam("currency") String currency)
    {
        return this.coinmarket.Currency().getPriceEUR(currency);
    }
}