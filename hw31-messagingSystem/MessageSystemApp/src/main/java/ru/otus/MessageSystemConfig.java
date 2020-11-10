package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.otus.handlers.db.GetUserDataRequestHandler;
import ru.otus.handlers.front.GetUserDataResponseHandler;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.CallbackRegistryImpl;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.services.DBServiceUser;
import ru.otus.services.FrontendService;
import ru.otus.services.FrontendServiceImpl;

@Configuration
public class MessageSystemConfig {
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    private final DBServiceUser dbServiceUser;
    private final FrontendService frontendService;

    public MessageSystemConfig(DBServiceUser dbServiceUser, FrontendService frontendService) {
        this.dbServiceUser = dbServiceUser;
        this.frontendService = frontendService;
    }

    @Bean
    CallbackRegistry callbackRegistry(){
        return new CallbackRegistryImpl();
    }

    @Bean
    MsClient frontendMsClient(CallbackRegistry callbackRegistry, MessageSystem messageSystem) {
        HandlersStore requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(callbackRegistry));
        return new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
    }

    @Bean
    MsClient databaseMsClient(CallbackRegistry callbackRegistry, MessageSystem messageSystem){
        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbServiceUser));
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);
    }

    @Bean
    FrontendService frontendService(MsClient frontendMsClient) {
       return new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }

    @Bean
    public MessageSystem messageSystem(CallbackRegistry callbackRegistry, MsClient frontendMsClient) {
        MessageSystem messageSystem = new MessageSystemImpl();
       // CallbackRegistry callbackRegistry = new CallbackRegistryImpl();

//        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
//        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbServiceUser));
//        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
//                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
//        messageSystem.addClient(databaseMsClient);

        messageSystem.addClient(frontendMsClient);

        return messageSystem;
    }
}
