package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import vn.vnpt.ehealt.blockchain.domain.DanToc;
import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.repository.BlcGiayChungSinhRepository;
import vn.vnpt.ehealt.blockchain.service.BlcGiayChungSinhService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayChungSinhCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcGiayChungSinhQueryService;

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
 * Integration tests for the {@link BlcGiayChungSinhResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class BlcGiayChungSinhResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_ME_NGUOI_NUOI_DUONG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_ME_NGUOI_NUOI_DUONG = "BBBBBBBBBB";

    private static final String DEFAULT_NAM_SINH = "AAAAAAAAAA";
    private static final String UPDATED_NAM_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI_THUONG_TRU = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI_THUONG_TRU = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CMND_PASSPORT = "AAAAAAAAAA";
    private static final String UPDATED_SO_CMND_PASSPORT = "BBBBBBBBBB";

    private static final String DEFAULT_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_GIO_SINH = "AAAAAAAAAA";
    private static final String UPDATED_GIO_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_THANG_NAM = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_THANG_NAM = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_SINH = "AAAAAAAAAA";
    private static final String UPDATED_NOI_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_SO_LAN_SINH = "AAAAAAAAAA";
    private static final String UPDATED_SO_LAN_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CON_HIEN_SONG = "AAAAAAAAAA";
    private static final String UPDATED_SO_CON_HIEN_SONG = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CON_TRONG_LAN_SINH = "AAAAAAAAAA";
    private static final String UPDATED_SO_CON_TRONG_LAN_SINH = "BBBBBBBBBB";

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_CAN_NANG = "AAAAAAAAAA";
    private static final String UPDATED_CAN_NANG = "BBBBBBBBBB";

    private static final String DEFAULT_HIEN_TRANG = "AAAAAAAAAA";
    private static final String UPDATED_HIEN_TRANG = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_DO_DE = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_DO_DE = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DU_DINH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DU_DINH = "BBBBBBBBBB";

    private static final String DEFAULT_QR_CODE_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_QR_CODE_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_APP = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_APP = "BBBBBBBBBB";

    private static final String DEFAULT_USER_APPROVE = "AAAAAAAAAA";
    private static final String UPDATED_USER_APPROVE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_CREATE = "AAAAAAAAAA";
    private static final String UPDATED_USER_CREATE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_GCS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_GCS = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_GCS = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_GCS = "BBBBBBBBBB";

    private static final String DEFAULT_SO = "AAAAAAAAAA";
    private static final String UPDATED_SO = "BBBBBBBBBB";

    private static final String DEFAULT_QUYEN_SO = "AAAAAAAAAA";
    private static final String UPDATED_QUYEN_SO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_NDK = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_NDK = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_ID_USER_CREATE = "AAAAAAAAAA";
    private static final String UPDATED_ID_USER_CREATE = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLIC_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PUBLIC_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_SOFT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_SOFT = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_THANG_NAM_CAP = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_THANG_NAM_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_CAP = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN_CHA = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN_CHA = "BBBBBBBBBB";

    private static final String DEFAULT_PDF_FILE = "AAAAAAAAAA";
    private static final String UPDATED_PDF_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private BlcGiayChungSinhRepository blcGiayChungSinhRepository;

    @Autowired
    private BlcGiayChungSinhService blcGiayChungSinhService;

    @Autowired
    private BlcGiayChungSinhQueryService blcGiayChungSinhQueryService;

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

    private MockMvc restBlcGiayChungSinhMockMvc;

    private BlcGiayChungSinh blcGiayChungSinh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlcGiayChungSinhResource blcGiayChungSinhResource = new BlcGiayChungSinhResource(blcGiayChungSinhService, blcGiayChungSinhQueryService);
        this.restBlcGiayChungSinhMockMvc = MockMvcBuilders.standaloneSetup(blcGiayChungSinhResource)
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
    public static BlcGiayChungSinh createEntity(EntityManager em) {
        BlcGiayChungSinh blcGiayChungSinh = new BlcGiayChungSinh()
            .uuid(DEFAULT_UUID)
            .createdDate(DEFAULT_CREATED_DATE)
            .tenMeNguoiNuoiDuong(DEFAULT_TEN_ME_NGUOI_NUOI_DUONG)
            .namSinh(DEFAULT_NAM_SINH)
            .diaChiThuongTru(DEFAULT_DIA_CHI_THUONG_TRU)
            .soCMNDPassport(DEFAULT_SO_CMND_PASSPORT)
            .danToc(DEFAULT_DAN_TOC)
            .gioSinh(DEFAULT_GIO_SINH)
            .ngayThangNam(DEFAULT_NGAY_THANG_NAM)
            .noiSinh(DEFAULT_NOI_SINH)
            .soLanSinh(DEFAULT_SO_LAN_SINH)
            .soConHienSong(DEFAULT_SO_CON_HIEN_SONG)
            .soConTrongLanSinh(DEFAULT_SO_CON_TRONG_LAN_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .canNang(DEFAULT_CAN_NANG)
            .hienTrang(DEFAULT_HIEN_TRANG)
            .nguoiDoDe(DEFAULT_NGUOI_DO_DE)
            .tenDuDinh(DEFAULT_TEN_DU_DINH)
            .qrCodeImage(DEFAULT_QR_CODE_IMAGE)
            .statusApp(DEFAULT_STATUS_APP)
            .userApprove(DEFAULT_USER_APPROVE)
            .userCreate(DEFAULT_USER_CREATE)
            .addressGCS(DEFAULT_ADDRESS_GCS)
            .typeGCS(DEFAULT_TYPE_GCS)
            .so(DEFAULT_SO)
            .quyenSo(DEFAULT_QUYEN_SO)
            .emailNDK(DEFAULT_EMAIL_NDK)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .idBenhVien(DEFAULT_ID_BENH_VIEN)
            .tenBenhVien(DEFAULT_TEN_BENH_VIEN)
            .idUserCreate(DEFAULT_ID_USER_CREATE)
            .publicKey(DEFAULT_PUBLIC_KEY)
            .codeSoft(DEFAULT_CODE_SOFT)
            .ngayThangNamCap(DEFAULT_NGAY_THANG_NAM_CAP)
            .noiCap(DEFAULT_NOI_CAP)
            .hoTenCha(DEFAULT_HO_TEN_CHA)
            .pdfFile(DEFAULT_PDF_FILE)
            .status(DEFAULT_STATUS);
        return blcGiayChungSinh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlcGiayChungSinh createUpdatedEntity(EntityManager em) {
        BlcGiayChungSinh blcGiayChungSinh = new BlcGiayChungSinh()
            .uuid(UPDATED_UUID)
            .createdDate(UPDATED_CREATED_DATE)
            .tenMeNguoiNuoiDuong(UPDATED_TEN_ME_NGUOI_NUOI_DUONG)
            .namSinh(UPDATED_NAM_SINH)
            .diaChiThuongTru(UPDATED_DIA_CHI_THUONG_TRU)
            .soCMNDPassport(UPDATED_SO_CMND_PASSPORT)
            .danToc(UPDATED_DAN_TOC)
            .gioSinh(UPDATED_GIO_SINH)
            .ngayThangNam(UPDATED_NGAY_THANG_NAM)
            .noiSinh(UPDATED_NOI_SINH)
            .soLanSinh(UPDATED_SO_LAN_SINH)
            .soConHienSong(UPDATED_SO_CON_HIEN_SONG)
            .soConTrongLanSinh(UPDATED_SO_CON_TRONG_LAN_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .canNang(UPDATED_CAN_NANG)
            .hienTrang(UPDATED_HIEN_TRANG)
            .nguoiDoDe(UPDATED_NGUOI_DO_DE)
            .tenDuDinh(UPDATED_TEN_DU_DINH)
            .qrCodeImage(UPDATED_QR_CODE_IMAGE)
            .statusApp(UPDATED_STATUS_APP)
            .userApprove(UPDATED_USER_APPROVE)
            .userCreate(UPDATED_USER_CREATE)
            .addressGCS(UPDATED_ADDRESS_GCS)
            .typeGCS(UPDATED_TYPE_GCS)
            .so(UPDATED_SO)
            .quyenSo(UPDATED_QUYEN_SO)
            .emailNDK(UPDATED_EMAIL_NDK)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .idUserCreate(UPDATED_ID_USER_CREATE)
            .publicKey(UPDATED_PUBLIC_KEY)
            .codeSoft(UPDATED_CODE_SOFT)
            .ngayThangNamCap(UPDATED_NGAY_THANG_NAM_CAP)
            .noiCap(UPDATED_NOI_CAP)
            .hoTenCha(UPDATED_HO_TEN_CHA)
            .pdfFile(UPDATED_PDF_FILE)
            .status(UPDATED_STATUS);
        return blcGiayChungSinh;
    }

    @BeforeEach
    public void initTest() {
        blcGiayChungSinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlcGiayChungSinh() throws Exception {
        int databaseSizeBeforeCreate = blcGiayChungSinhRepository.findAll().size();

        // Create the BlcGiayChungSinh
        restBlcGiayChungSinhMockMvc.perform(post("/api/blc-giay-chung-sinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayChungSinh)))
            .andExpect(status().isCreated());

        // Validate the BlcGiayChungSinh in the database
        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeCreate + 1);
        BlcGiayChungSinh testBlcGiayChungSinh = blcGiayChungSinhList.get(blcGiayChungSinhList.size() - 1);
        assertThat(testBlcGiayChungSinh.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testBlcGiayChungSinh.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBlcGiayChungSinh.getTenMeNguoiNuoiDuong()).isEqualTo(DEFAULT_TEN_ME_NGUOI_NUOI_DUONG);
        assertThat(testBlcGiayChungSinh.getNamSinh()).isEqualTo(DEFAULT_NAM_SINH);
        assertThat(testBlcGiayChungSinh.getDiaChiThuongTru()).isEqualTo(DEFAULT_DIA_CHI_THUONG_TRU);
        assertThat(testBlcGiayChungSinh.getSoCMNDPassport()).isEqualTo(DEFAULT_SO_CMND_PASSPORT);
        assertThat(testBlcGiayChungSinh.getDanToc()).isEqualTo(DEFAULT_DAN_TOC);
        assertThat(testBlcGiayChungSinh.getGioSinh()).isEqualTo(DEFAULT_GIO_SINH);
        assertThat(testBlcGiayChungSinh.getNgayThangNam()).isEqualTo(DEFAULT_NGAY_THANG_NAM);
        assertThat(testBlcGiayChungSinh.getNoiSinh()).isEqualTo(DEFAULT_NOI_SINH);
        assertThat(testBlcGiayChungSinh.getSoLanSinh()).isEqualTo(DEFAULT_SO_LAN_SINH);
        assertThat(testBlcGiayChungSinh.getSoConHienSong()).isEqualTo(DEFAULT_SO_CON_HIEN_SONG);
        assertThat(testBlcGiayChungSinh.getSoConTrongLanSinh()).isEqualTo(DEFAULT_SO_CON_TRONG_LAN_SINH);
        assertThat(testBlcGiayChungSinh.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testBlcGiayChungSinh.getCanNang()).isEqualTo(DEFAULT_CAN_NANG);
        assertThat(testBlcGiayChungSinh.getHienTrang()).isEqualTo(DEFAULT_HIEN_TRANG);
        assertThat(testBlcGiayChungSinh.getNguoiDoDe()).isEqualTo(DEFAULT_NGUOI_DO_DE);
        assertThat(testBlcGiayChungSinh.getTenDuDinh()).isEqualTo(DEFAULT_TEN_DU_DINH);
        assertThat(testBlcGiayChungSinh.getQrCodeImage()).isEqualTo(DEFAULT_QR_CODE_IMAGE);
        assertThat(testBlcGiayChungSinh.getStatusApp()).isEqualTo(DEFAULT_STATUS_APP);
        assertThat(testBlcGiayChungSinh.getUserApprove()).isEqualTo(DEFAULT_USER_APPROVE);
        assertThat(testBlcGiayChungSinh.getUserCreate()).isEqualTo(DEFAULT_USER_CREATE);
        assertThat(testBlcGiayChungSinh.getAddressGCS()).isEqualTo(DEFAULT_ADDRESS_GCS);
        assertThat(testBlcGiayChungSinh.getTypeGCS()).isEqualTo(DEFAULT_TYPE_GCS);
        assertThat(testBlcGiayChungSinh.getSo()).isEqualTo(DEFAULT_SO);
        assertThat(testBlcGiayChungSinh.getQuyenSo()).isEqualTo(DEFAULT_QUYEN_SO);
        assertThat(testBlcGiayChungSinh.getEmailNDK()).isEqualTo(DEFAULT_EMAIL_NDK);
        assertThat(testBlcGiayChungSinh.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testBlcGiayChungSinh.getIdBenhVien()).isEqualTo(DEFAULT_ID_BENH_VIEN);
        assertThat(testBlcGiayChungSinh.getTenBenhVien()).isEqualTo(DEFAULT_TEN_BENH_VIEN);
        assertThat(testBlcGiayChungSinh.getIdUserCreate()).isEqualTo(DEFAULT_ID_USER_CREATE);
        assertThat(testBlcGiayChungSinh.getPublicKey()).isEqualTo(DEFAULT_PUBLIC_KEY);
        assertThat(testBlcGiayChungSinh.getCodeSoft()).isEqualTo(DEFAULT_CODE_SOFT);
        assertThat(testBlcGiayChungSinh.getNgayThangNamCap()).isEqualTo(DEFAULT_NGAY_THANG_NAM_CAP);
        assertThat(testBlcGiayChungSinh.getNoiCap()).isEqualTo(DEFAULT_NOI_CAP);
        assertThat(testBlcGiayChungSinh.getHoTenCha()).isEqualTo(DEFAULT_HO_TEN_CHA);
        assertThat(testBlcGiayChungSinh.getPdfFile()).isEqualTo(DEFAULT_PDF_FILE);
        assertThat(testBlcGiayChungSinh.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBlcGiayChungSinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blcGiayChungSinhRepository.findAll().size();

        // Create the BlcGiayChungSinh with an existing ID
        blcGiayChungSinh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlcGiayChungSinhMockMvc.perform(post("/api/blc-giay-chung-sinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayChungSinh)))
            .andExpect(status().isBadRequest());

        // Validate the BlcGiayChungSinh in the database
        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = blcGiayChungSinhRepository.findAll().size();
        // set the field null
        blcGiayChungSinh.setUuid(null);

        // Create the BlcGiayChungSinh, which fails.

        restBlcGiayChungSinhMockMvc.perform(post("/api/blc-giay-chung-sinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayChungSinh)))
            .andExpect(status().isBadRequest());

        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhs() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcGiayChungSinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].tenMeNguoiNuoiDuong").value(hasItem(DEFAULT_TEN_ME_NGUOI_NUOI_DUONG.toString())))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].diaChiThuongTru").value(hasItem(DEFAULT_DIA_CHI_THUONG_TRU.toString())))
            .andExpect(jsonPath("$.[*].soCMNDPassport").value(hasItem(DEFAULT_SO_CMND_PASSPORT.toString())))
            .andExpect(jsonPath("$.[*].danToc").value(hasItem(DEFAULT_DAN_TOC.toString())))
            .andExpect(jsonPath("$.[*].gioSinh").value(hasItem(DEFAULT_GIO_SINH.toString())))
            .andExpect(jsonPath("$.[*].ngayThangNam").value(hasItem(DEFAULT_NGAY_THANG_NAM.toString())))
            .andExpect(jsonPath("$.[*].noiSinh").value(hasItem(DEFAULT_NOI_SINH.toString())))
            .andExpect(jsonPath("$.[*].soLanSinh").value(hasItem(DEFAULT_SO_LAN_SINH.toString())))
            .andExpect(jsonPath("$.[*].soConHienSong").value(hasItem(DEFAULT_SO_CON_HIEN_SONG.toString())))
            .andExpect(jsonPath("$.[*].soConTrongLanSinh").value(hasItem(DEFAULT_SO_CON_TRONG_LAN_SINH.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].canNang").value(hasItem(DEFAULT_CAN_NANG.toString())))
            .andExpect(jsonPath("$.[*].hienTrang").value(hasItem(DEFAULT_HIEN_TRANG.toString())))
            .andExpect(jsonPath("$.[*].nguoiDoDe").value(hasItem(DEFAULT_NGUOI_DO_DE.toString())))
            .andExpect(jsonPath("$.[*].tenDuDinh").value(hasItem(DEFAULT_TEN_DU_DINH.toString())))
            .andExpect(jsonPath("$.[*].qrCodeImage").value(hasItem(DEFAULT_QR_CODE_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].statusApp").value(hasItem(DEFAULT_STATUS_APP.toString())))
            .andExpect(jsonPath("$.[*].userApprove").value(hasItem(DEFAULT_USER_APPROVE.toString())))
            .andExpect(jsonPath("$.[*].userCreate").value(hasItem(DEFAULT_USER_CREATE.toString())))
            .andExpect(jsonPath("$.[*].addressGCS").value(hasItem(DEFAULT_ADDRESS_GCS.toString())))
            .andExpect(jsonPath("$.[*].typeGCS").value(hasItem(DEFAULT_TYPE_GCS.toString())))
            .andExpect(jsonPath("$.[*].so").value(hasItem(DEFAULT_SO.toString())))
            .andExpect(jsonPath("$.[*].quyenSo").value(hasItem(DEFAULT_QUYEN_SO.toString())))
            .andExpect(jsonPath("$.[*].emailNDK").value(hasItem(DEFAULT_EMAIL_NDK.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].idUserCreate").value(hasItem(DEFAULT_ID_USER_CREATE.toString())))
            .andExpect(jsonPath("$.[*].publicKey").value(hasItem(DEFAULT_PUBLIC_KEY.toString())))
            .andExpect(jsonPath("$.[*].codeSoft").value(hasItem(DEFAULT_CODE_SOFT.toString())))
            .andExpect(jsonPath("$.[*].ngayThangNamCap").value(hasItem(DEFAULT_NGAY_THANG_NAM_CAP.toString())))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP.toString())))
            .andExpect(jsonPath("$.[*].hoTenCha").value(hasItem(DEFAULT_HO_TEN_CHA.toString())))
            .andExpect(jsonPath("$.[*].pdfFile").value(hasItem(DEFAULT_PDF_FILE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getBlcGiayChungSinh() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get the blcGiayChungSinh
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs/{id}", blcGiayChungSinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blcGiayChungSinh.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.tenMeNguoiNuoiDuong").value(DEFAULT_TEN_ME_NGUOI_NUOI_DUONG.toString()))
            .andExpect(jsonPath("$.namSinh").value(DEFAULT_NAM_SINH.toString()))
            .andExpect(jsonPath("$.diaChiThuongTru").value(DEFAULT_DIA_CHI_THUONG_TRU.toString()))
            .andExpect(jsonPath("$.soCMNDPassport").value(DEFAULT_SO_CMND_PASSPORT.toString()))
            .andExpect(jsonPath("$.danToc").value(DEFAULT_DAN_TOC.toString()))
            .andExpect(jsonPath("$.gioSinh").value(DEFAULT_GIO_SINH.toString()))
            .andExpect(jsonPath("$.ngayThangNam").value(DEFAULT_NGAY_THANG_NAM.toString()))
            .andExpect(jsonPath("$.noiSinh").value(DEFAULT_NOI_SINH.toString()))
            .andExpect(jsonPath("$.soLanSinh").value(DEFAULT_SO_LAN_SINH.toString()))
            .andExpect(jsonPath("$.soConHienSong").value(DEFAULT_SO_CON_HIEN_SONG.toString()))
            .andExpect(jsonPath("$.soConTrongLanSinh").value(DEFAULT_SO_CON_TRONG_LAN_SINH.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.canNang").value(DEFAULT_CAN_NANG.toString()))
            .andExpect(jsonPath("$.hienTrang").value(DEFAULT_HIEN_TRANG.toString()))
            .andExpect(jsonPath("$.nguoiDoDe").value(DEFAULT_NGUOI_DO_DE.toString()))
            .andExpect(jsonPath("$.tenDuDinh").value(DEFAULT_TEN_DU_DINH.toString()))
            .andExpect(jsonPath("$.qrCodeImage").value(DEFAULT_QR_CODE_IMAGE.toString()))
            .andExpect(jsonPath("$.statusApp").value(DEFAULT_STATUS_APP.toString()))
            .andExpect(jsonPath("$.userApprove").value(DEFAULT_USER_APPROVE.toString()))
            .andExpect(jsonPath("$.userCreate").value(DEFAULT_USER_CREATE.toString()))
            .andExpect(jsonPath("$.addressGCS").value(DEFAULT_ADDRESS_GCS.toString()))
            .andExpect(jsonPath("$.typeGCS").value(DEFAULT_TYPE_GCS.toString()))
            .andExpect(jsonPath("$.so").value(DEFAULT_SO.toString()))
            .andExpect(jsonPath("$.quyenSo").value(DEFAULT_QUYEN_SO.toString()))
            .andExpect(jsonPath("$.emailNDK").value(DEFAULT_EMAIL_NDK.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.idBenhVien").value(DEFAULT_ID_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.tenBenhVien").value(DEFAULT_TEN_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.idUserCreate").value(DEFAULT_ID_USER_CREATE.toString()))
            .andExpect(jsonPath("$.publicKey").value(DEFAULT_PUBLIC_KEY.toString()))
            .andExpect(jsonPath("$.codeSoft").value(DEFAULT_CODE_SOFT.toString()))
            .andExpect(jsonPath("$.ngayThangNamCap").value(DEFAULT_NGAY_THANG_NAM_CAP.toString()))
            .andExpect(jsonPath("$.noiCap").value(DEFAULT_NOI_CAP.toString()))
            .andExpect(jsonPath("$.hoTenCha").value(DEFAULT_HO_TEN_CHA.toString()))
            .andExpect(jsonPath("$.pdfFile").value(DEFAULT_PDF_FILE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where uuid equals to DEFAULT_UUID
        defaultBlcGiayChungSinhShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the blcGiayChungSinhList where uuid equals to UPDATED_UUID
        defaultBlcGiayChungSinhShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultBlcGiayChungSinhShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the blcGiayChungSinhList where uuid equals to UPDATED_UUID
        defaultBlcGiayChungSinhShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where uuid is not null
        defaultBlcGiayChungSinhShouldBeFound("uuid.specified=true");

        // Get all the blcGiayChungSinhList where uuid is null
        defaultBlcGiayChungSinhShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where createdDate equals to DEFAULT_CREATED_DATE
        defaultBlcGiayChungSinhShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the blcGiayChungSinhList where createdDate equals to UPDATED_CREATED_DATE
        defaultBlcGiayChungSinhShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultBlcGiayChungSinhShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the blcGiayChungSinhList where createdDate equals to UPDATED_CREATED_DATE
        defaultBlcGiayChungSinhShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where createdDate is not null
        defaultBlcGiayChungSinhShouldBeFound("createdDate.specified=true");

        // Get all the blcGiayChungSinhList where createdDate is null
        defaultBlcGiayChungSinhShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenMeNguoiNuoiDuongIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong equals to DEFAULT_TEN_ME_NGUOI_NUOI_DUONG
        defaultBlcGiayChungSinhShouldBeFound("tenMeNguoiNuoiDuong.equals=" + DEFAULT_TEN_ME_NGUOI_NUOI_DUONG);

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong equals to UPDATED_TEN_ME_NGUOI_NUOI_DUONG
        defaultBlcGiayChungSinhShouldNotBeFound("tenMeNguoiNuoiDuong.equals=" + UPDATED_TEN_ME_NGUOI_NUOI_DUONG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenMeNguoiNuoiDuongIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong in DEFAULT_TEN_ME_NGUOI_NUOI_DUONG or UPDATED_TEN_ME_NGUOI_NUOI_DUONG
        defaultBlcGiayChungSinhShouldBeFound("tenMeNguoiNuoiDuong.in=" + DEFAULT_TEN_ME_NGUOI_NUOI_DUONG + "," + UPDATED_TEN_ME_NGUOI_NUOI_DUONG);

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong equals to UPDATED_TEN_ME_NGUOI_NUOI_DUONG
        defaultBlcGiayChungSinhShouldNotBeFound("tenMeNguoiNuoiDuong.in=" + UPDATED_TEN_ME_NGUOI_NUOI_DUONG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenMeNguoiNuoiDuongIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong is not null
        defaultBlcGiayChungSinhShouldBeFound("tenMeNguoiNuoiDuong.specified=true");

        // Get all the blcGiayChungSinhList where tenMeNguoiNuoiDuong is null
        defaultBlcGiayChungSinhShouldNotBeFound("tenMeNguoiNuoiDuong.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNamSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where namSinh equals to DEFAULT_NAM_SINH
        defaultBlcGiayChungSinhShouldBeFound("namSinh.equals=" + DEFAULT_NAM_SINH);

        // Get all the blcGiayChungSinhList where namSinh equals to UPDATED_NAM_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("namSinh.equals=" + UPDATED_NAM_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNamSinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where namSinh in DEFAULT_NAM_SINH or UPDATED_NAM_SINH
        defaultBlcGiayChungSinhShouldBeFound("namSinh.in=" + DEFAULT_NAM_SINH + "," + UPDATED_NAM_SINH);

        // Get all the blcGiayChungSinhList where namSinh equals to UPDATED_NAM_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("namSinh.in=" + UPDATED_NAM_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNamSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where namSinh is not null
        defaultBlcGiayChungSinhShouldBeFound("namSinh.specified=true");

        // Get all the blcGiayChungSinhList where namSinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("namSinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDiaChiThuongTruIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where diaChiThuongTru equals to DEFAULT_DIA_CHI_THUONG_TRU
        defaultBlcGiayChungSinhShouldBeFound("diaChiThuongTru.equals=" + DEFAULT_DIA_CHI_THUONG_TRU);

        // Get all the blcGiayChungSinhList where diaChiThuongTru equals to UPDATED_DIA_CHI_THUONG_TRU
        defaultBlcGiayChungSinhShouldNotBeFound("diaChiThuongTru.equals=" + UPDATED_DIA_CHI_THUONG_TRU);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDiaChiThuongTruIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where diaChiThuongTru in DEFAULT_DIA_CHI_THUONG_TRU or UPDATED_DIA_CHI_THUONG_TRU
        defaultBlcGiayChungSinhShouldBeFound("diaChiThuongTru.in=" + DEFAULT_DIA_CHI_THUONG_TRU + "," + UPDATED_DIA_CHI_THUONG_TRU);

        // Get all the blcGiayChungSinhList where diaChiThuongTru equals to UPDATED_DIA_CHI_THUONG_TRU
        defaultBlcGiayChungSinhShouldNotBeFound("diaChiThuongTru.in=" + UPDATED_DIA_CHI_THUONG_TRU);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDiaChiThuongTruIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where diaChiThuongTru is not null
        defaultBlcGiayChungSinhShouldBeFound("diaChiThuongTru.specified=true");

        // Get all the blcGiayChungSinhList where diaChiThuongTru is null
        defaultBlcGiayChungSinhShouldNotBeFound("diaChiThuongTru.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoCMNDPassportIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soCMNDPassport equals to DEFAULT_SO_CMND_PASSPORT
        defaultBlcGiayChungSinhShouldBeFound("soCMNDPassport.equals=" + DEFAULT_SO_CMND_PASSPORT);

        // Get all the blcGiayChungSinhList where soCMNDPassport equals to UPDATED_SO_CMND_PASSPORT
        defaultBlcGiayChungSinhShouldNotBeFound("soCMNDPassport.equals=" + UPDATED_SO_CMND_PASSPORT);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoCMNDPassportIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soCMNDPassport in DEFAULT_SO_CMND_PASSPORT or UPDATED_SO_CMND_PASSPORT
        defaultBlcGiayChungSinhShouldBeFound("soCMNDPassport.in=" + DEFAULT_SO_CMND_PASSPORT + "," + UPDATED_SO_CMND_PASSPORT);

        // Get all the blcGiayChungSinhList where soCMNDPassport equals to UPDATED_SO_CMND_PASSPORT
        defaultBlcGiayChungSinhShouldNotBeFound("soCMNDPassport.in=" + UPDATED_SO_CMND_PASSPORT);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoCMNDPassportIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soCMNDPassport is not null
        defaultBlcGiayChungSinhShouldBeFound("soCMNDPassport.specified=true");

        // Get all the blcGiayChungSinhList where soCMNDPassport is null
        defaultBlcGiayChungSinhShouldNotBeFound("soCMNDPassport.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where danToc equals to DEFAULT_DAN_TOC
        defaultBlcGiayChungSinhShouldBeFound("danToc.equals=" + DEFAULT_DAN_TOC);

        // Get all the blcGiayChungSinhList where danToc equals to UPDATED_DAN_TOC
        defaultBlcGiayChungSinhShouldNotBeFound("danToc.equals=" + UPDATED_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDanTocIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where danToc in DEFAULT_DAN_TOC or UPDATED_DAN_TOC
        defaultBlcGiayChungSinhShouldBeFound("danToc.in=" + DEFAULT_DAN_TOC + "," + UPDATED_DAN_TOC);

        // Get all the blcGiayChungSinhList where danToc equals to UPDATED_DAN_TOC
        defaultBlcGiayChungSinhShouldNotBeFound("danToc.in=" + UPDATED_DAN_TOC);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDanTocIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where danToc is not null
        defaultBlcGiayChungSinhShouldBeFound("danToc.specified=true");

        // Get all the blcGiayChungSinhList where danToc is null
        defaultBlcGiayChungSinhShouldNotBeFound("danToc.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioSinh equals to DEFAULT_GIO_SINH
        defaultBlcGiayChungSinhShouldBeFound("gioSinh.equals=" + DEFAULT_GIO_SINH);

        // Get all the blcGiayChungSinhList where gioSinh equals to UPDATED_GIO_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("gioSinh.equals=" + UPDATED_GIO_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioSinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioSinh in DEFAULT_GIO_SINH or UPDATED_GIO_SINH
        defaultBlcGiayChungSinhShouldBeFound("gioSinh.in=" + DEFAULT_GIO_SINH + "," + UPDATED_GIO_SINH);

        // Get all the blcGiayChungSinhList where gioSinh equals to UPDATED_GIO_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("gioSinh.in=" + UPDATED_GIO_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioSinh is not null
        defaultBlcGiayChungSinhShouldBeFound("gioSinh.specified=true");

        // Get all the blcGiayChungSinhList where gioSinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("gioSinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNam equals to DEFAULT_NGAY_THANG_NAM
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNam.equals=" + DEFAULT_NGAY_THANG_NAM);

        // Get all the blcGiayChungSinhList where ngayThangNam equals to UPDATED_NGAY_THANG_NAM
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNam.equals=" + UPDATED_NGAY_THANG_NAM);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNam in DEFAULT_NGAY_THANG_NAM or UPDATED_NGAY_THANG_NAM
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNam.in=" + DEFAULT_NGAY_THANG_NAM + "," + UPDATED_NGAY_THANG_NAM);

        // Get all the blcGiayChungSinhList where ngayThangNam equals to UPDATED_NGAY_THANG_NAM
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNam.in=" + UPDATED_NGAY_THANG_NAM);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNam is not null
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNam.specified=true");

        // Get all the blcGiayChungSinhList where ngayThangNam is null
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNam.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiSinh equals to DEFAULT_NOI_SINH
        defaultBlcGiayChungSinhShouldBeFound("noiSinh.equals=" + DEFAULT_NOI_SINH);

        // Get all the blcGiayChungSinhList where noiSinh equals to UPDATED_NOI_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("noiSinh.equals=" + UPDATED_NOI_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiSinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiSinh in DEFAULT_NOI_SINH or UPDATED_NOI_SINH
        defaultBlcGiayChungSinhShouldBeFound("noiSinh.in=" + DEFAULT_NOI_SINH + "," + UPDATED_NOI_SINH);

        // Get all the blcGiayChungSinhList where noiSinh equals to UPDATED_NOI_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("noiSinh.in=" + UPDATED_NOI_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiSinh is not null
        defaultBlcGiayChungSinhShouldBeFound("noiSinh.specified=true");

        // Get all the blcGiayChungSinhList where noiSinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("noiSinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoLanSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soLanSinh equals to DEFAULT_SO_LAN_SINH
        defaultBlcGiayChungSinhShouldBeFound("soLanSinh.equals=" + DEFAULT_SO_LAN_SINH);

        // Get all the blcGiayChungSinhList where soLanSinh equals to UPDATED_SO_LAN_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("soLanSinh.equals=" + UPDATED_SO_LAN_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoLanSinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soLanSinh in DEFAULT_SO_LAN_SINH or UPDATED_SO_LAN_SINH
        defaultBlcGiayChungSinhShouldBeFound("soLanSinh.in=" + DEFAULT_SO_LAN_SINH + "," + UPDATED_SO_LAN_SINH);

        // Get all the blcGiayChungSinhList where soLanSinh equals to UPDATED_SO_LAN_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("soLanSinh.in=" + UPDATED_SO_LAN_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoLanSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soLanSinh is not null
        defaultBlcGiayChungSinhShouldBeFound("soLanSinh.specified=true");

        // Get all the blcGiayChungSinhList where soLanSinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("soLanSinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConHienSongIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConHienSong equals to DEFAULT_SO_CON_HIEN_SONG
        defaultBlcGiayChungSinhShouldBeFound("soConHienSong.equals=" + DEFAULT_SO_CON_HIEN_SONG);

        // Get all the blcGiayChungSinhList where soConHienSong equals to UPDATED_SO_CON_HIEN_SONG
        defaultBlcGiayChungSinhShouldNotBeFound("soConHienSong.equals=" + UPDATED_SO_CON_HIEN_SONG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConHienSongIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConHienSong in DEFAULT_SO_CON_HIEN_SONG or UPDATED_SO_CON_HIEN_SONG
        defaultBlcGiayChungSinhShouldBeFound("soConHienSong.in=" + DEFAULT_SO_CON_HIEN_SONG + "," + UPDATED_SO_CON_HIEN_SONG);

        // Get all the blcGiayChungSinhList where soConHienSong equals to UPDATED_SO_CON_HIEN_SONG
        defaultBlcGiayChungSinhShouldNotBeFound("soConHienSong.in=" + UPDATED_SO_CON_HIEN_SONG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConHienSongIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConHienSong is not null
        defaultBlcGiayChungSinhShouldBeFound("soConHienSong.specified=true");

        // Get all the blcGiayChungSinhList where soConHienSong is null
        defaultBlcGiayChungSinhShouldNotBeFound("soConHienSong.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConTrongLanSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConTrongLanSinh equals to DEFAULT_SO_CON_TRONG_LAN_SINH
        defaultBlcGiayChungSinhShouldBeFound("soConTrongLanSinh.equals=" + DEFAULT_SO_CON_TRONG_LAN_SINH);

        // Get all the blcGiayChungSinhList where soConTrongLanSinh equals to UPDATED_SO_CON_TRONG_LAN_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("soConTrongLanSinh.equals=" + UPDATED_SO_CON_TRONG_LAN_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConTrongLanSinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConTrongLanSinh in DEFAULT_SO_CON_TRONG_LAN_SINH or UPDATED_SO_CON_TRONG_LAN_SINH
        defaultBlcGiayChungSinhShouldBeFound("soConTrongLanSinh.in=" + DEFAULT_SO_CON_TRONG_LAN_SINH + "," + UPDATED_SO_CON_TRONG_LAN_SINH);

        // Get all the blcGiayChungSinhList where soConTrongLanSinh equals to UPDATED_SO_CON_TRONG_LAN_SINH
        defaultBlcGiayChungSinhShouldNotBeFound("soConTrongLanSinh.in=" + UPDATED_SO_CON_TRONG_LAN_SINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoConTrongLanSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where soConTrongLanSinh is not null
        defaultBlcGiayChungSinhShouldBeFound("soConTrongLanSinh.specified=true");

        // Get all the blcGiayChungSinhList where soConTrongLanSinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("soConTrongLanSinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioiTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioiTinh equals to DEFAULT_GIOI_TINH
        defaultBlcGiayChungSinhShouldBeFound("gioiTinh.equals=" + DEFAULT_GIOI_TINH);

        // Get all the blcGiayChungSinhList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultBlcGiayChungSinhShouldNotBeFound("gioiTinh.equals=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioiTinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioiTinh in DEFAULT_GIOI_TINH or UPDATED_GIOI_TINH
        defaultBlcGiayChungSinhShouldBeFound("gioiTinh.in=" + DEFAULT_GIOI_TINH + "," + UPDATED_GIOI_TINH);

        // Get all the blcGiayChungSinhList where gioiTinh equals to UPDATED_GIOI_TINH
        defaultBlcGiayChungSinhShouldNotBeFound("gioiTinh.in=" + UPDATED_GIOI_TINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByGioiTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where gioiTinh is not null
        defaultBlcGiayChungSinhShouldBeFound("gioiTinh.specified=true");

        // Get all the blcGiayChungSinhList where gioiTinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("gioiTinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCanNangIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where canNang equals to DEFAULT_CAN_NANG
        defaultBlcGiayChungSinhShouldBeFound("canNang.equals=" + DEFAULT_CAN_NANG);

        // Get all the blcGiayChungSinhList where canNang equals to UPDATED_CAN_NANG
        defaultBlcGiayChungSinhShouldNotBeFound("canNang.equals=" + UPDATED_CAN_NANG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCanNangIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where canNang in DEFAULT_CAN_NANG or UPDATED_CAN_NANG
        defaultBlcGiayChungSinhShouldBeFound("canNang.in=" + DEFAULT_CAN_NANG + "," + UPDATED_CAN_NANG);

        // Get all the blcGiayChungSinhList where canNang equals to UPDATED_CAN_NANG
        defaultBlcGiayChungSinhShouldNotBeFound("canNang.in=" + UPDATED_CAN_NANG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCanNangIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where canNang is not null
        defaultBlcGiayChungSinhShouldBeFound("canNang.specified=true");

        // Get all the blcGiayChungSinhList where canNang is null
        defaultBlcGiayChungSinhShouldNotBeFound("canNang.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHienTrangIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hienTrang equals to DEFAULT_HIEN_TRANG
        defaultBlcGiayChungSinhShouldBeFound("hienTrang.equals=" + DEFAULT_HIEN_TRANG);

        // Get all the blcGiayChungSinhList where hienTrang equals to UPDATED_HIEN_TRANG
        defaultBlcGiayChungSinhShouldNotBeFound("hienTrang.equals=" + UPDATED_HIEN_TRANG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHienTrangIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hienTrang in DEFAULT_HIEN_TRANG or UPDATED_HIEN_TRANG
        defaultBlcGiayChungSinhShouldBeFound("hienTrang.in=" + DEFAULT_HIEN_TRANG + "," + UPDATED_HIEN_TRANG);

        // Get all the blcGiayChungSinhList where hienTrang equals to UPDATED_HIEN_TRANG
        defaultBlcGiayChungSinhShouldNotBeFound("hienTrang.in=" + UPDATED_HIEN_TRANG);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHienTrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hienTrang is not null
        defaultBlcGiayChungSinhShouldBeFound("hienTrang.specified=true");

        // Get all the blcGiayChungSinhList where hienTrang is null
        defaultBlcGiayChungSinhShouldNotBeFound("hienTrang.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNguoiDoDeIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where nguoiDoDe equals to DEFAULT_NGUOI_DO_DE
        defaultBlcGiayChungSinhShouldBeFound("nguoiDoDe.equals=" + DEFAULT_NGUOI_DO_DE);

        // Get all the blcGiayChungSinhList where nguoiDoDe equals to UPDATED_NGUOI_DO_DE
        defaultBlcGiayChungSinhShouldNotBeFound("nguoiDoDe.equals=" + UPDATED_NGUOI_DO_DE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNguoiDoDeIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where nguoiDoDe in DEFAULT_NGUOI_DO_DE or UPDATED_NGUOI_DO_DE
        defaultBlcGiayChungSinhShouldBeFound("nguoiDoDe.in=" + DEFAULT_NGUOI_DO_DE + "," + UPDATED_NGUOI_DO_DE);

        // Get all the blcGiayChungSinhList where nguoiDoDe equals to UPDATED_NGUOI_DO_DE
        defaultBlcGiayChungSinhShouldNotBeFound("nguoiDoDe.in=" + UPDATED_NGUOI_DO_DE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNguoiDoDeIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where nguoiDoDe is not null
        defaultBlcGiayChungSinhShouldBeFound("nguoiDoDe.specified=true");

        // Get all the blcGiayChungSinhList where nguoiDoDe is null
        defaultBlcGiayChungSinhShouldNotBeFound("nguoiDoDe.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenDuDinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenDuDinh equals to DEFAULT_TEN_DU_DINH
        defaultBlcGiayChungSinhShouldBeFound("tenDuDinh.equals=" + DEFAULT_TEN_DU_DINH);

        // Get all the blcGiayChungSinhList where tenDuDinh equals to UPDATED_TEN_DU_DINH
        defaultBlcGiayChungSinhShouldNotBeFound("tenDuDinh.equals=" + UPDATED_TEN_DU_DINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenDuDinhIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenDuDinh in DEFAULT_TEN_DU_DINH or UPDATED_TEN_DU_DINH
        defaultBlcGiayChungSinhShouldBeFound("tenDuDinh.in=" + DEFAULT_TEN_DU_DINH + "," + UPDATED_TEN_DU_DINH);

        // Get all the blcGiayChungSinhList where tenDuDinh equals to UPDATED_TEN_DU_DINH
        defaultBlcGiayChungSinhShouldNotBeFound("tenDuDinh.in=" + UPDATED_TEN_DU_DINH);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenDuDinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenDuDinh is not null
        defaultBlcGiayChungSinhShouldBeFound("tenDuDinh.specified=true");

        // Get all the blcGiayChungSinhList where tenDuDinh is null
        defaultBlcGiayChungSinhShouldNotBeFound("tenDuDinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQrCodeImageIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where qrCodeImage equals to DEFAULT_QR_CODE_IMAGE
        defaultBlcGiayChungSinhShouldBeFound("qrCodeImage.equals=" + DEFAULT_QR_CODE_IMAGE);

        // Get all the blcGiayChungSinhList where qrCodeImage equals to UPDATED_QR_CODE_IMAGE
        defaultBlcGiayChungSinhShouldNotBeFound("qrCodeImage.equals=" + UPDATED_QR_CODE_IMAGE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQrCodeImageIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where qrCodeImage in DEFAULT_QR_CODE_IMAGE or UPDATED_QR_CODE_IMAGE
        defaultBlcGiayChungSinhShouldBeFound("qrCodeImage.in=" + DEFAULT_QR_CODE_IMAGE + "," + UPDATED_QR_CODE_IMAGE);

        // Get all the blcGiayChungSinhList where qrCodeImage equals to UPDATED_QR_CODE_IMAGE
        defaultBlcGiayChungSinhShouldNotBeFound("qrCodeImage.in=" + UPDATED_QR_CODE_IMAGE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQrCodeImageIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where qrCodeImage is not null
        defaultBlcGiayChungSinhShouldBeFound("qrCodeImage.specified=true");

        // Get all the blcGiayChungSinhList where qrCodeImage is null
        defaultBlcGiayChungSinhShouldNotBeFound("qrCodeImage.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusAppIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where statusApp equals to DEFAULT_STATUS_APP
        defaultBlcGiayChungSinhShouldBeFound("statusApp.equals=" + DEFAULT_STATUS_APP);

        // Get all the blcGiayChungSinhList where statusApp equals to UPDATED_STATUS_APP
        defaultBlcGiayChungSinhShouldNotBeFound("statusApp.equals=" + UPDATED_STATUS_APP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusAppIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where statusApp in DEFAULT_STATUS_APP or UPDATED_STATUS_APP
        defaultBlcGiayChungSinhShouldBeFound("statusApp.in=" + DEFAULT_STATUS_APP + "," + UPDATED_STATUS_APP);

        // Get all the blcGiayChungSinhList where statusApp equals to UPDATED_STATUS_APP
        defaultBlcGiayChungSinhShouldNotBeFound("statusApp.in=" + UPDATED_STATUS_APP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusAppIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where statusApp is not null
        defaultBlcGiayChungSinhShouldBeFound("statusApp.specified=true");

        // Get all the blcGiayChungSinhList where statusApp is null
        defaultBlcGiayChungSinhShouldNotBeFound("statusApp.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserApproveIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userApprove equals to DEFAULT_USER_APPROVE
        defaultBlcGiayChungSinhShouldBeFound("userApprove.equals=" + DEFAULT_USER_APPROVE);

        // Get all the blcGiayChungSinhList where userApprove equals to UPDATED_USER_APPROVE
        defaultBlcGiayChungSinhShouldNotBeFound("userApprove.equals=" + UPDATED_USER_APPROVE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserApproveIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userApprove in DEFAULT_USER_APPROVE or UPDATED_USER_APPROVE
        defaultBlcGiayChungSinhShouldBeFound("userApprove.in=" + DEFAULT_USER_APPROVE + "," + UPDATED_USER_APPROVE);

        // Get all the blcGiayChungSinhList where userApprove equals to UPDATED_USER_APPROVE
        defaultBlcGiayChungSinhShouldNotBeFound("userApprove.in=" + UPDATED_USER_APPROVE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserApproveIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userApprove is not null
        defaultBlcGiayChungSinhShouldBeFound("userApprove.specified=true");

        // Get all the blcGiayChungSinhList where userApprove is null
        defaultBlcGiayChungSinhShouldNotBeFound("userApprove.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userCreate equals to DEFAULT_USER_CREATE
        defaultBlcGiayChungSinhShouldBeFound("userCreate.equals=" + DEFAULT_USER_CREATE);

        // Get all the blcGiayChungSinhList where userCreate equals to UPDATED_USER_CREATE
        defaultBlcGiayChungSinhShouldNotBeFound("userCreate.equals=" + UPDATED_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserCreateIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userCreate in DEFAULT_USER_CREATE or UPDATED_USER_CREATE
        defaultBlcGiayChungSinhShouldBeFound("userCreate.in=" + DEFAULT_USER_CREATE + "," + UPDATED_USER_CREATE);

        // Get all the blcGiayChungSinhList where userCreate equals to UPDATED_USER_CREATE
        defaultBlcGiayChungSinhShouldNotBeFound("userCreate.in=" + UPDATED_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByUserCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where userCreate is not null
        defaultBlcGiayChungSinhShouldBeFound("userCreate.specified=true");

        // Get all the blcGiayChungSinhList where userCreate is null
        defaultBlcGiayChungSinhShouldNotBeFound("userCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByAddressGCSIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where addressGCS equals to DEFAULT_ADDRESS_GCS
        defaultBlcGiayChungSinhShouldBeFound("addressGCS.equals=" + DEFAULT_ADDRESS_GCS);

        // Get all the blcGiayChungSinhList where addressGCS equals to UPDATED_ADDRESS_GCS
        defaultBlcGiayChungSinhShouldNotBeFound("addressGCS.equals=" + UPDATED_ADDRESS_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByAddressGCSIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where addressGCS in DEFAULT_ADDRESS_GCS or UPDATED_ADDRESS_GCS
        defaultBlcGiayChungSinhShouldBeFound("addressGCS.in=" + DEFAULT_ADDRESS_GCS + "," + UPDATED_ADDRESS_GCS);

        // Get all the blcGiayChungSinhList where addressGCS equals to UPDATED_ADDRESS_GCS
        defaultBlcGiayChungSinhShouldNotBeFound("addressGCS.in=" + UPDATED_ADDRESS_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByAddressGCSIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where addressGCS is not null
        defaultBlcGiayChungSinhShouldBeFound("addressGCS.specified=true");

        // Get all the blcGiayChungSinhList where addressGCS is null
        defaultBlcGiayChungSinhShouldNotBeFound("addressGCS.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTypeGCSIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where typeGCS equals to DEFAULT_TYPE_GCS
        defaultBlcGiayChungSinhShouldBeFound("typeGCS.equals=" + DEFAULT_TYPE_GCS);

        // Get all the blcGiayChungSinhList where typeGCS equals to UPDATED_TYPE_GCS
        defaultBlcGiayChungSinhShouldNotBeFound("typeGCS.equals=" + UPDATED_TYPE_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTypeGCSIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where typeGCS in DEFAULT_TYPE_GCS or UPDATED_TYPE_GCS
        defaultBlcGiayChungSinhShouldBeFound("typeGCS.in=" + DEFAULT_TYPE_GCS + "," + UPDATED_TYPE_GCS);

        // Get all the blcGiayChungSinhList where typeGCS equals to UPDATED_TYPE_GCS
        defaultBlcGiayChungSinhShouldNotBeFound("typeGCS.in=" + UPDATED_TYPE_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTypeGCSIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where typeGCS is not null
        defaultBlcGiayChungSinhShouldBeFound("typeGCS.specified=true");

        // Get all the blcGiayChungSinhList where typeGCS is null
        defaultBlcGiayChungSinhShouldNotBeFound("typeGCS.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where so equals to DEFAULT_SO
        defaultBlcGiayChungSinhShouldBeFound("so.equals=" + DEFAULT_SO);

        // Get all the blcGiayChungSinhList where so equals to UPDATED_SO
        defaultBlcGiayChungSinhShouldNotBeFound("so.equals=" + UPDATED_SO);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where so in DEFAULT_SO or UPDATED_SO
        defaultBlcGiayChungSinhShouldBeFound("so.in=" + DEFAULT_SO + "," + UPDATED_SO);

        // Get all the blcGiayChungSinhList where so equals to UPDATED_SO
        defaultBlcGiayChungSinhShouldNotBeFound("so.in=" + UPDATED_SO);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsBySoIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where so is not null
        defaultBlcGiayChungSinhShouldBeFound("so.specified=true");

        // Get all the blcGiayChungSinhList where so is null
        defaultBlcGiayChungSinhShouldNotBeFound("so.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQuyenSoIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where quyenSo equals to DEFAULT_QUYEN_SO
        defaultBlcGiayChungSinhShouldBeFound("quyenSo.equals=" + DEFAULT_QUYEN_SO);

        // Get all the blcGiayChungSinhList where quyenSo equals to UPDATED_QUYEN_SO
        defaultBlcGiayChungSinhShouldNotBeFound("quyenSo.equals=" + UPDATED_QUYEN_SO);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQuyenSoIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where quyenSo in DEFAULT_QUYEN_SO or UPDATED_QUYEN_SO
        defaultBlcGiayChungSinhShouldBeFound("quyenSo.in=" + DEFAULT_QUYEN_SO + "," + UPDATED_QUYEN_SO);

        // Get all the blcGiayChungSinhList where quyenSo equals to UPDATED_QUYEN_SO
        defaultBlcGiayChungSinhShouldNotBeFound("quyenSo.in=" + UPDATED_QUYEN_SO);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByQuyenSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where quyenSo is not null
        defaultBlcGiayChungSinhShouldBeFound("quyenSo.specified=true");

        // Get all the blcGiayChungSinhList where quyenSo is null
        defaultBlcGiayChungSinhShouldNotBeFound("quyenSo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByEmailNDKIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where emailNDK equals to DEFAULT_EMAIL_NDK
        defaultBlcGiayChungSinhShouldBeFound("emailNDK.equals=" + DEFAULT_EMAIL_NDK);

        // Get all the blcGiayChungSinhList where emailNDK equals to UPDATED_EMAIL_NDK
        defaultBlcGiayChungSinhShouldNotBeFound("emailNDK.equals=" + UPDATED_EMAIL_NDK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByEmailNDKIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where emailNDK in DEFAULT_EMAIL_NDK or UPDATED_EMAIL_NDK
        defaultBlcGiayChungSinhShouldBeFound("emailNDK.in=" + DEFAULT_EMAIL_NDK + "," + UPDATED_EMAIL_NDK);

        // Get all the blcGiayChungSinhList where emailNDK equals to UPDATED_EMAIL_NDK
        defaultBlcGiayChungSinhShouldNotBeFound("emailNDK.in=" + UPDATED_EMAIL_NDK);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByEmailNDKIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where emailNDK is not null
        defaultBlcGiayChungSinhShouldBeFound("emailNDK.specified=true");

        // Get all the blcGiayChungSinhList where emailNDK is null
        defaultBlcGiayChungSinhShouldNotBeFound("emailNDK.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByModifiedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where modifiedDate equals to DEFAULT_MODIFIED_DATE
        defaultBlcGiayChungSinhShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

        // Get all the blcGiayChungSinhList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultBlcGiayChungSinhShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByModifiedDateIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
        defaultBlcGiayChungSinhShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

        // Get all the blcGiayChungSinhList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultBlcGiayChungSinhShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByModifiedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where modifiedDate is not null
        defaultBlcGiayChungSinhShouldBeFound("modifiedDate.specified=true");

        // Get all the blcGiayChungSinhList where modifiedDate is null
        defaultBlcGiayChungSinhShouldNotBeFound("modifiedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idBenhVien equals to DEFAULT_ID_BENH_VIEN
        defaultBlcGiayChungSinhShouldBeFound("idBenhVien.equals=" + DEFAULT_ID_BENH_VIEN);

        // Get all the blcGiayChungSinhList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcGiayChungSinhShouldNotBeFound("idBenhVien.equals=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idBenhVien in DEFAULT_ID_BENH_VIEN or UPDATED_ID_BENH_VIEN
        defaultBlcGiayChungSinhShouldBeFound("idBenhVien.in=" + DEFAULT_ID_BENH_VIEN + "," + UPDATED_ID_BENH_VIEN);

        // Get all the blcGiayChungSinhList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcGiayChungSinhShouldNotBeFound("idBenhVien.in=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idBenhVien is not null
        defaultBlcGiayChungSinhShouldBeFound("idBenhVien.specified=true");

        // Get all the blcGiayChungSinhList where idBenhVien is null
        defaultBlcGiayChungSinhShouldNotBeFound("idBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenBenhVien equals to DEFAULT_TEN_BENH_VIEN
        defaultBlcGiayChungSinhShouldBeFound("tenBenhVien.equals=" + DEFAULT_TEN_BENH_VIEN);

        // Get all the blcGiayChungSinhList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcGiayChungSinhShouldNotBeFound("tenBenhVien.equals=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenBenhVien in DEFAULT_TEN_BENH_VIEN or UPDATED_TEN_BENH_VIEN
        defaultBlcGiayChungSinhShouldBeFound("tenBenhVien.in=" + DEFAULT_TEN_BENH_VIEN + "," + UPDATED_TEN_BENH_VIEN);

        // Get all the blcGiayChungSinhList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcGiayChungSinhShouldNotBeFound("tenBenhVien.in=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByTenBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where tenBenhVien is not null
        defaultBlcGiayChungSinhShouldBeFound("tenBenhVien.specified=true");

        // Get all the blcGiayChungSinhList where tenBenhVien is null
        defaultBlcGiayChungSinhShouldNotBeFound("tenBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdUserCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idUserCreate equals to DEFAULT_ID_USER_CREATE
        defaultBlcGiayChungSinhShouldBeFound("idUserCreate.equals=" + DEFAULT_ID_USER_CREATE);

        // Get all the blcGiayChungSinhList where idUserCreate equals to UPDATED_ID_USER_CREATE
        defaultBlcGiayChungSinhShouldNotBeFound("idUserCreate.equals=" + UPDATED_ID_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdUserCreateIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idUserCreate in DEFAULT_ID_USER_CREATE or UPDATED_ID_USER_CREATE
        defaultBlcGiayChungSinhShouldBeFound("idUserCreate.in=" + DEFAULT_ID_USER_CREATE + "," + UPDATED_ID_USER_CREATE);

        // Get all the blcGiayChungSinhList where idUserCreate equals to UPDATED_ID_USER_CREATE
        defaultBlcGiayChungSinhShouldNotBeFound("idUserCreate.in=" + UPDATED_ID_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByIdUserCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where idUserCreate is not null
        defaultBlcGiayChungSinhShouldBeFound("idUserCreate.specified=true");

        // Get all the blcGiayChungSinhList where idUserCreate is null
        defaultBlcGiayChungSinhShouldNotBeFound("idUserCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPublicKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where publicKey equals to DEFAULT_PUBLIC_KEY
        defaultBlcGiayChungSinhShouldBeFound("publicKey.equals=" + DEFAULT_PUBLIC_KEY);

        // Get all the blcGiayChungSinhList where publicKey equals to UPDATED_PUBLIC_KEY
        defaultBlcGiayChungSinhShouldNotBeFound("publicKey.equals=" + UPDATED_PUBLIC_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPublicKeyIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where publicKey in DEFAULT_PUBLIC_KEY or UPDATED_PUBLIC_KEY
        defaultBlcGiayChungSinhShouldBeFound("publicKey.in=" + DEFAULT_PUBLIC_KEY + "," + UPDATED_PUBLIC_KEY);

        // Get all the blcGiayChungSinhList where publicKey equals to UPDATED_PUBLIC_KEY
        defaultBlcGiayChungSinhShouldNotBeFound("publicKey.in=" + UPDATED_PUBLIC_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPublicKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where publicKey is not null
        defaultBlcGiayChungSinhShouldBeFound("publicKey.specified=true");

        // Get all the blcGiayChungSinhList where publicKey is null
        defaultBlcGiayChungSinhShouldNotBeFound("publicKey.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCodeSoftIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where codeSoft equals to DEFAULT_CODE_SOFT
        defaultBlcGiayChungSinhShouldBeFound("codeSoft.equals=" + DEFAULT_CODE_SOFT);

        // Get all the blcGiayChungSinhList where codeSoft equals to UPDATED_CODE_SOFT
        defaultBlcGiayChungSinhShouldNotBeFound("codeSoft.equals=" + UPDATED_CODE_SOFT);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCodeSoftIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where codeSoft in DEFAULT_CODE_SOFT or UPDATED_CODE_SOFT
        defaultBlcGiayChungSinhShouldBeFound("codeSoft.in=" + DEFAULT_CODE_SOFT + "," + UPDATED_CODE_SOFT);

        // Get all the blcGiayChungSinhList where codeSoft equals to UPDATED_CODE_SOFT
        defaultBlcGiayChungSinhShouldNotBeFound("codeSoft.in=" + UPDATED_CODE_SOFT);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByCodeSoftIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where codeSoft is not null
        defaultBlcGiayChungSinhShouldBeFound("codeSoft.specified=true");

        // Get all the blcGiayChungSinhList where codeSoft is null
        defaultBlcGiayChungSinhShouldNotBeFound("codeSoft.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamCapIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNamCap equals to DEFAULT_NGAY_THANG_NAM_CAP
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNamCap.equals=" + DEFAULT_NGAY_THANG_NAM_CAP);

        // Get all the blcGiayChungSinhList where ngayThangNamCap equals to UPDATED_NGAY_THANG_NAM_CAP
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNamCap.equals=" + UPDATED_NGAY_THANG_NAM_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamCapIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNamCap in DEFAULT_NGAY_THANG_NAM_CAP or UPDATED_NGAY_THANG_NAM_CAP
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNamCap.in=" + DEFAULT_NGAY_THANG_NAM_CAP + "," + UPDATED_NGAY_THANG_NAM_CAP);

        // Get all the blcGiayChungSinhList where ngayThangNamCap equals to UPDATED_NGAY_THANG_NAM_CAP
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNamCap.in=" + UPDATED_NGAY_THANG_NAM_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNgayThangNamCapIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where ngayThangNamCap is not null
        defaultBlcGiayChungSinhShouldBeFound("ngayThangNamCap.specified=true");

        // Get all the blcGiayChungSinhList where ngayThangNamCap is null
        defaultBlcGiayChungSinhShouldNotBeFound("ngayThangNamCap.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiCapIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiCap equals to DEFAULT_NOI_CAP
        defaultBlcGiayChungSinhShouldBeFound("noiCap.equals=" + DEFAULT_NOI_CAP);

        // Get all the blcGiayChungSinhList where noiCap equals to UPDATED_NOI_CAP
        defaultBlcGiayChungSinhShouldNotBeFound("noiCap.equals=" + UPDATED_NOI_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiCapIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiCap in DEFAULT_NOI_CAP or UPDATED_NOI_CAP
        defaultBlcGiayChungSinhShouldBeFound("noiCap.in=" + DEFAULT_NOI_CAP + "," + UPDATED_NOI_CAP);

        // Get all the blcGiayChungSinhList where noiCap equals to UPDATED_NOI_CAP
        defaultBlcGiayChungSinhShouldNotBeFound("noiCap.in=" + UPDATED_NOI_CAP);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByNoiCapIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where noiCap is not null
        defaultBlcGiayChungSinhShouldBeFound("noiCap.specified=true");

        // Get all the blcGiayChungSinhList where noiCap is null
        defaultBlcGiayChungSinhShouldNotBeFound("noiCap.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHoTenChaIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hoTenCha equals to DEFAULT_HO_TEN_CHA
        defaultBlcGiayChungSinhShouldBeFound("hoTenCha.equals=" + DEFAULT_HO_TEN_CHA);

        // Get all the blcGiayChungSinhList where hoTenCha equals to UPDATED_HO_TEN_CHA
        defaultBlcGiayChungSinhShouldNotBeFound("hoTenCha.equals=" + UPDATED_HO_TEN_CHA);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHoTenChaIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hoTenCha in DEFAULT_HO_TEN_CHA or UPDATED_HO_TEN_CHA
        defaultBlcGiayChungSinhShouldBeFound("hoTenCha.in=" + DEFAULT_HO_TEN_CHA + "," + UPDATED_HO_TEN_CHA);

        // Get all the blcGiayChungSinhList where hoTenCha equals to UPDATED_HO_TEN_CHA
        defaultBlcGiayChungSinhShouldNotBeFound("hoTenCha.in=" + UPDATED_HO_TEN_CHA);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByHoTenChaIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where hoTenCha is not null
        defaultBlcGiayChungSinhShouldBeFound("hoTenCha.specified=true");

        // Get all the blcGiayChungSinhList where hoTenCha is null
        defaultBlcGiayChungSinhShouldNotBeFound("hoTenCha.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPdfFileIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where pdfFile equals to DEFAULT_PDF_FILE
        defaultBlcGiayChungSinhShouldBeFound("pdfFile.equals=" + DEFAULT_PDF_FILE);

        // Get all the blcGiayChungSinhList where pdfFile equals to UPDATED_PDF_FILE
        defaultBlcGiayChungSinhShouldNotBeFound("pdfFile.equals=" + UPDATED_PDF_FILE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPdfFileIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where pdfFile in DEFAULT_PDF_FILE or UPDATED_PDF_FILE
        defaultBlcGiayChungSinhShouldBeFound("pdfFile.in=" + DEFAULT_PDF_FILE + "," + UPDATED_PDF_FILE);

        // Get all the blcGiayChungSinhList where pdfFile equals to UPDATED_PDF_FILE
        defaultBlcGiayChungSinhShouldNotBeFound("pdfFile.in=" + UPDATED_PDF_FILE);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByPdfFileIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where pdfFile is not null
        defaultBlcGiayChungSinhShouldBeFound("pdfFile.specified=true");

        // Get all the blcGiayChungSinhList where pdfFile is null
        defaultBlcGiayChungSinhShouldNotBeFound("pdfFile.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where status equals to DEFAULT_STATUS
        defaultBlcGiayChungSinhShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the blcGiayChungSinhList where status equals to UPDATED_STATUS
        defaultBlcGiayChungSinhShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBlcGiayChungSinhShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the blcGiayChungSinhList where status equals to UPDATED_STATUS
        defaultBlcGiayChungSinhShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);

        // Get all the blcGiayChungSinhList where status is not null
        defaultBlcGiayChungSinhShouldBeFound("status.specified=true");

        // Get all the blcGiayChungSinhList where status is null
        defaultBlcGiayChungSinhShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByBlcAPILogIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        BlcAPILog blcAPILog = BlcAPILogResourceIT.createEntity(em);
        em.persist(blcAPILog);
        em.flush();
        blcGiayChungSinh.setBlcAPILog(blcAPILog);
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        Long blcAPILogId = blcAPILog.getId();

        // Get all the blcGiayChungSinhList where blcAPILog equals to blcAPILogId
        defaultBlcGiayChungSinhShouldBeFound("blcAPILogId.equals=" + blcAPILogId);

        // Get all the blcGiayChungSinhList where blcAPILog equals to blcAPILogId + 1
        defaultBlcGiayChungSinhShouldNotBeFound("blcAPILogId.equals=" + (blcAPILogId + 1));
    }


    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByBlcRegisterPrivateKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        BlcRegisterPrivateKey blcRegisterPrivateKey = BlcRegisterPrivateKeyResourceIT.createEntity(em);
        em.persist(blcRegisterPrivateKey);
        em.flush();
        blcGiayChungSinh.addBlcRegisterPrivateKey(blcRegisterPrivateKey);
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        Long blcRegisterPrivateKeyId = blcRegisterPrivateKey.getId();

        // Get all the blcGiayChungSinhList where blcRegisterPrivateKey equals to blcRegisterPrivateKeyId
        defaultBlcGiayChungSinhShouldBeFound("blcRegisterPrivateKeyId.equals=" + blcRegisterPrivateKeyId);

        // Get all the blcGiayChungSinhList where blcRegisterPrivateKey equals to blcRegisterPrivateKeyId + 1
        defaultBlcGiayChungSinhShouldNotBeFound("blcRegisterPrivateKeyId.equals=" + (blcRegisterPrivateKeyId + 1));
    }


    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDanTocIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        DanToc danToc = DanTocResourceIT.createEntity(em);
        em.persist(danToc);
        em.flush();
        blcGiayChungSinh.setDanToc(danToc);
        danToc.setBlcGiayChungSinh(blcGiayChungSinh);
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        Long danTocId = danToc.getId();

        // Get all the blcGiayChungSinhList where danToc equals to danTocId
        defaultBlcGiayChungSinhShouldBeFound("danTocId.equals=" + danTocId);

        // Get all the blcGiayChungSinhList where danToc equals to danTocId + 1
        defaultBlcGiayChungSinhShouldNotBeFound("danTocId.equals=" + (danTocId + 1));
    }


    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByDonViHanhChinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        DonViHanhChinh donViHanhChinh = DonViHanhChinhResourceIT.createEntity(em);
        em.persist(donViHanhChinh);
        em.flush();
        blcGiayChungSinh.setDonViHanhChinh(donViHanhChinh);
        donViHanhChinh.setBlcGiayChungSinh(blcGiayChungSinh);
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        Long donViHanhChinhId = donViHanhChinh.getId();

        // Get all the blcGiayChungSinhList where donViHanhChinh equals to donViHanhChinhId
        defaultBlcGiayChungSinhShouldBeFound("donViHanhChinhId.equals=" + donViHanhChinhId);

        // Get all the blcGiayChungSinhList where donViHanhChinh equals to donViHanhChinhId + 1
        defaultBlcGiayChungSinhShouldNotBeFound("donViHanhChinhId.equals=" + (donViHanhChinhId + 1));
    }


    @Test
    @Transactional
    public void getAllBlcGiayChungSinhsByBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        BenhVien benhVien = BenhVienResourceIT.createEntity(em);
        em.persist(benhVien);
        em.flush();
        blcGiayChungSinh.setBenhVien(benhVien);
        benhVien.setBlcGiayChungSinh(blcGiayChungSinh);
        blcGiayChungSinhRepository.saveAndFlush(blcGiayChungSinh);
        Long benhVienId = benhVien.getId();

        // Get all the blcGiayChungSinhList where benhVien equals to benhVienId
        defaultBlcGiayChungSinhShouldBeFound("benhVienId.equals=" + benhVienId);

        // Get all the blcGiayChungSinhList where benhVien equals to benhVienId + 1
        defaultBlcGiayChungSinhShouldNotBeFound("benhVienId.equals=" + (benhVienId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBlcGiayChungSinhShouldBeFound(String filter) throws Exception {
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcGiayChungSinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].tenMeNguoiNuoiDuong").value(hasItem(DEFAULT_TEN_ME_NGUOI_NUOI_DUONG)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH)))
            .andExpect(jsonPath("$.[*].diaChiThuongTru").value(hasItem(DEFAULT_DIA_CHI_THUONG_TRU)))
            .andExpect(jsonPath("$.[*].soCMNDPassport").value(hasItem(DEFAULT_SO_CMND_PASSPORT)))
            .andExpect(jsonPath("$.[*].danToc").value(hasItem(DEFAULT_DAN_TOC)))
            .andExpect(jsonPath("$.[*].gioSinh").value(hasItem(DEFAULT_GIO_SINH)))
            .andExpect(jsonPath("$.[*].ngayThangNam").value(hasItem(DEFAULT_NGAY_THANG_NAM)))
            .andExpect(jsonPath("$.[*].noiSinh").value(hasItem(DEFAULT_NOI_SINH)))
            .andExpect(jsonPath("$.[*].soLanSinh").value(hasItem(DEFAULT_SO_LAN_SINH)))
            .andExpect(jsonPath("$.[*].soConHienSong").value(hasItem(DEFAULT_SO_CON_HIEN_SONG)))
            .andExpect(jsonPath("$.[*].soConTrongLanSinh").value(hasItem(DEFAULT_SO_CON_TRONG_LAN_SINH)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].canNang").value(hasItem(DEFAULT_CAN_NANG)))
            .andExpect(jsonPath("$.[*].hienTrang").value(hasItem(DEFAULT_HIEN_TRANG)))
            .andExpect(jsonPath("$.[*].nguoiDoDe").value(hasItem(DEFAULT_NGUOI_DO_DE)))
            .andExpect(jsonPath("$.[*].tenDuDinh").value(hasItem(DEFAULT_TEN_DU_DINH)))
            .andExpect(jsonPath("$.[*].qrCodeImage").value(hasItem(DEFAULT_QR_CODE_IMAGE)))
            .andExpect(jsonPath("$.[*].statusApp").value(hasItem(DEFAULT_STATUS_APP)))
            .andExpect(jsonPath("$.[*].userApprove").value(hasItem(DEFAULT_USER_APPROVE)))
            .andExpect(jsonPath("$.[*].userCreate").value(hasItem(DEFAULT_USER_CREATE)))
            .andExpect(jsonPath("$.[*].addressGCS").value(hasItem(DEFAULT_ADDRESS_GCS)))
            .andExpect(jsonPath("$.[*].typeGCS").value(hasItem(DEFAULT_TYPE_GCS)))
            .andExpect(jsonPath("$.[*].so").value(hasItem(DEFAULT_SO)))
            .andExpect(jsonPath("$.[*].quyenSo").value(hasItem(DEFAULT_QUYEN_SO)))
            .andExpect(jsonPath("$.[*].emailNDK").value(hasItem(DEFAULT_EMAIL_NDK)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].idUserCreate").value(hasItem(DEFAULT_ID_USER_CREATE)))
            .andExpect(jsonPath("$.[*].publicKey").value(hasItem(DEFAULT_PUBLIC_KEY)))
            .andExpect(jsonPath("$.[*].codeSoft").value(hasItem(DEFAULT_CODE_SOFT)))
            .andExpect(jsonPath("$.[*].ngayThangNamCap").value(hasItem(DEFAULT_NGAY_THANG_NAM_CAP)))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP)))
            .andExpect(jsonPath("$.[*].hoTenCha").value(hasItem(DEFAULT_HO_TEN_CHA)))
            .andExpect(jsonPath("$.[*].pdfFile").value(hasItem(DEFAULT_PDF_FILE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBlcGiayChungSinhShouldNotBeFound(String filter) throws Exception {
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlcGiayChungSinh() throws Exception {
        // Get the blcGiayChungSinh
        restBlcGiayChungSinhMockMvc.perform(get("/api/blc-giay-chung-sinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlcGiayChungSinh() throws Exception {
        // Initialize the database
        blcGiayChungSinhService.save(blcGiayChungSinh);

        int databaseSizeBeforeUpdate = blcGiayChungSinhRepository.findAll().size();

        // Update the blcGiayChungSinh
        BlcGiayChungSinh updatedBlcGiayChungSinh = blcGiayChungSinhRepository.findById(blcGiayChungSinh.getId()).get();
        // Disconnect from session so that the updates on updatedBlcGiayChungSinh are not directly saved in db
        em.detach(updatedBlcGiayChungSinh);
        updatedBlcGiayChungSinh
            .uuid(UPDATED_UUID)
            .createdDate(UPDATED_CREATED_DATE)
            .tenMeNguoiNuoiDuong(UPDATED_TEN_ME_NGUOI_NUOI_DUONG)
            .namSinh(UPDATED_NAM_SINH)
            .diaChiThuongTru(UPDATED_DIA_CHI_THUONG_TRU)
            .soCMNDPassport(UPDATED_SO_CMND_PASSPORT)
            .danToc(UPDATED_DAN_TOC)
            .gioSinh(UPDATED_GIO_SINH)
            .ngayThangNam(UPDATED_NGAY_THANG_NAM)
            .noiSinh(UPDATED_NOI_SINH)
            .soLanSinh(UPDATED_SO_LAN_SINH)
            .soConHienSong(UPDATED_SO_CON_HIEN_SONG)
            .soConTrongLanSinh(UPDATED_SO_CON_TRONG_LAN_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .canNang(UPDATED_CAN_NANG)
            .hienTrang(UPDATED_HIEN_TRANG)
            .nguoiDoDe(UPDATED_NGUOI_DO_DE)
            .tenDuDinh(UPDATED_TEN_DU_DINH)
            .qrCodeImage(UPDATED_QR_CODE_IMAGE)
            .statusApp(UPDATED_STATUS_APP)
            .userApprove(UPDATED_USER_APPROVE)
            .userCreate(UPDATED_USER_CREATE)
            .addressGCS(UPDATED_ADDRESS_GCS)
            .typeGCS(UPDATED_TYPE_GCS)
            .so(UPDATED_SO)
            .quyenSo(UPDATED_QUYEN_SO)
            .emailNDK(UPDATED_EMAIL_NDK)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .idUserCreate(UPDATED_ID_USER_CREATE)
            .publicKey(UPDATED_PUBLIC_KEY)
            .codeSoft(UPDATED_CODE_SOFT)
            .ngayThangNamCap(UPDATED_NGAY_THANG_NAM_CAP)
            .noiCap(UPDATED_NOI_CAP)
            .hoTenCha(UPDATED_HO_TEN_CHA)
            .pdfFile(UPDATED_PDF_FILE)
            .status(UPDATED_STATUS);

        restBlcGiayChungSinhMockMvc.perform(put("/api/blc-giay-chung-sinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlcGiayChungSinh)))
            .andExpect(status().isOk());

        // Validate the BlcGiayChungSinh in the database
        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeUpdate);
        BlcGiayChungSinh testBlcGiayChungSinh = blcGiayChungSinhList.get(blcGiayChungSinhList.size() - 1);
        assertThat(testBlcGiayChungSinh.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testBlcGiayChungSinh.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBlcGiayChungSinh.getTenMeNguoiNuoiDuong()).isEqualTo(UPDATED_TEN_ME_NGUOI_NUOI_DUONG);
        assertThat(testBlcGiayChungSinh.getNamSinh()).isEqualTo(UPDATED_NAM_SINH);
        assertThat(testBlcGiayChungSinh.getDiaChiThuongTru()).isEqualTo(UPDATED_DIA_CHI_THUONG_TRU);
        assertThat(testBlcGiayChungSinh.getSoCMNDPassport()).isEqualTo(UPDATED_SO_CMND_PASSPORT);
        assertThat(testBlcGiayChungSinh.getDanToc()).isEqualTo(UPDATED_DAN_TOC);
        assertThat(testBlcGiayChungSinh.getGioSinh()).isEqualTo(UPDATED_GIO_SINH);
        assertThat(testBlcGiayChungSinh.getNgayThangNam()).isEqualTo(UPDATED_NGAY_THANG_NAM);
        assertThat(testBlcGiayChungSinh.getNoiSinh()).isEqualTo(UPDATED_NOI_SINH);
        assertThat(testBlcGiayChungSinh.getSoLanSinh()).isEqualTo(UPDATED_SO_LAN_SINH);
        assertThat(testBlcGiayChungSinh.getSoConHienSong()).isEqualTo(UPDATED_SO_CON_HIEN_SONG);
        assertThat(testBlcGiayChungSinh.getSoConTrongLanSinh()).isEqualTo(UPDATED_SO_CON_TRONG_LAN_SINH);
        assertThat(testBlcGiayChungSinh.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testBlcGiayChungSinh.getCanNang()).isEqualTo(UPDATED_CAN_NANG);
        assertThat(testBlcGiayChungSinh.getHienTrang()).isEqualTo(UPDATED_HIEN_TRANG);
        assertThat(testBlcGiayChungSinh.getNguoiDoDe()).isEqualTo(UPDATED_NGUOI_DO_DE);
        assertThat(testBlcGiayChungSinh.getTenDuDinh()).isEqualTo(UPDATED_TEN_DU_DINH);
        assertThat(testBlcGiayChungSinh.getQrCodeImage()).isEqualTo(UPDATED_QR_CODE_IMAGE);
        assertThat(testBlcGiayChungSinh.getStatusApp()).isEqualTo(UPDATED_STATUS_APP);
        assertThat(testBlcGiayChungSinh.getUserApprove()).isEqualTo(UPDATED_USER_APPROVE);
        assertThat(testBlcGiayChungSinh.getUserCreate()).isEqualTo(UPDATED_USER_CREATE);
        assertThat(testBlcGiayChungSinh.getAddressGCS()).isEqualTo(UPDATED_ADDRESS_GCS);
        assertThat(testBlcGiayChungSinh.getTypeGCS()).isEqualTo(UPDATED_TYPE_GCS);
        assertThat(testBlcGiayChungSinh.getSo()).isEqualTo(UPDATED_SO);
        assertThat(testBlcGiayChungSinh.getQuyenSo()).isEqualTo(UPDATED_QUYEN_SO);
        assertThat(testBlcGiayChungSinh.getEmailNDK()).isEqualTo(UPDATED_EMAIL_NDK);
        assertThat(testBlcGiayChungSinh.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testBlcGiayChungSinh.getIdBenhVien()).isEqualTo(UPDATED_ID_BENH_VIEN);
        assertThat(testBlcGiayChungSinh.getTenBenhVien()).isEqualTo(UPDATED_TEN_BENH_VIEN);
        assertThat(testBlcGiayChungSinh.getIdUserCreate()).isEqualTo(UPDATED_ID_USER_CREATE);
        assertThat(testBlcGiayChungSinh.getPublicKey()).isEqualTo(UPDATED_PUBLIC_KEY);
        assertThat(testBlcGiayChungSinh.getCodeSoft()).isEqualTo(UPDATED_CODE_SOFT);
        assertThat(testBlcGiayChungSinh.getNgayThangNamCap()).isEqualTo(UPDATED_NGAY_THANG_NAM_CAP);
        assertThat(testBlcGiayChungSinh.getNoiCap()).isEqualTo(UPDATED_NOI_CAP);
        assertThat(testBlcGiayChungSinh.getHoTenCha()).isEqualTo(UPDATED_HO_TEN_CHA);
        assertThat(testBlcGiayChungSinh.getPdfFile()).isEqualTo(UPDATED_PDF_FILE);
        assertThat(testBlcGiayChungSinh.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBlcGiayChungSinh() throws Exception {
        int databaseSizeBeforeUpdate = blcGiayChungSinhRepository.findAll().size();

        // Create the BlcGiayChungSinh

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlcGiayChungSinhMockMvc.perform(put("/api/blc-giay-chung-sinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcGiayChungSinh)))
            .andExpect(status().isBadRequest());

        // Validate the BlcGiayChungSinh in the database
        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlcGiayChungSinh() throws Exception {
        // Initialize the database
        blcGiayChungSinhService.save(blcGiayChungSinh);

        int databaseSizeBeforeDelete = blcGiayChungSinhRepository.findAll().size();

        // Delete the blcGiayChungSinh
        restBlcGiayChungSinhMockMvc.perform(delete("/api/blc-giay-chung-sinhs/{id}", blcGiayChungSinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlcGiayChungSinh> blcGiayChungSinhList = blcGiayChungSinhRepository.findAll();
        assertThat(blcGiayChungSinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlcGiayChungSinh.class);
        BlcGiayChungSinh blcGiayChungSinh1 = new BlcGiayChungSinh();
        blcGiayChungSinh1.setId(1L);
        BlcGiayChungSinh blcGiayChungSinh2 = new BlcGiayChungSinh();
        blcGiayChungSinh2.setId(blcGiayChungSinh1.getId());
        assertThat(blcGiayChungSinh1).isEqualTo(blcGiayChungSinh2);
        blcGiayChungSinh2.setId(2L);
        assertThat(blcGiayChungSinh1).isNotEqualTo(blcGiayChungSinh2);
        blcGiayChungSinh1.setId(null);
        assertThat(blcGiayChungSinh1).isNotEqualTo(blcGiayChungSinh2);
    }
}
