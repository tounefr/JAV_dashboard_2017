package eu.epitech.java.controller;

/* je sais je ne respecte pas la mvc que t'as mis en place, c'est juste un démonstrateur car il est 4h du mat xD */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eu.epitech.java.api.CoinMarket.CoinMarket;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CoinMarketController
{

    private CoinMarket coinmarket;


    @Autowired
    public CoinMarketController()
    {
        this.coinmarket = new CoinMarket();
    }

    @RequestMapping(value = "/coinmarket", method = RequestMethod.GET)
    @ResponseBody
    public String getCoinmarket()
    {
        String data = "";

        data += "Total currencies:<br>";
        data += this.coinmarket.GlobalInfo().getNbCurrencies();
        data += "<br>Ethereum to EUR:<br>";
        data += this.coinmarket.Currency().getPriceEUR("ethereum");
        data += " EUR<br>Eth rank:<br>";
        data += this.coinmarket.Currency().getRank("ethereum");
        return data;
    }
}