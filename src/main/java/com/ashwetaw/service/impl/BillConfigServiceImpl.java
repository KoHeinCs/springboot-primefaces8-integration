package com.ashwetaw.service.impl;

import com.ashwetaw.model.billerconfiguration.BillerConfiguration;
import com.ashwetaw.repositories.primary.BillConfigurationRepository;
import com.ashwetaw.service.BillConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
@Service
@RequiredArgsConstructor
public class BillConfigServiceImpl implements BillConfigService {
    private final BillConfigurationRepository billConfigurationRepository;

    @Override
    public void save(BillerConfiguration billerConfig) {
        billConfigurationRepository.save(billerConfig);
    }
}
