package eu.epitech.java.service;

/*
Abstract service (classe mère abstraite, pour éviter la duplication de code, et déclarer des methodes communes)
 */

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AService<A>
{
    protected ConnectionRepository connectionRepository;

    public abstract Class<A> getController();

    public boolean isConnected() {
        return (connectionRepository.findPrimaryConnection(this.getController()) != null);
    }
}