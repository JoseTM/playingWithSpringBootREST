package org.pecera.demorest.paymentgateways;


import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;

@Component
public class PaypalGateway {

    private PayPalEnvironment envirometFromConfigMapping(Map<String, String> mapping) {
        return new PayPalEnvironment.Sandbox(
                mapping.get("PP_PUBLIC_KEY"),
                mapping.get("PP_PRIVATE_KEY")
        );
    }

    private PayPalEnvironment enviromentFromConfigFile(File configFile) {
        InputStream inputStream = null;
        Properties properties = new Properties();

        try {
            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        } finally {
            try { Objects.requireNonNull(inputStream).close(); }
            catch (IOException e) { System.err.println("Exception: " + e); }
        }

        return new PayPalEnvironment.Sandbox(
                properties.getProperty("PP_PUBLIC_KEY"),
                properties.getProperty("PP_PRIVATE_KEY")
        );
    }

    public PayPalHttpClient getClient(File configFile) {
        PayPalEnvironment payPalEnvironment = this.enviromentFromConfigFile(configFile);
        return new PayPalHttpClient(payPalEnvironment);
    }

    public String getOrder(String amount, PayPalHttpClient payPalHttpClient,RedirectAttributes redirectAttributes) {
        List<PurchaseUnitRequest> purchaseUnitRequestList = new ArrayList<>();
        purchaseUnitRequestList.add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("EUR").value(amount)));

        Order order = null;
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        orderRequest.purchaseUnits(purchaseUnitRequestList);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> response = payPalHttpClient.execute(ordersCreateRequest);
            order = response.result();
            return order.id();
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorDetails", "Error: 81503: Amount is an invalid format.");
            return "redirect:checkouts";
        }
    }

}