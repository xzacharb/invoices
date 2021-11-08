package com.xzacharb.coresvc.impl.rules;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.impl.rules.factories.ListRuleFactory;
import com.xzacharb.coresvc.infra.rules.Rule;
import com.xzacharb.coresvc.infra.rules.RuleFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class RuleGTTTest {

@Test
public void test1(){
    InvoiceDao i1 =new InvoiceDao();
    i1.setPrice(10);
    InvoiceDao i2 =new InvoiceDao();
    i2.setPrice(50);
    InvoiceDao i3 =new InvoiceDao();
    i3.setPrice(100);
    InvoiceDao i4 =new InvoiceDao();
    i4.setPrice(500);
    InvoiceDao i5 =new InvoiceDao();
    i5.setPrice(1000);
    InvoiceDao i6 =new InvoiceDao();
    i6.setPrice(5000);

    List<InvoiceDao> InvoiceDao = Arrays.asList(i1,i2,i3,i4,i5,i6);
    List<Integer> t1 = Arrays.asList(1,20000,500,4,5);
    RuleFactory rf = new ListRuleFactory();
    Rule r1 = rf.createRule("PriceBetweenThresholdsSumOverThreshold",t1);
    Rule r2 = rf.createRule("PriceOverThresholdSumOverThresholdCountOverThreshold",t1);

    System.out.println(r1.execute(InvoiceDao).size());
    System.out.println(r1.description());
    System.out.println(r1.score());
    r2.execute(InvoiceDao);
    System.out.println(r2.description());
    System.out.println(r2.score());
    assert(r1.score()==172);
    assert(r2.score()==12);
}
}