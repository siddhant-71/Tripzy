package com.tripzy.ExternalService;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Razorpay {

    private String secretkey="VDx0JBuiVVgordrjrDbGh6bs";
    private String id="rzp_test_YmTwJC615FKYFA";

    public String createOrder() throws Exception{
        RazorpayClient client=new RazorpayClient(id,secretkey);

        JSONObject options=new JSONObject();
        options.put("amount",1000);
        options.put("currency","INR");
        options.put("receipt","receipt-1");

        Order order=client.orders.create(options);
        return order.toString();
    }
}
