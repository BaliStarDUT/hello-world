package net.yang.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * author: yang
 * datetime: 2021/3/13 23:19
 */

@Configuration
@EnableWebMvc
@EnableWebSocket

public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(textMessageHandler(), "/ws")
//                .addInterceptors(new HandshakeInterceptor())
                .setAllowedOrigins("*");


    }
    @Bean
    public TextMessageHandler textMessageHandler() {
        return new TextMessageHandler();
    }

}
