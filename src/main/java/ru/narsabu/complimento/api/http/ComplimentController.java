package ru.narsabu.complimento.api.http;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.narsabu.complimento.service.ComplimentService;

@RestController
@RequiredArgsConstructor
public class ComplimentController {

    private final ComplimentService service;

    @GetMapping()
    @Operation(summary = "get random compliment")
    public String getCompliment() {
     return service.getCompliment();
    }
}
