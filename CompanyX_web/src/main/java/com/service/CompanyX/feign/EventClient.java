package com.service.CompanyX.feign;

import com.service.CompanyX.model.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("event-service")
public interface EventClient {
    @GetMapping(value = "/testCall")
    public String test();
}
