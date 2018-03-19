package eu.epitech.java.api.CoinMarket.methods;

import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AMethod {
    private Date last = null;
    protected RestTemplate restTemplate;

    public AMethod()
    {
        this.restTemplate = new RestTemplate();
    }

    private long DateDiffSeconds(final Date a, final Date b) {
        long aRaw = a.getTime();
        long bRaw = b.getTime();

        if (aRaw < bRaw)
            return 0;
        return (TimeUnit.SECONDS.convert((Math.abs(aRaw - bRaw)), TimeUnit.MILLISECONDS));
    }

    protected boolean refresh() {
        Date now = new Date();
        return (this.last == null || DateDiffSeconds(now, this.last) > 10);
    }

    protected void refreshed()
    {
        last = new Date();
    }
}
