package com.dorapos.web.rest;

import com.dorapos.service.ProductExtraService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.ProductExtraDTO;
import com.dorapos.service.dto.ProductExtraCriteria;
import com.dorapos.service.ProductExtraQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.dorapos.domain.ProductExtra}.
 */
@RestController
@RequestMapping("/api")
public class ProductExtraResource {

    private final Logger log = LoggerFactory.getLogger(ProductExtraResource.class);

    private static final String ENTITY_NAME = "productExtra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductExtraService productExtraService;

    private final ProductExtraQueryService productExtraQueryService;

    public ProductExtraResource(ProductExtraService productExtraService, ProductExtraQueryService productExtraQueryService) {
        this.productExtraService = productExtraService;
        this.productExtraQueryService = productExtraQueryService;
    }

    /**
     * {@code POST  /product-extras} : Create a new productExtra.
     *
     * @param productExtraDTO the productExtraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productExtraDTO, or with status {@code 400 (Bad Request)} if the productExtra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-extras")
    public ResponseEntity<ProductExtraDTO> createProductExtra(@RequestBody ProductExtraDTO productExtraDTO) throws URISyntaxException {
        log.debug("REST request to save ProductExtra : {}", productExtraDTO);
        if (productExtraDTO.getId() != null) {
            throw new BadRequestAlertException("A new productExtra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductExtraDTO result = productExtraService.save(productExtraDTO);
        return ResponseEntity.created(new URI("/api/product-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-extras} : Updates an existing productExtra.
     *
     * @param productExtraDTO the productExtraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productExtraDTO,
     * or with status {@code 400 (Bad Request)} if the productExtraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productExtraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-extras")
    public ResponseEntity<ProductExtraDTO> updateProductExtra(@RequestBody ProductExtraDTO productExtraDTO) throws URISyntaxException {
        log.debug("REST request to update ProductExtra : {}", productExtraDTO);
        if (productExtraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductExtraDTO result = productExtraService.save(productExtraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productExtraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-extras} : get all the productExtras.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productExtras in body.
     */
    @GetMapping("/product-extras")
    public ResponseEntity<List<ProductExtraDTO>> getAllProductExtras(ProductExtraCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ProductExtras by criteria: {}", criteria);
        Page<ProductExtraDTO> page = productExtraQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /product-extras/count} : count all the productExtras.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/product-extras/count")
    public ResponseEntity<Long> countProductExtras(ProductExtraCriteria criteria) {
        log.debug("REST request to count ProductExtras by criteria: {}", criteria);
        return ResponseEntity.ok().body(productExtraQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /product-extras/:id} : get the "id" productExtra.
     *
     * @param id the id of the productExtraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productExtraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-extras/{id}")
    public ResponseEntity<ProductExtraDTO> getProductExtra(@PathVariable Long id) {
        log.debug("REST request to get ProductExtra : {}", id);
        Optional<ProductExtraDTO> productExtraDTO = productExtraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productExtraDTO);
    }

    /**
     * {@code DELETE  /product-extras/:id} : delete the "id" productExtra.
     *
     * @param id the id of the productExtraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-extras/{id}")
    public ResponseEntity<Void> deleteProductExtra(@PathVariable Long id) {
        log.debug("REST request to delete ProductExtra : {}", id);
        productExtraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/product-extras?query=:query} : search for the productExtra corresponding
     * to the query.
     *
     * @param query the query of the productExtra search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/product-extras")
    public ResponseEntity<List<ProductExtraDTO>> searchProductExtras(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ProductExtras for query {}", query);
        Page<ProductExtraDTO> page = productExtraService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
