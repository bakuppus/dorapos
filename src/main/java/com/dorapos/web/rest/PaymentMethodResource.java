package com.dorapos.web.rest;

import com.dorapos.service.PaymentMethodService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.PaymentMethodDTO;
import com.dorapos.service.dto.PaymentMethodCriteria;
import com.dorapos.service.PaymentMethodQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.dorapos.domain.PaymentMethod}.
 */
@RestController
@RequestMapping("/api")
public class PaymentMethodResource {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodResource.class);

    private static final String ENTITY_NAME = "paymentMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentMethodService paymentMethodService;

    private final PaymentMethodQueryService paymentMethodQueryService;

    public PaymentMethodResource(PaymentMethodService paymentMethodService, PaymentMethodQueryService paymentMethodQueryService) {
        this.paymentMethodService = paymentMethodService;
        this.paymentMethodQueryService = paymentMethodQueryService;
    }

    /**
     * {@code POST  /payment-methods} : Create a new paymentMethod.
     *
     * @param paymentMethodDTO the paymentMethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentMethodDTO, or with status {@code 400 (Bad Request)} if the paymentMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-methods")
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentMethod : {}", paymentMethodDTO);
        if (paymentMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentMethodDTO result = paymentMethodService.save(paymentMethodDTO);
        return ResponseEntity.created(new URI("/api/payment-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-methods} : Updates an existing paymentMethod.
     *
     * @param paymentMethodDTO the paymentMethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentMethodDTO,
     * or with status {@code 400 (Bad Request)} if the paymentMethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentMethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-methods")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentMethod : {}", paymentMethodDTO);
        if (paymentMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentMethodDTO result = paymentMethodService.save(paymentMethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-methods} : get all the paymentMethods.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentMethods in body.
     */
    @GetMapping("/payment-methods")
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethods(PaymentMethodCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PaymentMethods by criteria: {}", criteria);
        Page<PaymentMethodDTO> page = paymentMethodQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /payment-methods/count} : count all the paymentMethods.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/payment-methods/count")
    public ResponseEntity<Long> countPaymentMethods(PaymentMethodCriteria criteria) {
        log.debug("REST request to count PaymentMethods by criteria: {}", criteria);
        return ResponseEntity.ok().body(paymentMethodQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /payment-methods/:id} : get the "id" paymentMethod.
     *
     * @param id the id of the paymentMethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentMethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethod(@PathVariable Long id) {
        log.debug("REST request to get PaymentMethod : {}", id);
        Optional<PaymentMethodDTO> paymentMethodDTO = paymentMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentMethodDTO);
    }

    /**
     * {@code DELETE  /payment-methods/:id} : delete the "id" paymentMethod.
     *
     * @param id the id of the paymentMethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-methods/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long id) {
        log.debug("REST request to delete PaymentMethod : {}", id);
        paymentMethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/payment-methods?query=:query} : search for the paymentMethod corresponding
     * to the query.
     *
     * @param query the query of the paymentMethod search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/payment-methods")
    public ResponseEntity<List<PaymentMethodDTO>> searchPaymentMethods(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of PaymentMethods for query {}", query);
        Page<PaymentMethodDTO> page = paymentMethodService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
