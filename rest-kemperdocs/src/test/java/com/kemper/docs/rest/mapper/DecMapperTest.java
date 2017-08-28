package com.kemper.docs.rest.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kemper.docs.rest.model.DecResult;
import com.kemper.docs.rest.model.SearchResults;
import com.kemper.docs.rest.util.CMSDomain;
import com.ksg.cms.client.model.DomainResponse;
import com.ksg.cms.client.model.SearchReply;

@RunWith(SpringRunner.class)
public class DecMapperTest {

	DecMapper mapper;
	
	@MockBean
	SearchReply searchReply;
	
    @Before
    public void setUp() {

        mapper = new DecMapper();
    }
    
	@Test
	public void testMapCMSToModel() {
	
		Mockito.when(searchReply.isSuccess()).thenReturn(true);
		//build map of list of domain responses
		Map<String, List<DomainResponse>> map1 = new HashMap<String, List<DomainResponse>>();
		//build list of domain responses
		List<DomainResponse> domainList = new ArrayList<DomainResponse>();
		DomainResponse dr1 = new DomainResponse();
		//build metadata map for domain response
		Map<String, String> metaDataMap = new HashMap<String, String>();
		metaDataMap.put("POL_NO", "1000010");
		metaDataMap.put("PROD_CD", "04109600");
		dr1.setMetaDataMap(metaDataMap);
		//add to domainlist
		domainList.add(dr1);
		//set list to map
		map1.put("DOM_DECS_AND_NTCE", domainList);
		
		Mockito.when(searchReply.getDomainResponses()).thenReturn(map1);

		SearchResults<DecResult> results = mapper.mapCMSToModel(searchReply, CMSDomain.DOM_DECS_AND_NTCE);
		
		assertThat(results.getResults().get(0).getPolicyno(), equalTo("1000010"));
		assertThat(results.getResults().get(0).getProducerCode(), equalTo("04109600"));
	}

}
