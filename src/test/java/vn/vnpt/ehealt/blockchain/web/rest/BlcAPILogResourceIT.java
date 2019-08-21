package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.GcsBlockchainApplicationApp;
import vn.vnpt.ehealt.blockchain.config.TestSecurityConfiguration;
import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.repository.BlcAPILogRepository;
import vn.vnpt.ehealt.blockchain.service.BlcAPILogService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.ExceptionTranslator;
import vn.vnpt.ehealt.blockchain.service.dto.BlcAPILogCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcAPILogQueryService;

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
 * Integration tests for the {@link BlcAPILogResource} REST controller.
 */
@SpringBootTest(classes = {GcsBlockchainApplicationApp.class, TestSecurityConfiguration.class})
public class BlcAPILogResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SO_GCS = "AAAAAAAAAA";
    private static final String UPDATED_SO_GCS = "BBBBBBBBBB";

    private static final String DEFAULT_SO_SOGCS = "AAAAAAAAAA";
    private static final String UPDATED_SO_SOGCS = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_MESSAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LOADED_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOADED_TIME = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LOADED_TIME = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ID_BENHVIEN = "AAAAAAAAAA";
    private static final String UPDATED_ID_BENHVIEN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BENHVIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BENHVIEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROCESS_TIME = 1;
    private static final Integer UPDATED_PROCESS_TIME = 2;
    private static final Integer SMALLER_PROCESS_TIME = 1 - 1;

    private static final Integer DEFAULT_RESPONSE_TIME = 1;
    private static final Integer UPDATED_RESPONSE_TIME = 2;
    private static final Integer SMALLER_RESPONSE_TIME = 1 - 1;

    @Autowired
    private BlcAPILogRepository blcAPILogRepository;

    @Autowired
    private BlcAPILogService blcAPILogService;

    @Autowired
    private BlcAPILogQueryService blcAPILogQueryService;

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

    private MockMvc restBlcAPILogMockMvc;

    private BlcAPILog blcAPILog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlcAPILogResource blcAPILogResource = new BlcAPILogResource(blcAPILogService, blcAPILogQueryService);
        this.restBlcAPILogMockMvc = MockMvcBuilders.standaloneSetup(blcAPILogResource)
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
    public static BlcAPILog createEntity(EntityManager em) {
        BlcAPILog blcAPILog = new BlcAPILog()
            .uuid(DEFAULT_UUID)
            .status(DEFAULT_STATUS)
            .messageStatus(DEFAULT_MESSAGE_STATUS)
            .methodName(DEFAULT_METHOD_NAME)
            .soGCS(DEFAULT_SO_GCS)
            .soSOGCS(DEFAULT_SO_SOGCS)
            .requestContent(DEFAULT_REQUEST_CONTENT)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .loadedTime(DEFAULT_LOADED_TIME)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .idBenhvien(DEFAULT_ID_BENHVIEN)
            .tenBenhvien(DEFAULT_TEN_BENHVIEN)
            .processTime(DEFAULT_PROCESS_TIME)
            .responseTime(DEFAULT_RESPONSE_TIME);
        return blcAPILog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlcAPILog createUpdatedEntity(EntityManager em) {
        BlcAPILog blcAPILog = new BlcAPILog()
            .uuid(UPDATED_UUID)
            .status(UPDATED_STATUS)
            .messageStatus(UPDATED_MESSAGE_STATUS)
            .methodName(UPDATED_METHOD_NAME)
            .soGCS(UPDATED_SO_GCS)
            .soSOGCS(UPDATED_SO_SOGCS)
            .requestContent(UPDATED_REQUEST_CONTENT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .loadedTime(UPDATED_LOADED_TIME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .idBenhvien(UPDATED_ID_BENHVIEN)
            .tenBenhvien(UPDATED_TEN_BENHVIEN)
            .processTime(UPDATED_PROCESS_TIME)
            .responseTime(UPDATED_RESPONSE_TIME);
        return blcAPILog;
    }

    @BeforeEach
    public void initTest() {
        blcAPILog = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlcAPILog() throws Exception {
        int databaseSizeBeforeCreate = blcAPILogRepository.findAll().size();

        // Create the BlcAPILog
        restBlcAPILogMockMvc.perform(post("/api/blc-api-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcAPILog)))
            .andExpect(status().isCreated());

        // Validate the BlcAPILog in the database
        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeCreate + 1);
        BlcAPILog testBlcAPILog = blcAPILogList.get(blcAPILogList.size() - 1);
        assertThat(testBlcAPILog.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testBlcAPILog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBlcAPILog.getMessageStatus()).isEqualTo(DEFAULT_MESSAGE_STATUS);
        assertThat(testBlcAPILog.getMethodName()).isEqualTo(DEFAULT_METHOD_NAME);
        assertThat(testBlcAPILog.getSoGCS()).isEqualTo(DEFAULT_SO_GCS);
        assertThat(testBlcAPILog.getSoSOGCS()).isEqualTo(DEFAULT_SO_SOGCS);
        assertThat(testBlcAPILog.getRequestContent()).isEqualTo(DEFAULT_REQUEST_CONTENT);
        assertThat(testBlcAPILog.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testBlcAPILog.getLoadedTime()).isEqualTo(DEFAULT_LOADED_TIME);
        assertThat(testBlcAPILog.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testBlcAPILog.getIdBenhvien()).isEqualTo(DEFAULT_ID_BENHVIEN);
        assertThat(testBlcAPILog.getTenBenhvien()).isEqualTo(DEFAULT_TEN_BENHVIEN);
        assertThat(testBlcAPILog.getProcessTime()).isEqualTo(DEFAULT_PROCESS_TIME);
        assertThat(testBlcAPILog.getResponseTime()).isEqualTo(DEFAULT_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void createBlcAPILogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blcAPILogRepository.findAll().size();

        // Create the BlcAPILog with an existing ID
        blcAPILog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlcAPILogMockMvc.perform(post("/api/blc-api-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcAPILog)))
            .andExpect(status().isBadRequest());

        // Validate the BlcAPILog in the database
        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = blcAPILogRepository.findAll().size();
        // set the field null
        blcAPILog.setUuid(null);

        // Create the BlcAPILog, which fails.

        restBlcAPILogMockMvc.perform(post("/api/blc-api-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcAPILog)))
            .andExpect(status().isBadRequest());

        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogs() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcAPILog.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].messageStatus").value(hasItem(DEFAULT_MESSAGE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].methodName").value(hasItem(DEFAULT_METHOD_NAME.toString())))
            .andExpect(jsonPath("$.[*].soGCS").value(hasItem(DEFAULT_SO_GCS.toString())))
            .andExpect(jsonPath("$.[*].soSOGCS").value(hasItem(DEFAULT_SO_SOGCS.toString())))
            .andExpect(jsonPath("$.[*].requestContent").value(hasItem(DEFAULT_REQUEST_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].loadedTime").value(hasItem(DEFAULT_LOADED_TIME.toString())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].idBenhvien").value(hasItem(DEFAULT_ID_BENHVIEN.toString())))
            .andExpect(jsonPath("$.[*].tenBenhvien").value(hasItem(DEFAULT_TEN_BENHVIEN.toString())))
            .andExpect(jsonPath("$.[*].processTime").value(hasItem(DEFAULT_PROCESS_TIME)))
            .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME)));
    }
    
    @Test
    @Transactional
    public void getBlcAPILog() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get the blcAPILog
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs/{id}", blcAPILog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blcAPILog.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.messageStatus").value(DEFAULT_MESSAGE_STATUS.toString()))
            .andExpect(jsonPath("$.methodName").value(DEFAULT_METHOD_NAME.toString()))
            .andExpect(jsonPath("$.soGCS").value(DEFAULT_SO_GCS.toString()))
            .andExpect(jsonPath("$.soSOGCS").value(DEFAULT_SO_SOGCS.toString()))
            .andExpect(jsonPath("$.requestContent").value(DEFAULT_REQUEST_CONTENT.toString()))
            .andExpect(jsonPath("$.errorMessage").value(DEFAULT_ERROR_MESSAGE.toString()))
            .andExpect(jsonPath("$.loadedTime").value(DEFAULT_LOADED_TIME.toString()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.idBenhvien").value(DEFAULT_ID_BENHVIEN.toString()))
            .andExpect(jsonPath("$.tenBenhvien").value(DEFAULT_TEN_BENHVIEN.toString()))
            .andExpect(jsonPath("$.processTime").value(DEFAULT_PROCESS_TIME))
            .andExpect(jsonPath("$.responseTime").value(DEFAULT_RESPONSE_TIME));
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where uuid equals to DEFAULT_UUID
        defaultBlcAPILogShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the blcAPILogList where uuid equals to UPDATED_UUID
        defaultBlcAPILogShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultBlcAPILogShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the blcAPILogList where uuid equals to UPDATED_UUID
        defaultBlcAPILogShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where uuid is not null
        defaultBlcAPILogShouldBeFound("uuid.specified=true");

        // Get all the blcAPILogList where uuid is null
        defaultBlcAPILogShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where status equals to DEFAULT_STATUS
        defaultBlcAPILogShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the blcAPILogList where status equals to UPDATED_STATUS
        defaultBlcAPILogShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBlcAPILogShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the blcAPILogList where status equals to UPDATED_STATUS
        defaultBlcAPILogShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where status is not null
        defaultBlcAPILogShouldBeFound("status.specified=true");

        // Get all the blcAPILogList where status is null
        defaultBlcAPILogShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMessageStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where messageStatus equals to DEFAULT_MESSAGE_STATUS
        defaultBlcAPILogShouldBeFound("messageStatus.equals=" + DEFAULT_MESSAGE_STATUS);

        // Get all the blcAPILogList where messageStatus equals to UPDATED_MESSAGE_STATUS
        defaultBlcAPILogShouldNotBeFound("messageStatus.equals=" + UPDATED_MESSAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMessageStatusIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where messageStatus in DEFAULT_MESSAGE_STATUS or UPDATED_MESSAGE_STATUS
        defaultBlcAPILogShouldBeFound("messageStatus.in=" + DEFAULT_MESSAGE_STATUS + "," + UPDATED_MESSAGE_STATUS);

        // Get all the blcAPILogList where messageStatus equals to UPDATED_MESSAGE_STATUS
        defaultBlcAPILogShouldNotBeFound("messageStatus.in=" + UPDATED_MESSAGE_STATUS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMessageStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where messageStatus is not null
        defaultBlcAPILogShouldBeFound("messageStatus.specified=true");

        // Get all the blcAPILogList where messageStatus is null
        defaultBlcAPILogShouldNotBeFound("messageStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMethodNameIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where methodName equals to DEFAULT_METHOD_NAME
        defaultBlcAPILogShouldBeFound("methodName.equals=" + DEFAULT_METHOD_NAME);

        // Get all the blcAPILogList where methodName equals to UPDATED_METHOD_NAME
        defaultBlcAPILogShouldNotBeFound("methodName.equals=" + UPDATED_METHOD_NAME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMethodNameIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where methodName in DEFAULT_METHOD_NAME or UPDATED_METHOD_NAME
        defaultBlcAPILogShouldBeFound("methodName.in=" + DEFAULT_METHOD_NAME + "," + UPDATED_METHOD_NAME);

        // Get all the blcAPILogList where methodName equals to UPDATED_METHOD_NAME
        defaultBlcAPILogShouldNotBeFound("methodName.in=" + UPDATED_METHOD_NAME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByMethodNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where methodName is not null
        defaultBlcAPILogShouldBeFound("methodName.specified=true");

        // Get all the blcAPILogList where methodName is null
        defaultBlcAPILogShouldNotBeFound("methodName.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoGCSIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soGCS equals to DEFAULT_SO_GCS
        defaultBlcAPILogShouldBeFound("soGCS.equals=" + DEFAULT_SO_GCS);

        // Get all the blcAPILogList where soGCS equals to UPDATED_SO_GCS
        defaultBlcAPILogShouldNotBeFound("soGCS.equals=" + UPDATED_SO_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoGCSIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soGCS in DEFAULT_SO_GCS or UPDATED_SO_GCS
        defaultBlcAPILogShouldBeFound("soGCS.in=" + DEFAULT_SO_GCS + "," + UPDATED_SO_GCS);

        // Get all the blcAPILogList where soGCS equals to UPDATED_SO_GCS
        defaultBlcAPILogShouldNotBeFound("soGCS.in=" + UPDATED_SO_GCS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoGCSIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soGCS is not null
        defaultBlcAPILogShouldBeFound("soGCS.specified=true");

        // Get all the blcAPILogList where soGCS is null
        defaultBlcAPILogShouldNotBeFound("soGCS.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoSOGCSIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soSOGCS equals to DEFAULT_SO_SOGCS
        defaultBlcAPILogShouldBeFound("soSOGCS.equals=" + DEFAULT_SO_SOGCS);

        // Get all the blcAPILogList where soSOGCS equals to UPDATED_SO_SOGCS
        defaultBlcAPILogShouldNotBeFound("soSOGCS.equals=" + UPDATED_SO_SOGCS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoSOGCSIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soSOGCS in DEFAULT_SO_SOGCS or UPDATED_SO_SOGCS
        defaultBlcAPILogShouldBeFound("soSOGCS.in=" + DEFAULT_SO_SOGCS + "," + UPDATED_SO_SOGCS);

        // Get all the blcAPILogList where soSOGCS equals to UPDATED_SO_SOGCS
        defaultBlcAPILogShouldNotBeFound("soSOGCS.in=" + UPDATED_SO_SOGCS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsBySoSOGCSIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where soSOGCS is not null
        defaultBlcAPILogShouldBeFound("soSOGCS.specified=true");

        // Get all the blcAPILogList where soSOGCS is null
        defaultBlcAPILogShouldNotBeFound("soSOGCS.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByRequestContentIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where requestContent equals to DEFAULT_REQUEST_CONTENT
        defaultBlcAPILogShouldBeFound("requestContent.equals=" + DEFAULT_REQUEST_CONTENT);

        // Get all the blcAPILogList where requestContent equals to UPDATED_REQUEST_CONTENT
        defaultBlcAPILogShouldNotBeFound("requestContent.equals=" + UPDATED_REQUEST_CONTENT);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByRequestContentIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where requestContent in DEFAULT_REQUEST_CONTENT or UPDATED_REQUEST_CONTENT
        defaultBlcAPILogShouldBeFound("requestContent.in=" + DEFAULT_REQUEST_CONTENT + "," + UPDATED_REQUEST_CONTENT);

        // Get all the blcAPILogList where requestContent equals to UPDATED_REQUEST_CONTENT
        defaultBlcAPILogShouldNotBeFound("requestContent.in=" + UPDATED_REQUEST_CONTENT);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByRequestContentIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where requestContent is not null
        defaultBlcAPILogShouldBeFound("requestContent.specified=true");

        // Get all the blcAPILogList where requestContent is null
        defaultBlcAPILogShouldNotBeFound("requestContent.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByErrorMessageIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where errorMessage equals to DEFAULT_ERROR_MESSAGE
        defaultBlcAPILogShouldBeFound("errorMessage.equals=" + DEFAULT_ERROR_MESSAGE);

        // Get all the blcAPILogList where errorMessage equals to UPDATED_ERROR_MESSAGE
        defaultBlcAPILogShouldNotBeFound("errorMessage.equals=" + UPDATED_ERROR_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByErrorMessageIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where errorMessage in DEFAULT_ERROR_MESSAGE or UPDATED_ERROR_MESSAGE
        defaultBlcAPILogShouldBeFound("errorMessage.in=" + DEFAULT_ERROR_MESSAGE + "," + UPDATED_ERROR_MESSAGE);

        // Get all the blcAPILogList where errorMessage equals to UPDATED_ERROR_MESSAGE
        defaultBlcAPILogShouldNotBeFound("errorMessage.in=" + UPDATED_ERROR_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByErrorMessageIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where errorMessage is not null
        defaultBlcAPILogShouldBeFound("errorMessage.specified=true");

        // Get all the blcAPILogList where errorMessage is null
        defaultBlcAPILogShouldNotBeFound("errorMessage.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime equals to DEFAULT_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.equals=" + DEFAULT_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime equals to UPDATED_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.equals=" + UPDATED_LOADED_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime in DEFAULT_LOADED_TIME or UPDATED_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.in=" + DEFAULT_LOADED_TIME + "," + UPDATED_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime equals to UPDATED_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.in=" + UPDATED_LOADED_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime is not null
        defaultBlcAPILogShouldBeFound("loadedTime.specified=true");

        // Get all the blcAPILogList where loadedTime is null
        defaultBlcAPILogShouldNotBeFound("loadedTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime is greater than or equal to DEFAULT_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.greaterThanOrEqual=" + DEFAULT_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime is greater than or equal to UPDATED_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.greaterThanOrEqual=" + UPDATED_LOADED_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime is less than or equal to DEFAULT_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.lessThanOrEqual=" + DEFAULT_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime is less than or equal to SMALLER_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.lessThanOrEqual=" + SMALLER_LOADED_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime is less than DEFAULT_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.lessThan=" + DEFAULT_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime is less than UPDATED_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.lessThan=" + UPDATED_LOADED_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByLoadedTimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where loadedTime is greater than DEFAULT_LOADED_TIME
        defaultBlcAPILogShouldNotBeFound("loadedTime.greaterThan=" + DEFAULT_LOADED_TIME);

        // Get all the blcAPILogList where loadedTime is greater than SMALLER_LOADED_TIME
        defaultBlcAPILogShouldBeFound("loadedTime.greaterThan=" + SMALLER_LOADED_TIME);
    }


    @Test
    @Transactional
    public void getAllBlcAPILogsByIpAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where ipAddress equals to DEFAULT_IP_ADDRESS
        defaultBlcAPILogShouldBeFound("ipAddress.equals=" + DEFAULT_IP_ADDRESS);

        // Get all the blcAPILogList where ipAddress equals to UPDATED_IP_ADDRESS
        defaultBlcAPILogShouldNotBeFound("ipAddress.equals=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByIpAddressIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where ipAddress in DEFAULT_IP_ADDRESS or UPDATED_IP_ADDRESS
        defaultBlcAPILogShouldBeFound("ipAddress.in=" + DEFAULT_IP_ADDRESS + "," + UPDATED_IP_ADDRESS);

        // Get all the blcAPILogList where ipAddress equals to UPDATED_IP_ADDRESS
        defaultBlcAPILogShouldNotBeFound("ipAddress.in=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByIpAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where ipAddress is not null
        defaultBlcAPILogShouldBeFound("ipAddress.specified=true");

        // Get all the blcAPILogList where ipAddress is null
        defaultBlcAPILogShouldNotBeFound("ipAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByIdBenhvienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where idBenhvien equals to DEFAULT_ID_BENHVIEN
        defaultBlcAPILogShouldBeFound("idBenhvien.equals=" + DEFAULT_ID_BENHVIEN);

        // Get all the blcAPILogList where idBenhvien equals to UPDATED_ID_BENHVIEN
        defaultBlcAPILogShouldNotBeFound("idBenhvien.equals=" + UPDATED_ID_BENHVIEN);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByIdBenhvienIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where idBenhvien in DEFAULT_ID_BENHVIEN or UPDATED_ID_BENHVIEN
        defaultBlcAPILogShouldBeFound("idBenhvien.in=" + DEFAULT_ID_BENHVIEN + "," + UPDATED_ID_BENHVIEN);

        // Get all the blcAPILogList where idBenhvien equals to UPDATED_ID_BENHVIEN
        defaultBlcAPILogShouldNotBeFound("idBenhvien.in=" + UPDATED_ID_BENHVIEN);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByIdBenhvienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where idBenhvien is not null
        defaultBlcAPILogShouldBeFound("idBenhvien.specified=true");

        // Get all the blcAPILogList where idBenhvien is null
        defaultBlcAPILogShouldNotBeFound("idBenhvien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByTenBenhvienIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where tenBenhvien equals to DEFAULT_TEN_BENHVIEN
        defaultBlcAPILogShouldBeFound("tenBenhvien.equals=" + DEFAULT_TEN_BENHVIEN);

        // Get all the blcAPILogList where tenBenhvien equals to UPDATED_TEN_BENHVIEN
        defaultBlcAPILogShouldNotBeFound("tenBenhvien.equals=" + UPDATED_TEN_BENHVIEN);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByTenBenhvienIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where tenBenhvien in DEFAULT_TEN_BENHVIEN or UPDATED_TEN_BENHVIEN
        defaultBlcAPILogShouldBeFound("tenBenhvien.in=" + DEFAULT_TEN_BENHVIEN + "," + UPDATED_TEN_BENHVIEN);

        // Get all the blcAPILogList where tenBenhvien equals to UPDATED_TEN_BENHVIEN
        defaultBlcAPILogShouldNotBeFound("tenBenhvien.in=" + UPDATED_TEN_BENHVIEN);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByTenBenhvienIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where tenBenhvien is not null
        defaultBlcAPILogShouldBeFound("tenBenhvien.specified=true");

        // Get all the blcAPILogList where tenBenhvien is null
        defaultBlcAPILogShouldNotBeFound("tenBenhvien.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime equals to DEFAULT_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.equals=" + DEFAULT_PROCESS_TIME);

        // Get all the blcAPILogList where processTime equals to UPDATED_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.equals=" + UPDATED_PROCESS_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime in DEFAULT_PROCESS_TIME or UPDATED_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.in=" + DEFAULT_PROCESS_TIME + "," + UPDATED_PROCESS_TIME);

        // Get all the blcAPILogList where processTime equals to UPDATED_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.in=" + UPDATED_PROCESS_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime is not null
        defaultBlcAPILogShouldBeFound("processTime.specified=true");

        // Get all the blcAPILogList where processTime is null
        defaultBlcAPILogShouldNotBeFound("processTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime is greater than or equal to DEFAULT_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.greaterThanOrEqual=" + DEFAULT_PROCESS_TIME);

        // Get all the blcAPILogList where processTime is greater than or equal to UPDATED_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.greaterThanOrEqual=" + UPDATED_PROCESS_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime is less than or equal to DEFAULT_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.lessThanOrEqual=" + DEFAULT_PROCESS_TIME);

        // Get all the blcAPILogList where processTime is less than or equal to SMALLER_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.lessThanOrEqual=" + SMALLER_PROCESS_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime is less than DEFAULT_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.lessThan=" + DEFAULT_PROCESS_TIME);

        // Get all the blcAPILogList where processTime is less than UPDATED_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.lessThan=" + UPDATED_PROCESS_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByProcessTimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where processTime is greater than DEFAULT_PROCESS_TIME
        defaultBlcAPILogShouldNotBeFound("processTime.greaterThan=" + DEFAULT_PROCESS_TIME);

        // Get all the blcAPILogList where processTime is greater than SMALLER_PROCESS_TIME
        defaultBlcAPILogShouldBeFound("processTime.greaterThan=" + SMALLER_PROCESS_TIME);
    }


    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime equals to DEFAULT_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.equals=" + DEFAULT_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime equals to UPDATED_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.equals=" + UPDATED_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsInShouldWork() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime in DEFAULT_RESPONSE_TIME or UPDATED_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.in=" + DEFAULT_RESPONSE_TIME + "," + UPDATED_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime equals to UPDATED_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.in=" + UPDATED_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime is not null
        defaultBlcAPILogShouldBeFound("responseTime.specified=true");

        // Get all the blcAPILogList where responseTime is null
        defaultBlcAPILogShouldNotBeFound("responseTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime is greater than or equal to DEFAULT_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.greaterThanOrEqual=" + DEFAULT_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime is greater than or equal to UPDATED_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.greaterThanOrEqual=" + UPDATED_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime is less than or equal to DEFAULT_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.lessThanOrEqual=" + DEFAULT_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime is less than or equal to SMALLER_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.lessThanOrEqual=" + SMALLER_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime is less than DEFAULT_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.lessThan=" + DEFAULT_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime is less than UPDATED_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.lessThan=" + UPDATED_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void getAllBlcAPILogsByResponseTimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);

        // Get all the blcAPILogList where responseTime is greater than DEFAULT_RESPONSE_TIME
        defaultBlcAPILogShouldNotBeFound("responseTime.greaterThan=" + DEFAULT_RESPONSE_TIME);

        // Get all the blcAPILogList where responseTime is greater than SMALLER_RESPONSE_TIME
        defaultBlcAPILogShouldBeFound("responseTime.greaterThan=" + SMALLER_RESPONSE_TIME);
    }


    @Test
    @Transactional
    public void getAllBlcAPILogsByBlcGiayChungSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        blcAPILogRepository.saveAndFlush(blcAPILog);
        BlcGiayChungSinh blcGiayChungSinh = BlcGiayChungSinhResourceIT.createEntity(em);
        em.persist(blcGiayChungSinh);
        em.flush();
        blcAPILog.setBlcGiayChungSinh(blcGiayChungSinh);
        blcGiayChungSinh.setBlcAPILog(blcAPILog);
        blcAPILogRepository.saveAndFlush(blcAPILog);
        Long blcGiayChungSinhId = blcGiayChungSinh.getId();

        // Get all the blcAPILogList where blcGiayChungSinh equals to blcGiayChungSinhId
        defaultBlcAPILogShouldBeFound("blcGiayChungSinhId.equals=" + blcGiayChungSinhId);

        // Get all the blcAPILogList where blcGiayChungSinh equals to blcGiayChungSinhId + 1
        defaultBlcAPILogShouldNotBeFound("blcGiayChungSinhId.equals=" + (blcGiayChungSinhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBlcAPILogShouldBeFound(String filter) throws Exception {
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blcAPILog.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].messageStatus").value(hasItem(DEFAULT_MESSAGE_STATUS)))
            .andExpect(jsonPath("$.[*].methodName").value(hasItem(DEFAULT_METHOD_NAME)))
            .andExpect(jsonPath("$.[*].soGCS").value(hasItem(DEFAULT_SO_GCS)))
            .andExpect(jsonPath("$.[*].soSOGCS").value(hasItem(DEFAULT_SO_SOGCS)))
            .andExpect(jsonPath("$.[*].requestContent").value(hasItem(DEFAULT_REQUEST_CONTENT)))
            .andExpect(jsonPath("$.[*].errorMessage").value(hasItem(DEFAULT_ERROR_MESSAGE)))
            .andExpect(jsonPath("$.[*].loadedTime").value(hasItem(DEFAULT_LOADED_TIME.toString())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].idBenhvien").value(hasItem(DEFAULT_ID_BENHVIEN)))
            .andExpect(jsonPath("$.[*].tenBenhvien").value(hasItem(DEFAULT_TEN_BENHVIEN)))
            .andExpect(jsonPath("$.[*].processTime").value(hasItem(DEFAULT_PROCESS_TIME)))
            .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME)));

        // Check, that the count call also returns 1
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBlcAPILogShouldNotBeFound(String filter) throws Exception {
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBlcAPILog() throws Exception {
        // Get the blcAPILog
        restBlcAPILogMockMvc.perform(get("/api/blc-api-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlcAPILog() throws Exception {
        // Initialize the database
        blcAPILogService.save(blcAPILog);

        int databaseSizeBeforeUpdate = blcAPILogRepository.findAll().size();

        // Update the blcAPILog
        BlcAPILog updatedBlcAPILog = blcAPILogRepository.findById(blcAPILog.getId()).get();
        // Disconnect from session so that the updates on updatedBlcAPILog are not directly saved in db
        em.detach(updatedBlcAPILog);
        updatedBlcAPILog
            .uuid(UPDATED_UUID)
            .status(UPDATED_STATUS)
            .messageStatus(UPDATED_MESSAGE_STATUS)
            .methodName(UPDATED_METHOD_NAME)
            .soGCS(UPDATED_SO_GCS)
            .soSOGCS(UPDATED_SO_SOGCS)
            .requestContent(UPDATED_REQUEST_CONTENT)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .loadedTime(UPDATED_LOADED_TIME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .idBenhvien(UPDATED_ID_BENHVIEN)
            .tenBenhvien(UPDATED_TEN_BENHVIEN)
            .processTime(UPDATED_PROCESS_TIME)
            .responseTime(UPDATED_RESPONSE_TIME);

        restBlcAPILogMockMvc.perform(put("/api/blc-api-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlcAPILog)))
            .andExpect(status().isOk());

        // Validate the BlcAPILog in the database
        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeUpdate);
        BlcAPILog testBlcAPILog = blcAPILogList.get(blcAPILogList.size() - 1);
        assertThat(testBlcAPILog.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testBlcAPILog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBlcAPILog.getMessageStatus()).isEqualTo(UPDATED_MESSAGE_STATUS);
        assertThat(testBlcAPILog.getMethodName()).isEqualTo(UPDATED_METHOD_NAME);
        assertThat(testBlcAPILog.getSoGCS()).isEqualTo(UPDATED_SO_GCS);
        assertThat(testBlcAPILog.getSoSOGCS()).isEqualTo(UPDATED_SO_SOGCS);
        assertThat(testBlcAPILog.getRequestContent()).isEqualTo(UPDATED_REQUEST_CONTENT);
        assertThat(testBlcAPILog.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testBlcAPILog.getLoadedTime()).isEqualTo(UPDATED_LOADED_TIME);
        assertThat(testBlcAPILog.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testBlcAPILog.getIdBenhvien()).isEqualTo(UPDATED_ID_BENHVIEN);
        assertThat(testBlcAPILog.getTenBenhvien()).isEqualTo(UPDATED_TEN_BENHVIEN);
        assertThat(testBlcAPILog.getProcessTime()).isEqualTo(UPDATED_PROCESS_TIME);
        assertThat(testBlcAPILog.getResponseTime()).isEqualTo(UPDATED_RESPONSE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingBlcAPILog() throws Exception {
        int databaseSizeBeforeUpdate = blcAPILogRepository.findAll().size();

        // Create the BlcAPILog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlcAPILogMockMvc.perform(put("/api/blc-api-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blcAPILog)))
            .andExpect(status().isBadRequest());

        // Validate the BlcAPILog in the database
        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlcAPILog() throws Exception {
        // Initialize the database
        blcAPILogService.save(blcAPILog);

        int databaseSizeBeforeDelete = blcAPILogRepository.findAll().size();

        // Delete the blcAPILog
        restBlcAPILogMockMvc.perform(delete("/api/blc-api-logs/{id}", blcAPILog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlcAPILog> blcAPILogList = blcAPILogRepository.findAll();
        assertThat(blcAPILogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlcAPILog.class);
        BlcAPILog blcAPILog1 = new BlcAPILog();
        blcAPILog1.setId(1L);
        BlcAPILog blcAPILog2 = new BlcAPILog();
        blcAPILog2.setId(blcAPILog1.getId());
        assertThat(blcAPILog1).isEqualTo(blcAPILog2);
        blcAPILog2.setId(2L);
        assertThat(blcAPILog1).isNotEqualTo(blcAPILog2);
        blcAPILog1.setId(null);
        assertThat(blcAPILog1).isNotEqualTo(blcAPILog2);
    }
}
