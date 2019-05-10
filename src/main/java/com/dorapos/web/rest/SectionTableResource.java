package com.dorapos.web.rest;

import com.dorapos.service.SectionTableService;
import com.dorapos.web.rest.errors.BadRequestAlertException;
import com.dorapos.service.dto.SectionTableDTO;
import com.dorapos.service.dto.SectionTableCriteria;
import com.dorapos.service.SectionTableQueryService;

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
 * REST controller for managing {@link com.dorapos.domain.SectionTable}.
 */
@RestController
@RequestMapping("/api")
public class SectionTableResource {

    private final Logger log = LoggerFactory.getLogger(SectionTableResource.class);

    private static final String ENTITY_NAME = "sectionTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SectionTableService sectionTableService;

    private final SectionTableQueryService sectionTableQueryService;

    public SectionTableResource(SectionTableService sectionTableService, SectionTableQueryService sectionTableQueryService) {
        this.sectionTableService = sectionTableService;
        this.sectionTableQueryService = sectionTableQueryService;
    }

    /**
     * {@code POST  /section-tables} : Create a new sectionTable.
     *
     * @param sectionTableDTO the sectionTableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sectionTableDTO, or with status {@code 400 (Bad Request)} if the sectionTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/section-tables")
    public ResponseEntity<SectionTableDTO> createSectionTable(@RequestBody SectionTableDTO sectionTableDTO) throws URISyntaxException {
        log.debug("REST request to save SectionTable : {}", sectionTableDTO);
        if (sectionTableDTO.getId() != null) {
            throw new BadRequestAlertException("A new sectionTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SectionTableDTO result = sectionTableService.save(sectionTableDTO);
        return ResponseEntity.created(new URI("/api/section-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /section-tables} : Updates an existing sectionTable.
     *
     * @param sectionTableDTO the sectionTableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sectionTableDTO,
     * or with status {@code 400 (Bad Request)} if the sectionTableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sectionTableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/section-tables")
    public ResponseEntity<SectionTableDTO> updateSectionTable(@RequestBody SectionTableDTO sectionTableDTO) throws URISyntaxException {
        log.debug("REST request to update SectionTable : {}", sectionTableDTO);
        if (sectionTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SectionTableDTO result = sectionTableService.save(sectionTableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sectionTableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /section-tables} : get all the sectionTables.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sectionTables in body.
     */
    @GetMapping("/section-tables")
    public ResponseEntity<List<SectionTableDTO>> getAllSectionTables(SectionTableCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get SectionTables by criteria: {}", criteria);
        Page<SectionTableDTO> page = sectionTableQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /section-tables/count} : count all the sectionTables.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/section-tables/count")
    public ResponseEntity<Long> countSectionTables(SectionTableCriteria criteria) {
        log.debug("REST request to count SectionTables by criteria: {}", criteria);
        return ResponseEntity.ok().body(sectionTableQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /section-tables/:id} : get the "id" sectionTable.
     *
     * @param id the id of the sectionTableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sectionTableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/section-tables/{id}")
    public ResponseEntity<SectionTableDTO> getSectionTable(@PathVariable Long id) {
        log.debug("REST request to get SectionTable : {}", id);
        Optional<SectionTableDTO> sectionTableDTO = sectionTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sectionTableDTO);
    }

    /**
     * {@code DELETE  /section-tables/:id} : delete the "id" sectionTable.
     *
     * @param id the id of the sectionTableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/section-tables/{id}")
    public ResponseEntity<Void> deleteSectionTable(@PathVariable Long id) {
        log.debug("REST request to delete SectionTable : {}", id);
        sectionTableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/section-tables?query=:query} : search for the sectionTable corresponding
     * to the query.
     *
     * @param query the query of the sectionTable search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/section-tables")
    public ResponseEntity<List<SectionTableDTO>> searchSectionTables(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of SectionTables for query {}", query);
        Page<SectionTableDTO> page = sectionTableService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
