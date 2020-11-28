package ru.otus;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.handlers.db.CreateUsersDataRequestHandler;
import ru.otus.handlers.db.GetAllUsersDataRequestHandler;
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

    public MessageSystemConfig(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Bean
    public CallbackRegistry callbackRegistry(){
        return new CallbackRegistryImpl();
    }

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    @Qualifier("front")
    public MsClient frontendMsClient(CallbackRegistry callbackRegistry, MessageSystem messageSystem) {
        HandlersStore requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(callbackRegistry));
        requestHandlerFrontendStore.addHandler(MessageType.USER, new GetUserDataResponseHandler(callbackRegistry));
        requestHandlerFrontendStore.addHandler(MessageType.ALLUSERS, new GetUserDataResponseHandler(callbackRegistry));
        requestHandlerFrontendStore.addHandler(MessageType.CREATEUSER, new GetUserDataResponseHandler(callbackRegistry));
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

    @Bean
    @Qualifier("DB")
    public MsClient databaseMsClient(CallbackRegistry callbackRegistry, MessageSystem messageSystem){
        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbServiceUser));
        requestHandlerDatabaseStore.addHandler(MessageType.USER, new GetUserDataRequestHandler(dbServiceUser));
        requestHandlerDatabaseStore.addHandler(MessageType.ALLUSERS, new GetAllUsersDataRequestHandler(dbServiceUser));
        requestHandlerDatabaseStore.addHandler(MessageType.CREATEUSER, new CreateUsersDataRequestHandler(dbServiceUser));
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public FrontendService frontendService(@Qualifier("front") MsClient frontendMsClient) {
       return new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }
}
