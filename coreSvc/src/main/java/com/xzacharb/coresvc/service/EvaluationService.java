package com.xzacharb.coresvc.service;

import com.xzacharb.coresvc.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.model.dao.InvoiceDao;
import com.xzacharb.coresvc.model.dto.EvaluatedResultData;
import com.xzacharb.coresvc.model.dto.InvoiceData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EvaluationService {

    @Value("${invoices.threshold.sum}")
    private int sumThreshold;
    @Value("${invoices.threshold.min-price}")
    private int minPriceThreshold;
    @Value("${invoices.threshold.max-price}")
    private int maxPriceThreshold;
    @Value("${invoices.threshold.min-invoice-count}")
    private int minInvoiceCountThreshold;

    /**
     * Rule evaluateOfFilteredInvoicesSumOverThreshold
     *
     * @param invoices
     * @return
     */
    public List<EvaluatedResult> evaluateOfFilteredInvoicesSumOverThreshold(List<InvoiceDao> invoices) {
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

    /**
     * Rule evaluateOfFilteredInvoicesSumOverThreshold
     *
     * @param invoices
     * @return
     */
    public List<EvaluatedResult> evaluateTotalSumOverThreshold(List<InvoiceDao> invoices) {
        int sum = 0;
        for (InvoiceDao invoiceDao : invoices)
            sum += invoiceDao.getPrice();
        if (sum >= sumThreshold)
            return createEvaluatedResultDao(invoices, "10", "Celkova suma vo vsetkych fakturach presiahla threshold",
                            "Celkova suma faktur presiahla: " + sumThreshold);
        return new ArrayList<>();
    }

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
}
