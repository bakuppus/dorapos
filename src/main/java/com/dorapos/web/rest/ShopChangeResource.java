package com.dorapos.web.rest;

import com.dorapos.service.ShopChangeService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.ShopChangeDTO;
import com.dorapos.service.dto.ShopChangeCriteria;
import com.dorapos.service.ShopChangeQueryService;

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
 * REST controller for managing {@link com.dorapos.domain.ShopChange}.
 */
@RestController
@RequestMapping("/api")
public class ShopChangeResource {

    private final Logger log = LoggerFactory.getLogger(ShopChangeResource.class);

    private static final String ENTITY_NAME = "shopChange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopChangeService shopChangeService;

    private final ShopChangeQueryService shopChangeQueryService;

    public ShopChangeResource(ShopChangeService shopChangeService, ShopChangeQueryService shopChangeQueryService) {
        this.shopChangeService = shopChangeService;
        this.shopChangeQueryService = shopChangeQueryService;
    }

    /**
     * {@code POST  /shop-changes} : Create a new shopChange.
     *
     * @param shopChangeDTO the shopChangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopChangeDTO, or with status {@code 400 (Bad Request)} if the shopChange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shop-changes")
    public ResponseEntity<ShopChangeDTO> createShopChange(@RequestBody ShopChangeDTO shopChangeDTO) throws URISyntaxException {
        log.debug("REST request to save ShopChange : {}", shopChangeDTO);
        if (shopChangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopChange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopChangeDTO result = shopChangeService.save(shopChangeDTO);
        return ResponseEntity.created(new URI("/api/shop-changes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shop-changes} : Updates an existing shopChange.
     *
     * @param shopChangeDTO the shopChangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopChangeDTO,
     * or with status {@code 400 (Bad Request)} if the shopChangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopChangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shop-changes")
    public ResponseEntity<ShopChangeDTO> updateShopChange(@RequestBody ShopChangeDTO shopChangeDTO) throws URISyntaxException {
        log.debug("REST request to update ShopChange : {}", shopChangeDTO);
        if (shopChangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopChangeDTO result = shopChangeService.save(shopChangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shopChangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shop-changes} : get all the shopChanges.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopChanges in body.
     */
    @GetMapping("/shop-changes")
    public ResponseEntity<List<ShopChangeDTO>> getAllShopChanges(ShopChangeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ShopChanges by criteria: {}", criteria);
        Page<ShopChangeDTO> page = shopChangeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /shop-changes/count} : count all the shopChanges.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/shop-changes/count")
    public ResponseEntity<Long> countShopChanges(ShopChangeCriteria criteria) {
        log.debug("REST request to count ShopChanges by criteria: {}", criteria);
        return ResponseEntity.ok().body(shopChangeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /shop-changes/:id} : get the "id" shopChange.
     *
     * @param id the id of the shopChangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopChangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shop-changes/{id}")
    public ResponseEntity<ShopChangeDTO> getShopChange(@PathVariable Long id) {
        log.debug("REST request to get ShopChange : {}", id);
        Optional<ShopChangeDTO> shopChangeDTO = shopChangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopChangeDTO);
    }

    /**
     * {@code DELETE  /shop-changes/:id} : delete the "id" shopChange.
     *
     * @param id the id of the shopChangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shop-changes/{id}")
    public ResponseEntity<Void> deleteShopChange(@PathVariable Long id) {
        log.debug("REST request to delete ShopChange : {}", id);
        shopChangeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/shop-changes?query=:query} : search for the shopChange corresponding
     * to the query.
     *
     * @param query the query of the shopChange search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/shop-changes")
    public ResponseEntity<List<ShopChangeDTO>> searchShopChanges(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ShopChanges for query {}", query);
        Page<ShopChangeDTO> page = shopChangeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
