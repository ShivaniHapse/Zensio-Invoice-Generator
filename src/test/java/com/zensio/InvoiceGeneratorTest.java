package com.zensio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InvoiceGeneratorTest {

    @Test
    void printsInvoice() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(2, 3, 35, 5);
        assertEquals("2 Open Seats : 11800\n" +
                "3 Cabin Seats : 35400\n" +
                "5 Meals : 560\n" +
                "Total : 47760\n" +
                "Total GST : 7260\n", invoiceGenerator.printInvoice());
    }
}