package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin;
import vn.vnpt.ehealt.blockchain.repository.AdmUserTokenLoginRepository;
import vn.vnpt.ehealt.blockchain.service.AdmUserTokenLoginService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.AdmUserTokenLoginCriteria;
import vn.vnpt.ehealt.blockchain.service.AdmUserTokenLoginQueryService;

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
 * Integration tests for the {@link AdmUserTokenLoginResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class AdmUserTokenLoginResourceIT {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_CREATED_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_EXPIRATION_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_LOGIN_IP = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_IP = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_HE_THONG = "AAAAAAAAAA";
    private static final String UPDATED_HE_THONG = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final Integer SMALLER_STATUS = 1 - 1;

    @Autowired
    private AdmUserTokenLoginRepository admUserTokenLoginRepository;

    @Autowired
    private AdmUserTokenLoginService admUserTokenLoginService;

    @Autowired
    private AdmUserTokenLoginQueryService admUserTokenLoginQueryService;

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

    private MockMvc restAdmUserTokenLoginMockMvc;

    private AdmUserTokenLogin admUserTokenLogin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdmUserTokenLoginResource admUserTokenLoginResource = new AdmUserTokenLoginResource(admUserTokenLoginService, admUserTokenLoginQueryService);
        this.restAdmUserTokenLoginMockMvc = MockMvcBuilders.standaloneSetup(admUserTokenLoginResource)
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
    public static AdmUserTokenLogin createEntity(EntityManager em) {
        AdmUserTokenLogin admUserTokenLogin = new AdmUserTokenLogin()
            .userName(DEFAULT_USER_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .expirationDate(DEFAULT_EXPIRATION_DATE)
            .loginIP(DEFAULT_LOGIN_IP)
            .active(DEFAULT_ACTIVE)
            .heThong(DEFAULT_HE_THONG)
            .tokenType(DEFAULT_TOKEN_TYPE)
            .status(DEFAULT_STATUS);
        return admUserTokenLogin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdmUserTokenLogin createUpdatedEntity(EntityManager em) {
        AdmUserTokenLogin admUserTokenLogin = new AdmUserTokenLogin()
            .userName(UPDATED_USER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .loginIP(UPDATED_LOGIN_IP)
            .active(UPDATED_ACTIVE)
            .heThong(UPDATED_HE_THONG)
            .tokenType(UPDATED_TOKEN_TYPE)
            .status(UPDATED_STATUS);
        return admUserTokenLogin;
    }

    @BeforeEach
    public void initTest() {
        admUserTokenLogin = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdmUserTokenLogin() throws Exception {
        int databaseSizeBeforeCreate = admUserTokenLoginRepository.findAll().size();

        // Create the AdmUserTokenLogin
        restAdmUserTokenLoginMockMvc.perform(post("/api/adm-user-token-logins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admUserTokenLogin)))
            .andExpect(status().isCreated());

        // Validate the AdmUserTokenLogin in the database
        List<AdmUserTokenLogin> admUserTokenLoginList = admUserTokenLoginRepository.findAll();
        assertThat(admUserTokenLoginList).hasSize(databaseSizeBeforeCreate + 1);
        AdmUserTokenLogin testAdmUserTokenLogin = admUserTokenLoginList.get(admUserTokenLoginList.size() - 1);
        assertThat(testAdmUserTokenLogin.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testAdmUserTokenLogin.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testAdmUserTokenLogin.getExpirationDate()).isEqualTo(DEFAULT_EXPIRATION_DATE);
        assertThat(testAdmUserTokenLogin.getLoginIP()).isEqualTo(DEFAULT_LOGIN_IP);
        assertThat(testAdmUserTokenLogin.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testAdmUserTokenLogin.getHeThong()).isEqualTo(DEFAULT_HE_THONG);
        assertThat(testAdmUserTokenLogin.getTokenType()).isEqualTo(DEFAULT_TOKEN_TYPE);
        assertThat(testAdmUserTokenLogin.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAdmUserTokenLoginWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = admUserTokenLoginRepository.findAll().size();

        // Create the AdmUserTokenLogin with an existing ID
        admUserTokenLogin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdmUserTokenLoginMockMvc.perform(post("/api/adm-user-token-logins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admUserTokenLogin)))
            .andExpect(status().isBadRequest());

        // Validate the AdmUserTokenLogin in the database
        List<AdmUserTokenLogin> admUserTokenLoginList = admUserTokenLoginRepository.findAll();
        assertThat(admUserTokenLoginList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdmUserTokenLogins() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admUserTokenLogin.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].loginIP").value(hasItem(DEFAULT_LOGIN_IP.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())))
            .andExpect(jsonPath("$.[*].heThong").value(hasItem(DEFAULT_HE_THONG.toString())))
            .andExpect(jsonPath("$.[*].tokenType").value(hasItem(DEFAULT_TOKEN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getAdmUserTokenLogin() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get the admUserTokenLogin
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins/{id}", admUserTokenLogin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(admUserTokenLogin.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.loginIP").value(DEFAULT_LOGIN_IP.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()))
            .andExpect(jsonPath("$.heThong").value(DEFAULT_HE_THONG.toString()))
            .andExpect(jsonPath("$.tokenType").value(DEFAULT_TOKEN_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByUserNameIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where userName equals to DEFAULT_USER_NAME
        defaultAdmUserTokenLoginShouldBeFound("userName.equals=" + DEFAULT_USER_NAME);

        // Get all the admUserTokenLoginList where userName equals to UPDATED_USER_NAME
        defaultAdmUserTokenLoginShouldNotBeFound("userName.equals=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByUserNameIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where userName in DEFAULT_USER_NAME or UPDATED_USER_NAME
        defaultAdmUserTokenLoginShouldBeFound("userName.in=" + DEFAULT_USER_NAME + "," + UPDATED_USER_NAME);

        // Get all the admUserTokenLoginList where userName equals to UPDATED_USER_NAME
        defaultAdmUserTokenLoginShouldNotBeFound("userName.in=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByUserNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where userName is not null
        defaultAdmUserTokenLoginShouldBeFound("userName.specified=true");

        // Get all the admUserTokenLoginList where userName is null
        defaultAdmUserTokenLoginShouldNotBeFound("userName.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate equals to DEFAULT_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate equals to UPDATED_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate equals to UPDATED_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate is not null
        defaultAdmUserTokenLoginShouldBeFound("createdDate.specified=true");

        // Get all the admUserTokenLoginList where createdDate is null
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate is greater than or equal to UPDATED_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate is less than or equal to DEFAULT_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate is less than or equal to SMALLER_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsLessThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate is less than DEFAULT_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.lessThan=" + DEFAULT_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate is less than UPDATED_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.lessThan=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByCreatedDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where createdDate is greater than DEFAULT_CREATED_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("createdDate.greaterThan=" + DEFAULT_CREATED_DATE);

        // Get all the admUserTokenLoginList where createdDate is greater than SMALLER_CREATED_DATE
        defaultAdmUserTokenLoginShouldBeFound("createdDate.greaterThan=" + SMALLER_CREATED_DATE);
    }


    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate equals to DEFAULT_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.equals=" + DEFAULT_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate equals to UPDATED_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.equals=" + UPDATED_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate in DEFAULT_EXPIRATION_DATE or UPDATED_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.in=" + DEFAULT_EXPIRATION_DATE + "," + UPDATED_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate equals to UPDATED_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.in=" + UPDATED_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate is not null
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.specified=true");

        // Get all the admUserTokenLoginList where expirationDate is null
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate is greater than or equal to DEFAULT_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.greaterThanOrEqual=" + DEFAULT_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate is greater than or equal to UPDATED_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.greaterThanOrEqual=" + UPDATED_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate is less than or equal to DEFAULT_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.lessThanOrEqual=" + DEFAULT_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate is less than or equal to SMALLER_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.lessThanOrEqual=" + SMALLER_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsLessThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate is less than DEFAULT_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.lessThan=" + DEFAULT_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate is less than UPDATED_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.lessThan=" + UPDATED_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByExpirationDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where expirationDate is greater than DEFAULT_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldNotBeFound("expirationDate.greaterThan=" + DEFAULT_EXPIRATION_DATE);

        // Get all the admUserTokenLoginList where expirationDate is greater than SMALLER_EXPIRATION_DATE
        defaultAdmUserTokenLoginShouldBeFound("expirationDate.greaterThan=" + SMALLER_EXPIRATION_DATE);
    }


    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByLoginIPIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where loginIP equals to DEFAULT_LOGIN_IP
        defaultAdmUserTokenLoginShouldBeFound("loginIP.equals=" + DEFAULT_LOGIN_IP);

        // Get all the admUserTokenLoginList where loginIP equals to UPDATED_LOGIN_IP
        defaultAdmUserTokenLoginShouldNotBeFound("loginIP.equals=" + UPDATED_LOGIN_IP);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByLoginIPIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where loginIP in DEFAULT_LOGIN_IP or UPDATED_LOGIN_IP
        defaultAdmUserTokenLoginShouldBeFound("loginIP.in=" + DEFAULT_LOGIN_IP + "," + UPDATED_LOGIN_IP);

        // Get all the admUserTokenLoginList where loginIP equals to UPDATED_LOGIN_IP
        defaultAdmUserTokenLoginShouldNotBeFound("loginIP.in=" + UPDATED_LOGIN_IP);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByLoginIPIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where loginIP is not null
        defaultAdmUserTokenLoginShouldBeFound("loginIP.specified=true");

        // Get all the admUserTokenLoginList where loginIP is null
        defaultAdmUserTokenLoginShouldNotBeFound("loginIP.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where active equals to DEFAULT_ACTIVE
        defaultAdmUserTokenLoginShouldBeFound("active.equals=" + DEFAULT_ACTIVE);

        // Get all the admUserTokenLoginList where active equals to UPDATED_ACTIVE
        defaultAdmUserTokenLoginShouldNotBeFound("active.equals=" + UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByActiveIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where active in DEFAULT_ACTIVE or UPDATED_ACTIVE
        defaultAdmUserTokenLoginShouldBeFound("active.in=" + DEFAULT_ACTIVE + "," + UPDATED_ACTIVE);

        // Get all the admUserTokenLoginList where active equals to UPDATED_ACTIVE
        defaultAdmUserTokenLoginShouldNotBeFound("active.in=" + UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where active is not null
        defaultAdmUserTokenLoginShouldBeFound("active.specified=true");

        // Get all the admUserTokenLoginList where active is null
        defaultAdmUserTokenLoginShouldNotBeFound("active.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByHeThongIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where heThong equals to DEFAULT_HE_THONG
        defaultAdmUserTokenLoginShouldBeFound("heThong.equals=" + DEFAULT_HE_THONG);

        // Get all the admUserTokenLoginList where heThong equals to UPDATED_HE_THONG
        defaultAdmUserTokenLoginShouldNotBeFound("heThong.equals=" + UPDATED_HE_THONG);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByHeThongIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where heThong in DEFAULT_HE_THONG or UPDATED_HE_THONG
        defaultAdmUserTokenLoginShouldBeFound("heThong.in=" + DEFAULT_HE_THONG + "," + UPDATED_HE_THONG);

        // Get all the admUserTokenLoginList where heThong equals to UPDATED_HE_THONG
        defaultAdmUserTokenLoginShouldNotBeFound("heThong.in=" + UPDATED_HE_THONG);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByHeThongIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where heThong is not null
        defaultAdmUserTokenLoginShouldBeFound("heThong.specified=true");

        // Get all the admUserTokenLoginList where heThong is null
        defaultAdmUserTokenLoginShouldNotBeFound("heThong.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByTokenTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where tokenType equals to DEFAULT_TOKEN_TYPE
        defaultAdmUserTokenLoginShouldBeFound("tokenType.equals=" + DEFAULT_TOKEN_TYPE);

        // Get all the admUserTokenLoginList where tokenType equals to UPDATED_TOKEN_TYPE
        defaultAdmUserTokenLoginShouldNotBeFound("tokenType.equals=" + UPDATED_TOKEN_TYPE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByTokenTypeIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where tokenType in DEFAULT_TOKEN_TYPE or UPDATED_TOKEN_TYPE
        defaultAdmUserTokenLoginShouldBeFound("tokenType.in=" + DEFAULT_TOKEN_TYPE + "," + UPDATED_TOKEN_TYPE);

        // Get all the admUserTokenLoginList where tokenType equals to UPDATED_TOKEN_TYPE
        defaultAdmUserTokenLoginShouldNotBeFound("tokenType.in=" + UPDATED_TOKEN_TYPE);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByTokenTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where tokenType is not null
        defaultAdmUserTokenLoginShouldBeFound("tokenType.specified=true");

        // Get all the admUserTokenLoginList where tokenType is null
        defaultAdmUserTokenLoginShouldNotBeFound("tokenType.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status equals to DEFAULT_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the admUserTokenLoginList where status equals to UPDATED_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the admUserTokenLoginList where status equals to UPDATED_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status is not null
        defaultAdmUserTokenLoginShouldBeFound("status.specified=true");

        // Get all the admUserTokenLoginList where status is null
        defaultAdmUserTokenLoginShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status is greater than or equal to DEFAULT_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.greaterThanOrEqual=" + DEFAULT_STATUS);

        // Get all the admUserTokenLoginList where status is greater than or equal to UPDATED_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.greaterThanOrEqual=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status is less than or equal to DEFAULT_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.lessThanOrEqual=" + DEFAULT_STATUS);

        // Get all the admUserTokenLoginList where status is less than or equal to SMALLER_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.lessThanOrEqual=" + SMALLER_STATUS);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status is less than DEFAULT_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.lessThan=" + DEFAULT_STATUS);

        // Get all the admUserTokenLoginList where status is less than UPDATED_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.lessThan=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllAdmUserTokenLoginsByStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        admUserTokenLoginRepository.saveAndFlush(admUserTokenLogin);

        // Get all the admUserTokenLoginList where status is greater than DEFAULT_STATUS
        defaultAdmUserTokenLoginShouldNotBeFound("status.greaterThan=" + DEFAULT_STATUS);

        // Get all the admUserTokenLoginList where status is greater than SMALLER_STATUS
        defaultAdmUserTokenLoginShouldBeFound("status.greaterThan=" + SMALLER_STATUS);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAdmUserTokenLoginShouldBeFound(String filter) throws Exception {
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(admUserTokenLogin.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].loginIP").value(hasItem(DEFAULT_LOGIN_IP)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)))
            .andExpect(jsonPath("$.[*].heThong").value(hasItem(DEFAULT_HE_THONG)))
            .andExpect(jsonPath("$.[*].tokenType").value(hasItem(DEFAULT_TOKEN_TYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));

        // Check, that the count call also returns 1
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAdmUserTokenLoginShouldNotBeFound(String filter) throws Exception {
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAdmUserTokenLogin() throws Exception {
        // Get the admUserTokenLogin
        restAdmUserTokenLoginMockMvc.perform(get("/api/adm-user-token-logins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdmUserTokenLogin() throws Exception {
        // Initialize the database
        admUserTokenLoginService.save(admUserTokenLogin);

        int databaseSizeBeforeUpdate = admUserTokenLoginRepository.findAll().size();

        // Update the admUserTokenLogin
        AdmUserTokenLogin updatedAdmUserTokenLogin = admUserTokenLoginRepository.findById(admUserTokenLogin.getId()).get();
        // Disconnect from session so that the updates on updatedAdmUserTokenLogin are not directly saved in db
        em.detach(updatedAdmUserTokenLogin);
        updatedAdmUserTokenLogin
            .userName(UPDATED_USER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE)
            .loginIP(UPDATED_LOGIN_IP)
            .active(UPDATED_ACTIVE)
            .heThong(UPDATED_HE_THONG)
            .tokenType(UPDATED_TOKEN_TYPE)
            .status(UPDATED_STATUS);

        restAdmUserTokenLoginMockMvc.perform(put("/api/adm-user-token-logins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdmUserTokenLogin)))
            .andExpect(status().isOk());

        // Validate the AdmUserTokenLogin in the database
        List<AdmUserTokenLogin> admUserTokenLoginList = admUserTokenLoginRepository.findAll();
        assertThat(admUserTokenLoginList).hasSize(databaseSizeBeforeUpdate);
        AdmUserTokenLogin testAdmUserTokenLogin = admUserTokenLoginList.get(admUserTokenLoginList.size() - 1);
        assertThat(testAdmUserTokenLogin.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testAdmUserTokenLogin.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAdmUserTokenLogin.getExpirationDate()).isEqualTo(UPDATED_EXPIRATION_DATE);
        assertThat(testAdmUserTokenLogin.getLoginIP()).isEqualTo(UPDATED_LOGIN_IP);
        assertThat(testAdmUserTokenLogin.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testAdmUserTokenLogin.getHeThong()).isEqualTo(UPDATED_HE_THONG);
        assertThat(testAdmUserTokenLogin.getTokenType()).isEqualTo(UPDATED_TOKEN_TYPE);
        assertThat(testAdmUserTokenLogin.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAdmUserTokenLogin() throws Exception {
        int databaseSizeBeforeUpdate = admUserTokenLoginRepository.findAll().size();

        // Create the AdmUserTokenLogin

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdmUserTokenLoginMockMvc.perform(put("/api/adm-user-token-logins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(admUserTokenLogin)))
            .andExpect(status().isBadRequest());

        // Validate the AdmUserTokenLogin in the database
        List<AdmUserTokenLogin> admUserTokenLoginList = admUserTokenLoginRepository.findAll();
        assertThat(admUserTokenLoginList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdmUserTokenLogin() throws Exception {
        // Initialize the database
        admUserTokenLoginService.save(admUserTokenLogin);

        int databaseSizeBeforeDelete = admUserTokenLoginRepository.findAll().size();

        // Delete the admUserTokenLogin
        restAdmUserTokenLoginMockMvc.perform(delete("/api/adm-user-token-logins/{id}", admUserTokenLogin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdmUserTokenLogin> admUserTokenLoginList = admUserTokenLoginRepository.findAll();
        assertThat(admUserTokenLoginList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmUserTokenLogin.class);
        AdmUserTokenLogin admUserTokenLogin1 = new AdmUserTokenLogin();
        admUserTokenLogin1.setId(1L);
        AdmUserTokenLogin admUserTokenLogin2 = new AdmUserTokenLogin();
        admUserTokenLogin2.setId(admUserTokenLogin1.getId());
        assertThat(admUserTokenLogin1).isEqualTo(admUserTokenLogin2);
        admUserTokenLogin2.setId(2L);
        assertThat(admUserTokenLogin1).isNotEqualTo(admUserTokenLogin2);
        admUserTokenLogin1.setId(null);
        assertThat(admUserTokenLogin1).isNotEqualTo(admUserTokenLogin2);
    }
}
