package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.repository.BlcRegisterPrivateKeyRepository;
import vn.vnpt.ehealt.blockchain.service.BlcRegisterPrivateKeyService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.BlcRegisterPrivateKeyCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcRegisterPrivateKeyQueryService;

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
 * Integration tests for the {@link BlcRegisterPrivateKeyResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class BlcRegisterPrivateKeyResourceIT {

    private static final String DEFAULT_ID_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BENH_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BENH_VIEN = "BBBBBBBBBB";

    private static final String DEFAULT_UUID_ACCCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_UUID_ACCCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_UUID_GCSDB = "AAAAAAAAAA";
    private static final String UPDATED_UUID_GCSDB = "BBBBBBBBBB";

    private static final String DEFAULT_ID_USER_CREATE = "AAAAAAAAAA";
    private static final String UPDATED_ID_USER_CREATE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_SOFT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_SOFT = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLIC_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PUBLIC_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private BlcRegisterPrivateKeyRepository blcRegisterPrivateKeyRepository;

    @Autowired
    private BlcRegisterPrivateKeyService blcRegisterPrivateKeyService;

    @Autowired
    private BlcRegisterPrivateKeyQueryService blcRegisterPrivateKeyQueryService;

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

    private MockMvc restBlcRegisterPrivateKeyMockMvc;

    private BlcRegisterPrivateKey blcRegisterPrivateKey;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlcRegisterPrivateKeyResource blcRegisterPrivateKeyResource = new BlcRegisterPrivateKeyResource(blcRegisterPrivateKeyService, blcRegisterPrivateKeyQueryService);
        this.restBlcRegisterPrivateKeyMockMvc = MockMvcBuilders.standaloneSetup(blcRegisterPrivateKeyResource)
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
    public static BlcRegisterPrivateKey createEntity(EntityManager em) {
        BlcRegisterPrivateKey blcRegisterPrivateKey = new BlcRegisterPrivateKey()
            .idBenhVien(DEFAULT_ID_BENH_VIEN)
            .tenBenhVien(DEFAULT_TEN_BENH_VIEN)
            .uuidAcccount(DEFAULT_UUID_ACCCOUNT)
            .uuidGCSDB(DEFAULT_UUID_GCSDB)
            .idUserCreate(DEFAULT_ID_USER_CREATE)
            .codeSoft(DEFAULT_CODE_SOFT)
            .publicKey(DEFAULT_PUBLIC_KEY)
            .addressKey(DEFAULT_ADDRESS_KEY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .status(DEFAULT_STATUS);
        return blcRegisterPrivateKey;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlcRegisterPrivateKey createUpdatedEntity(EntityManager em) {
        BlcRegisterPrivateKey blcRegisterPrivateKey = new BlcRegisterPrivateKey()
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .uuidAcccount(UPDATED_UUID_ACCCOUNT)
            .uuidGCSDB(UPDATED_UUID_GCSDB)
            .idUserCreate(UPDATED_ID_USER_CREATE)
            .codeSoft(UPDATED_CODE_SOFT)
            .publicKey(UPDATED_PUBLIC_KEY)
            .addressKey(UPDATED_ADDRESS_KEY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .status(UPDATED_STATUS);
        return blcRegisterPrivateKey;
    }

    @BeforeEach
    public void initTest() {
        blcRegisterPrivateKey = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlcRegisterPrivateKey() throws Exception {
        int databaseSizeBeforeCreate = blcRegisterPrivateKeyRepository.findAll().size();

        // Create the BlcRegisterPrivateKey
        restBlcRegisterPrivateKeyMockMvc.perform(post("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcRegisterPrivateKey)))
            .andExpect(status().isCreated());

        // Validate the BlcRegisterPrivateKey in the database
        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeCreate + 1);
        BlcRegisterPrivateKey testBlcRegisterPrivateKey = blcRegisterPrivateKeyList.get(blcRegisterPrivateKeyList.size() - 1);
        assertThat(testBlcRegisterPrivateKey.getIdBenhVien()).isEqualTo(DEFAULT_ID_BENH_VIEN);
        assertThat(testBlcRegisterPrivateKey.getTenBenhVien()).isEqualTo(DEFAULT_TEN_BENH_VIEN);
        assertThat(testBlcRegisterPrivateKey.getUuidAcccount()).isEqualTo(DEFAULT_UUID_ACCCOUNT);
        assertThat(testBlcRegisterPrivateKey.getUuidGCSDB()).isEqualTo(DEFAULT_UUID_GCSDB);
        assertThat(testBlcRegisterPrivateKey.getIdUserCreate()).isEqualTo(DEFAULT_ID_USER_CREATE);
        assertThat(testBlcRegisterPrivateKey.getCodeSoft()).isEqualTo(DEFAULT_CODE_SOFT);
        assertThat(testBlcRegisterPrivateKey.getPublicKey()).isEqualTo(DEFAULT_PUBLIC_KEY);
        assertThat(testBlcRegisterPrivateKey.getAddressKey()).isEqualTo(DEFAULT_ADDRESS_KEY);
        assertThat(testBlcRegisterPrivateKey.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBlcRegisterPrivateKey.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testBlcRegisterPrivateKey.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBlcRegisterPrivateKeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blcRegisterPrivateKeyRepository.findAll().size();

        // Create the BlcRegisterPrivateKey with an existing ID
        blcRegisterPrivateKey.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlcRegisterPrivateKeyMockMvc.perform(post("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcRegisterPrivateKey)))
            .andExpect(status().isBadRequest());

        // Validate the BlcRegisterPrivateKey in the database
        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdBenhVienIsRequired() throws Exception {
        int databaseSizeBeforeTest = blcRegisterPrivateKeyRepository.findAll().size();
        // set the field null
        blcRegisterPrivateKey.setIdBenhVien(null);

        // Create the BlcRegisterPrivateKey, which fails.

        restBlcRegisterPrivateKeyMockMvc.perform(post("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcRegisterPrivateKey)))
            .andExpect(status().isBadRequest());

        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenBenhVienIsRequired() throws Exception {
        int databaseSizeBeforeTest = blcRegisterPrivateKeyRepository.findAll().size();
        // set the field null
        blcRegisterPrivateKey.setTenBenhVien(null);

        // Create the BlcRegisterPrivateKey, which fails.

        restBlcRegisterPrivateKeyMockMvc.perform(post("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcRegisterPrivateKey)))
            .andExpect(status().isBadRequest());

        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeys() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcRegisterPrivateKey.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN.toString())))
            .andExpect(jsonPath("$.[*].uuidAcccount").value(hasItem(DEFAULT_UUID_ACCCOUNT.toString())))
            .andExpect(jsonPath("$.[*].uuidGCSDB").value(hasItem(DEFAULT_UUID_GCSDB.toString())))
            .andExpect(jsonPath("$.[*].idUserCreate").value(hasItem(DEFAULT_ID_USER_CREATE.toString())))
            .andExpect(jsonPath("$.[*].codeSoft").value(hasItem(DEFAULT_CODE_SOFT.toString())))
            .andExpect(jsonPath("$.[*].publicKey").value(hasItem(DEFAULT_PUBLIC_KEY.toString())))
            .andExpect(jsonPath("$.[*].addressKey").value(hasItem(DEFAULT_ADDRESS_KEY.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getBlcRegisterPrivateKey() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get the blcRegisterPrivateKey
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys/{id}", blcRegisterPrivateKey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blcRegisterPrivateKey.getId().intValue()))
            .andExpect(jsonPath("$.idBenhVien").value(DEFAULT_ID_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.tenBenhVien").value(DEFAULT_TEN_BENH_VIEN.toString()))
            .andExpect(jsonPath("$.uuidAcccount").value(DEFAULT_UUID_ACCCOUNT.toString()))
            .andExpect(jsonPath("$.uuidGCSDB").value(DEFAULT_UUID_GCSDB.toString()))
            .andExpect(jsonPath("$.idUserCreate").value(DEFAULT_ID_USER_CREATE.toString()))
            .andExpect(jsonPath("$.codeSoft").value(DEFAULT_CODE_SOFT.toString()))
            .andExpect(jsonPath("$.publicKey").value(DEFAULT_PUBLIC_KEY.toString()))
            .andExpect(jsonPath("$.addressKey").value(DEFAULT_ADDRESS_KEY.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idBenhVien equals to DEFAULT_ID_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldBeFound("idBenhVien.equals=" + DEFAULT_ID_BENH_VIEN);

        // Get all the blcRegisterPrivateKeyList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idBenhVien.equals=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idBenhVien in DEFAULT_ID_BENH_VIEN or UPDATED_ID_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldBeFound("idBenhVien.in=" + DEFAULT_ID_BENH_VIEN + "," + UPDATED_ID_BENH_VIEN);

        // Get all the blcRegisterPrivateKeyList where idBenhVien equals to UPDATED_ID_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idBenhVien.in=" + UPDATED_ID_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idBenhVien is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("idBenhVien.specified=true");

        // Get all the blcRegisterPrivateKeyList where idBenhVien is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByTenBenhVienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where tenBenhVien equals to DEFAULT_TEN_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldBeFound("tenBenhVien.equals=" + DEFAULT_TEN_BENH_VIEN);

        // Get all the blcRegisterPrivateKeyList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldNotBeFound("tenBenhVien.equals=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByTenBenhVienIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where tenBenhVien in DEFAULT_TEN_BENH_VIEN or UPDATED_TEN_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldBeFound("tenBenhVien.in=" + DEFAULT_TEN_BENH_VIEN + "," + UPDATED_TEN_BENH_VIEN);

        // Get all the blcRegisterPrivateKeyList where tenBenhVien equals to UPDATED_TEN_BENH_VIEN
        defaultBlcRegisterPrivateKeyShouldNotBeFound("tenBenhVien.in=" + UPDATED_TEN_BENH_VIEN);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByTenBenhVienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where tenBenhVien is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("tenBenhVien.specified=true");

        // Get all the blcRegisterPrivateKeyList where tenBenhVien is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("tenBenhVien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidAcccountIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidAcccount equals to DEFAULT_UUID_ACCCOUNT
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidAcccount.equals=" + DEFAULT_UUID_ACCCOUNT);

        // Get all the blcRegisterPrivateKeyList where uuidAcccount equals to UPDATED_UUID_ACCCOUNT
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidAcccount.equals=" + UPDATED_UUID_ACCCOUNT);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidAcccountIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidAcccount in DEFAULT_UUID_ACCCOUNT or UPDATED_UUID_ACCCOUNT
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidAcccount.in=" + DEFAULT_UUID_ACCCOUNT + "," + UPDATED_UUID_ACCCOUNT);

        // Get all the blcRegisterPrivateKeyList where uuidAcccount equals to UPDATED_UUID_ACCCOUNT
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidAcccount.in=" + UPDATED_UUID_ACCCOUNT);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidAcccountIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidAcccount is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidAcccount.specified=true");

        // Get all the blcRegisterPrivateKeyList where uuidAcccount is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidAcccount.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidGCSDBIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB equals to DEFAULT_UUID_GCSDB
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidGCSDB.equals=" + DEFAULT_UUID_GCSDB);

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB equals to UPDATED_UUID_GCSDB
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidGCSDB.equals=" + UPDATED_UUID_GCSDB);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidGCSDBIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB in DEFAULT_UUID_GCSDB or UPDATED_UUID_GCSDB
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidGCSDB.in=" + DEFAULT_UUID_GCSDB + "," + UPDATED_UUID_GCSDB);

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB equals to UPDATED_UUID_GCSDB
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidGCSDB.in=" + UPDATED_UUID_GCSDB);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByUuidGCSDBIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("uuidGCSDB.specified=true");

        // Get all the blcRegisterPrivateKeyList where uuidGCSDB is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("uuidGCSDB.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdUserCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idUserCreate equals to DEFAULT_ID_USER_CREATE
        defaultBlcRegisterPrivateKeyShouldBeFound("idUserCreate.equals=" + DEFAULT_ID_USER_CREATE);

        // Get all the blcRegisterPrivateKeyList where idUserCreate equals to UPDATED_ID_USER_CREATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idUserCreate.equals=" + UPDATED_ID_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdUserCreateIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idUserCreate in DEFAULT_ID_USER_CREATE or UPDATED_ID_USER_CREATE
        defaultBlcRegisterPrivateKeyShouldBeFound("idUserCreate.in=" + DEFAULT_ID_USER_CREATE + "," + UPDATED_ID_USER_CREATE);

        // Get all the blcRegisterPrivateKeyList where idUserCreate equals to UPDATED_ID_USER_CREATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idUserCreate.in=" + UPDATED_ID_USER_CREATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByIdUserCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where idUserCreate is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("idUserCreate.specified=true");

        // Get all the blcRegisterPrivateKeyList where idUserCreate is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("idUserCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCodeSoftIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where codeSoft equals to DEFAULT_CODE_SOFT
        defaultBlcRegisterPrivateKeyShouldBeFound("codeSoft.equals=" + DEFAULT_CODE_SOFT);

        // Get all the blcRegisterPrivateKeyList where codeSoft equals to UPDATED_CODE_SOFT
        defaultBlcRegisterPrivateKeyShouldNotBeFound("codeSoft.equals=" + UPDATED_CODE_SOFT);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCodeSoftIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where codeSoft in DEFAULT_CODE_SOFT or UPDATED_CODE_SOFT
        defaultBlcRegisterPrivateKeyShouldBeFound("codeSoft.in=" + DEFAULT_CODE_SOFT + "," + UPDATED_CODE_SOFT);

        // Get all the blcRegisterPrivateKeyList where codeSoft equals to UPDATED_CODE_SOFT
        defaultBlcRegisterPrivateKeyShouldNotBeFound("codeSoft.in=" + UPDATED_CODE_SOFT);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCodeSoftIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where codeSoft is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("codeSoft.specified=true");

        // Get all the blcRegisterPrivateKeyList where codeSoft is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("codeSoft.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByPublicKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where publicKey equals to DEFAULT_PUBLIC_KEY
        defaultBlcRegisterPrivateKeyShouldBeFound("publicKey.equals=" + DEFAULT_PUBLIC_KEY);

        // Get all the blcRegisterPrivateKeyList where publicKey equals to UPDATED_PUBLIC_KEY
        defaultBlcRegisterPrivateKeyShouldNotBeFound("publicKey.equals=" + UPDATED_PUBLIC_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByPublicKeyIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where publicKey in DEFAULT_PUBLIC_KEY or UPDATED_PUBLIC_KEY
        defaultBlcRegisterPrivateKeyShouldBeFound("publicKey.in=" + DEFAULT_PUBLIC_KEY + "," + UPDATED_PUBLIC_KEY);

        // Get all the blcRegisterPrivateKeyList where publicKey equals to UPDATED_PUBLIC_KEY
        defaultBlcRegisterPrivateKeyShouldNotBeFound("publicKey.in=" + UPDATED_PUBLIC_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByPublicKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where publicKey is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("publicKey.specified=true");

        // Get all the blcRegisterPrivateKeyList where publicKey is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("publicKey.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByAddressKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where addressKey equals to DEFAULT_ADDRESS_KEY
        defaultBlcRegisterPrivateKeyShouldBeFound("addressKey.equals=" + DEFAULT_ADDRESS_KEY);

        // Get all the blcRegisterPrivateKeyList where addressKey equals to UPDATED_ADDRESS_KEY
        defaultBlcRegisterPrivateKeyShouldNotBeFound("addressKey.equals=" + UPDATED_ADDRESS_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByAddressKeyIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where addressKey in DEFAULT_ADDRESS_KEY or UPDATED_ADDRESS_KEY
        defaultBlcRegisterPrivateKeyShouldBeFound("addressKey.in=" + DEFAULT_ADDRESS_KEY + "," + UPDATED_ADDRESS_KEY);

        // Get all the blcRegisterPrivateKeyList where addressKey equals to UPDATED_ADDRESS_KEY
        defaultBlcRegisterPrivateKeyShouldNotBeFound("addressKey.in=" + UPDATED_ADDRESS_KEY);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByAddressKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where addressKey is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("addressKey.specified=true");

        // Get all the blcRegisterPrivateKeyList where addressKey is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("addressKey.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where createdDate equals to DEFAULT_CREATED_DATE
        defaultBlcRegisterPrivateKeyShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the blcRegisterPrivateKeyList where createdDate equals to UPDATED_CREATED_DATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultBlcRegisterPrivateKeyShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the blcRegisterPrivateKeyList where createdDate equals to UPDATED_CREATED_DATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where createdDate is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("createdDate.specified=true");

        // Get all the blcRegisterPrivateKeyList where createdDate is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByModifiedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where modifiedDate equals to DEFAULT_MODIFIED_DATE
        defaultBlcRegisterPrivateKeyShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

        // Get all the blcRegisterPrivateKeyList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByModifiedDateIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
        defaultBlcRegisterPrivateKeyShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

        // Get all the blcRegisterPrivateKeyList where modifiedDate equals to UPDATED_MODIFIED_DATE
        defaultBlcRegisterPrivateKeyShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByModifiedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where modifiedDate is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("modifiedDate.specified=true");

        // Get all the blcRegisterPrivateKeyList where modifiedDate is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("modifiedDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status equals to DEFAULT_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the blcRegisterPrivateKeyList where status equals to UPDATED_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the blcRegisterPrivateKeyList where status equals to UPDATED_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status is not null
        defaultBlcRegisterPrivateKeyShouldBeFound("status.specified=true");

        // Get all the blcRegisterPrivateKeyList where status is null
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status is greater than or equal to DEFAULT_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the blcRegisterPrivateKeyList where status is greater than or equal to UPDATED_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status is less than or equal to DEFAULT_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the blcRegisterPrivateKeyList where status is less than or equal to SMALLER_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status is less than DEFAULT_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the blcRegisterPrivateKeyList where status is less than UPDATED_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);

        // Get all the blcRegisterPrivateKeyList where status is greater than DEFAULT_STATUS
        defaultBlcRegisterPrivateKeyShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the blcRegisterPrivateKeyList where status is greater than SMALLER_STATUS
        defaultBlcRegisterPrivateKeyShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }


    @Test
    @Transactional
    public void getAllBlcRegisterPrivateKeysByBlcGiayChungSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);
        BlcGiayChungSinh blcGiayChungSinh = BlcGiayChungSinhResourceIT.createEntity(em);
        em.persist(blcGiayChungSinh);
        em.flush();
        blcRegisterPrivateKey.setBlcGiayChungSinh(blcGiayChungSinh);
        blcRegisterPrivateKeyRepository.saveAndFlush(blcRegisterPrivateKey);
        Long blcGiayChungSinhId = blcGiayChungSinh.getId();

        // Get all the blcRegisterPrivateKeyList where blcGiayChungSinh equals to blcGiayChungSinhId
        defaultBlcRegisterPrivateKeyShouldBeFound("blcGiayChungSinhId.equals=" + blcGiayChungSinhId);

        // Get all the blcRegisterPrivateKeyList where blcGiayChungSinh equals to blcGiayChungSinhId + 1
        defaultBlcRegisterPrivateKeyShouldNotBeFound("blcGiayChungSinhId.equals=" + (blcGiayChungSinhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBlcRegisterPrivateKeyShouldBeFound(String filter) throws Exception {
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcRegisterPrivateKey.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBenhVien").value(hasItem(DEFAULT_ID_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].tenBenhVien").value(hasItem(DEFAULT_TEN_BENH_VIEN)))
            .andExpect(jsonPath("$.[*].uuidAcccount").value(hasItem(DEFAULT_UUID_ACCCOUNT)))
            .andExpect(jsonPath("$.[*].uuidGCSDB").value(hasItem(DEFAULT_UUID_GCSDB)))
            .andExpect(jsonPath("$.[*].idUserCreate").value(hasItem(DEFAULT_ID_USER_CREATE)))
            .andExpect(jsonPath("$.[*].codeSoft").value(hasItem(DEFAULT_CODE_SOFT)))
            .andExpect(jsonPath("$.[*].publicKey").value(hasItem(DEFAULT_PUBLIC_KEY)))
            .andExpect(jsonPath("$.[*].addressKey").value(hasItem(DEFAULT_ADDRESS_KEY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBlcRegisterPrivateKeyShouldNotBeFound(String filter) throws Exception {
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlcRegisterPrivateKey() throws Exception {
        // Get the blcRegisterPrivateKey
        restBlcRegisterPrivateKeyMockMvc.perform(get("/api/blc-register-private-keys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlcRegisterPrivateKey() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyService.save(blcRegisterPrivateKey);

        int databaseSizeBeforeUpdate = blcRegisterPrivateKeyRepository.findAll().size();

        // Update the blcRegisterPrivateKey
        BlcRegisterPrivateKey updatedBlcRegisterPrivateKey = blcRegisterPrivateKeyRepository.findById(blcRegisterPrivateKey.getId()).get();
        // Disconnect from session so that the updates on updatedBlcRegisterPrivateKey are not directly saved in db
        em.detach(updatedBlcRegisterPrivateKey);
        updatedBlcRegisterPrivateKey
            .idBenhVien(UPDATED_ID_BENH_VIEN)
            .tenBenhVien(UPDATED_TEN_BENH_VIEN)
            .uuidAcccount(UPDATED_UUID_ACCCOUNT)
            .uuidGCSDB(UPDATED_UUID_GCSDB)
            .idUserCreate(UPDATED_ID_USER_CREATE)
            .codeSoft(UPDATED_CODE_SOFT)
            .publicKey(UPDATED_PUBLIC_KEY)
            .addressKey(UPDATED_ADDRESS_KEY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .status(UPDATED_STATUS);

        restBlcRegisterPrivateKeyMockMvc.perform(put("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlcRegisterPrivateKey)))
            .andExpect(status().isOk());

        // Validate the BlcRegisterPrivateKey in the database
        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeUpdate);
        BlcRegisterPrivateKey testBlcRegisterPrivateKey = blcRegisterPrivateKeyList.get(blcRegisterPrivateKeyList.size() - 1);
        assertThat(testBlcRegisterPrivateKey.getIdBenhVien()).isEqualTo(UPDATED_ID_BENH_VIEN);
        assertThat(testBlcRegisterPrivateKey.getTenBenhVien()).isEqualTo(UPDATED_TEN_BENH_VIEN);
        assertThat(testBlcRegisterPrivateKey.getUuidAcccount()).isEqualTo(UPDATED_UUID_ACCCOUNT);
        assertThat(testBlcRegisterPrivateKey.getUuidGCSDB()).isEqualTo(UPDATED_UUID_GCSDB);
        assertThat(testBlcRegisterPrivateKey.getIdUserCreate()).isEqualTo(UPDATED_ID_USER_CREATE);
        assertThat(testBlcRegisterPrivateKey.getCodeSoft()).isEqualTo(UPDATED_CODE_SOFT);
        assertThat(testBlcRegisterPrivateKey.getPublicKey()).isEqualTo(UPDATED_PUBLIC_KEY);
        assertThat(testBlcRegisterPrivateKey.getAddressKey()).isEqualTo(UPDATED_ADDRESS_KEY);
        assertThat(testBlcRegisterPrivateKey.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBlcRegisterPrivateKey.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testBlcRegisterPrivateKey.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBlcRegisterPrivateKey() throws Exception {
        int databaseSizeBeforeUpdate = blcRegisterPrivateKeyRepository.findAll().size();

        // Create the BlcRegisterPrivateKey

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlcRegisterPrivateKeyMockMvc.perform(put("/api/blc-register-private-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcRegisterPrivateKey)))
            .andExpect(status().isBadRequest());

        // Validate the BlcRegisterPrivateKey in the database
        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlcRegisterPrivateKey() throws Exception {
        // Initialize the database
        blcRegisterPrivateKeyService.save(blcRegisterPrivateKey);

        int databaseSizeBeforeDelete = blcRegisterPrivateKeyRepository.findAll().size();

        // Delete the blcRegisterPrivateKey
        restBlcRegisterPrivateKeyMockMvc.perform(delete("/api/blc-register-private-keys/{id}", blcRegisterPrivateKey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlcRegisterPrivateKey> blcRegisterPrivateKeyList = blcRegisterPrivateKeyRepository.findAll();
        assertThat(blcRegisterPrivateKeyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlcRegisterPrivateKey.class);
        BlcRegisterPrivateKey blcRegisterPrivateKey1 = new BlcRegisterPrivateKey();
        blcRegisterPrivateKey1.setId(1L);
        BlcRegisterPrivateKey blcRegisterPrivateKey2 = new BlcRegisterPrivateKey();
        blcRegisterPrivateKey2.setId(blcRegisterPrivateKey1.getId());
        assertThat(blcRegisterPrivateKey1).isEqualTo(blcRegisterPrivateKey2);
        blcRegisterPrivateKey2.setId(2L);
        assertThat(blcRegisterPrivateKey1).isNotEqualTo(blcRegisterPrivateKey2);
        blcRegisterPrivateKey1.setId(null);
        assertThat(blcRegisterPrivateKey1).isNotEqualTo(blcRegisterPrivateKey2);
    }
}
