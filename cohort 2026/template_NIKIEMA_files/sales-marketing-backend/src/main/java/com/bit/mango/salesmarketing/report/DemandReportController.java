package com.bit.mango.salesmarketing.report;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demand-reports")
@CrossOrigin(origins = "http://localhost:3000")
public class DemandReportController {

    private final DemandReportService service;

    public DemandReportController(DemandReportService service) {
        this.service = service;
    }

    @GetMapping
    public List<DemandReport> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DemandReport getOne(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DemandReport create(@Valid @RequestBody DemandReport item) {
        return service.create(item);
    }

    @PutMapping("/{id}")
    public DemandReport update(@PathVariable Integer id, @Valid @RequestBody DemandReport item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
