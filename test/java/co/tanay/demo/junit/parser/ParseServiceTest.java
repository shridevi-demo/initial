package co.tanay.demo.junit.parser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ParseServiceTest {

    ParseServices parseService;



    @Before
    public void setup() {
        parseService = new ParseServiceImpl();
    }

    @Test
    public void assert_successfull_parsing() {
        String xml_correct = "" +
                "<head:MembId>3243</head:MmbId>" +
                "<head:CreDt>20148-09-22T05:30:32</head:CreDt>" +
                "<head:MsgDefIdr>.. some dummy data </head:MsgDefIdr>" +
                " .. ";
        PaymentInitWrapper paymentInitWrapper = parseService.parseRequestMessage(xml_correct);

        assertEquals(paymentInitWrapper.getMmbId(), "3243");
        assertNotEquals(paymentInitWrapper.getCreationTime(), 0);
        // .. more assertion tests

    }

    @Test
    public void assert_parsing_incorrect_date() {
        String xml_incorrect = "" +
                "<head:MembId>3243</head:MmbId>" +
//                "<head:CreDt></head:CreDt>" +
                "<head:MsgDefIdr>.. some dummy data </head:MsgDefIdr>" +
                " .. ";
        PaymentInitWrapper paymentInitWrapper = parseService.parseRequestMessage(xml_incorrect);

        //Long l = 5; // object
        //long m = 5; // primitive
        assertEquals(paymentInitWrapper.getCreationTime(), null);
        //assertEquals(paymentInitWrapper.getCreationTime(), 0);

        //assertEquals(paymentInitWrapper.getMmbId(), "3243");
        // .. more assertion tests

        // assert that irrecoverable error contains ErrorConstants.CREATION_DATE_NULL
        // == >
        //assertTrue(paymentInitWrapper.getIrrecoverableError().contains(ErrorConstants.CREATION_DATE_NULL));

    }

}
