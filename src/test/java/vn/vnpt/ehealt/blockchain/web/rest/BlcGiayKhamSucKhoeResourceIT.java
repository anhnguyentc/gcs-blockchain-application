package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.repository.BlcGiayKhamSucKhoeRepository;
import vn.vnpt.ehealt.blockchain.service.BlcGiayKhamSucKhoeService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayKhamSucKhoeCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcGiayKhamSucKhoeQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static vn.vnpt.ehealt.blockchain.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BlcGiayKhamSucKhoeResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class BlcGiayKhamSucKhoeResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_SO_GIAY_KSK = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_KSK = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final Integer DEFAULT_TUOI = 1;
    private static final Integer UPDATED_TUOI = 2;
    private static final Integer SMALLER_TUOI = 1 - 1;

    private static final String DEFAULT_SO_CMND = "AAAAAAAAAA";
    private static final String UPDATED_SO_CMND = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CAP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CAP = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CAP = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_NOI_CAP = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_O = "AAAAAAAAAA";
    private static final String UPDATED_NOI_O = "BBBBBBBBBB";

    private static final String DEFAULT_ID_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_KHAM = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_KHAM = "BBBBBBBBBB";

    private static final String DEFAULT_HANG_BANG_LAI = "AAAAAAAAAA";
    private static final String UPDATED_HANG_BANG_LAI = "BBBBBBBBBB";

    private static final String DEFAULT_KET_LUAN = "AAAAAAAAAA";
    private static final String UPDATED_KET_LUAN = "BBBBBBBBBB";

    private static final String DEFAULT_BAC_SY_KET_LUAN = "AAAAAAAAAA";
    private static final String UPDATED_BAC_SY_KET_LUAN = "BBBBBBBBBB";

    private static final String DEFAULT_PDF_GIAY_KSK = "AAAAAAAAAA";
    private static final String UPDATED_PDF_GIAY_KSK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private BlcGiayKhamSucKhoeRepository blcGiayKhamSucKhoeRepository;

    @Autowired
    private BlcGiayKhamSucKhoeService blcGiayKhamSucKhoeService;

    @Autowired
    private BlcGiayKhamSucKhoeQueryService blcGiayKhamSucKhoeQueryService;

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

    private MockMvc restBlcGiayKhamSucKhoeMockMvc;

    private BlcGiayKhamSucKhoe blcGiayKhamSucKhoe;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlcGiayKhamSucKhoeResource blcGiayKhamSucKhoeResource = new BlcGiayKhamSucKhoeResource(blcGiayKhamSucKhoeService, blcGiayKhamSucKhoeQueryService);
        this.restBlcGiayKhamSucKhoeMockMvc = MockMvcBuilders.standaloneSetup(blcGiayKhamSucKhoeResource)
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
    public static BlcGiayKhamSucKhoe createEntity(EntityManager em) {
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe = new BlcGiayKhamSucKhoe()
            .uuid(DEFAULT_UUID)
            .soGiayKSK(DEFAULT_SO_GIAY_KSK)
            .hoTen(DEFAULT_HO_TEN)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .tuoi(DEFAULT_TUOI)
            .soCMND(DEFAULT_SO_CMND)
            .ngayCap(DEFAULT_NGAY_CAP)
            .noiCap(DEFAULT_NOI_CAP)
            .noiO(DEFAULT_NOI_O)
            .idBenhVien(DEFAULT_ID_BENH_VIEN)
            .tenBenhVien(DEFAULT_TEN_BENH_VIEN)
            .ngayKham(DEFAULT_NGAY_KHAM)
            .hangBangLai(DEFAULT_HANG_BANG_LAI)
            .ketLuan(DEFAULT_KET_LUAN)
            .bacSyKetLuan(DEFAULT_BAC_SY_KET_LUAN)
            .pdfGiayKSK(DEFAULT_PDF_GIAY_KSK)
            .status(DEFAULT_STATUS);
        return blcGiayKhamSucKhoe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlcGiayKhamSucKhoe createUpdatedEntity(EntityManager em) {
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe = new BlcGiayKhamSucKhoe()
            .uuid(UPDATED_UUID)
            .soGiayKSK(UPDATED_SO_GIAY_KSK)
            .hoTen(UPDATED_HO_TEN)
            .gioiTinh(UPDATED_GIOI_TINH)
            .tuoi(UPDATED_TUOI)
            .soCMND(UPDATED_SO_CMND)
            .ngayCap(UPDATED_NGAY_CAP)
            .noiCap(UPDATED_NOI_CAP)
            .noiO(UPDATED_NOI_O)
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .ngayKham(UPDATED_NGAY_KHAM)
            .hangBangLai(UPDATED_HANG_BANG_LAI)
            .ketLuan(UPDATED_KET_LUAN)
            .bacSyKetLuan(UPDATED_BAC_SY_KET_LUAN)
            .pdfGiayKSK(UPDATED_PDF_GIAY_KSK)
            .status(UPDATED_STATUS);
        return blcGiayKhamSucKhoe;
    }

    @BeforeEach
    public void initTest() {
        blcGiayKhamSucKhoe = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlcGiayKhamSucKhoe() throws Exception {
        int databaseSizeBeforeCreate = blcGiayKhamSucKhoeRepository.findAll().size();

        // Create the BlcGiayKhamSucKhoe
        restBlcGiayKhamSucKhoeMockMvc.perform(post("/api/blc-giay-kham-suc-khoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayKhamSucKhoe)))
            .andExpect(status().isCreated());

        // Validate the BlcGiayKhamSucKhoe in the database
        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeCreate + 1);
        BlcGiayKhamSucKhoe testBlcGiayKhamSucKhoe = blcGiayKhamSucKhoeList.get(blcGiayKhamSucKhoeList.size() - 1);
        assertThat(testBlcGiayKhamSucKhoe.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testBlcGiayKhamSucKhoe.getSoGiayKSK()).isEqualTo(DEFAULT_SO_GIAY_KSK);
        assertThat(testBlcGiayKhamSucKhoe.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testBlcGiayKhamSucKhoe.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testBlcGiayKhamSucKhoe.getTuoi()).isEqualTo(DEFAULT_TUOI);
        assertThat(testBlcGiayKhamSucKhoe.getSoCMND()).isEqualTo(DEFAULT_SO_CMND);
        assertThat(testBlcGiayKhamSucKhoe.getNgayCap()).isEqualTo(DEFAULT_NGAY_CAP);
        assertThat(testBlcGiayKhamSucKhoe.getNoiCap()).isEqualTo(DEFAULT_NOI_CAP);
        assertThat(testBlcGiayKhamSucKhoe.getNoiO()).isEqualTo(DEFAULT_NOI_O);
        assertThat(testBlcGiayKhamSucKhoe.getIdBenhVien()).isEqualTo(DEFAULT_ID_BENH_VIEN);
        assertThat(testBlcGiayKhamSucKhoe.getTenBenhVien()).isEqualTo(DEFAULT_TEN_BENH_VIEN);
        assertThat(testBlcGiayKhamSucKhoe.getNgayKham()).isEqualTo(DEFAULT_NGAY_KHAM);
        assertThat(testBlcGiayKhamSucKhoe.getHangBangLai()).isEqualTo(DEFAULT_HANG_BANG_LAI);
        assertThat(testBlcGiayKhamSucKhoe.getKetLuan()).isEqualTo(DEFAULT_KET_LUAN);
        assertThat(testBlcGiayKhamSucKhoe.getBacSyKetLuan()).isEqualTo(DEFAULT_BAC_SY_KET_LUAN);
        assertThat(testBlcGiayKhamSucKhoe.getPdfGiayKSK()).isEqualTo(DEFAULT_PDF_GIAY_KSK);
        assertThat(testBlcGiayKhamSucKhoe.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBlcGiayKhamSucKhoeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blcGiayKhamSucKhoeRepository.findAll().size();

        // Create the BlcGiayKhamSucKhoe with an existing ID
        blcGiayKhamSucKhoe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlcGiayKhamSucKhoeMockMvc.perform(post("/api/blc-giay-kham-suc-khoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayKhamSucKhoe)))
            .andExpect(status().isBadRequest());

        // Validate the BlcGiayKhamSucKhoe in the database
        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = blcGiayKhamSucKhoeRepository.findAll().size();
        // set the field null
        blcGiayKhamSucKhoe.setUuid(null);

        // Create the BlcGiayKhamSucKhoe, which fails.

        restBlcGiayKhamSucKhoeMockMvc.perform(post("/api/blc-giay-kham-suc-khoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayKhamSucKhoe)))
            .andExpect(status().isBadRequest());

        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoes() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcGiayKhamSucKhoe.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].soGiayKSK").value(hasItem(DEFAULT_SO_GIAY_KSK.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].tuoi").value(hasItem(DEFAULT_TUOI)))
            .andExpect(jsonPath("$.[*].soCMND").value(hasItem(DEFAULT_SO_CMND.toString())))
            .andExpect(jsonPath("$.[*].ngayCap").value(hasItem(DEFAULT_NGAY_CAP.toString())))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP.toString())))
            .andExpect(jsonPath("$.[*].noiO").value(hasItem(DEFAULT_NOI_O.toString())))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].ngayKham").value(hasItem(DEFAULT_NGAY_KHAM.toString())))
            .andExpect(jsonPath("$.[*].hangBangLai").value(hasItem(DEFAULT_HANG_BANG_LAI.toString())))
            .andExpect(jsonPath("$.[*].ketLuan").value(hasItem(DEFAULT_KET_LUAN.toString())))
            .andExpect(jsonPath("$.[*].bacSyKetLuan").value(hasItem(DEFAULT_BAC_SY_KET_LUAN.toString())))
            .andExpect(jsonPath("$.[*].pdfGiayKSK").value(hasItem(DEFAULT_PDF_GIAY_KSK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getBlcGiayKhamSucKhoe() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get the blcGiayKhamSucKhoe
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes/{id}", blcGiayKhamSucKhoe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blcGiayKhamSucKhoe.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.soGiayKSK").value(DEFAULT_SO_GIAY_KSK.toString()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.tuoi").value(DEFAULT_TUOI))
            .andExpect(jsonPath("$.soCMND").value(DEFAULT_SO_CMND.toString()))
            .andExpect(jsonPath("$.ngayCap").value(DEFAULT_NGAY_CAP.toString()))
            .andExpect(jsonPath("$.noiCap").value(DEFAULT_NOI_CAP.toString()))
            .andExpect(jsonPath("$.noiO").value(DEFAULT_NOI_O.toString()))
            .andExpect(jsonPath("$.idBenhVien").value(DEFAULT_ID_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.tenBenhVien").value(DEFAULT_TEN_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.ngayKham").value(DEFAULT_NGAY_KHAM.toString()))
            .andExpect(jsonPath("$.hangBangLai").value(DEFAULT_HANG_BANG_LAI.toString()))
            .andExpect(jsonPath("$.ketLuan").value(DEFAULT_KET_LUAN.toString()))
            .andExpect(jsonPath("$.bacSyKetLuan").value(DEFAULT_BAC_SY_KET_LUAN.toString()))
            .andExpect(jsonPath("$.pdfGiayKSK").value(DEFAULT_PDF_GIAY_KSK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where uuid equals to DEFAULT_UUID
        defaultBlcGiayKhamSucKhoeShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the blcGiayKhamSucKhoeList where uuid equals to UPDATED_UUID
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultBlcGiayKhamSucKhoeShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the blcGiayKhamSucKhoeList where uuid equals to UPDATED_UUID
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where uuid is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("uuid.specified=true");

        // Get all the blcGiayKhamSucKhoeList where uuid is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoGiayKSKIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK equals to DEFAULT_SO_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldBeFound("soGiayKSK.equals=" + DEFAULT_SO_GIAY_KSK);

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK equals to UPDATED_SO_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soGiayKSK.equals=" + UPDATED_SO_GIAY_KSK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoGiayKSKIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK in DEFAULT_SO_GIAY_KSK or UPDATED_SO_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldBeFound("soGiayKSK.in=" + DEFAULT_SO_GIAY_KSK + "," + UPDATED_SO_GIAY_KSK);

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK equals to UPDATED_SO_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soGiayKSK.in=" + UPDATED_SO_GIAY_KSK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoGiayKSKIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("soGiayKSK.specified=true");

        // Get all the blcGiayKhamSucKhoeList where soGiayKSK is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soGiayKSK.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHoTenIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hoTen equals to DEFAULT_HO_TEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("hoTen.equals=" + DEFAULT_HO_TEN);

        // Get all the blcGiayKhamSucKhoeList where hoTen equals to UPDATED_HO_TEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hoTen.equals=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHoTenIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hoTen in DEFAULT_HO_TEN or UPDATED_HO_TEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("hoTen.in=" + DEFAULT_HO_TEN + "," + UPDATED_HO_TEN);

        // Get all the blcGiayKhamSucKhoeList where hoTen equals to UPDATED_HO_TEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hoTen.in=" + UPDATED_HO_TEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHoTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hoTen is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("hoTen.specified=true");

        // Get all the blcGiayKhamSucKhoeList where hoTen is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hoTen.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByGioiTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where gioiTinh equals to DEFAULT_GIOI_TINH
        defaultBlcGiayKhamSucKhoeShouldBeFound("gioiTinh.equals=" + DEFAULT_GIOI_TINH);

        // Get all the blcGiayKhamSucKhoeList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("gioiTinh.equals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByGioiTinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where gioiTinh in DEFAULT_GIOI_TINH or UPDATED_GIOI_TINH
        defaultBlcGiayKhamSucKhoeShouldBeFound("gioiTinh.in=" + DEFAULT_GIOI_TINH + "," + UPDATED_GIOI_TINH);

        // Get all the blcGiayKhamSucKhoeList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("gioiTinh.in=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByGioiTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where gioiTinh is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("gioiTinh.specified=true");

        // Get all the blcGiayKhamSucKhoeList where gioiTinh is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("gioiTinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi equals to DEFAULT_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.equals=" + DEFAULT_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi equals to UPDATED_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.equals=" + UPDATED_TUOI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi in DEFAULT_TUOI or UPDATED_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.in=" + DEFAULT_TUOI + "," + UPDATED_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi equals to UPDATED_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.in=" + UPDATED_TUOI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.specified=true");

        // Get all the blcGiayKhamSucKhoeList where tuoi is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi is greater than or equal to DEFAULT_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.greaterThanOrEqual=" + DEFAULT_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi is greater than or equal to UPDATED_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.greaterThanOrEqual=" + UPDATED_TUOI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi is less than or equal to DEFAULT_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.lessThanOrEqual=" + DEFAULT_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi is less than or equal to SMALLER_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.lessThanOrEqual=" + SMALLER_TUOI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsLessThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi is less than DEFAULT_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.lessThan=" + DEFAULT_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi is less than UPDATED_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.lessThan=" + UPDATED_TUOI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTuoiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tuoi is greater than DEFAULT_TUOI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tuoi.greaterThan=" + DEFAULT_TUOI);

        // Get all the blcGiayKhamSucKhoeList where tuoi is greater than SMALLER_TUOI
        defaultBlcGiayKhamSucKhoeShouldBeFound("tuoi.greaterThan=" + SMALLER_TUOI);
    }


    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoCMNDIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soCMND equals to DEFAULT_SO_CMND
        defaultBlcGiayKhamSucKhoeShouldBeFound("soCMND.equals=" + DEFAULT_SO_CMND);

        // Get all the blcGiayKhamSucKhoeList where soCMND equals to UPDATED_SO_CMND
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soCMND.equals=" + UPDATED_SO_CMND);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoCMNDIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soCMND in DEFAULT_SO_CMND or UPDATED_SO_CMND
        defaultBlcGiayKhamSucKhoeShouldBeFound("soCMND.in=" + DEFAULT_SO_CMND + "," + UPDATED_SO_CMND);

        // Get all the blcGiayKhamSucKhoeList where soCMND equals to UPDATED_SO_CMND
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soCMND.in=" + UPDATED_SO_CMND);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesBySoCMNDIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where soCMND is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("soCMND.specified=true");

        // Get all the blcGiayKhamSucKhoeList where soCMND is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("soCMND.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap equals to DEFAULT_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.equals=" + DEFAULT_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap equals to UPDATED_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.equals=" + UPDATED_NGAY_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap in DEFAULT_NGAY_CAP or UPDATED_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.in=" + DEFAULT_NGAY_CAP + "," + UPDATED_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap equals to UPDATED_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.in=" + UPDATED_NGAY_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.specified=true");

        // Get all the blcGiayKhamSucKhoeList where ngayCap is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is greater than or equal to DEFAULT_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.greaterThanOrEqual=" + DEFAULT_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is greater than or equal to UPDATED_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.greaterThanOrEqual=" + UPDATED_NGAY_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is less than or equal to DEFAULT_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.lessThanOrEqual=" + DEFAULT_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is less than or equal to SMALLER_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.lessThanOrEqual=" + SMALLER_NGAY_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsLessThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is less than DEFAULT_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.lessThan=" + DEFAULT_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is less than UPDATED_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.lessThan=" + UPDATED_NGAY_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayCapIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is greater than DEFAULT_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayCap.greaterThan=" + DEFAULT_NGAY_CAP);

        // Get all the blcGiayKhamSucKhoeList where ngayCap is greater than SMALLER_NGAY_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayCap.greaterThan=" + SMALLER_NGAY_CAP);
    }


    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiCapIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiCap equals to DEFAULT_NOI_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiCap.equals=" + DEFAULT_NOI_CAP);

        // Get all the blcGiayKhamSucKhoeList where noiCap equals to UPDATED_NOI_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiCap.equals=" + UPDATED_NOI_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiCapIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiCap in DEFAULT_NOI_CAP or UPDATED_NOI_CAP
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiCap.in=" + DEFAULT_NOI_CAP + "," + UPDATED_NOI_CAP);

        // Get all the blcGiayKhamSucKhoeList where noiCap equals to UPDATED_NOI_CAP
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiCap.in=" + UPDATED_NOI_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiCapIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiCap is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiCap.specified=true");

        // Get all the blcGiayKhamSucKhoeList where noiCap is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiCap.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiOIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiO equals to DEFAULT_NOI_O
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiO.equals=" + DEFAULT_NOI_O);

        // Get all the blcGiayKhamSucKhoeList where noiO equals to UPDATED_NOI_O
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiO.equals=" + UPDATED_NOI_O);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiOIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiO in DEFAULT_NOI_O or UPDATED_NOI_O
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiO.in=" + DEFAULT_NOI_O + "," + UPDATED_NOI_O);

        // Get all the blcGiayKhamSucKhoeList where noiO equals to UPDATED_NOI_O
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiO.in=" + UPDATED_NOI_O);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNoiOIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where noiO is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("noiO.specified=true");

        // Get all the blcGiayKhamSucKhoeList where noiO is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("noiO.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByIdBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where idBenhVien equals to DEFAULT_ID_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("idBenhVien.equals=" + DEFAULT_ID_BENH_VIEN);

        // Get all the blcGiayKhamSucKhoeList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("idBenhVien.equals=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByIdBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where idBenhVien in DEFAULT_ID_BENH_VIEN or UPDATED_ID_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("idBenhVien.in=" + DEFAULT_ID_BENH_VIEN + "," + UPDATED_ID_BENH_VIEN);

        // Get all the blcGiayKhamSucKhoeList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("idBenhVien.in=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByIdBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where idBenhVien is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("idBenhVien.specified=true");

        // Get all the blcGiayKhamSucKhoeList where idBenhVien is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("idBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTenBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien equals to DEFAULT_TEN_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("tenBenhVien.equals=" + DEFAULT_TEN_BENH_VIEN);

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tenBenhVien.equals=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTenBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien in DEFAULT_TEN_BENH_VIEN or UPDATED_TEN_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldBeFound("tenBenhVien.in=" + DEFAULT_TEN_BENH_VIEN + "," + UPDATED_TEN_BENH_VIEN);

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tenBenhVien.in=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByTenBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("tenBenhVien.specified=true");

        // Get all the blcGiayKhamSucKhoeList where tenBenhVien is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("tenBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayKhamIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayKham equals to DEFAULT_NGAY_KHAM
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayKham.equals=" + DEFAULT_NGAY_KHAM);

        // Get all the blcGiayKhamSucKhoeList where ngayKham equals to UPDATED_NGAY_KHAM
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayKham.equals=" + UPDATED_NGAY_KHAM);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayKhamIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayKham in DEFAULT_NGAY_KHAM or UPDATED_NGAY_KHAM
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayKham.in=" + DEFAULT_NGAY_KHAM + "," + UPDATED_NGAY_KHAM);

        // Get all the blcGiayKhamSucKhoeList where ngayKham equals to UPDATED_NGAY_KHAM
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayKham.in=" + UPDATED_NGAY_KHAM);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByNgayKhamIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ngayKham is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("ngayKham.specified=true");

        // Get all the blcGiayKhamSucKhoeList where ngayKham is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ngayKham.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHangBangLaiIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hangBangLai equals to DEFAULT_HANG_BANG_LAI
        defaultBlcGiayKhamSucKhoeShouldBeFound("hangBangLai.equals=" + DEFAULT_HANG_BANG_LAI);

        // Get all the blcGiayKhamSucKhoeList where hangBangLai equals to UPDATED_HANG_BANG_LAI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hangBangLai.equals=" + UPDATED_HANG_BANG_LAI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHangBangLaiIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hangBangLai in DEFAULT_HANG_BANG_LAI or UPDATED_HANG_BANG_LAI
        defaultBlcGiayKhamSucKhoeShouldBeFound("hangBangLai.in=" + DEFAULT_HANG_BANG_LAI + "," + UPDATED_HANG_BANG_LAI);

        // Get all the blcGiayKhamSucKhoeList where hangBangLai equals to UPDATED_HANG_BANG_LAI
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hangBangLai.in=" + UPDATED_HANG_BANG_LAI);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByHangBangLaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where hangBangLai is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("hangBangLai.specified=true");

        // Get all the blcGiayKhamSucKhoeList where hangBangLai is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("hangBangLai.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByKetLuanIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ketLuan equals to DEFAULT_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldBeFound("ketLuan.equals=" + DEFAULT_KET_LUAN);

        // Get all the blcGiayKhamSucKhoeList where ketLuan equals to UPDATED_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ketLuan.equals=" + UPDATED_KET_LUAN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByKetLuanIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ketLuan in DEFAULT_KET_LUAN or UPDATED_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldBeFound("ketLuan.in=" + DEFAULT_KET_LUAN + "," + UPDATED_KET_LUAN);

        // Get all the blcGiayKhamSucKhoeList where ketLuan equals to UPDATED_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ketLuan.in=" + UPDATED_KET_LUAN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByKetLuanIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where ketLuan is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("ketLuan.specified=true");

        // Get all the blcGiayKhamSucKhoeList where ketLuan is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("ketLuan.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByBacSyKetLuanIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan equals to DEFAULT_BAC_SY_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldBeFound("bacSyKetLuan.equals=" + DEFAULT_BAC_SY_KET_LUAN);

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan equals to UPDATED_BAC_SY_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("bacSyKetLuan.equals=" + UPDATED_BAC_SY_KET_LUAN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByBacSyKetLuanIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan in DEFAULT_BAC_SY_KET_LUAN or UPDATED_BAC_SY_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldBeFound("bacSyKetLuan.in=" + DEFAULT_BAC_SY_KET_LUAN + "," + UPDATED_BAC_SY_KET_LUAN);

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan equals to UPDATED_BAC_SY_KET_LUAN
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("bacSyKetLuan.in=" + UPDATED_BAC_SY_KET_LUAN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByBacSyKetLuanIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("bacSyKetLuan.specified=true");

        // Get all the blcGiayKhamSucKhoeList where bacSyKetLuan is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("bacSyKetLuan.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByPdfGiayKSKIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK equals to DEFAULT_PDF_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldBeFound("pdfGiayKSK.equals=" + DEFAULT_PDF_GIAY_KSK);

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK equals to UPDATED_PDF_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("pdfGiayKSK.equals=" + UPDATED_PDF_GIAY_KSK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByPdfGiayKSKIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK in DEFAULT_PDF_GIAY_KSK or UPDATED_PDF_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldBeFound("pdfGiayKSK.in=" + DEFAULT_PDF_GIAY_KSK + "," + UPDATED_PDF_GIAY_KSK);

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK equals to UPDATED_PDF_GIAY_KSK
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("pdfGiayKSK.in=" + UPDATED_PDF_GIAY_KSK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByPdfGiayKSKIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("pdfGiayKSK.specified=true");

        // Get all the blcGiayKhamSucKhoeList where pdfGiayKSK is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("pdfGiayKSK.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status equals to DEFAULT_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status equals to UPDATED_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status equals to UPDATED_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status is not null
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.specified=true");

        // Get all the blcGiayKhamSucKhoeList where status is null
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status is greater than or equal to DEFAULT_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status is greater than or equal to UPDATED_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status is less than or equal to DEFAULT_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status is less than or equal to SMALLER_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status is less than DEFAULT_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status is less than UPDATED_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);

        // Get all the blcGiayKhamSucKhoeList where status is greater than DEFAULT_STATUS
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the blcGiayKhamSucKhoeList where status is greater than SMALLER_STATUS
        defaultBlcGiayKhamSucKhoeShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByDonViHanhChinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);
        DonViHanhChinh donViHanhChinh = DonViHanhChinhResourceIT.createEntity(em);
        em.persist(donViHanhChinh);
        em.flush();
        blcGiayKhamSucKhoe.setDonViHanhChinh(donViHanhChinh);
        donViHanhChinh.setBlcGiayKhamSucKhoe(blcGiayKhamSucKhoe);
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);
        Long donViHanhChinhId = donViHanhChinh.getId();

        // Get all the blcGiayKhamSucKhoeList where donViHanhChinh equals to donViHanhChinhId
        defaultBlcGiayKhamSucKhoeShouldBeFound("donViHanhChinhId.equals=" + donViHanhChinhId);

        // Get all the blcGiayKhamSucKhoeList where donViHanhChinh equals to donViHanhChinhId + 1
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("donViHanhChinhId.equals=" + (donViHanhChinhId + 1));
    }


    @Test
    @Transactional
    public void getAllBlcGiayKhamSucKhoesByBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);
        BenhVien benhVien = BenhVienResourceIT.createEntity(em);
        em.persist(benhVien);
        em.flush();
        blcGiayKhamSucKhoe.setBenhVien(benhVien);
        benhVien.setBlcGiayKhamSucKhoe(blcGiayKhamSucKhoe);
        blcGiayKhamSucKhoeRepository.saveAndFlush(blcGiayKhamSucKhoe);
        Long benhVienId = benhVien.getId();

        // Get all the blcGiayKhamSucKhoeList where benhVien equals to benhVienId
        defaultBlcGiayKhamSucKhoeShouldBeFound("benhVienId.equals=" + benhVienId);

        // Get all the blcGiayKhamSucKhoeList where benhVien equals to benhVienId + 1
        defaultBlcGiayKhamSucKhoeShouldNotBeFound("benhVienId.equals=" + (benhVienId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBlcGiayKhamSucKhoeShouldBeFound(String filter) throws Exception {
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcGiayKhamSucKhoe.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].soGiayKSK").value(hasItem(DEFAULT_SO_GIAY_KSK)))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].tuoi").value(hasItem(DEFAULT_TUOI)))
            .andExpect(jsonPath("$.[*].soCMND").value(hasItem(DEFAULT_SO_CMND)))
            .andExpect(jsonPath("$.[*].ngayCap").value(hasItem(DEFAULT_NGAY_CAP.toString())))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP)))
            .andExpect(jsonPath("$.[*].noiO").value(hasItem(DEFAULT_NOI_O)))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].ngayKham").value(hasItem(DEFAULT_NGAY_KHAM)))
            .andExpect(jsonPath("$.[*].hangBangLai").value(hasItem(DEFAULT_HANG_BANG_LAI)))
            .andExpect(jsonPath("$.[*].ketLuan").value(hasItem(DEFAULT_KET_LUAN)))
            .andExpect(jsonPath("$.[*].bacSyKetLuan").value(hasItem(DEFAULT_BAC_SY_KET_LUAN)))
            .andExpect(jsonPath("$.[*].pdfGiayKSK").value(hasItem(DEFAULT_PDF_GIAY_KSK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBlcGiayKhamSucKhoeShouldNotBeFound(String filter) throws Exception {
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlcGiayKhamSucKhoe() throws Exception {
        // Get the blcGiayKhamSucKhoe
        restBlcGiayKhamSucKhoeMockMvc.perform(get("/api/blc-giay-kham-suc-khoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlcGiayKhamSucKhoe() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeService.save(blcGiayKhamSucKhoe);

        int databaseSizeBeforeUpdate = blcGiayKhamSucKhoeRepository.findAll().size();

        // Update the blcGiayKhamSucKhoe
        BlcGiayKhamSucKhoe updatedBlcGiayKhamSucKhoe = blcGiayKhamSucKhoeRepository.findById(blcGiayKhamSucKhoe.getId()).get();
        // Disconnect from session so that the updates on updatedBlcGiayKhamSucKhoe are not directly saved in db
        em.detach(updatedBlcGiayKhamSucKhoe);
        updatedBlcGiayKhamSucKhoe
            .uuid(UPDATED_UUID)
            .soGiayKSK(UPDATED_SO_GIAY_KSK)
            .hoTen(UPDATED_HO_TEN)
            .gioiTinh(UPDATED_GIOI_TINH)
            .tuoi(UPDATED_TUOI)
            .soCMND(UPDATED_SO_CMND)
            .ngayCap(UPDATED_NGAY_CAP)
            .noiCap(UPDATED_NOI_CAP)
            .noiO(UPDATED_NOI_O)
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .ngayKham(UPDATED_NGAY_KHAM)
            .hangBangLai(UPDATED_HANG_BANG_LAI)
            .ketLuan(UPDATED_KET_LUAN)
            .bacSyKetLuan(UPDATED_BAC_SY_KET_LUAN)
            .pdfGiayKSK(UPDATED_PDF_GIAY_KSK)
            .status(UPDATED_STATUS);

        restBlcGiayKhamSucKhoeMockMvc.perform(put("/api/blc-giay-kham-suc-khoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlcGiayKhamSucKhoe)))
            .andExpect(status().isOk());

        // Validate the BlcGiayKhamSucKhoe in the database
        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeUpdate);
        BlcGiayKhamSucKhoe testBlcGiayKhamSucKhoe = blcGiayKhamSucKhoeList.get(blcGiayKhamSucKhoeList.size() - 1);
        assertThat(testBlcGiayKhamSucKhoe.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testBlcGiayKhamSucKhoe.getSoGiayKSK()).isEqualTo(UPDATED_SO_GIAY_KSK);
        assertThat(testBlcGiayKhamSucKhoe.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testBlcGiayKhamSucKhoe.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testBlcGiayKhamSucKhoe.getTuoi()).isEqualTo(UPDATED_TUOI);
        assertThat(testBlcGiayKhamSucKhoe.getSoCMND()).isEqualTo(UPDATED_SO_CMND);
        assertThat(testBlcGiayKhamSucKhoe.getNgayCap()).isEqualTo(UPDATED_NGAY_CAP);
        assertThat(testBlcGiayKhamSucKhoe.getNoiCap()).isEqualTo(UPDATED_NOI_CAP);
        assertThat(testBlcGiayKhamSucKhoe.getNoiO()).isEqualTo(UPDATED_NOI_O);
        assertThat(testBlcGiayKhamSucKhoe.getIdBenhVien()).isEqualTo(UPDATED_ID_BENH_VIEN);
        assertThat(testBlcGiayKhamSucKhoe.getTenBenhVien()).isEqualTo(UPDATED_TEN_BENH_VIEN);
        assertThat(testBlcGiayKhamSucKhoe.getNgayKham()).isEqualTo(UPDATED_NGAY_KHAM);
        assertThat(testBlcGiayKhamSucKhoe.getHangBangLai()).isEqualTo(UPDATED_HANG_BANG_LAI);
        assertThat(testBlcGiayKhamSucKhoe.getKetLuan()).isEqualTo(UPDATED_KET_LUAN);
        assertThat(testBlcGiayKhamSucKhoe.getBacSyKetLuan()).isEqualTo(UPDATED_BAC_SY_KET_LUAN);
        assertThat(testBlcGiayKhamSucKhoe.getPdfGiayKSK()).isEqualTo(UPDATED_PDF_GIAY_KSK);
        assertThat(testBlcGiayKhamSucKhoe.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBlcGiayKhamSucKhoe() throws Exception {
        int databaseSizeBeforeUpdate = blcGiayKhamSucKhoeRepository.findAll().size();

        // Create the BlcGiayKhamSucKhoe

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlcGiayKhamSucKhoeMockMvc.perform(put("/api/blc-giay-kham-suc-khoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayKhamSucKhoe)))
            .andExpect(status().isBadRequest());

        // Validate the BlcGiayKhamSucKhoe in the database
        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlcGiayKhamSucKhoe() throws Exception {
        // Initialize the database
        blcGiayKhamSucKhoeService.save(blcGiayKhamSucKhoe);

        int databaseSizeBeforeDelete = blcGiayKhamSucKhoeRepository.findAll().size();

        // Delete the blcGiayKhamSucKhoe
        restBlcGiayKhamSucKhoeMockMvc.perform(delete("/api/blc-giay-kham-suc-khoes/{id}", blcGiayKhamSucKhoe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoeList = blcGiayKhamSucKhoeRepository.findAll();
        assertThat(blcGiayKhamSucKhoeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlcGiayKhamSucKhoe.class);
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe1 = new BlcGiayKhamSucKhoe();
        blcGiayKhamSucKhoe1.setId(1L);
        BlcGiayKhamSucKhoe blcGiayKhamSucKhoe2 = new BlcGiayKhamSucKhoe();
        blcGiayKhamSucKhoe2.setId(blcGiayKhamSucKhoe1.getId());
        assertThat(blcGiayKhamSucKhoe1).isEqualTo(blcGiayKhamSucKhoe2);
        blcGiayKhamSucKhoe2.setId(2L);
        assertThat(blcGiayKhamSucKhoe1).isNotEqualTo(blcGiayKhamSucKhoe2);
        blcGiayKhamSucKhoe1.setId(null);
        assertThat(blcGiayKhamSucKhoe1).isNotEqualTo(blcGiayKhamSucKhoe2);
    }
}
