package eu.epitech.java.controller;

/*
 * Fichier destiné à la gestion des routes via des "crontroleurs"
 */

import java.util.HashMap;

import eu.epitech.java.model.FacebookModel;
import eu.epitech.java.service.CoinMarketService;
import eu.epitech.java.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import eu.epitech.java.service.TwitterService;
import eu.epitech.java.model.TwitterModel;

@Controller
public class FeedController
{
    private TwitterService twitterService;
    private FacebookService facebookService;
    private CoinMarketService coinMarketService;

    @Autowired
    public FeedController(TwitterService twitterService, FacebookService facebookService, CoinMarketService coinMarketService)
    {
        this.twitterService = twitterService;
        this.facebookService = facebookService;
        this.coinMarketService = coinMarketService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model)
    {
        TwitterModel twitter = twitterService.getTwitterDatas();
        model.addAttribute("twitter", twitter);

        String[] currenciesStr = {"ethereum", "bitcoin", "zcash", "monero", "ripple", "litecoin"};
        HashMap<String, Double> currencies = this.coinMarketService.getCurrencies(currenciesStr);
        model.addAttribute("currencies", currencies);

        FacebookModel facebook = new FacebookModel();
        model.addAttribute("facebook", facebook);

        return "feed";
    }

    @RequestMapping("/teub")
    @ResponseBody
    public String getTeub()
    {
        return "teub";
    }

    @RequestMapping("/get/{userID}")
    @ResponseBody
    public String getUser(@PathVariable(value = "userID") String id)
    {
        return ("user id  = " + id + ".");
    }
}