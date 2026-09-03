package com.bit.mango.salesmarketing.pricing;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/competitor-prices")
@CrossOrigin(origins = "http://localhost:3000")
public class CompetitorPriceController {

    private final CompetitorPriceService service;

    public CompetitorPriceController(CompetitorPriceService service) {
        this.service = service;
    }

    @GetMapping
    public List<CompetitorPrice> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CompetitorPrice getOne(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitorPrice create(@Valid @RequestBody CompetitorPrice item) {
        return service.create(item);
    }

    @PutMapping("/{id}")
    public CompetitorPrice update(@PathVariable Integer id, @Valid @RequestBody CompetitorPrice item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
