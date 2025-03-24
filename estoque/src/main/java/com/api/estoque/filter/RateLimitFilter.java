package com.api.estoque.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitFilter implements Filter {

    private static final int MAX_REQUESTS = 5; // Máximo de requisições permitidas
    private static final long TIME_WINDOW = TimeUnit.MINUTES.toMillis(1); // Janela de tempo (1 minuto)

    private final ConcurrentHashMap<String, UserRequestInfo> requests = new ConcurrentHashMap<>();

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
    
        String clientIp = httpRequest.getRemoteAddr();
        long currentTime = System.currentTimeMillis();
    
        requests.compute(clientIp, (ip, info) -> {
            if (info == null || currentTime - info.startTime > TIME_WINDOW) {
                return new UserRequestInfo(1, currentTime);
            } else {
                info.requestCount++;
                return info;
            }
        });
    
        if (requests.get(clientIp).requestCount > MAX_REQUESTS) {
            httpResponse.setStatus(429); // Código de status HTTP para Too Many Requests
            httpResponse.getWriter().write("Too Many Requests");
            return;
        }
    
        chain.doFilter(request, response);
    }
    private static class UserRequestInfo {
        int requestCount;
        long startTime;

        UserRequestInfo(int requestCount, long startTime) {
            this.requestCount = requestCount;
            this.startTime = startTime;
        }
    }
}
