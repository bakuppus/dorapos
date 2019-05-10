package com.dorapos.web.rest;

import com.dorapos.service.OrdersLineVariantService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.OrdersLineVariantDTO;
import com.dorapos.service.dto.OrdersLineVariantCriteria;
import com.dorapos.service.OrdersLineVariantQueryService;

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
 * REST controller for managing {@link com.dorapos.domain.OrdersLineVariant}.
 */
@RestController
@RequestMapping("/api")
public class OrdersLineVariantResource {

    private final Logger log = LoggerFactory.getLogger(OrdersLineVariantResource.class);

    private static final String ENTITY_NAME = "ordersLineVariant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdersLineVariantService ordersLineVariantService;

    private final OrdersLineVariantQueryService ordersLineVariantQueryService;

    public OrdersLineVariantResource(OrdersLineVariantService ordersLineVariantService, OrdersLineVariantQueryService ordersLineVariantQueryService) {
        this.ordersLineVariantService = ordersLineVariantService;
        this.ordersLineVariantQueryService = ordersLineVariantQueryService;
    }

    /**
     * {@code POST  /orders-line-variants} : Create a new ordersLineVariant.
     *
     * @param ordersLineVariantDTO the ordersLineVariantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordersLineVariantDTO, or with status {@code 400 (Bad Request)} if the ordersLineVariant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders-line-variants")
    public ResponseEntity<OrdersLineVariantDTO> createOrdersLineVariant(@Valid @RequestBody OrdersLineVariantDTO ordersLineVariantDTO) throws URISyntaxException {
        log.debug("REST request to save OrdersLineVariant : {}", ordersLineVariantDTO);
        if (ordersLineVariantDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordersLineVariant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdersLineVariantDTO result = ordersLineVariantService.save(ordersLineVariantDTO);
        return ResponseEntity.created(new URI("/api/orders-line-variants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /orders-line-variants} : Updates an existing ordersLineVariant.
     *
     * @param ordersLineVariantDTO the ordersLineVariantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordersLineVariantDTO,
     * or with status {@code 400 (Bad Request)} if the ordersLineVariantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordersLineVariantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders-line-variants")
    public ResponseEntity<OrdersLineVariantDTO> updateOrdersLineVariant(@Valid @RequestBody OrdersLineVariantDTO ordersLineVariantDTO) throws URISyntaxException {
        log.debug("REST request to update OrdersLineVariant : {}", ordersLineVariantDTO);
        if (ordersLineVariantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdersLineVariantDTO result = ordersLineVariantService.save(ordersLineVariantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordersLineVariantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /orders-line-variants} : get all the ordersLineVariants.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordersLineVariants in body.
     */
    @GetMapping("/orders-line-variants")
    public ResponseEntity<List<OrdersLineVariantDTO>> getAllOrdersLineVariants(OrdersLineVariantCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get OrdersLineVariants by criteria: {}", criteria);
        Page<OrdersLineVariantDTO> page = ordersLineVariantQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /orders-line-variants/count} : count all the ordersLineVariants.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/orders-line-variants/count")
    public ResponseEntity<Long> countOrdersLineVariants(OrdersLineVariantCriteria criteria) {
        log.debug("REST request to count OrdersLineVariants by criteria: {}", criteria);
        return ResponseEntity.ok().body(ordersLineVariantQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /orders-line-variants/:id} : get the "id" ordersLineVariant.
     *
     * @param id the id of the ordersLineVariantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordersLineVariantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders-line-variants/{id}")
    public ResponseEntity<OrdersLineVariantDTO> getOrdersLineVariant(@PathVariable Long id) {
        log.debug("REST request to get OrdersLineVariant : {}", id);
        Optional<OrdersLineVariantDTO> ordersLineVariantDTO = ordersLineVariantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordersLineVariantDTO);
    }

    /**
     * {@code DELETE  /orders-line-variants/:id} : delete the "id" ordersLineVariant.
     *
     * @param id the id of the ordersLineVariantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders-line-variants/{id}")
    public ResponseEntity<Void> deleteOrdersLineVariant(@PathVariable Long id) {
        log.debug("REST request to delete OrdersLineVariant : {}", id);
        ordersLineVariantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/orders-line-variants?query=:query} : search for the ordersLineVariant corresponding
     * to the query.
     *
     * @param query the query of the ordersLineVariant search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/orders-line-variants")
    public ResponseEntity<List<OrdersLineVariantDTO>> searchOrdersLineVariants(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of OrdersLineVariants for query {}", query);
        Page<OrdersLineVariantDTO> page = ordersLineVariantService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
