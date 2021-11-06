package com.xzacharb.coresvc.infra.service.process;

public interface DetectionService {
    /**
     * Detection process runs web scraping for given city name
     *
     * @param cityShort cityShortName
     */
    public void runDetection(String cityShort);
}
