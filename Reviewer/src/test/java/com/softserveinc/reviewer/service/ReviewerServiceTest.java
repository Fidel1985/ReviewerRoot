package com.softserveinc.reviewer.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.softserveinc.reviewer.model.Product;
import com.softserveinc.reviewer.model.Syndication;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReviewerServiceTest {

    private static final String destinationClient = "destinationClient";
    private static final String sourceClient = "sourceClient";
    private static final String destinationProductId = "destinationProduct";
    private static final String sourceProductId = "sourceProductId";

    @InjectMocks
    private ReviewerService reviewerService;

    @Mock
    private SyndicationService syndicationService;

    @Mock
    private OracleService oracleService;

    @Mock
    private ElasticSearchService elasticSearchService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void refresh() {
        reset(syndicationService, oracleService, elasticSearchService);
    }

    @Test
    public void getSyndicationMatchesInvokesServicesWithCorrectParamsTest() throws Exception {
        List<Syndication> syndications = createSyndications();
        List<Product> products = createProductMatches();

        when(syndicationService.getSources(destinationClient)).thenReturn(syndications);
        when(oracleService.getSourceMatches(destinationClient, destinationProductId)).thenReturn(products);

        reviewerService.getSyndicationMatches(destinationClient, destinationProductId);

        verify(syndicationService).getSources(destinationClient);
        verify(oracleService).getSourceMatches(destinationClient, destinationProductId);
        verify(elasticSearchService).getReviews(destinationClient, destinationProductId);
        verify(elasticSearchService).getReviews(sourceClient, sourceProductId);
        verifyNoMoreInteractions(syndicationService);
        verifyNoMoreInteractions(oracleService);
        verifyNoMoreInteractions(elasticSearchService);
    }

    @Test
    public void getSyndicationMatchesNoSyndicationsTest() throws Exception {
        List<Syndication> syndications = new ArrayList<>();
        List<Product> products = createProductMatches();

        when(syndicationService.getSources(destinationClient)).thenReturn(syndications);
        when(oracleService.getSourceMatches(destinationClient, destinationProductId)).thenReturn(products);

        reviewerService.getSyndicationMatches(destinationClient, destinationProductId);

        verify(elasticSearchService, times(1)).getReviews(anyString(), anyString());
    }

    @Test
    public void getSyndicationMatchesNoProductMatchesTest() throws Exception {
        List<Syndication> syndications = createSyndications();
        List<Product> products = new ArrayList<>();

        when(syndicationService.getSources(destinationClient)).thenReturn(syndications);
        when(oracleService.getSourceMatches(destinationClient, destinationProductId)).thenReturn(products);

        reviewerService.getSyndicationMatches(destinationClient, destinationProductId);

        verify(elasticSearchService, times(1)).getReviews(anyString(), anyString());
    }

    private List<Syndication> createSyndications() {
        Syndication syndication = new Syndication(destinationClient, sourceClient);
        return Collections.singletonList(syndication);
    }

    private List<Product> createProductMatches() {
        Product destinationProduct = new Product(destinationClient, destinationProductId);
        Product sourceProduct = new Product(sourceClient, sourceProductId);
        return Arrays.asList(destinationProduct, sourceProduct);
    }

}
