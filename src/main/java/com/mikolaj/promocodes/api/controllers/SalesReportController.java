package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SalesReportController {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public SalesReportController(PurchaseRepository purchaseRepository) { this.purchaseRepository = purchaseRepository; }

    @GetMapping("/sales-report")
    public String displaySalesReport(Model model){
        model.addAttribute("salesReportData", purchaseRepository.generateSalesReport());
        return "sales-report";
    }
}
