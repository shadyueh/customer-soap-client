package io.customer.client;

import java.math.BigInteger;

public class ClientTest {
    public static void main(String[] args) {
        CustomerPortService service = new CustomerPortService();

        HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver();

        service.setHandlerResolver(handlerResolver);

        CustomerPort port = service.getCustomerPortSoap11();

        GetCustomerDetailRequest customerDetailRequest = new GetCustomerDetailRequest();
        customerDetailRequest.setId(BigInteger.valueOf(3));

        GetCustomerDetailResponse customerDetailResponse = port.getCustomerDetail(customerDetailRequest);

        System.out.println("id -> "+customerDetailResponse.getCustomerDetail().getId());
        System.out.println("name -> "+customerDetailResponse.getCustomerDetail().getName());
        System.out.println("email -> "+customerDetailResponse.getCustomerDetail().getEmail());
        System.out.println("phone -> "+customerDetailResponse.getCustomerDetail().getPhone());
    }
}
