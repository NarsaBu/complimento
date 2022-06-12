package ru.narsabu.complimento.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.narsabu.complimento.client.ComplimentClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplimentService {

    private final ComplimentClient client;
    private final UnicodeConvertService unicodeConvertService;

    public String getCompliment() {
        val response = client.performRequest();
        String complimentInUnicode = response
                .split("\"va\":\"")[1]
                .replace("\"}", "");
        log.info("compliment in unicode format is: {}", complimentInUnicode);

        String complimentInString = unicodeConvertService.unicodeToString(complimentInUnicode);
        log.info("compliment in string format is: {}", complimentInString);
        return complimentInString;
    }
}
