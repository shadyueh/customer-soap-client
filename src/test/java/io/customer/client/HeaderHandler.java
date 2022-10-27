package io.customer.client;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Set;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        Boolean outboundProperty = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()){
            try {
                SOAPEnvelope envelope = soapMessageContext.getMessage().getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                SOAPElement security = header.addChildElement("Security","wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

                SOAPElement usernameToken = security.addChildElement("UsernameToken","wsse");

                SOAPElement username = usernameToken.addChildElement("Username","wsse");
                username.addTextNode("Orochi");

                SOAPElement password = usernameToken.addChildElement("Password","wsse");
                password.addTextNode("8218c4c42a765247ed5d2cec01b9c098c3e781a3eae6da66629e42937007446b7a339173312357e5dc6204ced6e0befc3f5321a878b9b8e608cad91d3a8c3eee");

            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
        return outboundProperty;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {

    }
}
