package com.xzacharb.coresvc.impl.component;

import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.impl.rules.factories.ListRuleFactory;
import com.xzacharb.coresvc.infra.rules.Rule;
import com.xzacharb.coresvc.infra.rules.RuleFactory;
import com.xzacharb.coresvc.infra.rules.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    RuleFactory ruleFactory;

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
        List<Integer> thresholds = Arrays.asList(minPriceThreshold, maxPriceThreshold, sumThreshold);
        Rule rule = ruleFactory.createRule("PriceBetweenThresholdsSumOverThreshold", thresholds);
        List<InvoiceDao> filtered = rule.execute(invoices);
        if (filtered.isEmpty())
            return new ArrayList<>();
        return createEvaluatedResultDao(filtered, rule.score() + "", "Suma faktury v rozmedzi thresholdov s prekrocenim celkovej sumy",
                "Suma faktury medzi: " + minPriceThreshold + " a " + maxPriceThreshold + "\n" +
                        "Celkova suma faktur presiahla: " + sumThreshold);

    }

    @Override
    public List<EvaluatedResult> totalSumOverThreshold(List<InvoiceDao> invoices) {
        List<Integer> thresholds = Arrays.asList(minPriceThreshold, sumThreshold, minInvoiceCountThreshold);
        Rule rule = ruleFactory.createRule("PriceOverThresholdSumOverThresholdCountOverThreshold", thresholds);
        List<InvoiceDao> filtered = rule.execute(invoices);
        if (filtered.isEmpty())
            return new ArrayList<>();
        return createEvaluatedResultDao(filtered, rule.score() + "", "Ceny faktur, Suma a pocet faktur prekrocili thresholdy",
                "Suma faktury prekrocila: " + minPriceThreshold + "\n" +
                        "Celkova suma faktur presiahla: " + sumThreshold + "\n" +
                        "Pocet faktur presiahol: " + minInvoiceCountThreshold);

    }
}
