package com.xzacharb.coresvc.impl.component;

import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.component.Rules;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class EvaluationComponent implements Rules {

    @Value("${invoices.threshold.sum}")
    private int sumThreshold;
    @Value("${invoices.threshold.min-price}")
    private int minPriceThreshold;
    @Value("${invoices.threshold.max-price}")
    private int maxPriceThreshold;
    @Value("${invoices.threshold.min-invoice-count}")
    private int minInvoiceCountThreshold;

    /**
     * create list of EvaluatedResult for each invoice
     *
     * @param invoices
     * @return
     */
    private List<EvaluatedResult> createEvaluatedResultDao(List<InvoiceDao> invoices, String value, String evaluatorName, String description) {
        List<EvaluatedResult> evaluatedResultData = invoices.stream()
                .map(invoice ->
                        new EvaluatedResult(value, description, evaluatorName, invoice)
                ).collect(Collectors.toList());
        return evaluatedResultData;
    }

    @Override
    public List<EvaluatedResult> filteredSumOverThreshold(List<InvoiceDao> invoices) {
        List<InvoiceDao> filtered = invoices.stream()
                .filter(invoice ->
                        invoice.getPrice() >= minPriceThreshold && invoice.getPrice() <= maxPriceThreshold
                ).collect(Collectors.toList());
        if (filtered.size() >= minInvoiceCountThreshold) {
            int sum = 0;
            for (InvoiceDao invoiceDao : filtered)
                sum += invoiceDao.getPrice();
            if (sum >= sumThreshold)
                return createEvaluatedResultDao(filtered, "20", "Suma faktury v rozmedzi thresholdov s prekrocenim celkovej sumy",
                        "Suma faktury medzi: " + minPriceThreshold + " a " + maxPriceThreshold + "\n" +
                                "Pocet faktur presiahol: " + minInvoiceCountThreshold + " \n" +
                                "Celkova suma faktur presiahla: " + sumThreshold);
        }

        return new ArrayList<>();
    }

    @Override
    public List<EvaluatedResult> totalSumOverThreshold(List<InvoiceDao> invoices) {
        int sum = 0;
        for (InvoiceDao invoiceDao : invoices)
            sum += invoiceDao.getPrice();
        if (sum >= sumThreshold)
            return createEvaluatedResultDao(invoices, "10", "Celkova suma vo vsetkych fakturach presiahla threshold",
                    "Celkova suma faktur presiahla: " + sumThreshold);
        return new ArrayList<>();
    }
}
