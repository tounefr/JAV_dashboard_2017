package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import eu.epitech.java.api.CoinMarket.CoinMarket;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Double getCoinmarket(@RequestParam("currency") String currency)
    {
        //this.coinmarket.Currency().getRank("ethereum");
        return this.coinmarket.Currency().getPriceEUR(currency);
    }
}