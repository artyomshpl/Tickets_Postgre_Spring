package com.shep.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shep.entities.TicketData;
import com.shep.repositories.TicketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDataService {

    @Autowired
    private TicketDataRepository ticketDataRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public void loadTicketData() {
        Resource resource = resourceLoader.getResource("classpath:ticketData.txt");
        try (InputStream inputStream = resource.getInputStream()) {
            List<TicketData> ticketDataList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.replace("“", "\"").replace("”", "\"");
                    TicketData ticketData = objectMapper.readValue(line, TicketData.class);
                    ticketDataList.add(ticketData);
                }
            }

            ticketDataRepository.saveAll(ticketDataList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ticket data", e);
        }
    }
}
