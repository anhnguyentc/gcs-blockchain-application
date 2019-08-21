package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.repository.BenhVienRepository;
import vn.vnpt.ehealt.blockchain.service.BenhVienService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.BenhVienCriteria;
import vn.vnpt.ehealt.blockchain.service.BenhVienQueryService;

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
 * Integration tests for the {@link BenhVienResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class BenhVienResourceIT {

    private static final String DEFAULT_MA_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private BenhVienRepository benhVienRepository;

    @Autowired
    private BenhVienService benhVienService;

    @Autowired
    private BenhVienQueryService benhVienQueryService;

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

    private MockMvc restBenhVienMockMvc;

    private BenhVien benhVien;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BenhVienResource benhVienResource = new BenhVienResource(benhVienService, benhVienQueryService);
        this.restBenhVienMockMvc = MockMvcBuilders.standaloneSetup(benhVienResource)
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
    public static BenhVien createEntity(EntityManager em) {
        BenhVien benhVien = new BenhVien()
            .maBenhVien(DEFAULT_MA_BENH_VIEN)
            .tenBenhVien(DEFAULT_TEN_BENH_VIEN)
            .ghiChu(DEFAULT_GHI_CHU)
            .status(DEFAULT_STATUS);
        return benhVien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenhVien createUpdatedEntity(EntityManager em) {
        BenhVien benhVien = new BenhVien()
            .maBenhVien(UPDATED_MA_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);
        return benhVien;
    }

    @BeforeEach
    public void initTest() {
        benhVien = createEntity(em);
    }

    @Test
    @Transactional
    public void createBenhVien() throws Exception {
        int databaseSizeBeforeCreate = benhVienRepository.findAll().size();

        // Create the BenhVien
        restBenhVienMockMvc.perform(post("/api/benh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benhVien)))
            .andExpect(status().isCreated());

        // Validate the BenhVien in the database
        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeCreate + 1);
        BenhVien testBenhVien = benhVienList.get(benhVienList.size() - 1);
        assertThat(testBenhVien.getMaBenhVien()).isEqualTo(DEFAULT_MA_BENH_VIEN);
        assertThat(testBenhVien.getTenBenhVien()).isEqualTo(DEFAULT_TEN_BENH_VIEN);
        assertThat(testBenhVien.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testBenhVien.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBenhVienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = benhVienRepository.findAll().size();

        // Create the BenhVien with an existing ID
        benhVien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBenhVienMockMvc.perform(post("/api/benh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benhVien)))
            .andExpect(status().isBadRequest());

        // Validate the BenhVien in the database
        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaBenhVienIsRequired() throws Exception {
        int databaseSizeBeforeTest = benhVienRepository.findAll().size();
        // set the field null
        benhVien.setMaBenhVien(null);

        // Create the BenhVien, which fails.

        restBenhVienMockMvc.perform(post("/api/benh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benhVien)))
            .andExpect(status().isBadRequest());

        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBenhViens() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList
        restBenhVienMockMvc.perform(get("/api/benh-viens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(benhVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBenhVien").value(hasItem(DEFAULT_MA_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getBenhVien() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get the benhVien
        restBenhVienMockMvc.perform(get("/api/benh-viens/{id}", benhVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(benhVien.getId().intValue()))
            .andExpect(jsonPath("$.maBenhVien").value(DEFAULT_MA_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.tenBenhVien").value(DEFAULT_TEN_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllBenhViensByMaBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where maBenhVien equals to DEFAULT_MA_BENH_VIEN
        defaultBenhVienShouldBeFound("maBenhVien.equals=" + DEFAULT_MA_BENH_VIEN);

        // Get all the benhVienList where maBenhVien equals to UPDATED_MA_BENH_VIEN
        defaultBenhVienShouldNotBeFound("maBenhVien.equals=" + UPDATED_MA_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBenhViensByMaBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where maBenhVien in DEFAULT_MA_BENH_VIEN or UPDATED_MA_BENH_VIEN
        defaultBenhVienShouldBeFound("maBenhVien.in=" + DEFAULT_MA_BENH_VIEN + "," + UPDATED_MA_BENH_VIEN);

        // Get all the benhVienList where maBenhVien equals to UPDATED_MA_BENH_VIEN
        defaultBenhVienShouldNotBeFound("maBenhVien.in=" + UPDATED_MA_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBenhViensByMaBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where maBenhVien is not null
        defaultBenhVienShouldBeFound("maBenhVien.specified=true");

        // Get all the benhVienList where maBenhVien is null
        defaultBenhVienShouldNotBeFound("maBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBenhViensByTenBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where tenBenhVien equals to DEFAULT_TEN_BENH_VIEN
        defaultBenhVienShouldBeFound("tenBenhVien.equals=" + DEFAULT_TEN_BENH_VIEN);

        // Get all the benhVienList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBenhVienShouldNotBeFound("tenBenhVien.equals=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBenhViensByTenBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where tenBenhVien in DEFAULT_TEN_BENH_VIEN or UPDATED_TEN_BENH_VIEN
        defaultBenhVienShouldBeFound("tenBenhVien.in=" + DEFAULT_TEN_BENH_VIEN + "," + UPDATED_TEN_BENH_VIEN);

        // Get all the benhVienList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBenhVienShouldNotBeFound("tenBenhVien.in=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBenhViensByTenBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where tenBenhVien is not null
        defaultBenhVienShouldBeFound("tenBenhVien.specified=true");

        // Get all the benhVienList where tenBenhVien is null
        defaultBenhVienShouldNotBeFound("tenBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBenhViensByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where ghiChu equals to DEFAULT_GHI_CHU
        defaultBenhVienShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the benhVienList where ghiChu equals to UPDATED_GHI_CHU
        defaultBenhVienShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllBenhViensByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultBenhVienShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the benhVienList where ghiChu equals to UPDATED_GHI_CHU
        defaultBenhVienShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllBenhViensByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where ghiChu is not null
        defaultBenhVienShouldBeFound("ghiChu.specified=true");

        // Get all the benhVienList where ghiChu is null
        defaultBenhVienShouldNotBeFound("ghiChu.specified=false");
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status equals to DEFAULT_STATUS
        defaultBenhVienShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the benhVienList where status equals to UPDATED_STATUS
        defaultBenhVienShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBenhVienShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the benhVienList where status equals to UPDATED_STATUS
        defaultBenhVienShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status is not null
        defaultBenhVienShouldBeFound("status.specified=true");

        // Get all the benhVienList where status is null
        defaultBenhVienShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status is greater than or equal to DEFAULT_STATUS
        defaultBenhVienShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the benhVienList where status is greater than or equal to UPDATED_STATUS
        defaultBenhVienShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status is less than or equal to DEFAULT_STATUS
        defaultBenhVienShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the benhVienList where status is less than or equal to SMALLER_STATUS
        defaultBenhVienShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status is less than DEFAULT_STATUS
        defaultBenhVienShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the benhVienList where status is less than UPDATED_STATUS
        defaultBenhVienShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBenhViensByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);

        // Get all the benhVienList where status is greater than DEFAULT_STATUS
        defaultBenhVienShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the benhVienList where status is greater than SMALLER_STATUS
        defaultBenhVienShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllBenhViensByBlcGiayChungSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);
        BlcGiayChungSinh blcGiayChungSinh = BlcGiayChungSinhResourceIT.createEntity(em);
        em.persist(blcGiayChungSinh);
        em.flush();
        benhVien.setBlcGiayChungSinh(blcGiayChungSinh);
        benhVienRepository.saveAndFlush(benhVien);
        Long blcGiayChungSinhId = blcGiayChungSinh.getId();

        // Get all the benhVienList where blcGiayChungSinh equals to blcGiayChungSinhId
        defaultBenhVienShouldBeFound("blcGiayChungSinhId.equals=" + blcGiayChungSinhId);

        // Get all the benhVienList where blcGiayChungSinh equals to blcGiayChungSinhId + 1
        defaultBenhVienShouldNotBeFound("blcGiayChungSinhId.equals=" + (blcGiayChungSinhId + 1));
    }


    @Test
    @Transactional
    public void getAllBenhViensByBlcGiayKhamSucKhoeIsEqualToSomething() throws Exception {
        // Initialize the database
        benhVienRepository.saveAndFlush(benhVien);
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe = BlcGiayKhamSucKhoeResourceIT.createEntity(em);
        em.persist(blcGiayKhamSucKhoe);
        em.flush();
        benhVien.setBlcGiayKhamSucKhoe(blcGiayKhamSucKhoe);
        benhVienRepository.saveAndFlush(benhVien);
        Long blcGiayKhamSucKhoeId = blcGiayKhamSucKhoe.getId();

        // Get all the benhVienList where blcGiayKhamSucKhoe equals to blcGiayKhamSucKhoeId
        defaultBenhVienShouldBeFound("blcGiayKhamSucKhoeId.equals=" + blcGiayKhamSucKhoeId);

        // Get all the benhVienList where blcGiayKhamSucKhoe equals to blcGiayKhamSucKhoeId + 1
        defaultBenhVienShouldNotBeFound("blcGiayKhamSucKhoeId.equals=" + (blcGiayKhamSucKhoeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBenhVienShouldBeFound(String filter) throws Exception {
        restBenhVienMockMvc.perform(get("/api/benh-viens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(benhVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBenhVien").value(hasItem(DEFAULT_MA_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restBenhVienMockMvc.perform(get("/api/benh-viens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBenhVienShouldNotBeFound(String filter) throws Exception {
        restBenhVienMockMvc.perform(get("/api/benh-viens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBenhVienMockMvc.perform(get("/api/benh-viens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBenhVien() throws Exception {
        // Get the benhVien
        restBenhVienMockMvc.perform(get("/api/benh-viens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBenhVien() throws Exception {
        // Initialize the database
        benhVienService.save(benhVien);

        int databaseSizeBeforeUpdate = benhVienRepository.findAll().size();

        // Update the benhVien
        BenhVien updatedBenhVien = benhVienRepository.findById(benhVien.getId()).get();
        // Disconnect from session so that the updates on updatedBenhVien are not directly saved in db
        em.detach(updatedBenhVien);
        updatedBenhVien
            .maBenhVien(UPDATED_MA_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .ghiChu(UPDATED_GHI_CHU)
            .status(UPDATED_STATUS);

        restBenhVienMockMvc.perform(put("/api/benh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBenhVien)))
            .andExpect(status().isOk());

        // Validate the BenhVien in the database
        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeUpdate);
        BenhVien testBenhVien = benhVienList.get(benhVienList.size() - 1);
        assertThat(testBenhVien.getMaBenhVien()).isEqualTo(UPDATED_MA_BENH_VIEN);
        assertThat(testBenhVien.getTenBenhVien()).isEqualTo(UPDATED_TEN_BENH_VIEN);
        assertThat(testBenhVien.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testBenhVien.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBenhVien() throws Exception {
        int databaseSizeBeforeUpdate = benhVienRepository.findAll().size();

        // Create the BenhVien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenhVienMockMvc.perform(put("/api/benh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benhVien)))
            .andExpect(status().isBadRequest());

        // Validate the BenhVien in the database
        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBenhVien() throws Exception {
        // Initialize the database
        benhVienService.save(benhVien);

        int databaseSizeBeforeDelete = benhVienRepository.findAll().size();

        // Delete the benhVien
        restBenhVienMockMvc.perform(delete("/api/benh-viens/{id}", benhVien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BenhVien> benhVienList = benhVienRepository.findAll();
        assertThat(benhVienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenhVien.class);
        BenhVien benhVien1 = new BenhVien();
        benhVien1.setId(1L);
        BenhVien benhVien2 = new BenhVien();
        benhVien2.setId(benhVien1.getId());
        assertThat(benhVien1).isEqualTo(benhVien2);
        benhVien2.setId(2L);
        assertThat(benhVien1).isNotEqualTo(benhVien2);
        benhVien1.setId(null);
        assertThat(benhVien1).isNotEqualTo(benhVien2);
    }
}
