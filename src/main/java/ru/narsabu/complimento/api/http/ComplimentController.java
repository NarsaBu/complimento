package ru.narsabu.complimento.api.http;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.narsabu.complimento.service.ComplimentService;

@RestController
@RequiredArgsConstructor
public class ComplimentController {

    private final ComplimentService service;

    @GetMapping()
    public String getCompliment() {
     return service.getCompliment();
    }
}
