package guru.sfg.msscbrewery.services.order;

import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.sfg.msscbrewery.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListeners {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest){
        Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
                .orderId(validateOrderRequest.getBeerOrderDto().getId())
                .isValid(isValid)
                .build());
    }
}
