package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.DanToc;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.repository.DanTocRepository;
import vn.vnpt.ehealt.blockchain.service.DanTocService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.DanTocCriteria;
import vn.vnpt.ehealt.blockchain.service.DanTocQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static vn.vnpt.ehealt.blockchain.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DanTocResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class DanTocResourceIT {

    private static final String DEFAULT_MA_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_MA_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private DanTocRepository danTocRepository;

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private DanTocQueryService danTocQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDanTocMockMvc;

    private DanToc danToc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanTocResource danTocResource = new DanTocResource(danTocService, danTocQueryService);
        this.restDanTocMockMvc = MockMvcBuilders.standaloneSetup(danTocResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanToc createEntity(EntityManager em) {
        DanToc danToc = new DanToc()
            .maDanToc(DEFAULT_MA_DAN_TOC)
            .tenDanToc(DEFAULT_TEN_DAN_TOC)
            .ghiChu(DEFAULT_GHI_CHU)
            .status(DEFAULT_STATUS);
        return danToc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanToc createUpdatedEntity(EntityManager em) {
        DanToc danToc = new DanToc()
            .maDanToc(UPDATED_MA_DAN_TOC)
            .tenDanToc(UPDATED_TEN_DAN_TOC)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);
        return danToc;
    }

    @BeforeEach
    public void initTest() {
        danToc = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanToc() throws Exception {
        int databaseSizeBeforeCreate = danTocRepository.findAll().size();

        // Create the DanToc
        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danToc)))
            .andExpect(status().isCreated());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeCreate + 1);
        DanToc testDanToc = danTocList.get(danTocList.size() - 1);
        assertThat(testDanToc.getMaDanToc()).isEqualTo(DEFAULT_MA_DAN_TOC);
        assertThat(testDanToc.getTenDanToc()).isEqualTo(DEFAULT_TEN_DAN_TOC);
        assertThat(testDanToc.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testDanToc.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDanTocWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danTocRepository.findAll().size();

        // Create the DanToc with an existing ID
        danToc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanTocMockMvc.perform(post("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danToc)))
            .andExpect(status().isBadRequest());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDanTocs() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danToc.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDanToc").value(hasItem(DEFAULT_MA_DAN_TOC.toString())))
            .andExpect(jsonPath("$.[*].tenDanToc").value(hasItem(DEFAULT_TEN_DAN_TOC.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getDanToc() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get the danToc
        restDanTocMockMvc.perform(get("/api/dan-tocs/{id}", danToc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danToc.getId().intValue()))
            .andExpect(jsonPath("$.maDanToc").value(DEFAULT_MA_DAN_TOC.toString()))
            .andExpect(jsonPath("$.tenDanToc").value(DEFAULT_TEN_DAN_TOC.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc equals to DEFAULT_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.equals=" + DEFAULT_MA_DAN_TOC);

        // Get all the danTocList where maDanToc equals to UPDATED_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.equals=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc in DEFAULT_MA_DAN_TOC or UPDATED_MA_DAN_TOC
        defaultDanTocShouldBeFound("maDanToc.in=" + DEFAULT_MA_DAN_TOC + "," + UPDATED_MA_DAN_TOC);

        // Get all the danTocList where maDanToc equals to UPDATED_MA_DAN_TOC
        defaultDanTocShouldNotBeFound("maDanToc.in=" + UPDATED_MA_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByMaDanTocIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where maDanToc is not null
        defaultDanTocShouldBeFound("maDanToc.specified=true");

        // Get all the danTocList where maDanToc is null
        defaultDanTocShouldNotBeFound("maDanToc.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc equals to DEFAULT_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.equals=" + DEFAULT_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc equals to UPDATED_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.equals=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc in DEFAULT_TEN_DAN_TOC or UPDATED_TEN_DAN_TOC
        defaultDanTocShouldBeFound("tenDanToc.in=" + DEFAULT_TEN_DAN_TOC + "," + UPDATED_TEN_DAN_TOC);

        // Get all the danTocList where tenDanToc equals to UPDATED_TEN_DAN_TOC
        defaultDanTocShouldNotBeFound("tenDanToc.in=" + UPDATED_TEN_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllDanTocsByTenDanTocIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where tenDanToc is not null
        defaultDanTocShouldBeFound("tenDanToc.specified=true");

        // Get all the danTocList where tenDanToc is null
        defaultDanTocShouldNotBeFound("tenDanToc.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanTocsByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where ghiChu equals to DEFAULT_GHI_CHU
        defaultDanTocShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the danTocList where ghiChu equals to UPDATED_GHI_CHU
        defaultDanTocShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllDanTocsByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultDanTocShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the danTocList where ghiChu equals to UPDATED_GHI_CHU
        defaultDanTocShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllDanTocsByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where ghiChu is not null
        defaultDanTocShouldBeFound("ghiChu.specified=true");

        // Get all the danTocList where ghiChu is null
        defaultDanTocShouldNotBeFound("ghiChu.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status equals to DEFAULT_STATUS
        defaultDanTocShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the danTocList where status equals to UPDATED_STATUS
        defaultDanTocShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultDanTocShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the danTocList where status equals to UPDATED_STATUS
        defaultDanTocShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status is not null
        defaultDanTocShouldBeFound("status.specified=true");

        // Get all the danTocList where status is null
        defaultDanTocShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status is greater than or equal to DEFAULT_STATUS
        defaultDanTocShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the danTocList where status is greater than or equal to UPDATED_STATUS
        defaultDanTocShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status is less than or equal to DEFAULT_STATUS
        defaultDanTocShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the danTocList where status is less than or equal to SMALLER_STATUS
        defaultDanTocShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status is less than DEFAULT_STATUS
        defaultDanTocShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the danTocList where status is less than UPDATED_STATUS
        defaultDanTocShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDanTocsByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);

        // Get all the danTocList where status is greater than DEFAULT_STATUS
        defaultDanTocShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the danTocList where status is greater than SMALLER_STATUS
        defaultDanTocShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllDanTocsByBlcGiayChungSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        danTocRepository.saveAndFlush(danToc);
        BlcGiayChungSinh blcGiayChungSinh = BlcGiayChungSinhResourceIT.createEntity(em);
        em.persist(blcGiayChungSinh);
        em.flush();
        danToc.setBlcGiayChungSinh(blcGiayChungSinh);
        danTocRepository.saveAndFlush(danToc);
        Long blcGiayChungSinhId = blcGiayChungSinh.getId();

        // Get all the danTocList where blcGiayChungSinh equals to blcGiayChungSinhId
        defaultDanTocShouldBeFound("blcGiayChungSinhId.equals=" + blcGiayChungSinhId);

        // Get all the danTocList where blcGiayChungSinh equals to blcGiayChungSinhId + 1
        defaultDanTocShouldNotBeFound("blcGiayChungSinhId.equals=" + (blcGiayChungSinhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanTocShouldBeFound(String filter) throws Exception {
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danToc.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDanToc").value(hasItem(DEFAULT_MA_DAN_TOC)))
            .andExpect(jsonPath("$.[*].tenDanToc").value(hasItem(DEFAULT_TEN_DAN_TOC)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restDanTocMockMvc.perform(get("/api/dan-tocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanTocShouldNotBeFound(String filter) throws Exception {
        restDanTocMockMvc.perform(get("/api/dan-tocs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanTocMockMvc.perform(get("/api/dan-tocs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanToc() throws Exception {
        // Get the danToc
        restDanTocMockMvc.perform(get("/api/dan-tocs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanToc() throws Exception {
        // Initialize the database
        danTocService.save(danToc);

        int databaseSizeBeforeUpdate = danTocRepository.findAll().size();

        // Update the danToc
        DanToc updatedDanToc = danTocRepository.findById(danToc.getId()).get();
        // Disconnect from session so that the updates on updatedDanToc are not directly saved in db
        em.detach(updatedDanToc);
        updatedDanToc
            .maDanToc(UPDATED_MA_DAN_TOC)
            .tenDanToc(UPDATED_TEN_DAN_TOC)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);

        restDanTocMockMvc.perform(put("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDanToc)))
            .andExpect(status().isOk());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeUpdate);
        DanToc testDanToc = danTocList.get(danTocList.size() - 1);
        assertThat(testDanToc.getMaDanToc()).isEqualTo(UPDATED_MA_DAN_TOC);
        assertThat(testDanToc.getTenDanToc()).isEqualTo(UPDATED_TEN_DAN_TOC);
        assertThat(testDanToc.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testDanToc.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDanToc() throws Exception {
        int databaseSizeBeforeUpdate = danTocRepository.findAll().size();

        // Create the DanToc

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanTocMockMvc.perform(put("/api/dan-tocs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danToc)))
            .andExpect(status().isBadRequest());

        // Validate the DanToc in the database
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanToc() throws Exception {
        // Initialize the database
        danTocService.save(danToc);

        int databaseSizeBeforeDelete = danTocRepository.findAll().size();

        // Delete the danToc
        restDanTocMockMvc.perform(delete("/api/dan-tocs/{id}", danToc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanToc> danTocList = danTocRepository.findAll();
        assertThat(danTocList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanToc.class);
        DanToc danToc1 = new DanToc();
        danToc1.setId(1L);
        DanToc danToc2 = new DanToc();
        danToc2.setId(danToc1.getId());
        assertThat(danToc1).isEqualTo(danToc2);
        danToc2.setId(2L);
        assertThat(danToc1).isNotEqualTo(danToc2);
        danToc1.setId(null);
        assertThat(danToc1).isNotEqualTo(danToc2);
    }
}
