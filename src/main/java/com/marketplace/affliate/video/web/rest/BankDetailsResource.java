package com.marketplace.affliate.video.web.rest;

import com.marketplace.affliate.video.domain.BankDetails;
import com.marketplace.affliate.video.repository.BankDetailsRepository;
import com.marketplace.affliate.video.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.marketplace.affliate.video.domain.BankDetails}.
 */
@RestController
@RequestMapping("/api/bank-details")
@Transactional
public class BankDetailsResource {

    private static final Logger LOG = LoggerFactory.getLogger(BankDetailsResource.class);

    private static final String ENTITY_NAME = "bankDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankDetailsRepository bankDetailsRepository;

    public BankDetailsResource(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    /**
     * {@code POST  /bank-details} : Create a new bankDetails.
     *
     * @param bankDetails the bankDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankDetails, or with status {@code 400 (Bad Request)} if the bankDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BankDetails> createBankDetails(@RequestBody BankDetails bankDetails) throws URISyntaxException {
        LOG.debug("REST request to save BankDetails : {}", bankDetails);
        if (bankDetails.getId() != null) {
            throw new BadRequestAlertException("A new bankDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bankDetails = bankDetailsRepository.save(bankDetails);
        return ResponseEntity.created(new URI("/api/bank-details/" + bankDetails.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, bankDetails.getId().toString()))
            .body(bankDetails);
    }

    /**
     * {@code PUT  /bank-details/:id} : Updates an existing bankDetails.
     *
     * @param id the id of the bankDetails to save.
     * @param bankDetails the bankDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankDetails,
     * or with status {@code 400 (Bad Request)} if the bankDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BankDetails> updateBankDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankDetails bankDetails
    ) throws URISyntaxException {
        LOG.debug("REST request to update BankDetails : {}, {}", id, bankDetails);
        if (bankDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bankDetails = bankDetailsRepository.save(bankDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankDetails.getId().toString()))
            .body(bankDetails);
    }

    /**
     * {@code PATCH  /bank-details/:id} : Partial updates given fields of an existing bankDetails, field will ignore if it is null
     *
     * @param id the id of the bankDetails to save.
     * @param bankDetails the bankDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankDetails,
     * or with status {@code 400 (Bad Request)} if the bankDetails is not valid,
     * or with status {@code 404 (Not Found)} if the bankDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the bankDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BankDetails> partialUpdateBankDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BankDetails bankDetails
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update BankDetails partially : {}, {}", id, bankDetails);
        if (bankDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BankDetails> result = bankDetailsRepository
            .findById(bankDetails.getId())
            .map(existingBankDetails -> {
                if (bankDetails.getAccountName() != null) {
                    existingBankDetails.setAccountName(bankDetails.getAccountName());
                }
                if (bankDetails.getAccountNo() != null) {
                    existingBankDetails.setAccountNo(bankDetails.getAccountNo());
                }
                if (bankDetails.getBankName() != null) {
                    existingBankDetails.setBankName(bankDetails.getBankName());
                }
                if (bankDetails.getIfsc() != null) {
                    existingBankDetails.setIfsc(bankDetails.getIfsc());
                }
                if (bankDetails.getProofUrl() != null) {
                    existingBankDetails.setProofUrl(bankDetails.getProofUrl());
                }
                if (bankDetails.getUpiHandle() != null) {
                    existingBankDetails.setUpiHandle(bankDetails.getUpiHandle());
                }
                if (bankDetails.getIsActive() != null) {
                    existingBankDetails.setIsActive(bankDetails.getIsActive());
                }
                if (bankDetails.getCreatedBy() != null) {
                    existingBankDetails.setCreatedBy(bankDetails.getCreatedBy());
                }
                if (bankDetails.getCreatedOn() != null) {
                    existingBankDetails.setCreatedOn(bankDetails.getCreatedOn());
                }
                if (bankDetails.getUpdatedBy() != null) {
                    existingBankDetails.setUpdatedBy(bankDetails.getUpdatedBy());
                }
                if (bankDetails.getUpdatedOn() != null) {
                    existingBankDetails.setUpdatedOn(bankDetails.getUpdatedOn());
                }

                return existingBankDetails;
            })
            .map(bankDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /bank-details} : get all the bankDetails.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankDetails in body.
     */
    @GetMapping("")
    public List<BankDetails> getAllBankDetails(@RequestParam(name = "filter", required = false) String filter) {
        if ("user-is-null".equals(filter)) {
            LOG.debug("REST request to get all BankDetailss where user is null");
            return StreamSupport.stream(bankDetailsRepository.findAll().spliterator(), false)
                .filter(bankDetails -> bankDetails.getUser() == null)
                .toList();
        }
        LOG.debug("REST request to get all BankDetails");
        return bankDetailsRepository.findAll();
    }

    /**
     * {@code GET  /bank-details/:id} : get the "id" bankDetails.
     *
     * @param id the id of the bankDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BankDetails> getBankDetails(@PathVariable("id") Long id) {
        LOG.debug("REST request to get BankDetails : {}", id);
        Optional<BankDetails> bankDetails = bankDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bankDetails);
    }

    /**
     * {@code DELETE  /bank-details/:id} : delete the "id" bankDetails.
     *
     * @param id the id of the bankDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankDetails(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete BankDetails : {}", id);
        bankDetailsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
