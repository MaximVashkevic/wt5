package by.mvashkewi4.wt.service;

import by.mvashkewi4.wt.service.impl.ClientServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private ClientService clientService = new ClientServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }
}
