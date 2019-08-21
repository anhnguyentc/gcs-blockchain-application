package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.repository.DonViHanhChinhRepository;
import vn.vnpt.ehealt.blockchain.service.DonViHanhChinhService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.DonViHanhChinhCriteria;
import vn.vnpt.ehealt.blockchain.service.DonViHanhChinhQueryService;

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
 * Integration tests for the {@link DonViHanhChinhResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class DonViHanhChinhResourceIT {

    private static final String DEFAULT_MA_DON_VI_HANH_CHINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_DON_VI_HANH_CHINH = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DON_VI_HANH_CHINH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DON_VI_HANH_CHINH = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private DonViHanhChinhRepository donViHanhChinhRepository;

    @Autowired
    private DonViHanhChinhService donViHanhChinhService;

    @Autowired
    private DonViHanhChinhQueryService donViHanhChinhQueryService;

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

    private MockMvc restDonViHanhChinhMockMvc;

    private DonViHanhChinh donViHanhChinh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonViHanhChinhResource donViHanhChinhResource = new DonViHanhChinhResource(donViHanhChinhService, donViHanhChinhQueryService);
        this.restDonViHanhChinhMockMvc = MockMvcBuilders.standaloneSetup(donViHanhChinhResource)
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
    public static DonViHanhChinh createEntity(EntityManager em) {
        DonViHanhChinh donViHanhChinh = new DonViHanhChinh()
            .maDonViHanhChinh(DEFAULT_MA_DON_VI_HANH_CHINH)
            .tenDonViHanhChinh(DEFAULT_TEN_DON_VI_HANH_CHINH)
            .ghiChu(DEFAULT_GHI_CHU)
            .status(DEFAULT_STATUS);
        return donViHanhChinh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DonViHanhChinh createUpdatedEntity(EntityManager em) {
        DonViHanhChinh donViHanhChinh = new DonViHanhChinh()
            .maDonViHanhChinh(UPDATED_MA_DON_VI_HANH_CHINH)
            .tenDonViHanhChinh(UPDATED_TEN_DON_VI_HANH_CHINH)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);
        return donViHanhChinh;
    }

    @BeforeEach
    public void initTest() {
        donViHanhChinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonViHanhChinh() throws Exception {
        int databaseSizeBeforeCreate = donViHanhChinhRepository.findAll().size();

        // Create the DonViHanhChinh
        restDonViHanhChinhMockMvc.perform(post("/api/don-vi-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViHanhChinh)))
            .andExpect(status().isCreated());

        // Validate the DonViHanhChinh in the database
        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeCreate + 1);
        DonViHanhChinh testDonViHanhChinh = donViHanhChinhList.get(donViHanhChinhList.size() - 1);
        assertThat(testDonViHanhChinh.getMaDonViHanhChinh()).isEqualTo(DEFAULT_MA_DON_VI_HANH_CHINH);
        assertThat(testDonViHanhChinh.getTenDonViHanhChinh()).isEqualTo(DEFAULT_TEN_DON_VI_HANH_CHINH);
        assertThat(testDonViHanhChinh.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testDonViHanhChinh.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDonViHanhChinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donViHanhChinhRepository.findAll().size();

        // Create the DonViHanhChinh with an existing ID
        donViHanhChinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonViHanhChinhMockMvc.perform(post("/api/don-vi-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViHanhChinh)))
            .andExpect(status().isBadRequest());

        // Validate the DonViHanhChinh in the database
        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaDonViHanhChinhIsRequired() throws Exception {
        int databaseSizeBeforeTest = donViHanhChinhRepository.findAll().size();
        // set the field null
        donViHanhChinh.setMaDonViHanhChinh(null);

        // Create the DonViHanhChinh, which fails.

        restDonViHanhChinhMockMvc.perform(post("/api/don-vi-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViHanhChinh)))
            .andExpect(status().isBadRequest());

        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhs() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donViHanhChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDonViHanhChinh").value(hasItem(DEFAULT_MA_DON_VI_HANH_CHINH.toString())))
            .andExpect(jsonPath("$.[*].tenDonViHanhChinh").value(hasItem(DEFAULT_TEN_DON_VI_HANH_CHINH.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getDonViHanhChinh() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get the donViHanhChinh
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs/{id}", donViHanhChinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donViHanhChinh.getId().intValue()))
            .andExpect(jsonPath("$.maDonViHanhChinh").value(DEFAULT_MA_DON_VI_HANH_CHINH.toString()))
            .andExpect(jsonPath("$.tenDonViHanhChinh").value(DEFAULT_TEN_DON_VI_HANH_CHINH.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByMaDonViHanhChinhIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where maDonViHanhChinh equals to DEFAULT_MA_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldBeFound("maDonViHanhChinh.equals=" + DEFAULT_MA_DON_VI_HANH_CHINH);

        // Get all the donViHanhChinhList where maDonViHanhChinh equals to UPDATED_MA_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldNotBeFound("maDonViHanhChinh.equals=" + UPDATED_MA_DON_VI_HANH_CHINH);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByMaDonViHanhChinhIsInShouldWork() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where maDonViHanhChinh in DEFAULT_MA_DON_VI_HANH_CHINH or UPDATED_MA_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldBeFound("maDonViHanhChinh.in=" + DEFAULT_MA_DON_VI_HANH_CHINH + "," + UPDATED_MA_DON_VI_HANH_CHINH);

        // Get all the donViHanhChinhList where maDonViHanhChinh equals to UPDATED_MA_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldNotBeFound("maDonViHanhChinh.in=" + UPDATED_MA_DON_VI_HANH_CHINH);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByMaDonViHanhChinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where maDonViHanhChinh is not null
        defaultDonViHanhChinhShouldBeFound("maDonViHanhChinh.specified=true");

        // Get all the donViHanhChinhList where maDonViHanhChinh is null
        defaultDonViHanhChinhShouldNotBeFound("maDonViHanhChinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByTenDonViHanhChinhIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where tenDonViHanhChinh equals to DEFAULT_TEN_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldBeFound("tenDonViHanhChinh.equals=" + DEFAULT_TEN_DON_VI_HANH_CHINH);

        // Get all the donViHanhChinhList where tenDonViHanhChinh equals to UPDATED_TEN_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldNotBeFound("tenDonViHanhChinh.equals=" + UPDATED_TEN_DON_VI_HANH_CHINH);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByTenDonViHanhChinhIsInShouldWork() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where tenDonViHanhChinh in DEFAULT_TEN_DON_VI_HANH_CHINH or UPDATED_TEN_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldBeFound("tenDonViHanhChinh.in=" + DEFAULT_TEN_DON_VI_HANH_CHINH + "," + UPDATED_TEN_DON_VI_HANH_CHINH);

        // Get all the donViHanhChinhList where tenDonViHanhChinh equals to UPDATED_TEN_DON_VI_HANH_CHINH
        defaultDonViHanhChinhShouldNotBeFound("tenDonViHanhChinh.in=" + UPDATED_TEN_DON_VI_HANH_CHINH);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByTenDonViHanhChinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where tenDonViHanhChinh is not null
        defaultDonViHanhChinhShouldBeFound("tenDonViHanhChinh.specified=true");

        // Get all the donViHanhChinhList where tenDonViHanhChinh is null
        defaultDonViHanhChinhShouldNotBeFound("tenDonViHanhChinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where ghiChu equals to DEFAULT_GHI_CHU
        defaultDonViHanhChinhShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the donViHanhChinhList where ghiChu equals to UPDATED_GHI_CHU
        defaultDonViHanhChinhShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultDonViHanhChinhShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the donViHanhChinhList where ghiChu equals to UPDATED_GHI_CHU
        defaultDonViHanhChinhShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where ghiChu is not null
        defaultDonViHanhChinhShouldBeFound("ghiChu.specified=true");

        // Get all the donViHanhChinhList where ghiChu is null
        defaultDonViHanhChinhShouldNotBeFound("ghiChu.specified=false");
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status equals to DEFAULT_STATUS
        defaultDonViHanhChinhShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the donViHanhChinhList where status equals to UPDATED_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultDonViHanhChinhShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the donViHanhChinhList where status equals to UPDATED_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status is not null
        defaultDonViHanhChinhShouldBeFound("status.specified=true");

        // Get all the donViHanhChinhList where status is null
        defaultDonViHanhChinhShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status is greater than or equal to DEFAULT_STATUS
        defaultDonViHanhChinhShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the donViHanhChinhList where status is greater than or equal to UPDATED_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status is less than or equal to DEFAULT_STATUS
        defaultDonViHanhChinhShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the donViHanhChinhList where status is less than or equal to SMALLER_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status is less than DEFAULT_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the donViHanhChinhList where status is less than UPDATED_STATUS
        defaultDonViHanhChinhShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllDonViHanhChinhsByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);

        // Get all the donViHanhChinhList where status is greater than DEFAULT_STATUS
        defaultDonViHanhChinhShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the donViHanhChinhList where status is greater than SMALLER_STATUS
        defaultDonViHanhChinhShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllDonViHanhChinhsByBlcGiayChungSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);
        BlcGiayChungSinh blcGiayChungSinh = BlcGiayChungSinhResourceIT.createEntity(em);
        em.persist(blcGiayChungSinh);
        em.flush();
        donViHanhChinh.setBlcGiayChungSinh(blcGiayChungSinh);
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);
        Long blcGiayChungSinhId = blcGiayChungSinh.getId();

        // Get all the donViHanhChinhList where blcGiayChungSinh equals to blcGiayChungSinhId
        defaultDonViHanhChinhShouldBeFound("blcGiayChungSinhId.equals=" + blcGiayChungSinhId);

        // Get all the donViHanhChinhList where blcGiayChungSinh equals to blcGiayChungSinhId + 1
        defaultDonViHanhChinhShouldNotBeFound("blcGiayChungSinhId.equals=" + (blcGiayChungSinhId + 1));
    }


    @Test
    @Transactional
    public void getAllDonViHanhChinhsByBlcGiayKhamSucKhoeIsEqualToSomething() throws Exception {
        // Initialize the database
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe = BlcGiayKhamSucKhoeResourceIT.createEntity(em);
        em.persist(blcGiayKhamSucKhoe);
        em.flush();
        donViHanhChinh.setBlcGiayKhamSucKhoe(blcGiayKhamSucKhoe);
        donViHanhChinhRepository.saveAndFlush(donViHanhChinh);
        Long blcGiayKhamSucKhoeId = blcGiayKhamSucKhoe.getId();

        // Get all the donViHanhChinhList where blcGiayKhamSucKhoe equals to blcGiayKhamSucKhoeId
        defaultDonViHanhChinhShouldBeFound("blcGiayKhamSucKhoeId.equals=" + blcGiayKhamSucKhoeId);

        // Get all the donViHanhChinhList where blcGiayKhamSucKhoe equals to blcGiayKhamSucKhoeId + 1
        defaultDonViHanhChinhShouldNotBeFound("blcGiayKhamSucKhoeId.equals=" + (blcGiayKhamSucKhoeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDonViHanhChinhShouldBeFound(String filter) throws Exception {
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donViHanhChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDonViHanhChinh").value(hasItem(DEFAULT_MA_DON_VI_HANH_CHINH)))
            .andExpect(jsonPath("$.[*].tenDonViHanhChinh").value(hasItem(DEFAULT_TEN_DON_VI_HANH_CHINH)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDonViHanhChinhShouldNotBeFound(String filter) throws Exception {
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDonViHanhChinh() throws Exception {
        // Get the donViHanhChinh
        restDonViHanhChinhMockMvc.perform(get("/api/don-vi-hanh-chinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonViHanhChinh() throws Exception {
        // Initialize the database
        donViHanhChinhService.save(donViHanhChinh);

        int databaseSizeBeforeUpdate = donViHanhChinhRepository.findAll().size();

        // Update the donViHanhChinh
        DonViHanhChinh updatedDonViHanhChinh = donViHanhChinhRepository.findById(donViHanhChinh.getId()).get();
        // Disconnect from session so that the updates on updatedDonViHanhChinh are not directly saved in db
        em.detach(updatedDonViHanhChinh);
        updatedDonViHanhChinh
            .maDonViHanhChinh(UPDATED_MA_DON_VI_HANH_CHINH)
            .tenDonViHanhChinh(UPDATED_TEN_DON_VI_HANH_CHINH)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);

        restDonViHanhChinhMockMvc.perform(put("/api/don-vi-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonViHanhChinh)))
            .andExpect(status().isOk());

        // Validate the DonViHanhChinh in the database
        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeUpdate);
        DonViHanhChinh testDonViHanhChinh = donViHanhChinhList.get(donViHanhChinhList.size() - 1);
        assertThat(testDonViHanhChinh.getMaDonViHanhChinh()).isEqualTo(UPDATED_MA_DON_VI_HANH_CHINH);
        assertThat(testDonViHanhChinh.getTenDonViHanhChinh()).isEqualTo(UPDATED_TEN_DON_VI_HANH_CHINH);
        assertThat(testDonViHanhChinh.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testDonViHanhChinh.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDonViHanhChinh() throws Exception {
        int databaseSizeBeforeUpdate = donViHanhChinhRepository.findAll().size();

        // Create the DonViHanhChinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViHanhChinhMockMvc.perform(put("/api/don-vi-hanh-chinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViHanhChinh)))
            .andExpect(status().isBadRequest());

        // Validate the DonViHanhChinh in the database
        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDonViHanhChinh() throws Exception {
        // Initialize the database
        donViHanhChinhService.save(donViHanhChinh);

        int databaseSizeBeforeDelete = donViHanhChinhRepository.findAll().size();

        // Delete the donViHanhChinh
        restDonViHanhChinhMockMvc.perform(delete("/api/don-vi-hanh-chinhs/{id}", donViHanhChinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DonViHanhChinh> donViHanhChinhList = donViHanhChinhRepository.findAll();
        assertThat(donViHanhChinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViHanhChinh.class);
        DonViHanhChinh donViHanhChinh1 = new DonViHanhChinh();
        donViHanhChinh1.setId(1L);
        DonViHanhChinh donViHanhChinh2 = new DonViHanhChinh();
        donViHanhChinh2.setId(donViHanhChinh1.getId());
        assertThat(donViHanhChinh1).isEqualTo(donViHanhChinh2);
        donViHanhChinh2.setId(2L);
        assertThat(donViHanhChinh1).isNotEqualTo(donViHanhChinh2);
        donViHanhChinh1.setId(null);
        assertThat(donViHanhChinh1).isNotEqualTo(donViHanhChinh2);
    }
}
