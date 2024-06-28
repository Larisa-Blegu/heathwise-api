package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Appointment;
import com.healthwise.HealthwiseApp.entity.Price;
import com.healthwise.HealthwiseApp.repository.AppointmentRepository;
import com.healthwise.HealthwiseApp.repository.PriceRepository;
import com.healthwise.HealthwiseApp.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecreteKey;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private  AppointmentRepository appointmentRepository;

    public PaymentResponse createPaymentLink(int appointmentId) throws StripeException {

        Stripe.apiKey = stripeSecreteKey;
        Appointment appointment = appointmentRepository.getById(appointmentId);
        System.out.println(appointment);
        List<Price> prices = priceRepository.findByDoctorIdAndMedicalProcedureId(appointment.getDoctor().getId(), appointment.getMedicalProcedure().getId());

        double totalPrice = 0.0;
        for (Price price : prices) {
            totalPrice += price.getPrice();
        }

        SessionCreateParams params = SessionCreateParams.builder().addPaymentMethodType(
                SessionCreateParams
                        .PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/success/"+appointment.getId())
                .setCancelUrl("http://localhost:3000/fail")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("ron")
                                .setUnitAmount((long) totalPrice*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Healthwise")
                                        .build())
                                .build()
                        )
                        .build()
                )
                .build();

        Session session = Session.create(params);

        PaymentResponse response = new PaymentResponse();
        response.setPayment_url(session.getUrl());

        return response;
    }
}
