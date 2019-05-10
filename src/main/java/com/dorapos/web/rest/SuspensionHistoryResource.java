package com.dorapos.web.rest;

import com.dorapos.service.SuspensionHistoryService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.SuspensionHistoryDTO;
import com.dorapos.service.dto.SuspensionHistoryCriteria;
import com.dorapos.service.SuspensionHistoryQueryService;

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
 * REST controller for managing {@link com.dorapos.domain.SuspensionHistory}.
 */
@RestController
@RequestMapping("/api")
public class SuspensionHistoryResource {

    private final Logger log = LoggerFactory.getLogger(SuspensionHistoryResource.class);

    private static final String ENTITY_NAME = "suspensionHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuspensionHistoryService suspensionHistoryService;

    private final SuspensionHistoryQueryService suspensionHistoryQueryService;

    public SuspensionHistoryResource(SuspensionHistoryService suspensionHistoryService, SuspensionHistoryQueryService suspensionHistoryQueryService) {
        this.suspensionHistoryService = suspensionHistoryService;
        this.suspensionHistoryQueryService = suspensionHistoryQueryService;
    }

    /**
     * {@code POST  /suspension-histories} : Create a new suspensionHistory.
     *
     * @param suspensionHistoryDTO the suspensionHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suspensionHistoryDTO, or with status {@code 400 (Bad Request)} if the suspensionHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suspension-histories")
    public ResponseEntity<SuspensionHistoryDTO> createSuspensionHistory(@RequestBody SuspensionHistoryDTO suspensionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save SuspensionHistory : {}", suspensionHistoryDTO);
        if (suspensionHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new suspensionHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuspensionHistoryDTO result = suspensionHistoryService.save(suspensionHistoryDTO);
        return ResponseEntity.created(new URI("/api/suspension-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /suspension-histories} : Updates an existing suspensionHistory.
     *
     * @param suspensionHistoryDTO the suspensionHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suspensionHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the suspensionHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suspensionHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suspension-histories")
    public ResponseEntity<SuspensionHistoryDTO> updateSuspensionHistory(@RequestBody SuspensionHistoryDTO suspensionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update SuspensionHistory : {}", suspensionHistoryDTO);
        if (suspensionHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuspensionHistoryDTO result = suspensionHistoryService.save(suspensionHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suspensionHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /suspension-histories} : get all the suspensionHistories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suspensionHistories in body.
     */
    @GetMapping("/suspension-histories")
    public ResponseEntity<List<SuspensionHistoryDTO>> getAllSuspensionHistories(SuspensionHistoryCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get SuspensionHistories by criteria: {}", criteria);
        Page<SuspensionHistoryDTO> page = suspensionHistoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /suspension-histories/count} : count all the suspensionHistories.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/suspension-histories/count")
    public ResponseEntity<Long> countSuspensionHistories(SuspensionHistoryCriteria criteria) {
        log.debug("REST request to count SuspensionHistories by criteria: {}", criteria);
        return ResponseEntity.ok().body(suspensionHistoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /suspension-histories/:id} : get the "id" suspensionHistory.
     *
     * @param id the id of the suspensionHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suspensionHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suspension-histories/{id}")
    public ResponseEntity<SuspensionHistoryDTO> getSuspensionHistory(@PathVariable Long id) {
        log.debug("REST request to get SuspensionHistory : {}", id);
        Optional<SuspensionHistoryDTO> suspensionHistoryDTO = suspensionHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suspensionHistoryDTO);
    }

    /**
     * {@code DELETE  /suspension-histories/:id} : delete the "id" suspensionHistory.
     *
     * @param id the id of the suspensionHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suspension-histories/{id}")
    public ResponseEntity<Void> deleteSuspensionHistory(@PathVariable Long id) {
        log.debug("REST request to delete SuspensionHistory : {}", id);
        suspensionHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/suspension-histories?query=:query} : search for the suspensionHistory corresponding
     * to the query.
     *
     * @param query the query of the suspensionHistory search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/suspension-histories")
    public ResponseEntity<List<SuspensionHistoryDTO>> searchSuspensionHistories(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of SuspensionHistories for query {}", query);
        Page<SuspensionHistoryDTO> page = suspensionHistoryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
