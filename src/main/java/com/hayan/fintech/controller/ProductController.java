package com.hayan.fintech.controller;

import com.hayan.fintech.common.response.ApplicationResponse;
import com.hayan.fintech.common.response.SuccessCode;
import com.hayan.fintech.domain.dto.ProductInfoDto;
import com.hayan.fintech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fintech/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{organization-code}")
    public ApplicationResponse<List<ProductInfoDto>> getByOrganizationCode(@PathVariable("organization-code") String organizationCode) {
        var productList = productService.loadProductsByOrganizationCode(organizationCode);

        return ApplicationResponse.ok(productList, SuccessCode.SUCCESS);
    }

    @PostMapping("/information")
    public ApplicationResponse<Void> create(@RequestBody ProductInfoDto productInfoDto) {
        productService.save(productInfoDto);

        return ApplicationResponse.noData(SuccessCode.SUCCESS);
    }
}
