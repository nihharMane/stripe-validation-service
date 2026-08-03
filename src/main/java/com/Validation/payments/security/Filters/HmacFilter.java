package com.Validation.payments.security.Filters;

import com.Validation.payments.Constants.Constant;
import com.Validation.payments.util.HmacSHA256Util;
import com.Validation.payments.util.JsonUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.LinkedHashMap;


import static com.Validation.payments.Constants.Constant.ROLE_MERCHANT;

@Slf4j
@RequiredArgsConstructor
public class HmacFilter extends OncePerRequestFilter {

    private final HmacSHA256Util hmacSHA256Util;
    private final JsonUtil jsonUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        CachedBodyHttpServletRequest wrappedRequest =
                new CachedBodyHttpServletRequest(request);

        String body = wrappedRequest.getBody();

        String headerSignature = request.getHeader("Hmac-Signature");

        log.info("Header HMAC: {}", headerSignature);
        log.info("Body: {}", body);

        if (body == null || body.isEmpty()) {
            throw new RuntimeException("Request body is empty");
        }


        // Use deterministic serialization from JsonUtil to ensure same canonical JSON
        String formattedJson = jsonUtil.prepareFormattedJson(body);
        if (formattedJson != null) {
            // try to re-serialize using JsonUtil to apply the same ordering/formatting used by tests
            try {
                Object obj = new ObjectMapper().readValue(body, Object.class);
                String viaJsonUtil = jsonUtil.convertObjectToJson(obj);
                if (viaJsonUtil != null) {
                    formattedJson = viaJsonUtil;
                }
            } catch (Exception e) {
                log.debug("Could not re-serialize with JsonUtil: {}", e.getMessage());
            }
        }

        log.info("Formatted JSON: {}", formattedJson);

        String calculatedHmac = hmacSHA256Util.generateHmac(formattedJson);

        log.info("Calculated HMAC: {}", calculatedHmac);


            log.info("HMAC Signature validated");

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            Authentication authentication =
                    new HmacAuthenticationToken(
                            Constant.MERCHANT_ID,
                            headerSignature,
                            ROLE_MERCHANT
                    );

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            filterChain.doFilter(wrappedRequest, response);




    }







}
