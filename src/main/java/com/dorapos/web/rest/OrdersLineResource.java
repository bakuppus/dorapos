package com.dorapos.web.rest;

import com.dorapos.service.OrdersLineService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.OrdersLineDTO;
import com.dorapos.service.dto.OrdersLineCriteria;
import com.dorapos.service.OrdersLineQueryService;

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
 * REST controller for managing {@link com.dorapos.domain.OrdersLine}.
 */
@RestController
@RequestMapping("/api")
public class OrdersLineResource {

    private final Logger log = LoggerFactory.getLogger(OrdersLineResource.class);

    private static final String ENTITY_NAME = "ordersLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdersLineService ordersLineService;

    private final OrdersLineQueryService ordersLineQueryService;

    public OrdersLineResource(OrdersLineService ordersLineService, OrdersLineQueryService ordersLineQueryService) {
        this.ordersLineService = ordersLineService;
        this.ordersLineQueryService = ordersLineQueryService;
    }

    /**
     * {@code POST  /orders-lines} : Create a new ordersLine.
     *
     * @param ordersLineDTO the ordersLineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordersLineDTO, or with status {@code 400 (Bad Request)} if the ordersLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders-lines")
    public ResponseEntity<OrdersLineDTO> createOrdersLine(@Valid @RequestBody OrdersLineDTO ordersLineDTO) throws URISyntaxException {
        log.debug("REST request to save OrdersLine : {}", ordersLineDTO);
        if (ordersLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordersLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdersLineDTO result = ordersLineService.save(ordersLineDTO);
        return ResponseEntity.created(new URI("/api/orders-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /orders-lines} : Updates an existing ordersLine.
     *
     * @param ordersLineDTO the ordersLineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordersLineDTO,
     * or with status {@code 400 (Bad Request)} if the ordersLineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordersLineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders-lines")
    public ResponseEntity<OrdersLineDTO> updateOrdersLine(@Valid @RequestBody OrdersLineDTO ordersLineDTO) throws URISyntaxException {
        log.debug("REST request to update OrdersLine : {}", ordersLineDTO);
        if (ordersLineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdersLineDTO result = ordersLineService.save(ordersLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordersLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /orders-lines} : get all the ordersLines.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordersLines in body.
     */
    @GetMapping("/orders-lines")
    public ResponseEntity<List<OrdersLineDTO>> getAllOrdersLines(OrdersLineCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get OrdersLines by criteria: {}", criteria);
        Page<OrdersLineDTO> page = ordersLineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /orders-lines/count} : count all the ordersLines.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/orders-lines/count")
    public ResponseEntity<Long> countOrdersLines(OrdersLineCriteria criteria) {
        log.debug("REST request to count OrdersLines by criteria: {}", criteria);
        return ResponseEntity.ok().body(ordersLineQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /orders-lines/:id} : get the "id" ordersLine.
     *
     * @param id the id of the ordersLineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordersLineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders-lines/{id}")
    public ResponseEntity<OrdersLineDTO> getOrdersLine(@PathVariable Long id) {
        log.debug("REST request to get OrdersLine : {}", id);
        Optional<OrdersLineDTO> ordersLineDTO = ordersLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordersLineDTO);
    }

    /**
     * {@code DELETE  /orders-lines/:id} : delete the "id" ordersLine.
     *
     * @param id the id of the ordersLineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders-lines/{id}")
    public ResponseEntity<Void> deleteOrdersLine(@PathVariable Long id) {
        log.debug("REST request to delete OrdersLine : {}", id);
        ordersLineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/orders-lines?query=:query} : search for the ordersLine corresponding
     * to the query.
     *
     * @param query the query of the ordersLine search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/orders-lines")
    public ResponseEntity<List<OrdersLineDTO>> searchOrdersLines(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of OrdersLines for query {}", query);
        Page<OrdersLineDTO> page = ordersLineService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
